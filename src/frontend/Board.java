/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

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
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author thinhpham
 */
public class Board extends JPanel implements ActionListener, GameEventListener {
    Timer timer;
    Cell cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9;
    Image imgX;
    Image imgO;
    boolean turnX;
    char winner;
    boolean inGame = true;
    ArrayList<Cell> listX;
    ArrayList<Cell> listO;
    
    public Board() {
        listX = new ArrayList<Cell>();
        listO = new ArrayList<Cell>();
        cell1 = new Cell();
        cell2 = new Cell();
        cell3 = new Cell();
        cell4 = new Cell();
        cell5 = new Cell();
        cell6 = new Cell();
        cell7 = new Cell();
        cell8 = new Cell();
        cell9 = new Cell();
        
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
                int col = x/100;
                int y = me.getY();
                int row = y/100;
                if(turnX == true) {
                    if(row == 0 && col == 0 && cell1.isEditable()) {
                        cell1.setValue('x');
                        listX.add(cell1);
                        turnX = false;
                    } else if (col == 1 && row == 0 && cell2.isEditable()) {
                        cell2.setValue('x');
                        listX.add(cell2);
                        turnX = false;
                    } else if (col == 2 && row == 0 && cell3.isEditable()) {
                        cell3.setValue('x');
                        listX.add(cell3);
                        turnX = false;
                    } else if (col == 0 && row == 1 && cell4.isEditable()) {
                        cell4.setValue('x');
                        listX.add(cell4);
                        turnX = false;
                    } else if (col == 1 && row == 1 && cell5.isEditable()) {
                        cell5.setValue('x');
                        listX.add(cell5);
                        turnX = false;
                    } else if (x > 200 && x < 300 && y < 200 && y > 100 && cell6.isEditable()) {
                        cell6.setValue('x');
                        listX.add(cell6);
                        turnX = false;
                    } else if (x < 100 && y < 300 && y > 200 && cell7.isEditable()) {
                        cell7.setValue('x');
                        listX.add(cell7);
                        turnX = false;
                    } else if (x > 100 && x < 200 && y < 300 && y > 200 && cell8.isEditable()) {
                        cell8.setValue('x');
                        listX.add(cell8);
                        turnX = false;
                    } else if (x > 200 && x < 300 && y < 300 && y > 200 && cell9.isEditable()) {
                        cell9.setValue('x');
                        listX.add(cell9);
                        turnX = false;
                    }
                    
                } else {
                    if(x < 100 && y < 100 && cell1.isEditable()) {
                        cell1.setValue('o');
                        listO.add(cell1);
                        turnX = true;
                    } else if (x > 100 && x < 200 && y < 100 && cell2.isEditable()) {
                        cell2.setValue('o');
                        listO.add(cell2);
                        turnX = true;
                    } else if (x > 200 && x < 300 && y < 100 && cell3.isEditable()) {
                        cell3.setValue('o');
                        listO.add(cell3);
                        turnX = true;
                    } else if (x < 100 && y < 200 && y > 100 && cell4.isEditable()) {
                        cell4.setValue('o');
                        listO.add(cell4);
                        turnX = true;
                    } else if (x > 100 && x < 200 && y < 200 && y > 100 && cell5.isEditable()) {
                        cell5.setValue('o');
                        listO.add(cell5);
                        turnX = true;
                    } else if (x > 200 && x < 300 && y < 200 && y > 100 && cell6.isEditable()) {
                        cell6.setValue('o');
                        listO.add(cell6);
                        turnX = true;
                    } else if (x < 100 && y < 300 && y > 200 && cell7.isEditable()) {
                        cell7.setValue('o');
                        listO.add(cell7);
                        turnX = true;
                    } else if (x > 100 && x < 200 && y < 300 && y > 200 && cell8.isEditable()) {
                        cell8.setValue('o');
                        listO.add(cell8);
                        turnX = true;
                    } else if (x > 200 && x < 300 && y < 300 && y > 200 && cell9.isEditable()) {
                        cell9.setValue('o');
                        listO.add(cell9);
                        turnX = true;
                    }
                }
            }
            
        });
    }
    
    public Cell getCell(int row, int col) {
        if(row == 0 && col == 0) {
            return cell1;
        } else if(row == 0 && col == 1) {
            return cell2;
        } else if(row == 0 && col == 2) {
            return cell3;
        } else if(row == 1 && col == 0) {
            return cell4;
        } else if(row == 1 && col == 1) {
            return cell5;
        } else if(row == 1 && col == 2) {
            return cell6;
        } else if(row == 2 && col == 0) {
            return cell7;
        } else if(row == 2 && col == 1) {
            return cell8;
        }
        return cell9;
    }
    
    public void initGame() {
        timer = new Timer(17, this);
        timer.start();
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if(inGame) {
            render(g);
        } else {
            gameOver(g);
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
        if(cell1.getValue() == 'x') {
            g2.drawImage(imgX, 0, 0, this);
        } else if (cell1.getValue() == 'o') {
            g2.drawImage(imgO, 0, 0, this);
        }

        if(cell2.getValue() == 'x') {
            g2.drawImage(imgX, 100, 0, this);
        } else if (cell2.getValue() == 'o') {
            g2.drawImage(imgO, 100, 0, this);
        }

        if(cell3.getValue() == 'x') {
            g2.drawImage(imgX, 200, 0, this);
        } else if (cell3.getValue() == 'o') {
            g2.drawImage(imgO, 200, 0, this);
        }

        if(cell4.getValue() == 'x') {
            g2.drawImage(imgX, 0, 100, this);
        } else if (cell4.getValue() == 'o') {
            g2.drawImage(imgO, 0, 100, this);
        }

        if(cell5.getValue() == 'x') {
            g2.drawImage(imgX, 100, 100, this);
        } else if (cell5.getValue() == 'o') {
            g2.drawImage(imgO, 100, 100, this);
        }

        if(cell6.getValue() == 'x') {
            g2.drawImage(imgX, 200, 100, this);
        } else if (cell6.getValue() == 'o') {
            g2.drawImage(imgO, 200, 100, this);
        }

        if(cell7.getValue() == 'x') {
            g2.drawImage(imgX, 0, 200, this);
        } else if (cell7.getValue() == 'o') {
            g2.drawImage(imgO, 0, 200, this);
        }

        if(cell8.getValue() == 'x') {
            g2.drawImage(imgX, 100, 200, this);
        } else if (cell8.getValue() == 'o') {
            g2.drawImage(imgO, 100, 200, this);
        }

        if(cell9.getValue() == 'x') {
            g2.drawImage(imgX, 200, 200, this);
        } else if (cell9.getValue() == 'o') {
            g2.drawImage(imgO, 200, 200, this);
        }
    } 
    
    public void gameOver(Graphics g) {
        render(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(new Color(0, 0, 0, 128));
        g2.setColor(new Color(0, 0, 0, 128));
        g2.setComposite(AlphaComposite.SrcOver.derive(0.8f));
        g2.fillRoundRect(50, 50, 200, 200, 10, 10);
        g2.setComposite(AlphaComposite.SrcOver.derive(1.0f));
        g2.setColor(Color.WHITE);
        g2.setPaint(Color.WHITE);
        
        g2.setFont(new Font("Lucida Sans", Font.BOLD, 30));
        g2.drawString(winner + " win!", 100, 150);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {        
        repaint();
        if(listX.contains(cell1) && listX.contains(cell2) && listX.contains(cell3)) {
            inGame = false;
            winner = 'x';
        }
    }

    @Override
    public void onMoveMade(Move move) {
        int col = move.getColumn();
        int row = move.getRow();
        Player player = move.getPlayer();
        String playerName = player.getName();
        if(playerName.equals("X")) {
            
        } else {
            
        }
    }

    @Override
    public void onGameEnd(int result, Object arg) {
        
    }
}
