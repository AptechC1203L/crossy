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
    public int x;
    public int y;
    public String player;

    public Turn(int x, int y, String player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPlayer() {
        return player;
    }
}
