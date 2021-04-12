package com.sudoku.structure.controller;

import com.sudoku.game.SudokuGame;
import com.sudoku.game.SudokuSolver;
import com.sudoku.io.TextFactor;
import com.sudoku.structure.Coordinates;
import com.sudoku.structure.SudokuBoard;

import static com.sudoku.structure.SudokuBoard.MAX_VALUE;
import static java.util.Optional.ofNullable;
import java.util.logging.Logger;
import java.util.*;

public class SudokuBoardController {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private final SudokuSolver solver = new SudokuSolver();
    private Coordinates temp = new Coordinates();

    public List<Coordinates> getCoordinateList(String text) {
        List<Coordinates> list = new ArrayList<>();
        Coordinates coordinates = null;

        if (!((text.length() % 6 == 0) || ((text.length() + 1) % 6 == 0))) {
            return null;
        }
        for (int i = 0 ; i < text.length() ; i++) {

            switch (i % 6) {
                case 0: {
                    coordinates = new Coordinates();
                    coordinates.setColumn(text.charAt(i) - 48);
                    break;
                }
                case 2: {
                    if (!ofNullable(coordinates).isPresent()) {
                        throw new NullPointerException(TextFactor.elementDoNotExist("coordinates"));
                    } else {
                        coordinates.setRow(text.charAt(i) - 48);
                    }
                    break;
                }
                case 4: {
                    if (!ofNullable(coordinates).isPresent()) {
                        throw new NullPointerException(TextFactor.elementDoNotExist("coordinates"));
                    } else {
                        coordinates.setNumber(text.charAt(i) - 48);
                    }
                    break;
                }
                case 1:
                case 3:
                case 5: {
                    break;
                }
                default: {
                    logger.warning(TextFactor.mistakeChoice());
                }
            }
            if (5 == i % 6 || (text.length() - 1 == i)) {
                list.add(coordinates);
            }
        }
        return list;
    }

    public void putManyIntoBoard (SudokuBoard board, List<Coordinates> coordinatesList) {
        for (Coordinates element : coordinatesList) {
            putIntoBoard(board, element);
        }
    }

    public void putIntoBoard(SudokuBoard board, Coordinates coordinates) {
        if (solver.isPossibleToPut(board, coordinates)) {
            board.setBoardElement(coordinates);
        } else {
            logger.info(TextFactor.cannotPutNumberIntoBoard(coordinates));
        }
    }

    public boolean putOnlyPossibleNumber(SudokuBoard board) {
        boolean anyChanges, anyChangesInBoard = false;

        do {
            anyChanges = false;
            correctPossibleNumberList(board);
            for (int i = 0 ; i < MAX_VALUE ; i++) {
                for (int j = 0; j < MAX_VALUE; j++) {
                    if (0 == board.getBoardElement(j + 1, i + 1).getNumber()
                            && 1 == board.getBoardElement(j + 1, i + 1).getPossibleNumbers().size()) {
                        Coordinates coordinates = new Coordinates(j + 1, i + 1,
                                board.getBoardElement(j + 1, i + 1)
                                        .getPossibleNumbers()
                                        .get(0));
                        putIntoBoard(board, coordinates);
                        anyChanges = true;
                        anyChangesInBoard = true;
                    }
                }
            }
        } while (anyChanges);
        return anyChangesInBoard;
    }

    private void correctPossibleNumberList(SudokuBoard board) {
        for (int i = 0 ; i < MAX_VALUE ; i++) {
            for (int j = 0 ; j < MAX_VALUE ; j++) {
                if (0 == board.getBoardElement(j + 1, i + 1).getNumber()) {
                    for (int n = 0 ;
                         n < board.getBoardElement(j + 1, i + 1).getPossibleNumbers().size() ;
                         n++) {
                        int value = board.getBoardElement(j + 1, i + 1)
                                .getPossibleNumbers().get(n);
                        Coordinates coordinates = new Coordinates(j + 1, i + 1, value);
                        if (!solver.isPossibleToPut(board, coordinates)) {
                            board.getBoardElement(j + 1, i + 1).removeNumberFromPossibleNumbers(value);
                            n--;
                            /*logger.info("Element (" + coordinates.getColumn()
                                    + ", " + coordinates.getRow() + ") has now "
                                    + board.getBoardElement(coordinates.getColumn(), coordinates.getRow())
                                    .getPossibleNumbers().size() + " possible numbers..");*/
                        }
                    }
                }
            }
        }
    }

    public boolean putOnlyPossibleElement(SudokuBoard board) {
        boolean anyChanges, anyChangesInBoard = false;
        do {
            //logger.info("The putOnlyPossibleElement is running...");
            anyChanges = false;
            putOnlyPossibleNumber(board);
            for (int i = 0 ; i < MAX_VALUE && !anyChanges; i++) {
                for (int j = 0 ; j < MAX_VALUE && !anyChanges; j++) {
                    if (0 == board.getBoardElement(j + 1, i + 1).getNumber()) {
                        for (int n = 0 ; !anyChanges && n < board.getBoardElement(j + 1, i + 1)
                                .getPossibleNumbers().size() ; n++) {
                            int value = board.getBoardElement(j + 1, i + 1)
                                    .getPossibleNumbers().get(n);
                            if (checkNumberInPossibilitiesInRow(board, i + 1, value)) {
                                putIntoBoard(board, temp);
                                anyChanges = true;
                                anyChangesInBoard = true;
                            } else if (checkNumberInPossibilitiesInColumn(board, j + 1, value)) {
                                putIntoBoard(board, temp);
                                anyChanges = true;
                                anyChangesInBoard = true;
                            } else if (checkNumberInPossibilitiesInBox(board, j + 1, i + 1, value)) {
                                putIntoBoard(board, temp);
                                anyChanges = true;
                                anyChangesInBoard = true;
                            }
                        }
                    }
                }
            }
        } while (anyChanges);
        return anyChangesInBoard;
    }

    public boolean checkNumberInPossibilitiesInRow(SudokuBoard board,
                                                   int row,
                                                   int number) {
        int quantity = 0;

        for (int i = 0 ; i < MAX_VALUE ; i++) {
            if (0 == board.getBoardElement(i + 1, row).getNumber()
                    && board.getBoardElement(i + 1, row).isNumberExist(number)) {
                temp.setCoordinates(i + 1, row, number);
                quantity++;
                if (1 < quantity) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkNumberInPossibilitiesInColumn(SudokuBoard board,
                                                      int column,
                                                      int number) {
        int quantity = 0;

        for (int i = 0 ; i < MAX_VALUE ; i++) {
            if (0 == board.getBoardElement(column, i + 1).getNumber()
                    && board.getBoardElement(column, i + 1).isNumberExist(number)) {
                temp.setCoordinates(column, i + 1, number);
                quantity++;
                if (1 < quantity) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkNumberInPossibilitiesInBox(SudokuBoard board,
                                                   int column,
                                                   int row,
                                                   int number) {
        int quantity = 0;
        int r = (row - 1) / 3;
        int c = (column - 1) / 3;
        r *= 3;
        c *= 3;

        for (int i = r ; i < r + 3 ; i++) {
            for (int j = c ; j < c + 3 ; j++) {
                if(0 == board.getBoardElement(j + 1, i + 1).getNumber()
                        && board.getBoardElement(j + 1, i + 1).isNumberExist(number)) {
                    temp.setCoordinates(j + 1, i + 1, number);
                    quantity++;
                    if (1 < quantity) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isSudokuBoardEmptyOrAlmostEmpty(SudokuBoard board, int minValue) {
        int count = 0;
        for (int i = 0 ; i < MAX_VALUE ; i++) {
            for (int j = 0 ; j < MAX_VALUE ; j++) {
                if (0 != board.getBoardElement(j + 1, i + 1).getNumber()){
                    count++;
                }
            }
        }
        if (count >= minValue) {
            return true;
        }
        return false;
    }
}
