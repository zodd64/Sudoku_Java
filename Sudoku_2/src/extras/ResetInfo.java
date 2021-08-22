package extras;

import mainFrame.MainFrameSingle;
import mainFrame.PlayPanelSingle;
import mainFrame.mainFrameMenu.Hint;

public class ResetInfo {

    /**
     * Constructor that re-sets hints,K,secs and mins
     *
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder Sudoku
     * @param SqrN integer containing square root of N
     */
    public ResetInfo(int N, int K, int SqrN) {

        Hint.getInstance().setHints(0);
        MainFrameSingle.getInstance(N, K, SqrN).setK(K);
        PlayPanelSingle.getInstance(N, K, SqrN).setK(K);
        MainFrameSingle.getInstance(N, K, SqrN).setSecs(0);
        MainFrameSingle.getInstance(N, K, SqrN).setMins(0);

    }

}
