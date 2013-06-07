/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cross;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chin
 */
public class GameSession {

    private List<Player> playerList;
    private List<GameEventListener> gameEventListeners;
    private Board board;
    private boolean gameStillRunning;

    public GameSession(List<Player> playerList, int boardSize, int winLen) {
        // TODO check that winLen <= boardSize
        this.playerList = playerList;
        this.board = new Board(boardSize, boardSize, winLen);
        this.gameStillRunning = false;
        this.gameEventListeners = new ArrayList<>();
    }

    // Main game loop
    public void takeTurn() {
        this.gameStillRunning = true;
        while (this.gameStillRunning) {
            for (int i = 0; i < this.playerList.size(); i++) {
                Player player = this.playerList.get(i);
                Move move = player.makeAMove();
                if (this.board.makeMove(move) == -1) {
                    System.out.println("Wrong move!");
                    // Give them another chance
                    i--;
                    continue;
                } else {
                    // Signal the "move-made" event
                    this.onMoveMade(move);
                    int result = this.board.checkMoveIsWin(move);
                    if (result == 1 || result == -1) {
                        // Tell everybody that the game ended
                        this.onGameEnd(result, player);
                        this.gameStillRunning = false;
                        break;
                    }
                }
            }
        }
    }

    private void onGameEnd(int result, Player p) {
        for (GameEventListener l : this.gameEventListeners) {
            l.onGameEnd(result, p);
        }
    }

    private void onMoveMade(Move move) {
        for (GameEventListener l : this.gameEventListeners) {
            l.onMoveMade(move);
        }
    }

    public void addGameEventListener(GameEventListener listener) {
        this.gameEventListeners.add(listener);
    }

    public Board getBoard() {
        return this.board;
    }
}
