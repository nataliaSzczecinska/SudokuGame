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

    @Override
    public String toString() {
        return "(" + column + ", " + row + ", " + number + ")";
    }
}
