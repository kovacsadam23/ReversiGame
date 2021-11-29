package reversi;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static org.junit.Assert.assertEquals;



public class Reversi extends BaseFrame{
    int gameType;
    Player[] players = new Player[2];
    Board board;
    int currentPlayer = 0;
    int enemyType;
    JPanel table;
    JTextArea message;
    JLabel moves;
    boolean passed;
    boolean endOfGame = false;
    XMLHandler xmlHandler;


    public Reversi() throws IOException, ParserConfigurationException, SAXException {
        this.players[0] = null;
        this.players[1] = null;
        this.board = new Board();
        this.xmlHandler = new XMLHandler();
        this.initComponents();
    }


    public void initComponents() throws IOException, ParserConfigurationException, SAXException {
        xmlHandler.readXML();
        this.gameType = xmlHandler.getGameType();
        this.enemyType = xmlHandler.getEnemyType();
        String player1 = xmlHandler.getPlayer1();
        String player2 = xmlHandler.getPlayer2();

        if (enemyType == 1) {
            players[0] = new Player.HumanPlayer(player1, 0);
            players[1] = new Player(player2, 1);
        }
        else if (enemyType == 2) {
            players[0] = new Player.HumanPlayer(player1, 0);
            players[1] = new Player.AIPlayer(player2, 1);
        }
        else {
            players[0] = new Player.TestPlayer(player1, 0);
            players[1] = new Player.TestPlayer(player2, 1);
        }

        JPanel game = new JPanel();
        game.setSize(800, 800);

        JPanel score = new JPanel();
        score.setLayout(new BoxLayout(score, BoxLayout.Y_AXIS));

        moves = new JLabel();
        moves.setFont(new Font("Courier New", Font.BOLD, 50));
        moves.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        message = new JTextArea();
        message.setEditable(false);
        message.setFont(new Font("Courier New", Font.PLAIN, 50));

        score.add(moves);
        score.add(message);
        score.add(back);

        table = new JPanel(new GridLayout(8, 8));
        table.setSize(800, 800);


        game.add(table);
        game.add(score);
        game.setLayout(new GridLayout());
        this.add(game);
        this.setSize(1600, 800);
        this.setVisible(true);
        play();
    }


    public void nextMove(Square sq) throws IOException {
        boolean passPrinted = false;

        if (endOfGame)
        {
            return;
        }

        message.setText("");

        if (board.hasValidMove(players[currentPlayer])) {
            if (players[currentPlayer].isAutomated()) {
                sq = players[currentPlayer].nextMove(this);
            }

            if (this.isValidMove(players[currentPlayer], sq.getx(), sq.gety())) {
                board.makeMove(players[currentPlayer], sq);
                board.draw();
                currentPlayer = 1 - currentPlayer;
                passed = false;
            }
            else {
                message.setText("Wrong Move");
                board.drawValidMoves(players[currentPlayer]);
            }
        }
        else {
            message.setText(players[currentPlayer].getName() + " \ndoes not have valid move, \nmust pass");
            passPrinted = true;
            int whiteCount = board.countColor(0);
            int blackCount = board.countColor(1);
            if (passed || this.gameType == 1 || (whiteCount + blackCount) == 64)            // Reversi
            {
                String res;

                if (whiteCount > blackCount) {
                    res = players[0].getName() + " WON";
                }
                else if (blackCount > whiteCount) {
                    res = players[1].getName() + " WON";
                }
                else {
                    res = "DRAW";
                }

                message.setText("End of game\n\n" + res + "\n\n" + players[0].getName() + ": "
                        + whiteCount + "\n" + players[1].getName() + ": " + blackCount);
                endOfGame = true;
            }
            passed = true;
            currentPlayer = 1 - currentPlayer;
        }

        String whoMoves;
        if (currentPlayer == 0) {
            whoMoves = players[0].getName() + " moves";
        } else {
            whoMoves = players[1].getName() + " moves";
        }
        moves.setText(whoMoves);

        if (players[currentPlayer].isAutomated() && !passPrinted) {
            message.setText("Click on the board \nto continue...");
        }
        else if (gameType == 2 && !board.hasValidMove(players[currentPlayer]) && !endOfGame){
            message.setText(players[currentPlayer].getName() + " \ndoes not have valid move, \nmust pass");
            passed = true;
            currentPlayer = 1 - currentPlayer;
        }
    }


    public void play() throws IOException {
        board.initBoard();
        board.create(table);
        board.draw();
    }


    public boolean isValidMove(Player player, int x, int y) {
        int color = player.getColor();

        if (board.getPiece(x, y) == null)
        {
            for (int dx = -1; dx <= 1; dx++)                /// checking all the directions (except for null vector)
            {
                for (int dy = -1; dy <= 1; dy++)
                {
                    if (dx != 0 || dy != 0)
                    {
                        if(board.checkLine(x, y, dx, dy, color))
                        {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        else
        {
            return false;
        }
    }
// _____________________________________________________________________________________________________________________

    private class Board {
        Piece[][] pcs = new Piece[8][8];
        JButton[][] fields = new JButton[8][8];

        public Board() {
            for (int i = 0; i < pcs.length; i++) {
                for (int j = 0; j < pcs.length; j++) {
                    pcs[i][j] = null;
                }
            }
        }

        public Piece getPiece(int x, int y) {
            return pcs[x][y];
        }

        public void addPiece(int x, int y, int color) {
            pcs[x][y] = new Piece(color);
        }


        public void deleteBoard() {
            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    pcs[i][j] = null;
                }
            }
        }


        public void initBoard() {
            this.deleteBoard();
            this.addPiece(3, 3, 0);
            this.addPiece(4, 3, 1);
            this.addPiece(3, 4, 1);
            this.addPiece(4, 4, 0);
        }


        public void makeMove(Player player, Square square) {
            int x = square.getx();
            int y = square.gety();
            int color = player.getColor();

            addPiece(x, y, color);

            for (int dx = -1; dx <= 1; dx++) {            /// checking all the directions (except for null vector)
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx != 0 || dy != 0) {
                        if(checkLine(x, y, dx, dy, color)) {
                            flipLine(x, y, dx, dy, color);
                        }
                    }
                }
            }
        }


        public void drawValidMoves(Player player) {
            ImageIcon x = new ImageIcon("x.png");
            Image xim = x.getImage();

            Image x_red = xim.getScaledInstance(60, 60, 0);

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (isValidMove(player, i, j)) {
                        fields[i][j].setIcon(new ImageIcon(x_red));
                    }
                }
            }
        }


        public boolean hasValidMove(Player player){
            for (int i = 0; i < 8; i++)
            {
                for (int j = 0; j < 8; j++)
                {
                    if (isValidMove(player, i, j))
                    {
                        return true;
                    }
                }
            }
            return false;
        }


        public void flipLine(int x, int y, int dx, int dy, int color) {
            for (int i = x + dx, j = y + dy; i >= 0 && i < 8 && j >=0 && j < 8; i = i + dx, j = j + dy)
            {
                Piece piece = this.getPiece(i, j);
                if (piece == null)
                {
                    break;
                }
                if (piece.getColor() != color)
                {
                    piece.setColor(color);
                }
                else
                {
                    break;
                }
            }
        }


        public boolean checkLine(int x, int y, int dx, int dy, int color) {
            boolean enemyFound = false;
            boolean turnable = false;

            for (int i = x + dx, j = y + dy; i >= 0 && i < 8 && j >=0 && j < 8; i = i + dx, j = j + dy)
            {
                Piece piece = this.getPiece(i, j);

                if (piece == null)
                {
                    break;
                }

                if (piece.getColor() != color)
                {
                    enemyFound = true;
                }
                else
                {
                    if (piece.getColor() == color && enemyFound)
                    {
                        turnable = true;
                    }
                    break;
                }
            }
            return turnable;
        }


        public int countColor(int color) {
            int counter = 0;
            for(int i = 0; i < 8; i++)
            {
                for (int j = 0; j < 8; j++)
                {
                    Piece piece = this.getPiece(i, j);

                    if (piece != null && piece.getColor() == color)
                    {
                        counter++;
                    }
                }
            }
            return counter;
        }


        public void create(JPanel panel) {
            Border border = new LineBorder(Color.black);
            ActionListener listener = new ButtonActionListener();
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    fields[r][c] = new JButton();
                    fields[r][c].setSize(30, 30);
                    fields[r][c].setBorder(border);
                    fields[r][c].setBackground(new Color(0, 128, 0));
                    fields[r][c].addActionListener(listener);

                    panel.add(fields[r][c]);
                }
            }
        }


        public void draw() throws IOException {
            // Image white = ImageIO.read(Objects.requireNonNull(getClass().getResource("resources/reversi_white.png")));
            // Image black = ImageIO.read(Objects.requireNonNull(getClass().getResource("resources/reversi_black.png")));
            ImageIcon white = new ImageIcon("reversi_white.png");
            ImageIcon black = new ImageIcon("reversi_black.png");

            Image w = white.getImage();
            Image white_reduced = w.getScaledInstance(100, 100, 0);

            Image b = black.getImage();
            Image black_reduced = b.getScaledInstance(100, 100, 0);

            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    fields[r][c].setIcon(null);
                    if (pcs[r][c] != null) {
                        if (pcs[r][c].getColor() == 0) {
                            fields[r][c].setIcon(new ImageIcon(white_reduced));
                            // fields[r][c].setBackground(Color.WHITE);
                        } else {
                            fields[r][c].setIcon(new ImageIcon(black_reduced));
                            // fields[r][c].setBackground(Color.BLACK);
                        }
                    }
                    String cmd = (String.valueOf(r) + String.valueOf(c));
                    fields[r][c].setActionCommand(cmd);
                }
            }
        }


        private class ButtonActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String coord = e.getActionCommand();
                char cx = coord.charAt(0);
                char cy = coord.charAt(1);
                int x = Character.getNumericValue(cx);
                int y = Character.getNumericValue(cy);

                Square sq = new Square(x, y);
                try {
                    nextMove(sq);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }


        public static class TestBoard extends Reversi{
            public TestBoard() throws IOException, ParserConfigurationException, SAXException {
                super();
            }

            @Test
            public void testGetPiece() {
                Board board = new Board();
                Piece expected = new Piece(0);
                board.pcs[1][1] = expected;
                Piece result = board.getPiece(1, 1);
                assertEquals(expected, result);
            }
        }
    }
}
