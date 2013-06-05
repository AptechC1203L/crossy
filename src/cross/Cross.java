/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cross;

import java.util.ArrayList;

/**
 *
 * @author chin
 */
public class Cross {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Player player1 = new Player('X', "Chin");
        Player player2 = new Player('O', "Kin");

        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);

        GameSession gameSession = new GameSession(playerList, 9);
        gameSession.takeTurn();
    }
}
