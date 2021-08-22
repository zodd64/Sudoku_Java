package extras;

import javax.swing.JButton;

public class UpdateSudokuView {

    private JButton[][] subGrid;

    /**
     * UpdateSudokuView Constructor that calls update function
     *
     * @param SqrN integer containing square root of N
     * @param grid SubGrid[][] grid
     * @param mat int[][] mat
     */
    public UpdateSudokuView(int SqrN, SubGrid[][] grid, int[][] mat) {
        update(SqrN, grid, mat);
    }

    /**
     * update function updates sudoku grid view based on mat contents
     *
     * @param SqrN integer containing square root of N
     * @param grid SubGrid[][] grid
     * @param mat int[][] mat
     */
    private void update(int SqrN, SubGrid[][] grid, int[][] mat) { //opou den exei mhdenika vazei ta noumera sta koumpia
        for (int row = 0; row < SqrN; row++) {
            for (int col = 0; col < SqrN; col++) {
                subGrid = grid[row][col].getSubGrid();
                for (int row2 = 0; row2 < SqrN; row2++) {
                    for (int col2 = 0; col2 < SqrN; col2++) {
                        if (mat[row2 + SqrN * row][col2 + SqrN * col] != 0) {
                            subGrid[row2][col2].setText(String.valueOf(mat[row2 + SqrN * row][col2 + SqrN * col]));
                        } else {
                            subGrid[row2][col2].setText("");
                        }
                    }
                }
            }
        }
    }
}
