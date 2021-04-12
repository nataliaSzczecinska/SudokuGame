package com.sudoku.structure;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Coordinates {
    private int column;
    private int row;
    private int number;

    public void setCoordinates(int column, int row, int number) {
        this.column = column;
        this.row = row;
        this.number = number;
    }

    @Override
    public String toString() {
        return "(" + column + ", " + row + ", " + number + ")";
    }
}
