/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @author chin
 */
public class Player {

    protected String name;
    protected BlockingQueue<Move> ourNextMove;

    public Player(String name, BlockingQueue<Move> ourNextMove) {
        this.name = name;
        this.ourNextMove = ourNextMove;
        System.out.println(ourNextMove.hashCode());
    }
    
    public Player(String name) {
        this.name = name;
    }

    public Move makeAMove() throws InterruptedException {
        System.out.println(this.ourNextMove);
        return ourNextMove.take();
    }

    public char getSignature() {
        return this.name.charAt(0);
    }

    public String getName() {
        return this.name;
    }

    void signalGameEnded(int status, Player player) {
    }
}
