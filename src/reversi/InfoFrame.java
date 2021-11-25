package reversi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoFrame extends MainFrame{
    public void initComponents() {
        JPanel p = new JPanel();
        JLabel title = new JLabel("Help");
        title.setFont(new Font("Courier New", Font.BOLD, 70));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea info = new JTextArea("Reversi is a strategy board game for two players, played on an 8×8 uncheckered " +
                "board. It was invented in 1883. Othello, a variant with a change to the " +
                "board's initial setup, was patented in 1971. \n\nThere are sixty-four identical game pieces called disks " +
                "(often spelled \"discs\"), which are light on one side and dark on the other. Players take turns " +
                "placing disks on the board with their assigned color facing up. \nDuring a play, any disks of the " +
                "opponent's color that are in a straight line and bounded by the disk just placed and another disk " +
                "of the current player's color are turned over to the current player's color. \n\nThe objective of the " +
                "game is to have the majority of disks turned to display one's color when the last playable empty " +
                "square is filled.");                                                           //Wikipedia
        info.setFont(new Font("Courier New", Font.PLAIN, 30));
        info.setEditable(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        JScrollPane jScrollPane = new JScrollPane(info, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        ActionListener backListener = new BackButtonActionListener();
        JButton back = new JButton("Back");
        back.setFont(new Font("Courier New", Font.PLAIN, 30));
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.addActionListener(backListener);

        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(title);
        p.add(jScrollPane);
        p.add(back);

        this.add(p, BorderLayout.CENTER);
    }


    private class BackButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        }
    }

}
