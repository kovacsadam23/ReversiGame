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
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.addActionListener(backListener);


        JPanel s1 = new JPanel();
        JLabel p1 = new JLabel("Player 1's name:");
        p1.setFont(new Font("Courier New", Font.PLAIN, 30));
        JTextField player1 = new JTextField(30);
        player1.setFont(new Font("Courier New", Font.PLAIN, 30));

        s1.add(p1);
        s1.add(player1);

        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(title);
        p.add(s1);
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
