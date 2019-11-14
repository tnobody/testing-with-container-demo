package org.tnobody.oss;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BoardGrid {

    private final String[] board;
    private final char self;
    private final char human;

    BoardGrid(String[] board, char human, char self) {
        this.board = board;
        this.human = human;
        this.self = self;
    }

    private boolean isChar(String str, char c) {
        return str != null && str.length() == 1 && str.charAt(0) == c;
    }

    public String[] getBoard() {
        return board;
    }

    boolean isValid() {
        if (board.length != 9) {
            return false;
        }

        for (String c : board) {
            if (!(isChar(c, human) || isChar(c, self) || c.isEmpty())) {
                return false;
            }
        }
        return true;
    }

    List<Integer> getIndices(Predicate<String> predicate) {
        List<Integer> indices = new ArrayList<Integer>();
        for(int i = 0; i < board.length; i++) {
            if(predicate.test(board[i])) {
                indices.add(i);
            }
        }
        return indices;
    }

    List<Integer> getEmptyIndices() {
        return this.getIndices(String::isEmpty);
    }

    List<Integer> getHumanIndices() {
        return this.getIndices(c -> isChar(c, human));
    }

    List<Integer> getSelfIndices() {
        return this.getIndices(c -> isChar(c, self));
    }

    void selfTurn(int place) {
        if(getEmptyIndices().contains(place)) {
            Array.set(board, place, String.valueOf(self));
        }
    }
}
