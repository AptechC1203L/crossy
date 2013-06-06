/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cross;

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

    private GameSession gameSession;

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
        Player player1 = new Player('X', "Chin");
        Player player2 = new Player('O', "Kin");

        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);

        gameSession = new GameSession(playerList, 9, 5);
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
}
