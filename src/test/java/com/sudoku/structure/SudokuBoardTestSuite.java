package com.sudoku.structure;

import com.sudoku.io.TextFactor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.logging.Logger;

public class SudokuBoardTestSuite {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private SudokuBoard board = new SudokuBoard();

    @Test
    public void testDeepClone() {
        //Given
        board.setBoardElement(1,1,1);
        board.setBoardElement(1,2,2);
        board.setBoardElement(1,3,3);
        SudokuBoard cloneBoard = null;

        //When
        try {
            cloneBoard = board.deepClone();
            cloneBoard.setBoardElement(1,1,4);
        } catch (CloneNotSupportedException exception) {
            logger.warning(TextFactor.cloneException());
        }

        //Then
        logger.info("\n" + board);
        logger.info("\n" + cloneBoard);
        assertEquals(1, board.getBoardElement(1,1).getNumber());
        assertEquals(4, cloneBoard.getBoardElement(1,1).getNumber());
        assertEquals(2, board.getBoardElement(1,2).getNumber());
        assertEquals(2, cloneBoard.getBoardElement(1,2).getNumber());
        assertNotEquals(board, cloneBoard);
    }

    @Test
    public void testSetBoardElement() {
        //Given
        Coordinates coordinates = new Coordinates(1,2,3);

        //When
        board.setBoardElement(1, 1, 1);
        board.setBoardElement(coordinates);

        //Then
        assertEquals(1, board.getBoardElement(1,1).getNumber());
        assertEquals(3, board.getBoardElement(1,2).getNumber());
        assertNull(board.getBoardElement(1,1).getPossibleNumbers());
        assertNull(board.getBoardElement(1,2).getPossibleNumbers());
    }
}