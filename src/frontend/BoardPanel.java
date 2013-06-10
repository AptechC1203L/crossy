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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author thinhpham
 */
public class BoardPanel extends JPanel implements ActionListener {

    private Timer timer;
    private Cell cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9;
    private Image imgX;
    private Image imgO;
    private ArrayList<Cell> listX;
    private ArrayList<Cell> listO;
    private BoardModel board;

    public BoardPanel(BoardModel board) {
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

        ImageIcon iix = new ImageIcon("img/x.png");
        imgX = iix.getImage();
        ImageIcon iio = new ImageIcon("img/o.png");
        imgO = iio.getImage();
        
        this.board = board;
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

    public void makeAMove(Move move) {
        int col = move.getColumn();
        int row = move.getRow();
        Player player = move.getPlayer();
        String playerName = player.getName();
        Cell cell = getCell(row, col);
        if (playerName.equals("X") && !listX.contains(cell)) {
            cell.setValue('x');
            listX.add(cell);
        } else if (playerName.equals("O") && !listO.contains(cell)){
            cell.setValue('o');
            listO.add(cell);
        }
    }
}
