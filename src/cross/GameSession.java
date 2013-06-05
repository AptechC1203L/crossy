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

    private List<Turn> turns;
    private List<Player> playerList;
    private Board board;
    private boolean gameStillRunning;

    public GameSession(List<Player> playerList, int boardSize) {
        this.playerList = playerList;
        this.board = new Board(boardSize, boardSize);
        this.gameStillRunning = false;
        this.turns = new ArrayList<>();
    }

    // Main game loop
    public void takeTurn() {
        this.gameStillRunning = true;
        while (this.gameStillRunning) {
            for (int i = 0; i < this.playerList.size(); i++) {
                Player player = this.playerList.get(i);
                Turn move = player.makeAMove();
                this.turns.add(move);
                if (this.board.makeMove(move.x, move.y, player) == -1) {
                    System.out.println("Wrong move!");
                    // Give them another chance
                    i--;
                    continue;
                } else {
                    updateBoard();
                    if (this.checkWiningCondition(move)) {
                        // Tell everybody that the game ended
                        // If the game is a local game then display a dialog or a
                        // signal
                        this.endGame();
                        this.gameStillRunning = false;
                    }
                    // Next player
                }
            }
        }
    }

    private void updateBoard() {
        // For now just print the board out onto the console
        // TODO Update GUI or something
        char symbol;
        Player p;
        for (int i = 0; i < board.getHeight(); i++) {
            for (int k = 0; k < board.getWidth(); k++) {
                if ((p = board.get(i, k)) != null) {
                    symbol = p.getSignature();
                } else {
                    symbol = '-';
                }
                System.out.print(symbol + " ");
            }
            System.out.println("");
        }
    }

    private void endGame() {
        System.out.println("Hey, game ended!");
    }

    private boolean checkWiningCondition(Turn move) {
        for (int i = 0; i < board.getHeight(); i++) {
            for (int k = 0; k < board.getWidth(); k++) {
                board.get(i, k);
            }
        }
        return false;
    }
}
