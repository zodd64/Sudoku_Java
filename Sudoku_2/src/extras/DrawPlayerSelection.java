package extras;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import mainFrame.PlayPanelSingle;

public class DrawPlayerSelection {

    private int row, col;
    private JButton[][] subGrid;
    private SubGrid[][] grid;

    /**
     * DrawPlayerSelection Constructor that calls reDraw and drawSelection
     * functions
     *
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder Sudoku
     * @param SqrN integer containing square root of N
     * @param gridRow integer containing a specific row number
     * @param gridCol integer containing a specific col number
     */
    public DrawPlayerSelection(int N, int K, int SqrN, int gridRow, int gridCol) {

        reDraw(N, K, SqrN);
        drawSelection(N, K, SqrN, gridRow, gridCol);

    }

    /**
     * function that resets sudoku grid's colors to their former state
     *
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder sudoku
     * @param SqrN integer containing square root of N
     */
    private void reDraw(int N, int K, int SqrN) {
        for (int row = 0; row < SqrN; row++) {
            for (int col = 0; col < SqrN; col++) {
                grid = PlayPanelSingle.getInstance(N, K, SqrN).getGrid();
                subGrid = grid[row][col].getSubGrid();
                for (int row2 = 0; row2 < SqrN; row2++) {
                    for (int col2 = 0; col2 < SqrN; col2++) {
                        subGrid[row2][col2].setBackground(Color.WHITE);
                        subGrid[row2][col2].setBorder(new LineBorder(Color.GRAY));
                    }
                }
            }
        }
    }

    /**
     * Function that changes background color of specific buttons to help the
     * player
     *
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder sudoku
     * @param SqrN integer containing square root of N
     * @param gridRow integer containing a specific row number
     * @param gridCol integer containing a specific col number
     */
    private void drawSelection(int N, int K, int SqrN, int gridRow, int gridCol) { //pairnei thn epilogh tou xrhsth kai zwgrafizei to grid pros voh8eia tou xrhsth

        subGrid = PlayPanelSingle.getInstance(N, K, SqrN).getSubGrid();
        grid = PlayPanelSingle.getInstance(N, K, SqrN).getGrid();
        row = PlayPanelSingle.getInstance(N, K, SqrN).getRow();
        col = PlayPanelSingle.getInstance(N, K, SqrN).getCol();

        subGrid[row][col].setBorder(new LineBorder(Color.BLACK)); //kane thn epilogh me black border

        //draw SubGrid
        for (int row = 0; row < SqrN; row++) {
            for (int col = 0; col < SqrN; col++) {
                subGrid[row][col].setBackground(Color.LIGHT_GRAY); //kane to subgrid gkri
            }
        }

        //draw vertical
        for (int row = 0; row < SqrN; row++) {
            subGrid = grid[row][gridCol].getSubGrid();
            for (int row2 = 0; row2 < SqrN; row2++) {
                subGrid[row2][col].setBackground(Color.LIGHT_GRAY); //kane to subgrid gkri
            }
        }

        //draw horizontal
        for (int col = 0; col < SqrN; col++) {
            subGrid = grid[gridRow][col].getSubGrid();
            for (int col2 = 0; col2 < SqrN; col2++) {
                subGrid[row][col2].setBackground(Color.LIGHT_GRAY); //kane to subgrid gkri
            }
        }

    }

}
