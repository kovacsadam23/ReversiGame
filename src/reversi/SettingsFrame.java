package reversi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends BaseFrame implements XMLHandler{

    public void initComponents() {
        JPanel p = new JPanel();

        JLabel title = new JLabel("Settings");
        title.setFont(new Font("Courier New", Font.BOLD, 70));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        ActionListener backListener = new BackButtonActionListener();
        JButton back = new JButton("Back");
        back.setFont(new Font("Courier New", Font.PLAIN, 30));
        // back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.addActionListener(backListener);

        ActionListener saveListener = new SaveButtonActionListener();
        JButton save = new JButton("Save & Back");
        save.setFont(new Font("Courier New", Font.PLAIN, 30));
        save.addActionListener(saveListener);

        JPanel s1 = new JPanel();
        JLabel p1 = new JLabel("Player 1's name:");
        p1.setFont(new Font("Courier New", Font.PLAIN, 30));
        JTextField player1 = new JTextField(30);
        player1.setFont(new Font("Courier New", Font.PLAIN, 30));

        s1.add(p1);
        s1.add(player1);

        JPanel s2 = new JPanel();
        JLabel p2 = new JLabel(("Player 2's name:"));
        p2.setFont(new Font("Courier New", Font.PLAIN, 30));
        JTextField player2 = new JTextField(30);
        player2.setFont(new Font("Courier New", Font.PLAIN, 30));

        s2.add(p2);
        s2.add(player2);


        JPanel s4 = new JPanel();
        String[] enemies = new String[3];
        enemies[0] = "Human player";
        enemies[1] = "AI player";
        enemies[2] = "Test";

        JLabel e = new JLabel("Enemy type:");
        e.setFont(new Font("Courier New", Font.PLAIN, 30));
        JComboBox ejcb = new JComboBox(enemies);
        ejcb.setFont(new Font("Arial", Font.PLAIN, 30));

        s4.add(e);
        s4.add(ejcb);

        JPanel s3 = new JPanel();
        String[] gameType = new String[2];
        gameType[0] = "Reversi";
        gameType[1] = "Othello";
        JLabel g = new JLabel("Game type:");
        g.setFont(new Font("Courier New", Font.PLAIN, 30));
        JComboBox gjcb = new JComboBox(gameType);
        gjcb.setFont(new Font("Arial", Font.PLAIN, 30));

        s3.add(g);
        s3.add(gjcb);

        JPanel s5 = new JPanel();
        s5.add(back);
        s5.add(Box.createRigidArea(new Dimension(20, 0)));
        s5.add(save);

        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(title);
        p.add(s1);
        p.add(s2);
        p.add(s3);
        p.add(s4);
        p.add(s5);

        this.add(p, BorderLayout.CENTER);
    }

    public SettingsFrame() {
        this.initComponents();
    }


    private class BackButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        }
    }


    private class SaveButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        }
    }
}
