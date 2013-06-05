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
    
    // Main game loop
    public void takeTurn() {
        while (gameStillRunning) {
            for (player : playerList) {
                move = askForMove(player);
                updateBoard();
                if (checkWiningCondition(move, board)) {
                    // Tell everybody that the game ended
                    // If the game is a local game then display a dialog or a signal
                    endGame();
                    gameStillRunning = false;
                }
                // Next player
            }
        }
    }
}
