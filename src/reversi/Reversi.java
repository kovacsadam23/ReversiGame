package reversi;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Reversi extends BaseFrame{
    int gameType = 0;
    Player[] players = new Player[2];
    Board board;
    int currentPlayer = 0;
    int opponent;
    JPanel table;
    JTextArea message;
    boolean passed = false;

    private void gameTypeSelect(int type) {
        this.gameType = type;
    }

    private void opponentTypeSelect(int type) {
        this.opponent = type;
    }

    private void setPlayerName() {

    }

    public Reversi() throws IOException {
        this.players[0] = null;
        this.players[1] = null;
        this.board = new Board();
        this.initComponents();
    }


    public void initComponents() throws IOException {
        players[0] = new Player("Player 1", 0);
        players[1] = new Player("Player 2", 1);

        JPanel game = new JPanel();
        game.setSize(800, 800);

        JPanel score = new JPanel();
        score.setLayout(new BoxLayout(score, BoxLayout.Y_AXIS));

        String whoMoves;
        if (currentPlayer == 0) {
            whoMoves = players[0].getName() + " moves";
        }
        else {
            whoMoves = players[1].getName() + " moves";
        }

        JLabel moves = new JLabel();
        moves.setText(whoMoves);
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
        message.setText("");
        if (board.hasValidMove(players[currentPlayer])) {
            if (board.isValidMove(players[currentPlayer], sq.getx(), sq.gety())) {
                board.makeMove(players[currentPlayer], sq);
                board.draw();
                currentPlayer = 1- currentPlayer;
                passed = false;
            }
            else {
                message.setText("Wrong Move");
            }
        }
        else {
            message.setText("Does not have valid move, must pass");
            if (passed || gameType == 0);
            {
                message.setText("End of game");
            }
            passed = true;
            currentPlayer = 1 - currentPlayer;
        }
    }


    public void play() throws IOException {
        board.initBoard();
        board.create(table);
        board.draw();
        if (this.opponent == 0) {

        }
        players[0] = new Player("Player 1", 0);
        players[1] = new Player("Player 2", 1);

        // currentPlayer = 0;
        // boolean passed = false;
        /*
        for(;;) {
            if (board.hasValidMove(players[currentPlayer])) {
                Square sq = players[currentPlayer].nextMove(board);

                if (board.isValidMove(players[currentPlayer], sq.getx(), sq.gety())) {
                    board.makeMove(players[currentPlayer], sq);
                    board.draw();
                    currentPlayer = 1 - currentPlayer;
                    passed = false;
                }
                else {
                    System.out.println("Wrong move");
                }
            }
            else {
                System.out.println("Does not have valid move, must pass");
                if (passed || gameType == 0);
                {
                    System.out.println("End of game");
                    break;
                }
                passed = true;
                currentPlayer = 1 - currentPlayer;
            }
        }

        int whiteCount = board.countColor(0);
        int blackCount = board.countColor(1);

        if (whiteCount > blackCount) {
            System.out.println("White won");
        }
        if (blackCount > whiteCount) {
            System.out.println("Black won");
        }
        else {
            System.out.println("Draw");
        }*/
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


        public boolean isValidMove(Player player, int x, int y) {
            int color = player.getColor();

            if (this.getPiece(x, y) == null)
            {
                for (int dx = -1; dx <= 1; dx++)                /// checking all the directions (except for null vector)
                {
                    for (int dy = -1; dy <= 1; dy++)
                    {
                        if (dx != 0 || dy != 0)
                        {
                            if(checkLine(x, y, dx, dy, color))
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
    }

}
