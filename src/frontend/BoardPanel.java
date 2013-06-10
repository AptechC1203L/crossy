/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.BoardModel;
import backend.Move;
import backend.Player;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author thinhpham
 */
public class BoardPanel extends JPanel implements ActionListener {

    private Timer timer;
    private Image imgX;
    private Image imgO;
    private BoardModel board;

    public BoardPanel(BoardModel board) {
        ImageIcon iix = new ImageIcon(BoardPanel.class.getResource("/img/x.png"));
        imgX = iix.getImage();
        ImageIcon iio = new ImageIcon(BoardPanel.class.getResource("/img/o.png"));
        imgO = iio.getImage();
        
        this.board = board;
        this.setPreferredSize(new Dimension(board.getWidth() * 20, board.getHeight() * 20));
    }

    public void startTimer() {
        timer = new Timer(17, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        render(g);
    }

    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(1F));
        
        int width = 20;
        
        for (int i = 0; i < this.board.getWidth(); i++) {
            // Draw horizontal lines
            g2.drawLine(0, width * (i + 1), width * this.board.getWidth(), width * (i + 1));
        }
        
        for (int i = 0; i < this.board.getHeight(); i++) {
            // Draw vertical lines
            g2.drawLine(width * (i + 1), 0, width * (i + 1), width * this.board.getWidth());
        }
        
        for (int row = 0; row < this.board.getHeight(); row++) {
            for (int col = 0; col < this.board.getWidth(); col++) {
                // Draw the pieces
                Player player = this.board.get(row, col);
                if (player != null) {
                    char signature = player.getSignature();
                    Image image;
                    if (signature == 'X') {
                        image = imgX;
                    } else {
                        image = imgO;
                    }
                    g2.drawImage(image, width * col, width * row, this);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        repaint();
    }

    public boolean makeAMove(Move move) {
        return this.board.makeMove(move);
    }
}
