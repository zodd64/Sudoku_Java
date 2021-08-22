package mainFrame.mainFrameMenu;

import mainFrame.MainFrameSingle;

public class HideHistory {

    /**
     * HideHistory Constructor sets HISTORY and HistoryTextArea invisible
     *
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder Sudoku
     * @param SqrN integer containing square root of N
     */
    public HideHistory(int N, int K, int SqrN) {

        MainFrameSingle.getInstance(N, K, SqrN).getHistoryTextArea().setVisible(false);
        MainFrameSingle.getInstance(N, K, SqrN).getHISTORY().setVisible(false);

    }

}
