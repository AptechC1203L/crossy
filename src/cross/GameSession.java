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
    private List<Player> playerList;
    private Board board;
    private boolean gameStillRunning;

    public GameSession(List<Player> playerList) {
        this.playerList = playerList;
    }
    
    // Main game loop
    public void takeTurn() {
        while (gameStillRunning) {
            for (Player player : playerList) {
                move = player.makeAMove()
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

    private void updateBoard() {
        // For now just print the board out onto the console
        // TODO Update GUI or something
        for (int i = 0; i < board.getHeight(); i++) {
            for (int k = 0; k < board.getWidth(); k++)
                System.out.print(board.get(i, k));
            System.out.println("");
        }
    }

    private void endGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
