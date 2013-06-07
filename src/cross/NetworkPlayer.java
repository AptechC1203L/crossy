/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cross;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trungnd_b01414
 */
public class NetworkPlayer extends Player {

    private final ServerSocket serverSocket;
    private final PrintWriter outStream;
    private final BufferedReader inStream;

    public NetworkPlayer(char signature) throws IOException {
        super(signature, "No Name");

        serverSocket = new ServerSocket(1337);
        Socket conn = serverSocket.accept();

        outStream = new PrintWriter(conn.getOutputStream());
        inStream = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        // Negotiation
        this.setName(conn.getInetAddress().toString());
    }

    @Override
    public Move makeAMove() {
        // Send make a move request over network then wait
        System.out.println("Sending player command");
        this.outStream.write("YOUR-TURN\n");
        String result;
        try {
            result = this.inStream.readLine();
            String[] cmdTokens = result.split(" ");
            if (cmdTokens[0].equals("PLAY")) {
                return new Move(Integer.parseInt(cmdTokens[1]),
                        Integer.parseInt(cmdTokens[2]), this);
            } else {
                // TODO some error handling here. perhaps a resend would be
                // appropriate?
            }
        } catch (IOException ex) {
            Logger.getLogger(NetworkPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    void signalGameEnded(int status, Player p) {
        this.outStream.write("END " + p.getName());
    }
}
