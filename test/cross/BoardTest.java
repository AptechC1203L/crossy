/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cross;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chin
 */
public class BoardTest {

    private Board instance;
    private Player player1;
    private Player player2;

    public BoardTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new Board(20, 20, 5);
        player1 = new Player('X', "Chin");
        player2 = new Player('O', "Kin");

        /////////////////////////
        // Draw a "star" shape //
        /////////////////////////

        // x       x       x
        //   x     x     x
        //     x   x   x
        //       x x x
        // x x x x o x x x x
        //       x x x
        //     x   x   x
        //   x     x     x
        // x       x       x

        // NW - SE
        instance.makeMove(new Turn(5, 5, player1));
        instance.makeMove(new Turn(6, 6, player1));
        instance.makeMove(new Turn(7, 7, player1));
        instance.makeMove(new Turn(8, 8, player1));
        instance.makeMove(new Turn(9, 9, player1));
        instance.makeMove(new Turn(10, 10, player1));
        instance.makeMove(new Turn(11, 11, player1));
        instance.makeMove(new Turn(12, 12, player1));
        instance.makeMove(new Turn(13, 13, player1));

        // NE - SW
        instance.makeMove(new Turn(5, 13, player1));
        instance.makeMove(new Turn(6, 12, player1));
        instance.makeMove(new Turn(7, 11, player1));
        instance.makeMove(new Turn(8, 10, player1));
        instance.makeMove(new Turn(9, 9, player1));
        instance.makeMove(new Turn(10, 8, player1));
        instance.makeMove(new Turn(11, 7, player1));
        instance.makeMove(new Turn(12, 6, player1));
        instance.makeMove(new Turn(13, 5, player1));

        // N - S
        instance.makeMove(new Turn(5, 9, player1));
        instance.makeMove(new Turn(6, 9, player1));
        instance.makeMove(new Turn(7, 9, player1));
        instance.makeMove(new Turn(8, 9, player1));
        instance.makeMove(new Turn(9, 9, player1));
        instance.makeMove(new Turn(10, 9, player1));
        instance.makeMove(new Turn(11, 9, player1));
        instance.makeMove(new Turn(12, 9, player1));
        instance.makeMove(new Turn(13, 9, player1));

        // W - E
        instance.makeMove(new Turn(9, 5, player1));
        instance.makeMove(new Turn(9, 6, player1));
        instance.makeMove(new Turn(9, 7, player1));
        instance.makeMove(new Turn(9, 8, player1));
        instance.makeMove(new Turn(9, 9, player1));
        instance.makeMove(new Turn(9, 10, player1));
        instance.makeMove(new Turn(9, 11, player1));
        instance.makeMove(new Turn(9, 12, player1));
        instance.makeMove(new Turn(9, 13, player1));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of makeMove method, of class Board.
     */
    @Test
    public void testMakeMove() {
        int res1 = instance.makeMove(new Turn(2, 2, player1));
        int res2 = instance.makeMove(new Turn(4, 6, player2));

        assertEquals(0, res1);
        assertEquals(0, res2);

        assertEquals(player1, instance.get(2, 2));
        assertEquals(player2, instance.get(4, 6));
    }

    /**
     * Test of checkMoveIsWin method, of class Board.
     */
    @Test
    public void testWinCenter() {


        System.out.println("checkMoveIsWin");

        int expResult = 1;
        int result = instance.checkMoveIsWin(new Turn(9, 9, player1));
        assertEquals(expResult, result);
    }

    @Test
    public void TestWinNorth() {
        int expResult = 1;
        int result;
        result = instance.checkMoveIsWin(new Turn(9, 8, player1));
        assertEquals(expResult, result);
    }

    @Test
    public void TestWinSE() {
        int expResult = 1;
        int result;
        result = instance.checkMoveIsWin(new Turn(11, 11, player1));
        assertEquals(expResult, result);
    }

    @Test
    public void TestWinNW() {
        int expResult = 1;
        int result;
        result = instance.checkMoveIsWin(new Turn(9, 7, player1));
        assertEquals(expResult, result);
    }

    @Test
    public void TestNoWinRandom() {
        int expResult = 0;
        int result;

        result = instance.checkMoveIsWin(new Turn(8, 13, player1));
        assertEquals(expResult, result);

        result = instance.checkMoveIsWin(new Turn(16, 16, player1));
        assertEquals(expResult, result);
    }

    @Test
    public void TestNoWinVertex() {
        int expResult = 0;
        int result;

        result = instance.checkMoveIsWin(new Turn(0, 0, player1));
        assertEquals(expResult, result);

        result = instance.checkMoveIsWin(new Turn(19, 19, player1));
        assertEquals(expResult, result);
    }

    @Test
    public void TestNoWinEdge() {
        int result;
        int expResult = 0;

        result = instance.checkMoveIsWin(new Turn(0, 6, player1));
        assertEquals(expResult, result);
    }
}
