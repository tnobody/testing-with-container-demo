package org.tnobody.oss;

import com.google.gson.Gson;

import java.util.Optional;

import static spark.Spark.get;
import static spark.Spark.halt;

/**
 * Hello world!
 */
public class App {

    private static Gson gson = new Gson();
    private static char human = '1';
    private static char self = '0';
    private static String validatorBackendUrl = Optional.ofNullable(
            System.getenv("VALIDATOR_BACKEND")
    ).orElse("http://localhost:8081");

    public static void main(String[] args) {

        final ValidatorService validatorService = new ValidatorService(validatorBackendUrl);

        get("/api/next-turn", (req, res) -> {
            Optional<String> board = Optional.ofNullable(req.queryMap().get("board").value());
            Optional<String> gameId = Optional.ofNullable(req.queryMap().get("gameId").value());
            if (!board.isPresent() && !gameId.isPresent()) {
                halt(400, "Either query parameter board or gameid is not specified");
            }
            final BoardGrid boardGrid = new BoardGrid(
                    board.map(b -> b.split(",", -1)).orElse(new String[]{}),
                    human, self
            );

            if (!boardGrid.isValid()) {
                halt(400, String.format("Board must contain 9 comma separated values that are either empty, '%s' or '%s'", human, self));
            }

            final ValidatorService.ValidatorResult result = validatorService.requestWinner(boardGrid.getBoard());
            if (result.getWinner().isEmpty()) {
                GameService gameService = new GameService(boardGrid);
                gameService.nextTurn();
                final ValidatorService.ValidatorResult nextResult = validatorService.requestWinner(boardGrid.getBoard());
                return new Response(
                        nextResult.getWinner(),
                        gameService.getBoardGrid().getBoard()
                );
            } else {
                return new Response(
                        result.getWinner(),
                        boardGrid.getBoard()
                );
            }
        }, gson::toJson);
    }

}
