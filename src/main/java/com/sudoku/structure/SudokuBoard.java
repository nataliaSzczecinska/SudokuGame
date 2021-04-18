package com.sudoku.structure;

import com.sudoku.io.TextFactor;
import com.sudoku.structure.prototype.Prototype;
import java.util.logging.Logger;
import java.util.Arrays;

public class SudokuBoard extends Prototype<SudokuBoard> {
    public static final int MAX_VALUE = 9;
    private final Logger logger = Logger.getLogger(getClass().getName());
    private SudokuElement[][] board;

    public SudokuBoard() {
        this.board = createEmptyBoard();
    }

    private SudokuElement[][] createEmptyBoard() {
        SudokuElement[][] sudokuBoard = new SudokuElement[MAX_VALUE][MAX_VALUE];

        for (int i = 0 ; i < MAX_VALUE ; i++) {
            for (int j = 0 ; j < MAX_VALUE ; j++) {
                sudokuBoard[i][j] = new SudokuElement();
            }
        }
        return sudokuBoard;
    }

    public SudokuBoard deepClone() throws CloneNotSupportedException {
        SudokuBoard cloneBoard = super.clone();
        cloneBoard.setBoard(createEmptyBoard());

        for (int i = 0 ; i < MAX_VALUE ; i++) {
            for (int j = 0 ; j < MAX_VALUE ; j++) {
                SudokuElement sudokuElement = null;
                try {
                    sudokuElement = this.getBoardElement(j + 1, i + 1).deepClone();
                } catch (CloneNotSupportedException exception) {
                    logger.warning(TextFactor.cloneException());
                }
                cloneBoard.setBoardElement(j + 1, i + 1, sudokuElement);
            }
        }
        return cloneBoard;
    }

    public SudokuElement getBoardElement(int column, int row) {
        return board[row - 1][column - 1];
    }

    public void setBoard(SudokuElement[][] board) {
        this.board = board;
    }

    public void setBoardElement(int column, int row, int number) {
        this.board[row - 1][column - 1].setNumber(number);
    }

    public void setBoardElement(Coordinates coordinates) {
        setBoardElement(coordinates.getColumn(), coordinates.getRow(), coordinates.getNumber());
    }

    private void setBoardElement(int column, int row, SudokuElement element) {
        this.board[row - 1][column - 1] = element;
    }

    @Override
    public String toString() {
        if (board == null) {
            throw new NullPointerException(TextFactor.elementDoNotExist("Sudoku Board"));
        } else {
            String text = "";
            for (int i = 0; i < MAX_VALUE; i++) {
                if (i % 3 == 0 && 0 != i) {
                    text += "|=====|=====|=====||=====|=====|=====||=====|=====|=====|\n";
                } else {
                    text += "|-----|-----|-----||-----|-----|-----||-----|-----|-----|\n";
                }
                for (int j = 0; j < MAX_VALUE; j++) {
                    if (j % 3 == 0 && 0 != j) {
                        text += "|";
                    }
                    if (0 == board[i][j].getNumber()) {
                        text += "|     ";
                    } else {
                        text += ("|  " + board[i][j].getNumber() + "  ");
                    }
                }
                text += "|\n";
            }
            text += "|-----|-----|-----||-----|-----|-----||-----|-----|-----|\n";

            return text;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SudokuBoard that = (SudokuBoard) o;
        return Arrays.equals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(board);
    }
}
