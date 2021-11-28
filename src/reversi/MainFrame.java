package reversi;

import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainFrame extends BaseFrame implements ActionListener {

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


        ActionListener startListener = new StartButtonActionListener();
        JButton start = new JButton("START");
        start.setFont(new Font("Courier New", Font.BOLD, 40));
        start.setBackground(Color.GREEN);
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        start.addActionListener(startListener);

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
        this.initComponents();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("test");
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
            SettingsFrame settingsFrame = null;
            try {
                settingsFrame = new SettingsFrame();
            } catch (ParserConfigurationException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (SAXException ex) {
                ex.printStackTrace();
            }
            settingsFrame.setVisible(true);
        }
    }


    private class StartButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Reversi reversi = null;
            try {
                reversi = new Reversi();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ParserConfigurationException parserConfigurationException) {
                parserConfigurationException.printStackTrace();
            } catch (SAXException saxException) {
                saxException.printStackTrace();
            }
            reversi.setVisible(true);
        }
    }
}
