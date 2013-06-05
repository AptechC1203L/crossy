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

    public Player get(int x, int y) {
        return board[x][y];
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

    public int makeMove(int x, int y, Player p) {
        if (x < this.getWidth() && y < this.getHeight()
                && x >= 0 && y >= 0
                && board[y][x] == null) {
            this.board[y][x] = p;
            return 0;
        } else {
            return -1;
        }
    }
}
