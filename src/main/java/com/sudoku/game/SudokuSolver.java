package com.sudoku.game;

import com.sudoku.game.backtrack.Backtrack;
import com.sudoku.game.backtrack.BacktrackList;
import com.sudoku.io.TextFactor;
import com.sudoku.structure.Coordinates;
import com.sudoku.structure.SudokuBoard;
import com.sudoku.structure.controller.SudokuBoardController;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.logging.Logger;

import static com.sudoku.structure.SudokuBoard.MAX_VALUE;

@Getter
@NoArgsConstructor
public class SudokuSolver {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private BacktrackList backtrackList = new BacktrackList();
    private SudokuBoardController controller = new SudokuBoardController();

    public boolean isSolved(SudokuBoard board) {
        for (int i = 0 ; i < MAX_VALUE ; i++) {
            for (int j = 0 ; j < MAX_VALUE ; j++) {
                if (0 == board.getBoardElement(j + 1, i + 1).getNumber()) {
                    return false;
                }
            }
        }
        return true;
    }

    public SudokuBoard guessNumber(SudokuBoard board) {
        SudokuBoard clone = null;

        try {
            clone = board.deepClone();
            if (!isSolved(board)) {
                for (int i = 0 ; i < MAX_VALUE ; i++) {
                    for (int j = 0 ; j < MAX_VALUE ; j++) {
                        if (0 == board.getBoardElement(j + 1, i + 1).getNumber()) {
                            int number = board.getBoardElement(j + 1, i + 1).getPossibleNumbers().get(0);
                            Coordinates coordinates = new Coordinates(j + 1, i + 1, number);
                            if (controller.isPossibleToPut(board, coordinates)) {
                                backtrackList.addElement(new Backtrack(clone, coordinates));
                                return board;
                            }
                        }
                    }
                }
            }
        } catch (CloneNotSupportedException exception) {
            logger.warning(TextFactor.cloneException());
        }
        return board;
    }
}