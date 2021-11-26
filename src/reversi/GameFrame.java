package reversi;

import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends MainFrame{

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

        Border border = new LineBorder(Color.black);
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                fields[r][c] = new JButton();
                fields[r][c].setSize(30, 30);
                fields[r][c].setBorder(border);
                fields[r][c].setBackground(new Color(0, 128, 0));

                board.add(fields[r][c]);
            }
        }

        game.add(board);
        game.add(score);
        game.setLayout(new GridLayout());
        this.add(game);
        this.setSize(1600, 800);
    }

}
