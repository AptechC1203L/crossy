/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cross;

import java.util.List;

/**
 *
 * @author chin
 */
public class GameSession {
    private List<Turn> turns;
    private Board board;

    public GameSession() {
    }
    
    /*
     * Contract:
     * x <= board.x
     * y <= board.y
     * 
     * Out:
     * Turn is propagated over network, turn is manifested in user's view
     * */
    public void takeTurn(int x, int y) {
    }
}
