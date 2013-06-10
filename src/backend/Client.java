/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author trungnd_b01414
 */
public class Client {

    private PrintWriter outStream;
    private BufferedReader inStream;
    private List<GameEventListener> gameEventListeners;
    private BlockingQueue<Move> ourNextMove;
    private Player ourPlayer;
    private Player remotePlayer;
    private AtomicReference<Player> whoseTurn;
    private BoardModel board;

    public Client(Player ourPlayer, BlockingQueue<Move> ourNextMove, AtomicReference<Player> whoseTurn) {
        this.gameEventListeners = new ArrayList<>();
        this.ourPlayer = ourPlayer;
        this.ourNextMove = ourNextMove;
        this.whoseTurn = whoseTurn;
    }

    public void connect() throws IOException {
        // TODO autodiscovery
        Socket conn = new Socket();
        conn.connect(new InetSocketAddress("localhost", 1337));

        outStream = new PrintWriter(conn.getOutputStream());
        inStream = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    }

    public Player waitForServerPlayer() throws IOException {
        // TODO Send server our player info
        this.send("JOIN " + this.ourPlayer.getName() + " " + this.ourPlayer.getSignature());
        while (true) {
            String[] tokens = this.inStream.readLine().split(" ");
            if (tokens[0].equals("JOIN")) {
                remotePlayer = new Player(tokens[1]);
                return remotePlayer;
            }
        }
    }

    public BoardModel waitForBoardInfo() throws IOException {
        while (true) {
            String[] tokens = this.inStream.readLine().split(" ");
            if (tokens[0].equals("BOARD")) {
                int width = Integer.parseInt(tokens[1]);
                int height = Integer.parseInt(tokens[2]);
                board = new BoardModel(width, height);
                return board;
            }
        }
    }

    public void startGame() throws IOException, InterruptedException {
        this.send("START");

        while (true) {
            String msg = inStream.readLine();
            System.out.println(msg);
            String[] tokens = msg.split(" ");
            switch (tokens[0]) {
                case "GAME-START":
                    break;
                case "QUIT":
                    this.onPlayerDisconnected();
                    return;
                case "PLAY":
                    // TODO Null check
                    String player = tokens[1];
                    if (player.equals(this.ourPlayer.getName())) {
                        break;
                    }
                    int row = Integer.parseInt(tokens[2]);
                    int col = Integer.parseInt(tokens[3]);
                    this.onMoveMade(new Move(row, col, new Player(player)));
                    break;
                case "YOUR-TURN":
                    onOurTurn();
                    break;
                case "GAME-END":
                    int result = Integer.parseInt(tokens[1]);
                    if (result == 1) {
                        this.onGameEnd(result, new Player(tokens[2]));
                    } else if (result == -1) {
                        this.onGameEnd(result, null);
                    }
                    return;
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

    private void onOurTurn() throws InterruptedException {
        this.whoseTurn.set(ourPlayer);
        Move move = this.ourNextMove.take();
        this.send("PLAY " + move.getRow() + " " + move.getColumn());
        board.makeMove(move);
        this.whoseTurn.set(remotePlayer);
    }

    public void addGameEventListener(GameEventListener listener) {
        this.gameEventListeners.add(listener);
    }

    private void send(String msg) {
        this.outStream.write(msg + "\n");
        this.outStream.flush();
    }
}
