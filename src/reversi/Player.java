package reversi;

import java.io.*;
import java.util.Random;

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

    public Square nextMove(Reversi reversi) throws IOException {
        return new Square(0, 0);
    }



    static class HumanPlayer extends Player {
        public HumanPlayer(String name, int color) {
            super(name, color);
        }

        @Override
        public boolean isAutomated() {
            return false;
        }
    }


    static class TestPlayer extends Player {
        static BufferedReader reader = null;

        public TestPlayer(String name, int color) throws IOException {
            super(name, color);
            reader = new BufferedReader(new FileReader("testplayer.moves"));
        }


        @Override
        public boolean isAutomated() {
            return true;
        }


        @Override
        public Square nextMove(Reversi reversi) throws IOException {
            String nextMove = reader.readLine();
            char xc = nextMove.charAt(1);
            char yc = nextMove.charAt(2);

            int x = xc - 'A';
            int y = yc - '1';

            return new Square(x, y);
        }
    }


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