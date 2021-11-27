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

/*
    public Square nextMove(Board board) throws FileNotFoundException {

    }
}



public class HumanPlayer extends Player {
    public HumanPlayer(String name, int color) {
        super(name, color);
    }

    @Override
    public Square nextMove(Board board){

    }
}



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



class AIPlayer extends Player {
    public AIPlayer(String name, int color) {
        super(name, color);
    }

    @Override
    public Square nextMove(Board board) {
        Random random = new Random();
        int x, y;

        for(;;) {
            x = random.nextInt(8);
            y = random.nextInt(8);

            if (board.isValidMove(this, x, y)) {
                break;
            }
        }
        return new Square(x, y);

    }*/
}