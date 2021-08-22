package mainFrame.mainFrameMenu;

import extras.HistoryProcessing;
import extras.ResetInfo;
import extras.SubGrid;
import extras.UpdateSudokuView;
import historyDAO.HistoryDao;
import historyDAO.HistoryDaoImpl;
import mainFrame.MainFrameSingle;

public class RestartGame {

    private HistoryDao historyDao;
    private SubGrid[][] grid;
    private int[][] mat;

    /**
     * RestartGame Constructor creates a new UpdateSudokuView and a ResetInfo
     * Object,also starts the timer,shows new history and refreshes Sudoku UI
     *
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder Sudoku
     * @param SqrN integer containing square root of N
     */
    public RestartGame(int N, int K, int SqrN) {

        historyDao = new HistoryDaoImpl();
        grid = MainFrameSingle.getInstance(N, K, SqrN).getSudokuPanelController().getSudokuView().getGrid();
        mat = MainFrameSingle.getInstance(N, K, SqrN).getSudokuPanelController().getSudokuModel().getMat();
        new UpdateSudokuView(SqrN, grid, mat);
        new ResetInfo(N, K, SqrN);
        MainFrameSingle.getInstance(N, K, SqrN).getHistoryTextArea().setText(new HistoryProcessing().process(historyDao.getAllHistory()));
        MainFrameSingle.getInstance(N, K, SqrN).getTimer().start();
        MainFrameSingle.getInstance(N, K, SqrN).getSudokuPanelController().getSudokuView().getGridPanel().revalidate();

    }

}
