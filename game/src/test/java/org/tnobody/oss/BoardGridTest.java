package org.tnobody.oss;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardGridTest {

    @Test
    void isValid_true_with_9_empties() {
        final BoardGrid boardGrid = new BoardGrid(
                ",,,,,,,,".split(",", -1),
                'x',
                'o'
        );
        assertTrue(boardGrid.isValid());
    }

    @Test
    void isValid_true_with_configured_icons() {
        final BoardGrid boardGrid = new BoardGrid(
                ",o,o,,x,,,x,".split(",", -1),
                'x',
                'o'
        );
        assertTrue(boardGrid.isValid());
    }

    @Test
    void isValid_false_with_unConfigured_icons() {
        final BoardGrid boardGrid = new BoardGrid(
                ",o,o,,y,,,x,".split(",", -1),
                'x',
                'o'
        );
        assertFalse(boardGrid.isValid());
    }

    @Test
    void getSelfIndices() {
        final BoardGrid boardGrid = new BoardGrid(
                ",o,o,,x,,,o,".split(",", -1),
                'x',
                'o'
        );
        List<Integer> indices = boardGrid.getSelfIndices();
        assertEquals(3, indices.size());
        assertEquals(1, indices.get(0));
        assertEquals(2, indices.get(1));
        assertEquals(7, indices.get(2));
    }

    @Test
    void getEmptyIndices() {
        final BoardGrid boardGrid = new BoardGrid(
                ",o,o,,x,,,o,".split(",", -1),
                'x',
                'o'
        );
        List<Integer> indices = boardGrid.getEmptyIndices();
        assertEquals(5, indices.size());

        assertEquals(0, indices.get(0));
        assertEquals(3, indices.get(1));
        assertEquals(5, indices.get(2));
        assertEquals(6, indices.get(3));
        assertEquals(8, indices.get(4));
    }

    @Test
    void getHumanIndices() {
        final BoardGrid boardGrid = new BoardGrid(
                ",o,o,,x,,,o,".split(",", -1),
                'x',
                'o'
        );
        List<Integer> indices = boardGrid.getHumanIndices();

        assertEquals(1, indices.size());
        assertEquals(4, indices.get(0));
    }

    @Test
    void selfTurn() {
        final BoardGrid boardGrid = new BoardGrid(
                ",1,,,,,,,".split(",", -1),
                '1',
                '0'
        );
        boardGrid.selfTurn(5);
    }

}