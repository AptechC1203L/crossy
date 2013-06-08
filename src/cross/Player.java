/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cross;

import java.util.Scanner;

/**
 *
 * @author chin
 */
public class Player {

    protected String name;

    public Player(String name) {
        this.name = name;
    }

    public Move makeAMove() {
        // TODO randomize or ask user
        System.out.println("Hey, make a move, " + this.name + ":");
        Scanner s = new Scanner(System.in);
        int x = s.nextInt();
        int y = s.nextInt();
        Move t = new Move(x, y, this);
        return t;
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
