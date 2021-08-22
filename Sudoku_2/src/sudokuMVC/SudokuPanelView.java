package sudokuMVC;

import extras.SubGrid;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
import mainFrame.PlayPanelSingle;

public class SudokuPanelView {

    private JPanel gridPanel;
    private JPanel[][] subGridPanel;
    private SubGrid[][] grid;
    private int row, col;
    private JButton[][] subGrid;

    /**
     * SudokuPanelView Constructor initializes the SudokuPanel
     *
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder Sudoku
     * @param SqrN integer containing square root of N
     */
    public SudokuPanelView(int N, int K, int SqrN) {

        subGrid = new JButton[SqrN][SqrN];
        grid = new SubGrid[SqrN][SqrN];
        gridPanel = new JPanel();
        subGridPanel = new JPanel[SqrN][SqrN];
        gridPanel.setLayout(new GridLayout(SqrN, SqrN));

        for (row = 0; row < SqrN; row++) {
            for (col = 0; col < SqrN; col++) {
                grid[row][col] = new SubGrid(N, K, SqrN, row, col);
                subGridPanel[row][col] = new JPanel();
                subGridPanel[row][col].setLayout(new GridLayout(SqrN, SqrN));
                subGrid = grid[row][col].getSubGrid();
                for (int row2 = 0; row2 < SqrN; row2++) {
                    for (int col2 = 0; col2 < SqrN; col2++) {
                        subGridPanel[row][col].add(subGrid[row2][col2]);
                    }
                }
                gridPanel.add(subGridPanel[row][col]);
                subGridPanel[row][col].setBorder(new LineBorder(Color.GRAY));//kane ta subgrids me border Gray
            }
        }
        gridPanel.setBorder(new LineBorder(Color.GRAY));
        PlayPanelSingle.getInstance(N, K, SqrN).setGrid(grid);
    }

    public JPanel getGridPanel() {
        return gridPanel;
    }

    public void setGridPanel(JPanel gridPanel) {
        this.gridPanel = gridPanel;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public SubGrid[][] getGrid() {
        return grid;
    }

    public void setGrid(SubGrid[][] grid) {
        this.grid = grid;
    }

}
