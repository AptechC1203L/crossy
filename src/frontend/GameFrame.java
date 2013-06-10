/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.BoardModel;
import backend.Client;
import backend.GameEventListener;
import backend.GameSession;
import backend.Move;
import backend.Player;
import backend.ServerPlayer;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;

/**
 *
 * @author thinhpham
 */
public class GameFrame extends JFrame implements GameEventListener {

    private BlockingQueue<Move> nextMove; // Shared variable
    private AtomicReference<Player> whoseTurn; // Shared variable
    private Player remotePlayer;
    private Player localPlayer;
    private BoardModel boardModel;
    private BoardPanel boardPanel;

    /**
     * @param args the command line arguments
     */
    public GameFrame() throws IOException, InterruptedException {
        super("Tic Tac Toe");

        this.nextMove = new ArrayBlockingQueue<>(1);
        this.whoseTurn = new AtomicReference<>();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setSize(300, 320);
//        this.setResizable(false);
        this.setVisible(true);

        Object[] options = {"Host a game",
            "Join an existing game"};

        final int n = JOptionPane.showOptionDialog(this,
                "Would you like some green eggs to go "
                + "with that ham?",
                "A Silly Question",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);


        if (n == 0) {
            startServer();
        } else if (n == 1) {
            startClient();
        }
    }

    /**
     * This can only be run after a this.boardModel is initialized
     */
    public void initBoardPanel() {
        boardPanel = new BoardPanel(this.boardModel);
        this.add(boardPanel, BorderLayout.CENTER);
        this.pack();
        this.revalidate();

        boardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println("x:" + me.getX() + " y:" + me.getY());
                int x = me.getX();
                int y = me.getY();

                // TODO Refactor into a method of BoardPanel
                int col = x / 20;
                int row = y / 20;

                if (whoseTurn.get() == localPlayer) {
                    Move move = new Move(row, col, localPlayer);
                    if (boardModel.checkMoveEligible(move) == true) {
                        try {
                            nextMove.put(move);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("Move " + row + " " + col);
                    }
                }
            }
        });

        boardPanel.startTimer();
    }

    public void startClient() throws IOException, InterruptedException {
        localPlayer = new Player("O", this.nextMove);
        final Client gameClient = new Client(localPlayer, nextMove, this.whoseTurn);

        gameClient.addGameEventListener(this);
        gameClient.connect();

        remotePlayer = gameClient.waitForServerPlayer();
        boardModel = gameClient.waitForBoardInfo();

        this.initBoardPanel();

        System.out.format("Server name: %s\nBoard %dx%d\n",
                remotePlayer.getName(),
                boardModel.getHeight(),
                boardModel.getWidth());
        // Maybe we'll wait here a bit before sending the start signal. The other
        // end will have to wait for us.

        gameClient.startGame();
    }

    public void startServer() throws IOException, InterruptedException {
        this.remotePlayer = new ServerPlayer();
        localPlayer = new Player("X", this.nextMove);

        ServerPlayer _remotePlayer = (ServerPlayer) this.remotePlayer;

        // TODO Ask the user for the dimensions
        boardModel = new backend.BoardModel(20, 20);
        this.initBoardPanel();

        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(_remotePlayer);
        playerList.add(localPlayer);

        System.out.println("Waiting for remote connection...");
        _remotePlayer.waitForConnection();
        System.out.format("Player %s at %s connected!\n", _remotePlayer.getName(), _remotePlayer.getAddress().toString());

        _remotePlayer.sendServerName(localPlayer.getName());
        _remotePlayer.sendBoardInfo(boardModel);

        _remotePlayer.waitForClientToStart();
        System.out.println("Game started!");

        final GameSession gameSession = new GameSession(playerList, boardModel, 5);

        gameSession.addGameEventListener(_remotePlayer);
        gameSession.addGameEventListener(this);

        gameSession.takeTurn(whoseTurn);
    }

    @Override
    public void onMoveMade(Move move) {
        this.boardPanel.makeAMove(move);
    }

    @Override
    public void onGameEnd(int result, Object arg) {
        Player p = (Player) arg;
        JOptionPane.showMessageDialog(this, "Player " + p.getName() + " has won!");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO code application logic here
        GameFrame gameFrame = new GameFrame();
    }
}
