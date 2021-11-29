package reversi;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class Piece {
    int color;


    public Piece(int color) {
        this.color = color;
    }


    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }



    public static class TestPiece extends Piece{
        public TestPiece() {
            super(0);
        }

        @Test
        public void testSetColor() {
            // System.out.println("test Piece::setColor");
            int c = 1;
            TestPiece piece = new TestPiece();
            piece.setColor(c);

            assertEquals(piece.getColor(), c);
        }

        @Test
        public void testGetColor() {
            TestPiece piece = new TestPiece();
            int expected = 1;
            piece.setColor(1);
            int result = piece.getColor();
            assertEquals(expected, result);
        }

    }
}
