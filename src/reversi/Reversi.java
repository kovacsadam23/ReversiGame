package reversi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Reversi extends BaseFrame{
    int gameType = 0;
    Player[] players = new Player[2];
    Board board;
    int currentPlayer;
    int opponent;
    String player1 = "Player 1";
    String player2 = "PLayer 2";
    JPanel table;


    private void gameTypeSelect(int type) {
        this.gameType = type;
    }

    private void opponentTypeSelect(int type) {
        this.opponent = type;
    }

    private void setPlayerName() {

    }

    public Reversi() throws IOException {
        this.players[0] = null;
        this.players[1] = null;
        this.board = new Board();
        this.initComponents();
    }


    public void initComponents() throws IOException {

        JPanel game = new JPanel();
        game.setSize(800, 800);

        JPanel score = new JPanel();
        score.setLayout(new BoxLayout(score, BoxLayout.Y_AXIS));

        JLabel moves = new JLabel("valami");


        JButton back = new JButton("Back");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setFont(new Font("Courier New", Font.PLAIN, 30));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });


        score.add(moves);
        score.add(back);

        table = new JPanel(new GridLayout(8, 8));
        table.setSize(800, 800);


        game.add(table);
        game.add(score);
        game.setLayout(new GridLayout());
        this.add(game);
        this.setSize(1600, 800);
        this.setVisible(true);
        play();
    }


    public void play() throws IOException {
        board.initBoard();
        board.create(table);
        board.draw();
        if (this.opponent == 0) {

        }
    }
}
