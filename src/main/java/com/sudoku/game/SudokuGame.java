package com.sudoku.game;

import com.sudoku.io.Input;
import com.sudoku.io.TextFactor;
import com.sudoku.structure.SudokuBoard;
import com.sudoku.structure.controller.SudokuBoardController;

import java.util.logging.Logger;

public class SudokuGame {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private SudokuSolver solver = new SudokuSolver();
    private Input input = new Input();
    private SudokuBoardController controller = new SudokuBoardController();
    private SudokuBoard board = new SudokuBoard();
    private SudokuBoard solvedBoard = new SudokuBoard();

    public void play() {
        System.out.println(TextFactor.start());
        String option = "";
        do {
            do {
                option = input.readInput();
            }while (!input.checkCorrectLoadData(option, solver.isSolved(solvedBoard)));
        } while (!analiseOption(option));
    }

    private boolean analiseOption (String text) {
        if (1 == text.length()) {
            switch (text.charAt(0)) {
                case 'e': {
                    System.out.println(TextFactor.endGame());
                    return true;
                } case 'p': {
                    board = new SudokuBoard();
                    solvedBoard = new SudokuBoard();
                    System.out.println(TextFactor.start());
                    break;
                } case 's': {
                    if (!controller.isSudokuBoardEmptyOrAlmostEmpty(board, 10)) {
                        System.out.println(TextFactor.notEnoughBoardElements());
                    } else {
                        solvedBoard = solver.solve(board);
                        if (solver.isSolved(solvedBoard)) {
                            System.out.println(TextFactor.gameIsSolve(solvedBoard));
                        } else {
                            System.out.println(TextFactor.cannotBeSolve());
                        }
                    }
                    break;
                } default: {
                    logger.warning(TextFactor.problemWithMethod("analiseOption in SudokuGame"));
                }
            }
        } else {
            controller.putManyIntoBoard(board, controller.getCoordinateList(text));
            System.out.println("ACTUAL BOARD\n" + board);
        }
        return false;
    }
}
