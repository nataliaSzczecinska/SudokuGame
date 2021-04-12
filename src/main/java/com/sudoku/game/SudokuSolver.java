package com.sudoku.game;

import com.sudoku.structure.Coordinates;
import com.sudoku.structure.SudokuBoard;

import java.util.logging.Logger;

import static com.sudoku.structure.SudokuBoard.MAX_VALUE;


public class SudokuSolver {
    private final Logger logger = Logger.getLogger(getClass().getName());

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
            return true;
        }
        //logger.info(TextFactor.cannotPutNumberIntoBoard(coordinates));
        return false;
    }

    public boolean isSolved(SudokuBoard board) {
        for (int i = 0 ; i < MAX_VALUE ; i++) {
            for (int j = 0 ; j < MAX_VALUE ; j++) {
                if (0 == board.getBoardElement(j + 1, i + 1).getNumber()) {
                    return false;
                }
            }
        }
        return true;
    }
}