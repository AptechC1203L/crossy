/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author chin
 */
public class BoardModel {

    private Player[][] board;

    public BoardModel(int width, int height) {
        this.board = new Player[height][width];
    }

    public Player get(int row, int col) {
        return board[row][col];
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return board[0].length;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return board.length;
    }

    public boolean makeMove(Move move) {
        if (this.checkMoveEligible(move)) {
            this.board[move.getRow()][move.getColumn()] = move.getPlayer();
            return true;
        }
        return false;
    }
    
    public boolean checkMoveEligible(Move move) {
        int row = move.getRow();
        int col = move.getColumn();
        Player p = move.getPlayer();
        
        if (col < this.getWidth() && row < this.getHeight()
                && row >= 0 && col >= 0
                && board[row][col] == null) {
            return true;
        }
        return false;
    }
    
    public void clear() {
        this.board = new Player[this.board.length][this.board[0].length];
    }
}
