/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

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

    private BoardModel instance;
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
        instance = new BoardModel(20, 20);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of makeMove method, of class Board.
     */
    @Test
    public void testMakeMove() {
        boolean res1 = instance.makeMove(new Move(2, 2, player1));
        boolean res2 = instance.makeMove(new Move(4, 6, player2));

        assertEquals(true, res1);
        assertEquals(true, res2);

        assertEquals(player1, instance.get(2, 2));
        assertEquals(player2, instance.get(4, 6));
    }
}
