package reversi;

import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SettingsFrame extends BaseFrame{
    int gameType = 0;                   // 1 - Reversi, 2 - Othello
    int enemyType = 0;                  // 1 - HumanPlayer, 2 - AIPlayer, 3 - TestPlayer
    String player1;
    String player2;
    XMLHandler xmlHandler;
    JTextField pl1, pl2;
    ArrayList<String> gt;

    JComboBox gjcb, ejcb;

    public void initComponents() throws ParserConfigurationException, IOException, SAXException {
        this.xmlHandler.readXML();
        player1 = xmlHandler.getPlayer1();
        player2 = xmlHandler.getPlayer2();
        gameType = xmlHandler.getGameType();
        enemyType = xmlHandler.getEnemyType();

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
        pl1 = new JTextField(30);
        pl1.setFont(new Font("Courier New", Font.PLAIN, 30));

        s1.add(p1);
        s1.add(pl1);

        JPanel s2 = new JPanel();
        JLabel p2 = new JLabel(("Player 2's name:"));
        p2.setFont(new Font("Courier New", Font.PLAIN, 30));
        pl2 = new JTextField(30);
        pl2.setFont(new Font("Courier New", Font.PLAIN, 30));

        s2.add(p2);
        s2.add(pl2);


        JPanel s4 = new JPanel();
        String[] enemies = new String[3];
        enemies[0] = "Human player";
        enemies[1] = "AI player";
        enemies[2] = "Test";

        JLabel e = new JLabel("Enemy type:");
        e.setFont(new Font("Courier New", Font.PLAIN, 30));
        ejcb = new JComboBox(enemies);
        ejcb.setFont(new Font("Arial", Font.PLAIN, 30));
        ejcb.setSelectedIndex(enemyType - 1);

        s4.add(e);
        s4.add(ejcb);

        JPanel s3 = new JPanel();
        /*
        String[] gt = new String[2];
        gt[0] = "Reversi";
        gt[1] = "Othello"; */
        JLabel g = new JLabel("Game type:");
        g.setFont(new Font("Courier New", Font.PLAIN, 30));
        gjcb = new JComboBox(gt.toArray());
        gjcb.setFont(new Font("Arial", Font.PLAIN, 30));
        gjcb.setSelectedIndex(gameType - 1);

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

        pl1.setText(player1);
        pl2.setText(player2);

        this.add(p, BorderLayout.CENTER);
    }

    public SettingsFrame() throws ParserConfigurationException, IOException, SAXException {
        gt = new ArrayList<>(Arrays.asList("Reversi", "Othello"));
        this.xmlHandler = new XMLHandler();
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
            gameType = gjcb.getSelectedIndex() + 1;
            enemyType = ejcb.getSelectedIndex() + 1;
            player1 = pl1.getText();
            player2 = pl2.getText();

            xmlHandler.setPlayer1(player1);
            xmlHandler.setPlayer2(player2);
            xmlHandler.setGameType(gameType);
            xmlHandler.setEnemyType(enemyType);

            try {
                xmlHandler.writeXML();
            } catch (ParserConfigurationException | IOException | SAXException | TransformerException ex) {
                ex.printStackTrace();
            }

            dispose();
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        }
    }
}
