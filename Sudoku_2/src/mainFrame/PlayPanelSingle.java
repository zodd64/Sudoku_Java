package mainFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import extras.SubGrid;
import extras.SubMat;
import historyDAO.History;
import historyDAO.HistoryDao;
import historyDAO.HistoryDaoImpl;

public class PlayPanelSingle {

    private int subMat[][];
    private SubMat matOriginal[][];
    private int x, K, N, SqrN, i, j;
    private JFrame mainFrame;
    private static PlayPanelSingle instance;
    private JPanel playPanel;
    private JButton playPin[];
    private SubGrid[][] grid;
    private JButton[][] subGrid;
    private int row, col;
    private History history;
    private HistoryDao historyDao;

    /**
     * PlayPanelSingle Constructor
     *
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder Sudoku
     * @param SqrN integer containing square root of N
     */
    private PlayPanelSingle(int N, int K, int SqrN) {
        this.K = K;
        this.N = N;
        this.SqrN = SqrN;
        historyDao = new HistoryDaoImpl();
        grid = new SubGrid[SqrN][SqrN];
        subGrid = new JButton[SqrN][SqrN];
        subMat = new int[SqrN][SqrN];
        matOriginal = new SubMat[SqrN][SqrN];
        playPanel = new JPanel();
        playPin = new JButton[N];
        playPinInit();
    }

    /**
     * playPinInit function initializes the playPanel
     */
    private void playPinInit() {
        for (x = 0; x < N; x++) {
            playPin[x] = new JButton(String.valueOf(x + 1)); //pernaei neo koumpi sto playPin
            playPin[x].setBackground(Color.WHITE); //kanei to koumpi aspro          
            playPin[x].addActionListener(new ActionListener() { //neo listener gia ka8e koumpi
                private int num = x + 1;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (subGrid[row][col].getForeground() == Color.RED || subGrid[row][col].getText().equals("")) {//an to noumero einai kokkino h keno
                        subGrid[row][col].setText(String.valueOf(num));//allakse to noumero sto grid me to num
                        indicateMistake();
                    }
                }
            });
            playPin[x].setPreferredSize(new Dimension(60, 60)); //dinei mege8os sto ka8e koumpi
            playPin[x].setBorder(new LineBorder(Color.GRAY)); //vazei border mauro
            playPin[x].setFont(new Font("Arial", Font.BOLD, 16));
            playPanel.add(playPin[x]); //sto telos to pernaei sto panel
        }
    }

    /**
     * indicateMistake function sets a button's foreground red in case it finds
     * a mistake else it sets it black
     */
    private void indicateMistake() {
        //An to text tou koumpiou den einai to swsto
        if (!subGrid[row][col].getText().equals(String.valueOf(subMat[row][col]))) {
            subGrid[row][col].setForeground(Color.RED); //kane to keimeno sto koumpi RED
        } else {
            subGrid[row][col].setForeground(Color.BLACK); //alliws gurna to keimeno mauro
            K--; //meiwse to counter
            if (K < 1) { //otan to counter mhdenisei anakoinwse ston paixth oti kerdise
                endCast();
            }
        }
    }

    /**
     * endCast function creates a MessageDialog and also stops the timer and
     * stores history
     */
    public void endCast() {
        MainFrameSingle.getInstance(N, K, SqrN).getTimer().stop();
        history = new History(MainFrameSingle.getInstance(N, K, SqrN).getPlayer().getText(),
                MainFrameSingle.getInstance(N, K, SqrN).getDifficulty().getText(),
                MainFrameSingle.getInstance(N, K, SqrN).getTime().getText());
        historyDao.addHistory(history);
        JOptionPane.showMessageDialog(mainFrame,
                "You have won! Congratulations!",
                "Winner Winner!!",
                JOptionPane.PLAIN_MESSAGE);
    }

    public JPanel getPlayPanel() {
        return playPanel;
    }

    public void setPlayPanel(JPanel playPanel) {
        this.playPanel = playPanel;
    }

    public JButton[] getPlayPin() {
        return playPin;
    }

    public void setPlayPin(JButton[] playPin) {
        this.playPin = playPin;
    }

    public static PlayPanelSingle getInstance(int N, int K, int SqrN) {
        if (instance == null) {
            instance = new PlayPanelSingle(N, K, SqrN);
        }
        return instance;
    }

    public JButton[][] getSubGrid() {
        return subGrid;
    }

    public void setSubGrid(JButton[][] subGrid) {
        this.subGrid = subGrid;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public SubMat[][] getMatOriginal() {
        return matOriginal;
    }

    public int[][] getSubMat() {
        return subMat;
    }

    public void setSubMat(int[][] subMat) {
        this.subMat = subMat;
    }

    public void setMatOriginal(SubMat[][] matOriginal) {
        this.matOriginal = matOriginal;
    }

    public SubGrid[][] getGrid() {
        return grid;
    }

    public void setGrid(SubGrid[][] grid) {
        this.grid = grid;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getK() {
        return K;
    }

    public void setK(int K) {
        this.K = K;
    }

}
