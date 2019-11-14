package org.tnobody.oss;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class ValidatorServiceIT {

    @Container
    private static final GenericContainer ValidatorServiceContainer = new GenericContainer(new ImageFromDockerfile()
            .withDockerfile(Paths.get("../validator/Dockerfile"))
    );

    @Test
    void test() {
        final String serviceUrl = String.format("http://%s:%d", ValidatorServiceContainer.getContainerIpAddress(), ValidatorServiceContainer.getMappedPort(8080));
        ValidatorService validatorService = new ValidatorService(serviceUrl);

        /**
         * This will ask validator-service endpoint for the winner of the board:
         *
         *  1 | 1 | 1
         * -----------
         *  0 |   | 0
         * -----------
         *    |   |
         *
         * Which should be "1"
         */
        final String winner = validatorService.requestWinner(
                "1,1,1,0,,0,,,".split(",", -1)
        ).getWinner();

        assertEquals("1", winner);

    }

}
