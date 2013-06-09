/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.GameClient;
import backend.GameEventListener;
import backend.Move;
import backend.Player;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author thinhpham
 */
public class Board extends JPanel implements ActionListener, GameEventListener {
    private Timer timer;
    private Cell cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9;
    private Image imgX;
    private Image imgO;
    private boolean turnX;
    private boolean inGame = true;
    private ArrayList<Cell> listX;
    private ArrayList<Cell> listO;
    private ArrayBlockingQueue<Move> nextMove;
    private Player remotePlayer;
    private Player localPlayer;

    GameClient gameClient;
    private backend.Board board;

    public Board() {
        listX = new ArrayList<>();
        listO = new ArrayList<>();
        cell1 = new Cell();
        cell2 = new Cell();
        cell3 = new Cell();
        cell4 = new Cell();
        cell5 = new Cell();
        cell6 = new Cell();
        cell7 = new Cell();
        cell8 = new Cell();
        cell9 = new Cell();

        localPlayer = new Player("O");
        nextMove = new ArrayBlockingQueue<>(1);
        gameClient = new GameClient(localPlayer, nextMove);

        initGame();
        ImageIcon iix = new ImageIcon("img/x.png");
        imgX = iix.getImage();
        ImageIcon iio = new ImageIcon("img/o.png");
        imgO = iio.getImage();

        turnX = true;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println("x:" + me.getX() + " y:" + me.getY());
                int x = me.getX();
                int col = x / 100;
                int y = me.getY();
                int row = y/100;
                
                if(gameClient.getWhoseTurn().get() == localPlayer) {
                   Cell cell = getCell(row, col);
                   if(cell.isEditable()) {
                       cell.setValue('o');
                       nextMove.offer(new Move(row, col, remotePlayer));
                       listX.add(cell);
                   }
                }
            }
        });
    }

    public Cell getCell(int row, int col) {
        if (row == 0 && col == 0) {
            return cell1;
        } else if (row == 0 && col == 1) {
            return cell2;
        } else if (row == 0 && col == 2) {
            return cell3;
        } else if (row == 1 && col == 0) {
            return cell4;
        } else if (row == 1 && col == 1) {
            return cell5;
        } else if (row == 1 && col == 2) {
            return cell6;
        } else if (row == 2 && col == 0) {
            return cell7;
        } else if (row == 2 && col == 1) {
            return cell8;
        }
        return cell9;
    }

    public void initGame() {
        timer = new Timer(17, this);
        timer.start();
    }

    public void run() throws IOException, InterruptedException {
        gameClient.addGameEventListener(this);
        gameClient.connect();

        remotePlayer = gameClient.waitForServerPlayer();
        board = gameClient.waitForBoardInfo();

        System.out.format("Server name: %s\nBoard %dx%d\n",
                remotePlayer.getName(),
                board.getHeight(),
                board.getWidth());
        // Maybe we'll wait here a bit before sending the start signal. The other
        // end will have to wait for us.

        gameClient.startGame();
        inGame = false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if (inGame) {
            render(g);
        }
    }

    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(3F));
        g2.drawLine(100, 0, 100, 300);
        g2.drawLine(200, 0, 200, 300);
        g2.drawLine(0, 100, 300, 100);
        g2.drawLine(0, 200, 300, 200);
        if (cell1.getValue() == 'x') {
            g2.drawImage(imgX, 0, 0, this);
        } else if (cell1.getValue() == 'o') {
            g2.drawImage(imgO, 0, 0, this);
        }

        if (cell2.getValue() == 'x') {
            g2.drawImage(imgX, 100, 0, this);
        } else if (cell2.getValue() == 'o') {
            g2.drawImage(imgO, 100, 0, this);
        }

        if (cell3.getValue() == 'x') {
            g2.drawImage(imgX, 200, 0, this);
        } else if (cell3.getValue() == 'o') {
            g2.drawImage(imgO, 200, 0, this);
        }

        if (cell4.getValue() == 'x') {
            g2.drawImage(imgX, 0, 100, this);
        } else if (cell4.getValue() == 'o') {
            g2.drawImage(imgO, 0, 100, this);
        }

        if (cell5.getValue() == 'x') {
            g2.drawImage(imgX, 100, 100, this);
        } else if (cell5.getValue() == 'o') {
            g2.drawImage(imgO, 100, 100, this);
        }

        if (cell6.getValue() == 'x') {
            g2.drawImage(imgX, 200, 100, this);
        } else if (cell6.getValue() == 'o') {
            g2.drawImage(imgO, 200, 100, this);
        }

        if (cell7.getValue() == 'x') {
            g2.drawImage(imgX, 0, 200, this);
        } else if (cell7.getValue() == 'o') {
            g2.drawImage(imgO, 0, 200, this);
        }

        if (cell8.getValue() == 'x') {
            g2.drawImage(imgX, 100, 200, this);
        } else if (cell8.getValue() == 'o') {
            g2.drawImage(imgO, 100, 200, this);
        }

        if (cell9.getValue() == 'x') {
            g2.drawImage(imgX, 200, 200, this);
        } else if (cell9.getValue() == 'o') {
            g2.drawImage(imgO, 200, 200, this);
        }
    } 
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        repaint();
    }

    @Override
    public void onMoveMade(Move move) {
        int col = move.getColumn();
        int row = move.getRow();
        Player player = move.getPlayer();
        String playerName = player.getName();
        Cell cell = getCell(row, col);
        if(playerName.equals("X") && !listX.contains(cell) && !listO.contains(cell)) {
            cell.setValue('x');
            listX.add(cell);
        } else {
            cell.setValue('o');
            listO.add(cell);
        }
    }

    @Override
    public void onGameEnd(int result, Object arg) {
        inGame = false;
        Player p = (Player) arg;
        JOptionPane.showMessageDialog(this, "Player " + p.getName() + "has won!");
    }
}
