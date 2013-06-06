/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cross;

/**
 *
 * @author chin
 */
public class Turn {

    public int row;
    public int y;
    public Player player;

    public Turn(int row, int column, Player player) {
        this.row = row;
        this.y = column;
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return y;
    }

    public Player getPlayer() {
        return player;
    }
}
