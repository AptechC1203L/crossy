/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import java.awt.Dimension;
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
    public TicTacToe() {
        super("Tic Tac Toe");
        add(new Board());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 320);
        setResizable(false);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        new TicTacToe();
    }
}
