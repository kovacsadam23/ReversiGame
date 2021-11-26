package reversi;

public class Reversi {
    int gameType = 0;
    Player[] players = new Player[2];
    Board board;
    int currentPlayer;
    int opponent;
    String player1 = "Player 1";
    String player2 = "PLayer 2";


    private void gameTypeSelect(int type) {
        this.gameType = type;
    }

    private void opponentTypeSelect(int type) {
        this.opponent = type;
    }

    private void setPlayerName() {

    }

    public Reversi() {
        this.players[0] = null;
        this.players[1] = null;
    }

    public void play() {

    }

}
