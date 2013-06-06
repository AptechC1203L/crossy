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

    private char signature;
    private String name;

    public Player(char signature, String name) {
        this.signature = signature;
        this.name = name;
    }

    public Turn makeAMove() {
        // TODO randomize or ask user
        System.out.println("Hey, make a move, " + this.name + ":");
        Scanner s = new Scanner(System.in);
        int x = s.nextInt();
        int y = s.nextInt();
        Turn t = new Turn(x, y, this);
        return t;
    }

    public char getSignature() {
        return this.signature;
    }

    public String getName() {
        return this.name;
    }
}
