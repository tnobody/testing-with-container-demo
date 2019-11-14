package org.tnobody.oss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

public class GameService {

    Logger logger = LoggerFactory.getLogger(GameService.class);

    private Random random = new Random();
    private BoardGrid boardGrid;

    public GameService(BoardGrid boardGrid) {
        this.boardGrid = boardGrid;
    }

    public BoardGrid getBoardGrid() {
        return boardGrid;
    }

    boolean nextTurn() {
        final List<Integer> emptyIndices = boardGrid.getEmptyIndices();
        if(boardGrid.isValid() && emptyIndices.size() > 0) {
            final int turnTo = emptyIndices.get(
                    random.nextInt(emptyIndices.size())
            );
            logger.info("Next turn to: " + turnTo);
            boardGrid.selfTurn(turnTo);
            return true;
        } else {
            return false;
        }
    }
}
