package com.sudoku.game.backtrack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BacktrackList {
    private List<Backtrack> backtracks = new ArrayList<>();

    public void addElement(Backtrack backtrack) {
        backtracks.add(backtrack);
    }

    public void removeLastElement() {
        backtracks.remove(backtracks.size() - 1);
    }

    public Backtrack getLastElement() {
        return backtracks.get(backtracks.size() - 1);
    }
}
