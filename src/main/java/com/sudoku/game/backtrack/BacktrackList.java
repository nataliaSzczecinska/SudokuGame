package com.sudoku.game.backtrack;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class BacktrackList {
    private List<Backtrack> backtrackList = new ArrayList<>();

    public void addElement(Backtrack backtrack) {
        backtrackList.add(backtrack);
    }

    public void removeLastElement() {
        backtrackList.remove(backtrackList.size() - 1);
    }

    public Backtrack getLastElement() {
        return backtrackList.get(backtrackList.size() - 1);
    }
}
