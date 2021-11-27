package reversi;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends MainFrame implements XMLHandler{


    public void initComponents() {

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

        JPanel board = new JPanel(new GridLayout(8, 8));
        board.setSize(800, 800);
        JButton[][] fields = new JButton[8][8];


        game.add(board);
        game.add(score);
        game.setLayout(new GridLayout());
        this.add(game);
        this.setSize(1600, 800);
    }

}
