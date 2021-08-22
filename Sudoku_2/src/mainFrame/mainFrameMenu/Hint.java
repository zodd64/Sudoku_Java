package mainFrame.mainFrameMenu;

import java.awt.Color;
import javax.swing.JButton;
import mainFrame.MainFrameSingle;
import mainFrame.PlayPanelSingle;

public class Hint {

    static private Hint instance;
    private int row, col, hints;
    private JButton[][] subGrid;
    private int[][] subMat;

    /**
     * Hint Constructor
     */
    private Hint() {

        hints = 0;

    }

    /**
     * giveHint function gets Player's button selection and fills with the right
     * number,also gives time penalty
     *
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder Sudoku
     * @param SqrN integer containing square root of N
     */
    public void giveHint(int N, int K, int SqrN) { //pairnei thn epilogh tou xrhsth kai dinei thn apanthsh

        subGrid = PlayPanelSingle.getInstance(N, K, SqrN).getSubGrid();
        subMat = PlayPanelSingle.getInstance(N, K, SqrN).getSubMat();
        row = PlayPanelSingle.getInstance(N, K, SqrN).getRow();
        col = PlayPanelSingle.getInstance(N, K, SqrN).getCol();
        try {
            if (subGrid[row][col].getText().equals("") || subGrid[row][col].getForeground() == Color.RED) { //an auto pou epelekse o xrhsths einai keno h la8os
                subGrid[row][col].setText(String.valueOf(subMat[row][col])); //vale to swsto
                subGrid[row][col].setForeground(Color.BLACK);
                PlayPanelSingle.getInstance(N, K, SqrN).setK(PlayPanelSingle.getInstance(N, K, SqrN).getK() - 1); //meiwse to counter gia to win condition
                MainFrameSingle.getInstance(N, K, SqrN).setMins(MainFrameSingle.getInstance(N, K, SqrN).getMins() + 1 * hints++); //aukshse tou ton xrono kata 1 lepto epeidh phre hint
            }
        } catch (NullPointerException e) {
            System.out.println("You need to click a button in the grid to get a hint!");
        }
        if (PlayPanelSingle.getInstance(N, K, SqrN).getK() < 1) {
            PlayPanelSingle.getInstance(N, K, SqrN).endCast();
        }
    }

    public static Hint getInstance() {
        if (instance == null) {
            instance = new Hint();
        }
        return instance;
    }

    public int getHints() {
        return hints;
    }

    public void setHints(int hints) {
        this.hints = hints;
    }

}
