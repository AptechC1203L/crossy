/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cross;

/**
 *
 * @author chin
 */
public class Player {
    public Turn makeAMove() {
        // TODO randomize or ask user
        Turn t = new Turn(3, 4, null);
        return t;
    }
}
