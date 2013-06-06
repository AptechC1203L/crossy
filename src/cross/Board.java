/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cross;

/**
 *
 * @author chin
 */
public class Board {

    private Player[][] board;
    private int winLen;

    public Board(int width, int height, int winLen) {
        this.board = new Player[height][width];
        this.winLen = this.winLen;
    }

    public Player get(int row, int col) {
        return board[row][col];
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return board[0].length;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return board.length;
    }

    public int makeMove(Turn move) {
        int row = move.getRow();
        int col = move.getColumn();
        Player p = move.getPlayer();

        if (col < this.getWidth() && row < this.getHeight()
                && row >= 0 && col >= 0
                && board[row][col] == null) {
            this.board[row][col] = p;
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * This method checks whether a move is a winning move. It should be called
     * after each move is made.
     *
     * @param move
     * @param this.winLen the Number of consecutive move to win
     * @return 0 if no one wins, 1 if the move is a winning one, -1 if it's a
     * draw.
     */
    public int checkMoveIsWin(Turn move) {
        Player p = move.getPlayer();
        int row = move.getRow();
        int column = move.getColumn();


        // This implements the idea portrayed here:
        // http://stackoverflow.com/questions/2670217/detect-winning-game-in-nought-and-crosses

        // Check horizontal
        boolean win;
        for (int i = 0; i < this.winLen; i++) {
            win = true;
            for (int k = 0; k < this.winLen; k++) {
                try {
                    if (this.get(row, column - i + k) != p) {
                        win = false;
                        break;
                    }
                } catch (Exception e) {
                    win = false;
                    break;
                }
            }
            if (win == true) {
                return 1;
            }
        }

        // Check vertical
        for (int i = 0; i < this.winLen; i++) {
            win = true;
            for (int k = 0; k < this.winLen; k++) {
                try {
                    if (this.get(row - i + k, column) != p) {
                        win = false;
                        break;
                    }
                } catch (Exception e) {
                    win = false;
                    break;
                }
            }
            if (win == true) {
                return 1;
            }
        }

        // Check reversed diagonal (NE -> SW)
        for (int i = 0; i < this.winLen; i++) {
            win = true;
            for (int k = 0; k < this.winLen; k++) {
                try {
                    if (this.get(row - i + k, column - i + k) != p) {
                        win = false;
                        break;
                    }
                } catch (Exception e) {
                    win = false;
                    break;
                }
            }
            if (win == true) {
                return 1;
            }
        }

        // Check diagonal (NW -> SE)
        for (int i = 0; i < this.winLen; i++) {
            win = true;
            for (int k = 0; k < this.winLen; k++) {
                try {
                    if (this.get(row, column - i + k) != p) {
                        win = false;
                        break;
                    }
                } catch (Exception e) {
                    win = false;
                    break;
                }
            }
            if (win == true) {
                return 1;
            }
        }

        return 0;
    }
}
