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

    public GameSession(List<Player> playerList, int boardSize, int winLen) {
        // TODO check that winLen <= boardSize
        this.playerList = playerList;
        this.board = new Board(boardSize, boardSize, winLen);
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
                if (this.board.makeMove(move) == -1) {
                    System.out.println("Wrong move!");
                    // Give them another chance
                    i--;
                    continue;
                } else {
                    updateBoard();
                    if (this.board.checkMoveIsWin(move) == 1) {
                        // Tell everybody that the game ended
                        // If the game is a local game then display a dialog or a
                        // signal
                        this.endGame(player);
                        this.gameStillRunning = false;
                        break;
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

    private void endGame(Player p) {
        String msg;

        if (p != null) {
            msg = p.getName() + " won!";
        } else {
            msg = "It's a draw...";
        }


        // TODO Make a formal message (over the net)
        System.out.println("Hey, game ended!");
        System.out.println(msg);
        
        for (Player player : playerList) {
            player.signalGameEnded(1, p);
        }
    }
}
