package sudokuMVC;

import extras.SubGrid;
import extras.UpdateSudokuView;

public class SudokuPanelController {

    private SudokuPanelModel sudokuModel;
    private SudokuPanelView sudokuView;
    private SubGrid[][] grid;
    private int mat[][];

    /**
     * SudokuPanelController Constructor creates a new SudokuModel SudokuView
     * and UpdateSudokuView Object
     *
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder Sudoku
     * @param SqrN integer containing square root of N
     */
    public SudokuPanelController(int N, int K, int SqrN) {
        sudokuModel = new SudokuPanelModel(N, K);
        sudokuView = new SudokuPanelView(N, K, SqrN);
        grid = sudokuView.getGrid();
        mat = sudokuModel.getMat();
        new UpdateSudokuView(SqrN, grid, mat);
        sudokuView.getGridPanel().revalidate();
    }

    public SudokuPanelView getSudokuView() {
        return sudokuView;
    }

    public void setSudokuView(SudokuPanelView sudokuView) {
        this.sudokuView = sudokuView;
    }

    public SudokuPanelModel getSudokuModel() {
        return sudokuModel;
    }

    public void setSudokuModel(SudokuPanelModel sudokuModel) {
        this.sudokuModel = sudokuModel;
    }

}
