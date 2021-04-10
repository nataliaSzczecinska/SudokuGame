package com.sudoku.game.statistic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Statistics {
    private int numberOfGuess = 0;
    private int numberOfIteration = 0;
    private int numberOfPossibleSolveBoard = 0;

    @Override
    public String toString() {
        return "Statistic: " +
                "\nnumber of guess: " + numberOfGuess +
                "\nnumber of iteration: " + numberOfIteration +
                "\nnumber of possibilities of solved board: " + numberOfPossibleSolveBoard;
    }
}
