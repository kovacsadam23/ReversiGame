package reversi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends MainFrame{

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

        JButton save = new JButton("Save & Back");
        save.setFont(new Font("Courier New", Font.PLAIN, 30));


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


        JPanel s3 = new JPanel();
        String[] enemies = new String[3];
        enemies[0] = "Human player";
        enemies[1] = "AI player";
        enemies[2] = "Test";

        JLabel e = new JLabel("Enemy type:");
        e.setFont(new Font("Courier New", Font.PLAIN, 30));
        JComboBox jcb = new JComboBox(enemies);
        jcb.setFont(new Font("Arial", Font.PLAIN, 30));

        s3.add(e);
        s3.add(jcb);

        JPanel s4 = new JPanel();
        s4.add(back);
        s4.add(Box.createRigidArea(new Dimension(20, 0)));
        s4.add(save);

        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(title);
        p.add(s1);
        p.add(s2);
        p.add(s3);
        p.add(s4);

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


    private class SaveButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        }
    }
}
