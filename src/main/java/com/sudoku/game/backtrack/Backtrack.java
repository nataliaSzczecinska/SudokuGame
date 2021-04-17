package com.sudoku.game.backtrack;

import com.sudoku.structure.Coordinates;
import com.sudoku.structure.SudokuBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Backtrack {
    private final SudokuBoard board;
    private final Coordinates coordinates;
}