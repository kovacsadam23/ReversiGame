package reversi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Player {
    String name;
    int color;


    public Player(String name, int color) {
        this.name = name;
        this.color = color;
    }


    public int getColor() {
        return this.color;
    }

    public String getName() {
        return this.name;
    }

    public boolean isAutomated() {
        return false;
    }

    public Square nextMove(Reversi reversi) {
        return new Square(0, 0);
    }

    /*
        public Square nextMove(Board board) throws FileNotFoundException {

        }
    }


 */
    static class HumanPlayer extends Player {
        public HumanPlayer(String name, int color) {
            super(name, color);
        }

        @Override
        public boolean isAutomated() {
            return false;
        }
    }


/*
    public class TestPlayer extends Player {
        public TestPlayer(String name, int color) {
            super(name, color);
        }

        @Override
        public Square nextMove(Board board) throws FileNotFoundException {
            File moves = new File("testplayer.moves");
            Scanner sc = new Scanner(moves);

            String nextMove = sc.nextLine();
            int x = (int)nextMove.charAt(1);
            int y = (int)nextMove.charAt(2);
            sc.close();

            return new Square(x, y);
        }
    }


    */
static class AIPlayer extends Player {
        public AIPlayer(String name, int color) {
            super(name, color);
        }

        @Override
        public boolean isAutomated() {
           return true;
        }


        @Override
        public Square nextMove(Reversi reversi) {
            Random random = new Random();
            int x, y;

            for (;;) {
                x = random.nextInt(8);
                y = random.nextInt(8);

                if (reversi.isValidMove(this, x, y)) {
                    break;
                }
            }
            return new Square(x, y);
        }
    }
}