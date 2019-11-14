FROM maven as builder
WORKDIR /usr/app/

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /usr/app/src
RUN mvn package

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=builder /usr/app/target/game-service.jar .
EXPOSE 4567
CMD ["java", "-jar", "game-service.jar"]
