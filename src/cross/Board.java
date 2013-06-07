/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cross;

/**
 *
 * @author chin
 */
public class Board {

    private Player[][] board;

    public Board(int width, int height) {
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

    public int makeMove(Move move) {
        int row = move.getRow();
        int col = move.getColumn();
        Player p = move.getPlayer();

        if (col < this.getWidth() && row < this.getHeight()
                && row >= 0 && col >= 0
                && board[row][col] == null) {
            this.board[row][col] = p;
            return 0;
        } else {
            return -1;
        }
    }
}
