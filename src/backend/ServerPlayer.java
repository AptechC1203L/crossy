/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trungnd_b01414
 */
public class ServerPlayer extends Player implements GameEventListener {

    private final ServerSocket serverSocket;
    private PrintWriter outStream;
    private BufferedReader inStream;
    private Socket conn;

    public ServerPlayer() throws IOException {
        super("No Name");
        serverSocket = new ServerSocket(1337);
    }

    public void waitForConnection() throws IOException {

        conn = serverSocket.accept();

        outStream = new PrintWriter(conn.getOutputStream());
        inStream = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        // Negotiation
        while (true) {
            String[] msg = inStream.readLine().split(" ");
            if (msg[0].equals("JOIN")) {
                this.name = msg[1];
                break;
            }
        }
    }

    public void sendServerName(String name) {
        this.send("JOIN " + name);
    }

    public void sendBoardInfo(BoardModel board) {
        this.send("BOARD " + board.getWidth() + " " + board.getHeight());
    }

    public void waitForClientToStart() throws IOException {
        while (true) {
            String msg = inStream.readLine();
            if ("START".equals(msg)) {
                break;
            }
        }
    }

    @Override
    public Move makeAMove() {
        // Send make a move request over network then wait
        this.send("YOUR-TURN");
        String result;
        try {
            result = this.inStream.readLine();
            if (result == null) {
                return null;
            }
            String[] cmdTokens = result.split(" ");
            if (cmdTokens[0].equals("PLAY")) {
                return new Move(Integer.parseInt(cmdTokens[1]),
                        Integer.parseInt(cmdTokens[2]), this);
            } else {
                // TODO some error handling here. perhaps a resend would be
                // appropriate?
                return this.makeAMove();
            }
        } catch (IOException ex) {
        }
        return null;
    }

    public InetAddress getAddress() {
        return this.conn.getInetAddress();
    }

    private void send(String msg) {
        this.outStream.write(msg + "\n");
        this.outStream.flush();
    }

    @Override
    public void onMoveMade(Move move) {
        this.send("PLAY " + move.getPlayer().getName() + " " + move.getRow() + " " + move.getColumn());
    }

    @Override
    public void onGameEnd(int result, Object arg) {
        if (result == 1) {
            this.send("GAME-END " + result + " " + ((Player) arg).getName());
        } else if (result == -1) {
            this.send("GAME-END " + result);
        }
    }
}
