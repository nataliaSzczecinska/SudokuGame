package com.sudoku.structure.controller;

import com.sudoku.game.SudokuSolver;
import com.sudoku.structure.Coordinates;
import com.sudoku.structure.SudokuBoard;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardControllerTestSuite {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private final SudokuSolver solver = new SudokuSolver();
    private SudokuBoardController boardController = new SudokuBoardController();
    private SudokuBoard board = new SudokuBoard();

    @Test
    public void testGetCoordinatesList() {
        //Given
        String text1 = "1,2,3,5,4,3,2,1,6,";
        String text2 = "1,2,3,5,4,3,2,1,6";
        String text3 = "1,2,3,2";

        //When
        List<Coordinates> list1 = boardController.getCoordinateList(text1);
        List<Coordinates> list2 = boardController.getCoordinateList(text2);
        List<Coordinates> list3 = boardController.getCoordinateList(text3);

        //Then
        assertNotNull(list1);
        assertNotNull(list2);
        assertTrue(list3.isEmpty());
    }

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

        boardController.putIntoBoard(board, coordinates1);
        boardController.putIntoBoard(board, coordinates2);
        boardController.putIntoBoard(board, coordinates3);
        boardController.putIntoBoard(board, coordinates4);
        boardController.putIntoBoard(board, coordinates5);

        //Then
        assertEquals(0, board.getBoardElement(9, 2).getNumber());
        assertEquals(0, board.getBoardElement(3, 9).getNumber());
        assertEquals(0, board.getBoardElement(1, 1).getNumber());
        assertEquals(4, board.getBoardElement(1, 2).getNumber());
        assertEquals(2, board.getBoardElement(2, 1).getNumber());
    }

    @Test
    public void testPutOnlyPossibleNumber() {
        //Given
        board.setBoardElement(1,1,1);
        board.setBoardElement(1,2,2);
        board.setBoardElement(1,3,3);
        board.setBoardElement(1,4,4);
        board.setBoardElement(1,5,6);
        board.setBoardElement(2,1,7);
        board.setBoardElement(3,1,8);
        board.setBoardElement(3,2,5);
        board.setBoardElement(3,3,6);
        board.setBoardElement(9,3,5);
        board.setBoardElement(8,3,4);
        board.setBoardElement(4,6,1);
        board.setBoardElement(4,6,1);
        board.setBoardElement(6,5,1);
        board.setBoardElement(5,2,7);

        logger.info("ACTUAL BOARD\n" + board);

        //When
        boardController.putOnlyPossibleNumber(board);
        logger.info("ACTUAL BOARD\n" + board);

        //Then
        assertEquals(4, board.getBoardElement(2, 2).getNumber());
        assertEquals(9, board.getBoardElement(2, 3).getNumber());
    }

    @Test
    public void testPutOnlyPossibleElement() {
        //Given
        board.setBoardElement(1,1,1);
        board.setBoardElement(1,2,2);
        board.setBoardElement(1,3,3);
        board.setBoardElement(1,4,4);
        board.setBoardElement(1,5,6);
        board.setBoardElement(2,1,7);
        board.setBoardElement(3,1,8);
        board.setBoardElement(3,2,5);
        board.setBoardElement(3,3,6);
        board.setBoardElement(9,3,5);
        board.setBoardElement(8,3,4);
        board.setBoardElement(4,6,1);
        board.setBoardElement(4,6,1);
        board.setBoardElement(6,5,1);
        board.setBoardElement(5,2,7);

        logger.info("ACTUAL BOARD\n" + board);

        //When
        boardController.putOnlyPossibleElement(board);
        logger.info("ACTUAL BOARD\n" + board);

        //Then
        assertEquals(4, board.getBoardElement(2, 2).getNumber());
        assertEquals(9, board.getBoardElement(2, 3).getNumber());
        assertEquals(1, board.getBoardElement(5, 3).getNumber());
    }

    @Test
    public void testIsThereEmptyElement() {
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
        boolean check1 = boardController.isThereEmptyElement(board);
        board.setBoardElement(1, 1, 4);
        boolean check2 = boardController.isThereEmptyElement(board);
        board.setBoardElement(9, 9, 2);
        boolean check3 = boardController.isThereEmptyElement(board);

        //Then
        assertTrue(check1);
        assertTrue(check2);
        assertFalse(check3);
    }
}
