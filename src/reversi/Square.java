package reversi;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Square {
    int x;
    int y;


    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getx() {
        return this.x;
    }

    public int gety() {
        return this.y;
    }



    public static class TestSquare extends Square{
        public TestSquare() {
            super(1, 2);
        }

        @Test
        public void testGetX() {
            TestSquare test = new TestSquare();
            int expected = 1;
            int result = test.getx();
            assertEquals(expected, result);
        }

        @Test
        public void testGetY() {
            TestSquare test = new TestSquare();
            int expected = 2;
            int result = test.gety();
            assertEquals(expected, result);
        }

    }
}
