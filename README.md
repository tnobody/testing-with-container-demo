# TicTacToe

## Services

**Validator**

Takes a array with size 9 of type `null | "0" | "1"` and Returns if there is a winner


Run E2E:
```
docker-compose -f docker-compose.yml -f docker-compose.e2e.yml down && docker-compose -f docker-compose.yml -f docker-compose.e2e.yml build && docker-compose -f docker-compose.yml -f docker-compose.e2e.yml up
```
