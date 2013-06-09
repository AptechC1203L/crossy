/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.util.ArrayList;
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
public class GameSessionTest {

    private GameSession instance;
    private Player player1;
    private Player player2;

    public GameSessionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        player1 = new Player("Chin");
        player2 = new Player("Kin");

        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);

        instance = new GameSession(playerList, new Board(20, 20), 5);

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
        instance.getBoard().makeMove(new Move(5, 5, player1));
        instance.getBoard().makeMove(new Move(6, 6, player1));
        instance.getBoard().makeMove(new Move(7, 7, player1));
        instance.getBoard().makeMove(new Move(8, 8, player1));
        instance.getBoard().makeMove(new Move(9, 9, player1));
        instance.getBoard().makeMove(new Move(10, 10, player1));
        instance.getBoard().makeMove(new Move(11, 11, player1));
        instance.getBoard().makeMove(new Move(12, 12, player1));
        instance.getBoard().makeMove(new Move(13, 13, player1));

        // NE - SW
        instance.getBoard().makeMove(new Move(5, 13, player1));
        instance.getBoard().makeMove(new Move(6, 12, player1));
        instance.getBoard().makeMove(new Move(7, 11, player1));
        instance.getBoard().makeMove(new Move(8, 10, player1));
        instance.getBoard().makeMove(new Move(9, 9, player1));
        instance.getBoard().makeMove(new Move(10, 8, player1));
        instance.getBoard().makeMove(new Move(11, 7, player1));
        instance.getBoard().makeMove(new Move(12, 6, player1));
        instance.getBoard().makeMove(new Move(13, 5, player1));

        // N - S
        instance.getBoard().makeMove(new Move(5, 9, player1));
        instance.getBoard().makeMove(new Move(6, 9, player1));
        instance.getBoard().makeMove(new Move(7, 9, player1));
        instance.getBoard().makeMove(new Move(8, 9, player1));
        instance.getBoard().makeMove(new Move(9, 9, player1));
        instance.getBoard().makeMove(new Move(10, 9, player1));
        instance.getBoard().makeMove(new Move(11, 9, player1));
        instance.getBoard().makeMove(new Move(12, 9, player1));
        instance.getBoard().makeMove(new Move(13, 9, player1));

        // W - E
        instance.getBoard().makeMove(new Move(9, 5, player1));
        instance.getBoard().makeMove(new Move(9, 6, player1));
        instance.getBoard().makeMove(new Move(9, 7, player1));
        instance.getBoard().makeMove(new Move(9, 8, player1));
        instance.getBoard().makeMove(new Move(9, 9, player1));
        instance.getBoard().makeMove(new Move(9, 10, player1));
        instance.getBoard().makeMove(new Move(9, 11, player1));
        instance.getBoard().makeMove(new Move(9, 12, player1));
        instance.getBoard().makeMove(new Move(9, 13, player1));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of takeTurn method, of class GameSession.
     */
    @Test
    public void testTakeTurn() {
//        System.out.println("takeTurn");
//        GameSession instance = null;
//        instance.takeTurn();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of checkMoveIsWin method, of class Board.
     */
    @Test
    public void testWinCenter() {


        System.out.println("checkMoveIsWin");

        int expResult = 1;
        int result = instance.checkMoveIsWin(new Move(9, 9, player1));
        assertEquals(expResult, result);
    }

    @Test
    public void TestWinNorth() {
        int expResult = 1;
        int result;
        result = instance.checkMoveIsWin(new Move(9, 8, player1));
        assertEquals(expResult, result);
    }

    @Test
    public void TestWinSE() {
        int expResult = 1;
        int result;
        result = instance.checkMoveIsWin(new Move(11, 11, player1));
        assertEquals(expResult, result);
    }

    @Test
    public void TestWinNW() {
        int expResult = 1;
        int result;
        result = instance.checkMoveIsWin(new Move(9, 7, player1));
        assertEquals(expResult, result);
    }

    @Test
    public void TestNoWinRandom() {
        int expResult = 0;
        int result;

        result = instance.checkMoveIsWin(new Move(8, 13, player1));
        assertEquals(expResult, result);

        result = instance.checkMoveIsWin(new Move(16, 16, player1));
        assertEquals(expResult, result);
    }

    @Test
    public void TestNoWinVertex() {
        int expResult = 0;
        int result;

        result = instance.checkMoveIsWin(new Move(0, 0, player1));
        assertEquals(expResult, result);

        result = instance.checkMoveIsWin(new Move(19, 19, player1));
        assertEquals(expResult, result);
    }

    @Test
    public void TestNoWinEdge() {
        int result;
        int expResult = 0;

        result = instance.checkMoveIsWin(new Move(0, 6, player1));
        assertEquals(expResult, result);
    }
}
