package com.sudoku.structure;

import com.sudoku.io.TextFactor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.logging.Logger;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Sudoku Element Tests")
public class SudokuElementTestSuite {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private SudokuElement element = new SudokuElement();;

    @Test
    public void testDeepClone() {
        //Given

        //When
        SudokuElement cloneElement = null;
        try {
            cloneElement = element.deepClone();
        } catch (CloneNotSupportedException exception) {
            logger.warning(TextFactor.cloneException());
        }
        element.setNumber(1);

        //Then
        assertEquals(1, element.getNumber());
        assertEquals(0, cloneElement.getNumber());
        assertNotNull(cloneElement.getPossibleNumbers());
        assertNull(element.getPossibleNumbers());
        assertNotEquals(element, cloneElement);
    }

    @Test
    public void setNumber() {
        //Given

        //When
        element.setNumber(5);

        //Then
        assertNull(element.getPossibleNumbers());
        assertEquals(5, element.getNumber());
    }

    @Test
    public void testIsNumberExistInPossibleNumbers() {
        //Given
        List<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(4);
        list.add(7);

        element.setPossibleNumbers(list);

        //When
        boolean check1 = element.isNumberExistInPossibleNumbers(4);
        boolean check2 = element.isNumberExistInPossibleNumbers(8);

        //Then
        assertTrue(check1);
        assertFalse(check2);
    }

    @Test
    public void testRemoveNumberFromPossibleNumbers() {
        //Given
        List<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(4);
        list.add(7);

        element.setPossibleNumbers(list);

        //When
        element.removeNumberFromPossibleNumbers(4);
        boolean check1 = element.isNumberExistInPossibleNumbers(4);
        boolean check2 = element.isNumberExistInPossibleNumbers(7);

        //Then
        assertFalse(check1);
        assertTrue(check2);
        assertEquals(2, element.getPossibleNumbers().size());
    }
}
