package mainFrame.mainFrameMenu;

import mainFrame.MainFrameSingle;

public class ShowHistory {

    /**
     * ShowHistory Constructor sets History and HistoryTextArea Visible
     *
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder Sudoku
     * @param SqrN integer containing square root of N
     */
    public ShowHistory(int N, int K, int SqrN) {

        MainFrameSingle.getInstance(N, K, SqrN).getHistoryTextArea().setVisible(true);
        MainFrameSingle.getInstance(N, K, SqrN).getHISTORY().setVisible(true);

    }

}
