package reversi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    private JButton settings, info, start;

    public void initComponents() {
        JPanel p = new JPanel();
        JLabel title = new JLabel("REVERSI - OTHELLO");
        title.setFont(new Font("Times New Roman", Font.BOLD, 40));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton settings = new JButton("Settings");
        settings.setFont(new Font("Times New Roman", Font.PLAIN ,20));
        settings.setAlignmentX(Component.CENTER_ALIGNMENT);
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("faszom");
            }
        });

        JButton info = new JButton("Help");
        info.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        info.setAlignmentX(Component.CENTER_ALIGNMENT);


        JButton start = new JButton("START");
        start.setFont(new Font("Times New Roman", Font.BOLD, 20));
        start.setBackground(Color.GREEN);
        start.setAlignmentX(Component.CENTER_ALIGNMENT);

        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(title);
        p.add(settings);
        p.add(info);
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
}
