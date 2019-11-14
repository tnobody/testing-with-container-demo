package org.tnobody.oss;

class Response {
    private String winner = "";
    private String[] board;

    public Response(String winner, String[] board) {
        this.winner = winner;
        this.board = board;
    }

    public String getWinner() {
        return winner;
    }

    public String[] getBoard() {
        return board;
    }
}
