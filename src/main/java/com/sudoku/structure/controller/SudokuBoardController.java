package com.sudoku.structure.controller;

import com.sudoku.io.TextFactor;
import com.sudoku.structure.Coordinates;
import com.sudoku.structure.SudokuBoard;

import static com.sudoku.structure.SudokuBoard.MAX_VALUE;
import static java.util.Optional.ofNullable;
import java.util.logging.Logger;
import java.util.*;

public class SudokuBoardController {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private Coordinates temp = new Coordinates();

    public List<Coordinates> getCoordinateList(String text) {
        List<Coordinates> list = new ArrayList<>();
        Coordinates coordinates = null;

        if (!((text.length() % 6 == 0) || ((text.length() + 1) % 6 == 0))) {
            return list;
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

    public boolean putOnlyPossibleNumber(SudokuBoard board) {
        boolean anyChanges;
        boolean anyChangesInBoard = false;

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
                        isPossibleToPut(board, coordinates);
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
                         null != board.getBoardElement(j + 1, i + 1).getPossibleNumbers()
                         && n < board.getBoardElement(j + 1, i + 1).getPossibleNumbers().size() ;
                         n++) {
                        int value = board.getBoardElement(j + 1, i + 1)
                                .getPossibleNumbers().get(n);
                        Coordinates coordinates = new Coordinates(j + 1, i + 1, value);
                        if (!isPossibleToPut(board, coordinates)) {
                            board.getBoardElement(j + 1, i + 1).removeNumberFromPossibleNumbers(value);
                            n--;
                        }
                    }
                }
            }
        }
    }

    public boolean putOnlyPossibleElement(SudokuBoard board) {
        boolean anyChanges;
        boolean anyChangesInBoard = false;
        do {
            anyChanges = false;
            putOnlyPossibleNumber(board);
            for (int i = 0 ; i < MAX_VALUE && !anyChanges; i++) {
                for (int j = 0 ; j < MAX_VALUE && !anyChanges; j++) {
                    if (0 == board.getBoardElement(j + 1, i + 1).getNumber()) {
                        for (int n = 0 ; !anyChanges && n < board.getBoardElement(j + 1, i + 1)
                                .getPossibleNumbers().size() ; n++) {
                            int value = board.getBoardElement(j + 1, i + 1)
                                    .getPossibleNumbers().get(n);
                            if (checkNumberInPossibilitiesInRow(board, i + 1, value)
                            || checkNumberInPossibilitiesInColumn(board, j + 1, value)
                            || checkNumberInPossibilitiesInBox(board, j + 1, i + 1, value)) {
                                isPossibleToPut(board, temp);
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

    private boolean checkRows(SudokuBoard board, Coordinates coordinates) {
        for (int i = 0 ; i < MAX_VALUE ; i++) {
            if (coordinates.getNumber() == (board
                    .getBoardElement(i + 1, coordinates.getRow())
                    .getNumber())
                    && (i != coordinates.getColumn() - 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumns(SudokuBoard board, Coordinates coordinates) {
        for (int i = 0 ; i < MAX_VALUE ; i++) {
            if(coordinates.getNumber() == (board
                    .getBoardElement(coordinates.getColumn(), i + 1)
                    .getNumber())
                    && i != (coordinates.getRow() - 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkBoxes(SudokuBoard board, Coordinates coordinates) {
        int row = (coordinates.getRow() - 1) / 3;
        int col = (coordinates.getColumn() - 1) / 3;
        row *= 3;
        col *= 3;

        for (int i = row ; i < row + 3 ; i++) {
            for (int j = col ; j < col + 3 ; j++) {
                if (i == (coordinates.getRow() - 1) && j == (coordinates.getColumn() - 1)) {
                    break;
                } else {
                    if(coordinates.getNumber() == (board
                            .getBoardElement(j + 1, i + 1)
                            .getNumber())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean checkElement(SudokuBoard board, Coordinates coordinates) {
        if (0 == board.getBoardElement(coordinates.getColumn(), coordinates.getRow()).getNumber()) {
            return true;
        }
        return false;
    }

    public boolean isPossibleToPut(SudokuBoard board, Coordinates coordinates) {
        if (checkRows(board, coordinates)
                && checkColumns(board, coordinates)
                && checkBoxes(board, coordinates)
                && checkElement(board, coordinates)) {
            board.setBoardElement(coordinates);
            return true;
        }
        logger.info(TextFactor.cannotPutNumberIntoBoard(coordinates));
        return false;
    }

    public void putManyIntoBoard (SudokuBoard board, List<Coordinates> coordinatesList) {
        for (Coordinates element : coordinatesList) {
            isPossibleToPut(board, element);
        }
    }
}
