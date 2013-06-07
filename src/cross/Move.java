/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cross;

/**
 *
 * @author chin
 */
public class Move {

    public int row;
    public int col;
    public Player player;

    public Move(int row, int column, Player player) {
        this.row = row;
        this.col = column;
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return col;
    }

    public Player getPlayer() {
        return player;
    }
}
