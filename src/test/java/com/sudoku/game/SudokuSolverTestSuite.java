package com.sudoku.game;

import com.sudoku.structure.Coordinates;
import com.sudoku.structure.SudokuBoard;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuSolverTestSuite {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private SudokuSolver solver = new SudokuSolver();
    private SudokuBoard board = new SudokuBoard();

    @Test
    public void testIsPossibleToPut() {
        //Given
        board.setBoardElement(1,2,4);
        board.setBoardElement(2,4,5);
        board.setBoardElement(3,2,1);
        board.setBoardElement(4,7,7);
        board.setBoardElement(5,2,8);

        //When
        Coordinates coordinates1 = new Coordinates(9, 2, 1);
        Coordinates coordinates2 = new Coordinates(3, 9, 1);
        Coordinates coordinates3 = new Coordinates(1, 1, 1);
        Coordinates coordinates4 = new Coordinates(1, 2, 2);
        Coordinates coordinates5 = new Coordinates(2, 1, 2);

        boolean check1 = solver.isPossibleToPut(board, coordinates1);
        boolean check2 = solver.isPossibleToPut(board, coordinates2);
        boolean check3 = solver.isPossibleToPut(board, coordinates3);
        boolean check4 = solver.isPossibleToPut(board, coordinates4);
        boolean check5 = solver.isPossibleToPut(board, coordinates5);

        //Then
        assertFalse(check1);
        assertFalse(check2);
        assertFalse(check3);
        assertFalse(check4);
        assertTrue(check5);
    }
}
