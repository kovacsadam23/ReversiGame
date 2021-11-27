package reversi;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class Board {
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

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                fields[r][c] = new JButton();
                fields[r][c].setSize(30, 30);
                fields[r][c].setBorder(border);
                fields[r][c].setBackground(new Color(0, 128, 0));

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
                    }
                    else {
                        fields[r][c].setIcon(new ImageIcon(black_reduced));
                        // fields[r][c].setBackground(Color.BLACK);
                    }
                }
            }
        }
    }
}
