/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author chin
 */
public class Cross implements GameEventListener {

    /**
     * @param args the command line arguments
     */
    private Board board;

    public static void main(String[] args) throws IOException {
//        Player player1 = new Player('X', "Chin");
        NetworkPlayer player1 = new NetworkPlayer();
        Player player2 = new Player("X");

        Board board = new Board(9, 9);

        Cross c = new Cross();
        c.board = board;

        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);

        System.out.println("Waiting for remote connection...");
        player1.waitForConnection();
        System.out.format("Player %s at %s connected!\n", player1.getName(), player1.getAddress().toString());

        player1.sendServerName(player2.getName());
        player1.sendBoardInfo(board);

        player1.waitForClientToStart();
        System.out.println("Game started!");

        GameSession gameSession = new GameSession(playerList, board, 2);

        gameSession.addGameEventListener(c);
        gameSession.addGameEventListener(player1);

        gameSession.takeTurn();
    }

    @Override
    public void onMoveMade(Move move) {
        // For now just print the board out onto the console
        // TODO Update GUI or something
        char symbol;
        Player p;
        for (int i = 0; i < board.getHeight(); i++) {
            for (int k = 0; k < board.getWidth(); k++) {
                if ((p = board.get(i, k)) != null) {
                    symbol = p.getSignature();
                } else {
                    symbol = '-';
                }
                System.out.print(symbol + " ");
            }
            System.out.println("");
        }
    }

    @Override
    public void onGameEnd(int result, Object arg) {
        if (result == 1) {
            System.out.println("Game ended, player " + ((Player) arg).getName() + " won!");
        } else if (result == 0) {
            System.out.println("It's a draw...");
        }
    }
}
