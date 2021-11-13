package reversi;

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


    public Square nextMove(Board board) {

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
    public Square nextMove(Board board) {

    }
}



public class AIPlayer extends Player {
    public AIPlayer(String name, int color) {
        super(name, color);
    }

    @Override
    public Square nextMove(Board board) {

    }
}