package reversi;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends MainFrame{

    public void initComponents() {

        JPanel game = new JPanel();

        JPanel score = new JPanel();
        JLabel moves = new JLabel("valami");
        score.add(moves);

        JPanel board = new JPanel(new GridLayout(8, 8));
        JButton[][] fields = new JButton[8][8];

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                fields[r][c] = new JButton("teszt sor: " + r);
                fields[r][c].setSize(30, 30);

                board.add(fields[r][c]);
            }
        }
        board.setSize(400, 400);


        game.setLayout(new BoxLayout(game, BoxLayout.Y_AXIS));
        //game.add(score);
        game.setSize(400, 400);
        game.add(board);
        this.add(game);
        this.setSize(800, 800);
    }
}
