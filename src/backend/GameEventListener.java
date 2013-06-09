/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author chin
 */
public interface GameEventListener {

    public void onMoveMade(Move move);

    public void onGameEnd(int result, Object arg);
}
