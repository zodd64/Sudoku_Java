package mainFrame.mainFrameMenu;

import extras.HistoryProcessing;
import extras.ResetInfo;
import historyDAO.HistoryDao;
import historyDAO.HistoryDaoImpl;
import java.awt.Font;
import javax.swing.JLabel;
import mainFrame.MainFrameSingle;

public class NewGame {

    private HistoryDao historyDao;
    private JLabel diff;
    private int K;

    /**
     * NewGame Constructor calls startNewGameFunction
     *
     * @param N integer containing the size of the grid
     * @param SqrN integer containing square root of N
     * @param reqDif String reqDif contains difficulty chosen by the player
     */
    public NewGame(int N, int SqrN, String reqDif) {

        historyDao = new HistoryDaoImpl();
        diff = new JLabel();
        diff.setFont(new Font("Arial", Font.PLAIN, 14));
        startNewGame(N, SqrN, reqDif);

    }

    /**
     * startNewGame function starts a new game with difficulty based on user
     * input by changing K value and calling update function
     *
     * @param N integer containing the size of the grid
     * @param SqrN integer containing square root of N
     * @param reqDif String reqDif contains difficulty chosen by the player
     */
    private void startNewGame(int N, int SqrN, String reqDif) {

        if (reqDif.equals("Easy")) {
            K = N * N / 5;
            diff.setText("Easy");
            update(N, K, SqrN);

        } else if (reqDif.equals("Medium")) {
            K = N * N / 4;
            diff.setText("Medium");
            update(N, K, SqrN);

        } else if (reqDif.equals("Hard")) {
            K = N * N / 3;
            diff.setText("Hard");
            update(N, K, SqrN);
        } else if (reqDif.equals("VeryHard")) {
            K = N * N / 2;
            diff.setText("VeryHard");
            update(N, K, SqrN);
        } else if (reqDif.equals("VeryEasy")) {
            K = N * N / 6;
            diff.setText("VeryEasy");
            update(N, K, SqrN);
        }
    }

    /**
     * update function updates MainFrameSingle JFrame, creates a new ResetInfo
     * object,starts the timer and updates history in the MainFrame
     *
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder Sudoku
     * @param SqrN integer containing square root of N
     */
    private void update(int N, int K, int SqrN) {

        MainFrameSingle.getInstance(N, K, SqrN).setDifficulty(diff);
        MainFrameSingle.getInstance(N, K, SqrN).reBuildFrame(N, K);
        new ResetInfo(N, K, SqrN);
        MainFrameSingle.getInstance(N, K, SqrN).getHistoryTextArea().setText(new HistoryProcessing().process(historyDao.getAllHistory()));
        MainFrameSingle.getInstance(N, K, SqrN).getTimer().start();

    }
}
