/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chin
 */
public class CrossClient implements GameEventListener {

    private final ArrayBlockingQueue<Move> ourNextmove;

    public static void main(String[] args) {
        try {
            CrossClient crossClient = new CrossClient();
        } catch (IOException ex) {
            System.out.println("Cannot read");
        } catch (InterruptedException ex) {
            System.out.println("Interrupted");
        }
    }
    private final GameClient gameClient;
    private final Board board;
    private final Player serverPlayer;
    private final AtomicBoolean isOurTurn;
    private boolean running;

    public CrossClient() throws IOException, InterruptedException {
        final Player chin = new Player("Chin_Client");

        ourNextmove = new ArrayBlockingQueue<>(1);
        isOurTurn = new AtomicBoolean(false);
        running = true;

        gameClient = new GameClient(chin, ourNextmove);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Very primitive, we don't even check for our turn here
                Scanner s = new Scanner(System.in);
                while (running) {
                    int row = s.nextInt();
                    int col = s.nextInt();
                    if (gameClient.getWhoseTurn().get() == chin) {
                        try {
                            Move move = new Move(row, col, chin);
                            ourNextmove.put(move);
                            board.makeMove(move);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(CrossClient.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }).start();


        gameClient.addGameEventListener(this);
        gameClient.connect();

        serverPlayer = gameClient.waitForServerPlayer();
        board = gameClient.waitForBoardInfo();

        System.out.format("Server name: %s\nBoard %dx%d\n",
                serverPlayer.getName(),
                board.getHeight(),
                board.getWidth());
        // Maybe we'll wait here a bit before sending the start signal. The other
        // end will have to wait for us.

        gameClient.startGame();
        running = false;
    }

    @Override
    public void onMoveMade(Move move) {
        char symbol;
        Player p;

        System.out.format("Player %s made a move: %d %d\n",
                move.getPlayer().getName(),
                move.getRow(),
                move.getColumn());

        board.makeMove(move);

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
        } else if (result == -1) {
            System.out.println("It's a draw...");
        }
    }
}
