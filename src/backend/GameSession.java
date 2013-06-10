/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author chin
 */
public class GameSession {

    private List<Player> playerList;
    private List<GameEventListener> gameEventListeners;
    private BoardModel board;
    private boolean gameStillRunning;
    private int winLen;

    public GameSession(List<Player> playerList, BoardModel board, int winLen) {
        // TODO check that winLen <= boardSize
        this.playerList = playerList;
        this.winLen = winLen;
        this.board = board;
        this.gameStillRunning = false;
        this.gameEventListeners = new ArrayList<>();
    }

    // Main game loop
    public void takeTurn(AtomicReference<Player> whoseTurn) throws InterruptedException {
        this.gameStillRunning = true;
        while (this.gameStillRunning) {
            for (int i = 0; i < this.playerList.size(); i++) {
                Player player = this.playerList.get(i);
                whoseTurn.set(player);
                
                System.out.println(whoseTurn.get().getName());
                
                Move move = player.makeAMove();
                if (this.board.makeMove(move) == false) {
                    System.out.println("Wrong move!");
                    // Give them another chance
                    i--;
                    continue;
                } else {
                    // Signal the "move-made" event
                    this.onMoveMade(move);
                    int result = this.checkMoveIsWin(move);
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

    public BoardModel getBoard() {
        return this.board;
    }

    // TODO This should also return a list of winning moves to help visualize
    // them.
    /**
     * This method checks whether a move is a winning move. It should be called
     * after each move is made.
     *
     * @param move
     * @param this.winLen the Number of consecutive move to win
     * @return 0 if no one wins, 1 if the move is a winning one, -1 if it's a
     * draw.
     *
     */
    public int checkMoveIsWin(Move move) {
        Player p = move.getPlayer();
        int row = move.getRow();
        int column = move.getColumn();


        // This implements the idea portrayed here:
        // http://stackoverflow.com/questions/2670217/detect-winning-game-in-nought-and-crosses

        // Check horizontal
        boolean win;
        for (int i = 0; i < this.winLen; i++) {
            win = true;
            for (int k = 0; k < this.winLen; k++) {
                try {
                    if (this.board.get(row, column - i + k) != p) {
                        win = false;
                        break;
                    }
                } catch (Exception e) {
                    win = false;
                    break;
                }
            }
            if (win == true) {
                return 1;
            }
        }

        // Check vertical
        for (int i = 0; i < this.winLen; i++) {
            win = true;
            for (int k = 0; k < this.winLen; k++) {
                try {
                    if (this.board.get(row - i + k, column) != p) {
                        win = false;
                        break;
                    }
                } catch (Exception e) {
                    win = false;
                    break;
                }
            }
            if (win == true) {
                return 1;
            }
        }

        // Check reversed diagonal (NE -> SW)
        for (int i = 0; i < this.winLen; i++) {
            win = true;
            for (int k = 0; k < this.winLen; k++) {
                try {
                    if (this.board.get(row - i + k, column + i - k) != p) {
                        win = false;
                        break;
                    }
                } catch (Exception e) {
                    win = false;
                    break;
                }
            }
            if (win == true) {
                return 1;
            }
        }

        // Check diagonal (NW -> SE)
        for (int i = 0; i < this.winLen; i++) {
            win = true;
            for (int k = 0; k < this.winLen; k++) {
                try {
                    if (this.board.get(row - i + k, column - i + k) != p) {
                        win = false;
                        break;
                    }
                } catch (Exception e) {
                    win = false;
                    break;
                }
            }
            if (win == true) {
                return 1;
            }
        }

        return 0;
    }
}
