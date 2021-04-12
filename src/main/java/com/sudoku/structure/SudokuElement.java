package com.sudoku.structure;

import com.sudoku.structure.prototype.Prototype;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.*;

import static java.util.Optional.ofNullable;
import static com.sudoku.structure.SudokuBoard.MAX_VALUE;

@Getter
@AllArgsConstructor
public class SudokuElement extends Prototype {
    private int number;
    private List<Integer> possibleNumbers;

    public SudokuElement() {
        this.number = 0;
        this.possibleNumbers = createPossibleNumbersList();
    }

    private List<Integer> createPossibleNumbersList() {
        List<Integer> list = new ArrayList<>();

        for (int i = 1 ; i <= MAX_VALUE ; i++) {
            list.add(i);
        }

        return list;
    }

    public SudokuElement deepClone() throws CloneNotSupportedException {
        SudokuElement cloneSudokuElement = (SudokuElement) super.clone();

        if (this.number == 0) {
            cloneSudokuElement.setNumber(0);
            List<Integer> possibleNumberList = new ArrayList<>();
            for (Integer element: this.possibleNumbers) {
                possibleNumberList.add(element);
            }
            cloneSudokuElement.setPossibleNumbers(possibleNumberList);
            return cloneSudokuElement;
        }

        cloneSudokuElement.setNumber(this.number);
        return cloneSudokuElement;
    }

    public boolean isNumberExistInPossibleNumbers(int number) {
        if (ofNullable(possibleNumbers).isPresent()) {
            for (Integer element : possibleNumbers) {
                if (number == element) {
                    return true;
                }
            }
        }
        return false;
    }

    public void removeNumberFromPossibleNumbers(int number) {
        if (ofNullable(possibleNumbers).isPresent()) {
            for (int i = 0 ; i < possibleNumbers.size() ; i++) {
                if (number == possibleNumbers.get(i)) {
                    possibleNumbers.remove(i);
                    break;
                }
            }
        }
    }

    public boolean isNumberExist(int number) {
        if (ofNullable(possibleNumbers).isPresent()) {
            for (int i = 0 ; i < possibleNumbers.size() ; i++) {
                if (number == possibleNumbers.get(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setNumber(int number) {
        this.number = number;
        this.possibleNumbers = null;
    }

    public void setPossibleNumbers(List<Integer> possibleNumbers) {
        this.possibleNumbers = possibleNumbers;
    }

    @Override
    public String toString() {
        String text = "";
        if (0 == number) {
            text += "There is no stable number, possible numbers: ";
            for (Integer element : possibleNumbers) {
                text += (element + ", ");
            }
        } else {
            text += ("The number is " + number);
        }
        return text;
    }
}
