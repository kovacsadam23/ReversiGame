package reversi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {


    public void initComponents() {
        JPanel p = new JPanel();
        JLabel title = new JLabel("REVERSI - OTHELLO");
        title.setFont(new Font("Courier New", Font.BOLD, 70));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        ActionListener settingsListener = new SettingsButtonActionListener();
        JButton settings = new JButton("Settings");
        settings.setFont(new Font("Courier New", Font.PLAIN ,40));
        settings.setAlignmentX(Component.CENTER_ALIGNMENT);
        settings.addActionListener(settingsListener);


        ActionListener infoListener = new InfoButtonActionListener();
        JButton info = new JButton("Help");
        info.setFont(new Font("Courier New", Font.PLAIN, 40));
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.addActionListener(infoListener);


        JButton start = new JButton("START");
        start.setFont(new Font("Courier New", Font.BOLD, 40));
        start.setBackground(Color.GREEN);
        start.setAlignmentX(Component.CENTER_ALIGNMENT);

        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(title);
        p.add(Box.createRigidArea(new Dimension(0, 100)));
        p.add(settings);
        p.add(Box.createRigidArea(new Dimension(0, 15)));
        p.add(info);
        p.add(Box.createRigidArea(new Dimension(0, 15)));
        p.add(start);

        this.add(p, BorderLayout.CENTER);
    }


    public MainFrame() {
        super("Reversi");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage("reversi_logo.jpg");
        this.setIconImage(icon);

        this.setMinimumSize(new Dimension(900, 600));
        this.setResizable(false);
        this.initComponents();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("faszom2");
    }


    private class InfoButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            InfoFrame infoFrame = new InfoFrame();
            infoFrame.setVisible(true);
        }
    }


    private class SettingsButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            SettingsFrame settingsFrame = new SettingsFrame();
            settingsFrame.setVisible(true);
        }
    }
}
