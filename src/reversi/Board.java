package reversi;

import java.util.Arrays;

public class Board {
    Piece[][] pcs = new Piece[8][8];



    public Board() {
        Arrays.fill(pcs, 0);
    }

    public Piece getPiece(int x, int y) {

    }

    public void addPiece(int x, int y, int color) {

    }

    public void initBoard() {

    }

    public void deleteBoard() {

    }

    public void makeMove(Player player, Square sq) {

    }

    public boolean isValidMove(Player player, int x, int y) {

    }

    public boolean hasValidMove(Player player){

    }

    public void flipLine(int x, int y, int dx, int dy, int color) {

    }

    public boolean checkLine(int x, int y, int dx, int dy, int color) {

    }

    public int countColor(int color) {

    }

}
