package mainFrame;

import extras.HistoryProcessing;
import historyDAO.HistoryDao;
import historyDAO.HistoryDaoImpl;
import java.awt.*;
import javax.swing.*;
import sudoku.IntroFrameSingle;
import mainFrame.mainFrameMenu.MainFrameMenuBar;
import sudokuMVC.SudokuPanelController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class MainFrameSingle extends JFrame {

    private static MainFrameSingle instance;
    private SudokuPanelController sudokuPanelController;
    private Container pane;
    private JLabel PLAYER, DIFFICULTY, TIME, HISTORY;
    private JLabel player, difficulty, time;
    private TextArea historyTextArea;
    private int secs, mins, SqrN;
    private Timer timer;
    private GridBagConstraints c;
    private JPanel sudokuPanel;
    private int K;
    private HistoryDao historyDao;

    /**
     * MainFrameSingle Constructor
     *
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder Sudoku
     * @param SqrN integer containing square root of N
     */
    private MainFrameSingle(int N, int K, int SqrN) {

        historyDao = new HistoryDaoImpl();
        this.K = K;
        sudokuPanelController = new SudokuPanelController(N, K, SqrN);
        this.SqrN = SqrN;
        c = new GridBagConstraints();
        pane = getContentPane();
        new MainFrameMenuBar(this, N, K, SqrN);
        PlayPanelSingle.getInstance(N, K, SqrN).setMainFrame(this);
        PLAYER = new JLabel("Player");
        player = new JLabel(IntroFrameSingle.getInstance().getUserNameTextField().getText());
        DIFFICULTY = new JLabel("Difficulty");
        difficulty = new JLabel(checkDifficulty(N, K));
        TIME = new JLabel("Time");
        time = new JLabel();
        HISTORY = new JLabel("History");
        historyTextArea = new TextArea(20, 25);
        historyTextArea.setEditable(false);
        simpleTimer();
        timer.start();
        setFonts();
        historyTextArea.setText(new HistoryProcessing().process(historyDao.getAllHistory()));
        mainPanelLayout(N, K);
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    /**
     * mainPanelLayout function arranges MainFrame's UI layout
     *
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder Sudoku
     */
    private void mainPanelLayout(int N, int K) {

        pane.setLayout(new GridBagLayout());

        sudokuPanel = sudokuPanelController.getSudokuView().getGridPanel();
        c.insets = new Insets(15, 15, 15, 15);
        c.gridwidth = 6;//6 sthles na pianei
        c.gridheight = 10;//6 grammes na pianei
        c.gridx = 0; //8esh x sto grid
        c.gridy = 0; //8esh y sto grid
        pane.add(sudokuPanel, c); //vale to panel tou sudoku

        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 10;
        pane.add(PlayPanelSingle.getInstance(N, K, SqrN).getPlayPanel(), c); //vale to playpanel

        c.insets = new Insets(10, 0, 10, 15);
        c.gridwidth = 1;
        c.gridx = 6;
        c.gridy = 0;
        pane.add(Box.createRigidArea(new Dimension(0, 5)), c);//keno

        c.gridy = 1;
        pane.add(PLAYER, c); //vale to label tou PLAYER

        c.gridy = 2;
        pane.add(player, c);

        c.gridy = 3;
        pane.add(DIFFICULTY, c); //vale to label tou DIFFICULTY

        c.gridy = 4;
        pane.add(difficulty, c);

        c.gridy = 5;
        pane.add(TIME, c); //vale to label tou TIME

        c.gridy = 6;
        pane.add(time, c);

        c.gridy = 7;
        pane.add(Box.createRigidArea(new Dimension(0, 5)), c);//keno

        c.gridy = 7;
        pane.add(HISTORY, c); //vale to label tou HISTORY

        c.gridheight = 2;
        c.gridy = 8;
        pane.add(historyTextArea, c); //vale to text area tou history   

    }

    /**
     * setFonts Function sets the Font of several UI elements
     */
    private void setFonts() {
        PLAYER.setFont(new Font("Arial", Font.BOLD, 18));
        DIFFICULTY.setFont(new Font("Arial", Font.BOLD, 18));
        TIME.setFont(new Font("Arial", Font.BOLD, 18));
        HISTORY.setFont(new Font("Arial", Font.BOLD, 14));
        player.setFont(new Font("Arial", Font.PLAIN, 14));
        difficulty.setFont(new Font("Arial", Font.PLAIN, 14));
        time.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    /**
     * checkDifficulty function changes the difficulty label according to value
     * of K
     *
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder Sudoku
     */
    private String checkDifficulty(int N, int K) {
        String string = "VeryEasy";
        if (K == N * N / 6){
            string = "VeryEasy";
        } else if (K == N * N / 5) {
            string = "Easy";
        } else if (K == N * N / 4) {
            string = "Medium";
        } else if (K == N * N / 3) {
            string = "Hard";
        } else if(K == N * N / 2){
            string = "VeryHard";
        }
        
        return string;
    }

    /**
     * initComponents function sets some properties on our JFrame
     */
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sudoku");
        //setResizable(false);
        //setSize(1000,600);
        setLocationRelativeTo(null);
        pack();

    }

    /**
     * simpleTimer function creates a Timer that refreshes the time JLabel
     */
    private void simpleTimer() {

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secs++;
                if (secs == 60) {
                    secs = 0;
                    mins++;
                }
                time.setText(String.valueOf(mins) + ":" + String.valueOf(secs));
            }
        });
    }

    /**
     * reBuildFrame function rebuilds the JFrame
     *
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder Sudoku
     */
    public void reBuildFrame(int N, int K) {
        pane.removeAll();
        sudokuPanelController = new SudokuPanelController(N, K, SqrN);
        mainPanelLayout(N, K);
        this.revalidate();
        this.repaint();
    }

    public SudokuPanelController getSudokuPanelController() {
        return sudokuPanelController;
    }

    public JLabel getHISTORY() {
        return HISTORY;
    }

    public void setHISTORY(JLabel HISTORY) {
        this.HISTORY = HISTORY;
    }

    public TextArea getHistoryTextArea() {
        return historyTextArea;
    }

    public void setHistoryTextArea(TextArea historyTextArea) {
        this.historyTextArea = historyTextArea;
    }

    public int getSecs() {
        return secs;
    }

    public void setSecs(int secs) {
        this.secs = secs;
    }

    public int getMins() {
        return mins;
    }

    public void setMins(int mins) {
        this.mins = mins;
    }

    public int getK() {
        return K;
    }

    public void setK(int K) {
        this.K = K;
    }

    public JLabel getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(JLabel difficulty) {
        this.difficulty = difficulty;
    }

    public JLabel getPlayer() {
        return player;
    }

    public JLabel getTime() {
        return time;
    }

    public Timer getTimer() {
        return timer;
    }

    public void reDrawSudoku(int N, int K, int SqrN) {
        this.revalidate();
    }

    public static MainFrameSingle getInstance(int N, int K, int SqrN) {
        if (instance == null) {
            instance = new MainFrameSingle(N, K, SqrN);
        }
        return instance;
    }
}
