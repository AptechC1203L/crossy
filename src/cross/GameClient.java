/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cross;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author trungnd_b01414
 */
public class GameClient {

    private PrintWriter outStream;
    private BufferedReader inStream;
    private boolean gameStarted;
    private List<GameEventListener> gameEventListeners;

    public GameClient() throws IOException {
        this.gameEventListeners = new ArrayList<>();
        this.gameStarted = false;

        // TODO autodiscovery
        Socket conn = new Socket();
        conn.connect(new InetSocketAddress("localhost", 1337));

        outStream = new PrintWriter(conn.getOutputStream());
        inStream = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    }

    public void startGame() throws IOException {
        outStream.write("START");

        while (true) {
            String msg = inStream.readLine();
            String[] tokens = msg.split(" ");
            switch (tokens[0]) {
                case "GAME-START":
                    break;
                case "QUIT":
                    onPlayerDisconnected();
                case "PLAY":
                    String player = tokens[1];
                    int row = Integer.parseInt(tokens[2]);
                    int col = Integer.parseInt(tokens[3]);
                    onMoveMade(new Move(row, col, new Player('X', player)));
                case "YOUR-TURN":
                    onOurTurn();
                case "GAME-END":
                    int result = Integer.parseInt(tokens[1]);
                    this.onGameEnd(result, null);
            }
        }
    }

    private void onPlayerDisconnected() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void onMoveMade(Move move) {
        for (GameEventListener l : this.gameEventListeners) {
            l.onMoveMade(move);
        }
    }

    private void onGameEnd(int result, Object arg) {
        for (GameEventListener l : this.gameEventListeners) {
            l.onGameEnd(result, arg);
        }
    }

    private void onOurTurn() {
        System.out.println("Hey, make a move:");
        Scanner s = new Scanner(System.in);
        int row = s.nextInt();
        int col = s.nextInt();

        this.outStream.write("PLAY " + row + " " + col);
    }

    public void addGameEventListener(GameEventListener listener) {
        this.gameEventListeners.add(listener);
    }
}
