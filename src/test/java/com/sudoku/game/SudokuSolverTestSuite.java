package com.sudoku.game;

import com.sudoku.structure.Coordinates;
import com.sudoku.structure.SudokuBoard;
import com.sudoku.structure.controller.SudokuBoardController;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuSolverTestSuite {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private SudokuSolver solver = new SudokuSolver();
    private SudokuBoardController controller = new SudokuBoardController();
    private SudokuBoard board = new SudokuBoard();

    @Test
    public void testIsSolved() {
        //Given
        board.setBoardElement(2, 1, 2);
        board.setBoardElement(3, 1, 6);
        board.setBoardElement(4, 1, 5);
        board.setBoardElement(5, 1, 7);
        board.setBoardElement(6, 1, 1);
        board.setBoardElement(7, 1, 3);
        board.setBoardElement(8, 1, 9);
        board.setBoardElement(9, 1, 8);
        board.setBoardElement(1, 2, 8);
        board.setBoardElement(2, 2, 5);
        board.setBoardElement(3, 2, 7);
        board.setBoardElement(4, 2, 2);
        board.setBoardElement(5, 2, 9);
        board.setBoardElement(6, 2, 3);
        board.setBoardElement(7, 2, 1);
        board.setBoardElement(8, 2, 4);
        board.setBoardElement(9, 2, 6);
        board.setBoardElement(1, 3, 1);
        board.setBoardElement(2, 3, 3);
        board.setBoardElement(3, 3, 9);
        board.setBoardElement(4, 3, 4);
        board.setBoardElement(5, 3, 6);
        board.setBoardElement(6, 3, 8);
        board.setBoardElement(7, 3, 2);
        board.setBoardElement(8, 3, 7);
        board.setBoardElement(9, 3, 5);
        board.setBoardElement(1, 4, 9);
        board.setBoardElement(2, 4, 7);
        board.setBoardElement(3, 4, 1);
        board.setBoardElement(4, 4, 3);
        board.setBoardElement(5, 4, 8);
        board.setBoardElement(6, 4, 5);
        board.setBoardElement(7, 4, 6);
        board.setBoardElement(8, 4, 2);
        board.setBoardElement(9, 4, 4);
        board.setBoardElement(1, 5, 5);
        board.setBoardElement(2, 5, 4);
        board.setBoardElement(3, 5, 3);
        board.setBoardElement(4, 5, 7);
        board.setBoardElement(5, 5, 2);
        board.setBoardElement(6, 5, 6);
        board.setBoardElement(7, 5, 8);
        board.setBoardElement(8, 5, 1);
        board.setBoardElement(9, 5, 9);
        board.setBoardElement(1, 6, 6);
        board.setBoardElement(2, 6, 8);
        board.setBoardElement(3, 6, 2);
        board.setBoardElement(4, 6, 1);
        board.setBoardElement(5, 6, 4);
        board.setBoardElement(6, 6, 9);
        board.setBoardElement(7, 6, 7);
        board.setBoardElement(8, 6, 5);
        board.setBoardElement(9, 6, 3);
        board.setBoardElement(1, 7, 7);
        board.setBoardElement(2, 7, 9);
        board.setBoardElement(3, 7, 4);
        board.setBoardElement(4, 7, 6);
        board.setBoardElement(5, 7, 3);
        board.setBoardElement(6, 7, 2);
        board.setBoardElement(7, 7, 5);
        board.setBoardElement(8, 7, 8);
        board.setBoardElement(9, 7, 1);
        board.setBoardElement(1, 8, 2);
        board.setBoardElement(2, 8, 6);
        board.setBoardElement(3, 8, 5);
        board.setBoardElement(4, 8, 8);
        board.setBoardElement(5, 8, 1);
        board.setBoardElement(6, 8, 4);
        board.setBoardElement(7, 8, 9);
        board.setBoardElement(8, 8, 3);
        board.setBoardElement(9, 8, 7);
        board.setBoardElement(1, 9, 3);
        board.setBoardElement(2, 9, 1);
        board.setBoardElement(3, 9, 8);
        board.setBoardElement(4, 9, 9);
        board.setBoardElement(5, 9, 5);
        board.setBoardElement(6, 9, 7);
        board.setBoardElement(7, 9, 4);
        board.setBoardElement(8, 9, 6);

        //When
        boolean check1 = solver.isSolved(board);
        board.setBoardElement(1, 1, 4);
        boolean check2 = solver.isSolved(board);
        board.setBoardElement(9, 9, 2);
        boolean check3 = solver.isSolved(board);

        //Then
        assertFalse(check1);
        assertFalse(check2);
        assertTrue(check3);
    }

    @Test
    public void testGuessNumber() {
        //Given
        board.setBoardElement(4, 1, 5);
        board.setBoardElement(5, 1, 7);
        board.setBoardElement(6, 1, 1);
        board.setBoardElement(7, 1, 3);
        board.setBoardElement(8, 1, 9);
        board.setBoardElement(9, 1, 8);
        board.setBoardElement(1, 2, 8);
        board.setBoardElement(2, 2, 5);
        board.setBoardElement(3, 2, 7);
        board.setBoardElement(4, 2, 2);
        board.setBoardElement(5, 2, 9);
        board.setBoardElement(6, 2, 3);
        board.setBoardElement(7, 2, 1);
        board.setBoardElement(8, 2, 4);
        board.setBoardElement(9, 2, 6);
        board.setBoardElement(1, 3, 1);
        board.setBoardElement(2, 3, 3);
        board.setBoardElement(3, 3, 9);
        board.setBoardElement(7, 8, 9);
        board.setBoardElement(8, 8, 3);
        board.setBoardElement(9, 8, 7);
        board.setBoardElement(1, 9, 3);
        board.setBoardElement(2, 9, 1);
        board.setBoardElement(3, 9, 8);
        board.setBoardElement(4, 9, 9);
        board.setBoardElement(5, 9, 5);
        board.setBoardElement(6, 9, 7);
        board.setBoardElement(7, 9, 4);

        //When
        logger.info("BOARD\n" + board);
        controller.putOnlyPossibleElement(board);
        logger.info("BOARD AFTER EARLY CORRECTION\n" + board);
        solver.guessNumber(board);
        logger.info("BOARD AFTER ONE GUESS\n" + board);
        solver.guessNumber(board);
        logger.info("BOARD AFTER TWO GUESSES\n" + board);

        //Then
        assertEquals(2, board.getBoardElement(1, 1).getNumber());
        assertEquals(4, board.getBoardElement(4, 3).getNumber());
    }

    @Test
    public void testSolve() {
        //Given
        board.setBoardElement(2, 1, 2);
        board.setBoardElement(4, 1, 5);
        board.setBoardElement(6, 1, 1);
        board.setBoardElement(8, 1, 9);
        board.setBoardElement(1, 2, 8);
        board.setBoardElement(4, 2, 2);
        board.setBoardElement(6, 2, 3);
        board.setBoardElement(9, 2, 6);
        board.setBoardElement(2, 3, 3);
        board.setBoardElement(5, 3, 6);
        board.setBoardElement(8, 3, 7);
        board.setBoardElement(3, 4, 1);
        board.setBoardElement(7, 4, 6);
        board.setBoardElement(1, 5, 5);
        board.setBoardElement(2, 5, 4);
        board.setBoardElement(8, 5, 1);
        board.setBoardElement(9, 5, 9);
        board.setBoardElement(3, 6, 2);
        board.setBoardElement(7, 6, 7);
        board.setBoardElement(2, 7, 9);
        board.setBoardElement(5, 7, 3);
        board.setBoardElement(8, 7, 8);
        board.setBoardElement(1, 8, 2);
        board.setBoardElement(4, 8, 8);
        board.setBoardElement(6, 8, 4);
        board.setBoardElement(9, 8, 7);
        board.setBoardElement(2, 9, 1);
        board.setBoardElement(4, 9, 9);
        board.setBoardElement(6, 9, 7);
        board.setBoardElement(8, 9, 6);

        //When
        SudokuBoard solvedBoard = solver.solve(board);
        boolean isBoardSolved = solver.isSolved(board);
        boolean isSolvedBoardSolve = solver.isSolved(solvedBoard);

        //Then
        logger.info("BOARD\n" + board);
        logger.info("SOLVED BOARD\n" + solvedBoard);
        assertFalse(isBoardSolved);
        assertTrue(isSolvedBoardSolve);
    }

    @Test
    public void testFindSolution() {
        //Given
        board.setBoardElement(2, 1, 2);
        board.setBoardElement(4, 1, 5);
        board.setBoardElement(6, 1, 1);
        board.setBoardElement(8, 1, 9);
        board.setBoardElement(1, 2, 8);
        board.setBoardElement(4, 2, 2);
        board.setBoardElement(6, 2, 3);
        board.setBoardElement(9, 2, 6);
        board.setBoardElement(2, 3, 3);
        board.setBoardElement(5, 3, 6);
        board.setBoardElement(8, 3, 7);
        board.setBoardElement(3, 4, 1);
        board.setBoardElement(7, 4, 6);
        board.setBoardElement(1, 5, 5);
        board.setBoardElement(2, 5, 4);
        board.setBoardElement(8, 5, 1);
        board.setBoardElement(9, 5, 9);
        board.setBoardElement(3, 6, 2);
        board.setBoardElement(7, 6, 7);
        board.setBoardElement(2, 7, 9);
        board.setBoardElement(5, 7, 3);
        board.setBoardElement(8, 7, 8);
        board.setBoardElement(1, 8, 2);
        board.setBoardElement(4, 8, 8);
        board.setBoardElement(6, 8, 4);
        board.setBoardElement(9, 8, 7);
        board.setBoardElement(2, 9, 1);
        board.setBoardElement(4, 9, 9);
        board.setBoardElement(6, 9, 7);
        board.setBoardElement(8, 9, 6);

        //When
        logger.info("BOARD\n" + board);
        board = solver.findSolution(board);
        logger.info("BOARD\n" + board);
        boolean isSolved = solver.isSolved(board);

        //Then
        assertTrue(isSolved);
    }

    @Test
    public void testSolveBoardWhichCannotBeSolved() {
        //Given
        board.setBoardElement(2, 1, 2);
        board.setBoardElement(4, 1, 5);
        board.setBoardElement(6, 1, 1);
        board.setBoardElement(8, 1, 9);
        board.setBoardElement(1, 2, 8);
        board.setBoardElement(4, 2, 2);
        board.setBoardElement(6, 2, 3);
        board.setBoardElement(9, 2, 6);
        board.setBoardElement(2, 3, 3);
        board.setBoardElement(5, 3, 6);
        board.setBoardElement(8, 3, 7);
        board.setBoardElement(3, 4, 1);
        board.setBoardElement(7, 4, 6);
        board.setBoardElement(1, 5, 5);
        board.setBoardElement(2, 5, 4);
        board.setBoardElement(8, 5, 1);
        board.setBoardElement(9, 5, 9);
        board.setBoardElement(3, 6, 2);
        board.setBoardElement(7, 6, 7);
        board.setBoardElement(2, 7, 9);
        board.setBoardElement(5, 7, 3);
        board.setBoardElement(8, 7, 8);
        board.setBoardElement(1, 8, 2);
        board.setBoardElement(4, 8, 8);
        board.setBoardElement(6, 8, 4);
        board.setBoardElement(9, 8, 7);
        board.setBoardElement(2, 9, 1);
        board.setBoardElement(4, 9, 9);
        board.setBoardElement(6, 9, 7);
        board.setBoardElement(8, 9, 6);
        board.setBoardElement(4, 7, 1);


        //When
        SudokuBoard solvedBoard = solver.solve(board);
        boolean isBoardSolved = solver.isSolved(board);
        boolean isSolvedBoardSolve = solver.isSolved(solvedBoard);

        //Then
        logger.info("BOARD\n" + board);
        logger.info("SOLVED BOARD\n" + solvedBoard);
        assertFalse(isBoardSolved);
        assertFalse(isSolvedBoardSolve);
    }
}
