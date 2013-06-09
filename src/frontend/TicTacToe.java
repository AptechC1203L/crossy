/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import java.awt.Dimension;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author thinhpham
 */
public class TicTacToe extends JFrame {

    /**
     * @param args the command line arguments
     */
    public TicTacToe() throws IOException, InterruptedException {
        super("Tic Tac Toe");
        final Board board = new Board();
        add(board);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 320);
        setResizable(false);
        setVisible(true);
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    board.run();
                } catch (IOException ex) {
                    Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO code application logic here

        new TicTacToe();
    }
}
