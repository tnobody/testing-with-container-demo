package org.tnobody.oss;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidatorService {

    Logger logger = LoggerFactory.getLogger(ValidatorService.class);

    class ValidatorResult {
        private String winner;

        public String getWinner() {
            return winner;
        }

        public void setWinner(String winner) {
            this.winner = winner;
        }

        @Override
        public String toString() {
            return "ValidatorResult{" +
                    "winner='" + winner + '\'' +
                    '}';
        }
    }

    private String baseUrl;

    public ValidatorService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    ValidatorResult requestWinner(String[] board) {
        String serviceUrl = baseUrl + "/api/validate";
        final HttpResponse<ValidatorResult> response = Unirest.get(serviceUrl)
                .queryString("board", String.join(",", board))
                .asObject(ValidatorResult.class);
        System.out.println(response.getStatus());
        final ValidatorResult validatorResult = response.getBody();
        logger.info(String.format("Got %d %s",
                response.getStatus(),
                validatorResult.toString()));
        return validatorResult;
    }
}
