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
    private boolean[][] board;

    public Board(int width, int height) {
        this.board = new boolean[height][width];
    }
    
    public boolean get(int x, int y) {
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

}
