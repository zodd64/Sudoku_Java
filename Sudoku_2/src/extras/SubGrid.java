package extras;

import mainFrame.PlayPanelSingle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubGrid {

    private JButton subGrid[][];
    private int row, col;
    private SubMat matOriginal[][];
    private int subMat[][];

    /**
     * SubGrid Constructor that also calls GridInit
     *
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder sudoku
     * @param SqrN integer containing square root of N
     * @param x integer containing a specific row number
     * @param y integer containing a specific col number
     */
    public SubGrid(int N, int K, int SqrN, int x, int y) {

        subMat = new int[SqrN][SqrN];
        matOriginal = new SubMat[SqrN][SqrN];
        matOriginal = PlayPanelSingle.getInstance(N, K, SqrN).getMatOriginal();
        subMat = matOriginal[x][y].getSubMat();
        subGrid = new JButton[SqrN][SqrN];
        GridInit(N, K, SqrN, x, y);

    }

    /**
     * Function GridInit initializes a 2d grid of buttons
     *
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder sudoku
     * @param SqrN integer containing square root of N
     * @param x integer containing a specific row number
     * @param y integer containing a specific col number
     */
    private void GridInit(int N, int K, int SqrN, int x, int y) {
        for (row = 0; row < SqrN; row++) {
            for (col = 0; col < SqrN; col++) {
                subGrid[row][col] = new JButton(); //neo koumpi mesa sto grid
                subGrid[row][col].setBackground(Color.WHITE);
                subGrid[row][col].addActionListener(new ActionListener() { //neo listener gia ka8e koumpi
                    private int row2 = row, col2 = col;

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        PlayPanelSingle.getInstance(N, K, SqrN).setCol(col2); //enhmerwse to col sto playpanel
                        PlayPanelSingle.getInstance(N, K, SqrN).setRow(row2); //enhmerwse to row sto playpanel
                        PlayPanelSingle.getInstance(N, K, SqrN).setSubGrid(subGrid); //enhmerwse to grid sto playpanel
                        PlayPanelSingle.getInstance(N, K, SqrN).setSubMat(subMat); //enhmerwse to mat sto playpanel
                        new DrawPlayerSelection(row2, col2, SqrN, x, y);

                    }
                });

                subGrid[row][col].setPreferredSize(new Dimension(70, 70)); //dinei mege8os sto ka8e koumpi                
                subGrid[row][col].setFont(new Font("Arial", Font.BOLD, 15)); //allakse font
            }
        }
    }

    public JButton[][] getSubGrid() {
        return subGrid;
    }

    public void setSubGrid(JButton[][] subGrid) {
        this.subGrid = subGrid;
    }

}
