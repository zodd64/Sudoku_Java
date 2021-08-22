package mainFrame.mainFrameMenu;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class MainFrameMenuBar {

    private JMenuBar menuBar;
    private JMenu gameMenu, optionsMenu, helpMenu;
    private JMenuItem newGameItem, restartItem, hintItem, exitItem;
    private JMenuItem easyItem, mediumItem, hardItem;
    private JMenuItem showHistoryItem, hideHistoryItem;
    private JMenuItem aboutItem;

    /**
     * MainFrameMenuBar Constructor initializes the MainFrameMenuBar and its
     * JMenuItems
     *
     * @param mainFrame JFrame of the mainFrame
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder Sudoku
     * @param SqrN integer containing square root of N
     */
    public MainFrameMenuBar(JFrame mainFrame, int N, int K, int SqrN) {

        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");
        optionsMenu = new JMenu("Options");
        helpMenu = new JMenu("Help");
        newGameItem = new JMenu("New Game");
        restartItem = new JMenuItem("Restart");
        hintItem = new JMenuItem("Hint");
        exitItem = new JMenuItem("Exit");
        showHistoryItem = new JMenuItem("Show History");
        hideHistoryItem = new JMenuItem("Hide History");
        aboutItem = new JMenuItem("About");
        easyItem = new JMenuItem("Easy");
        mediumItem = new JMenuItem("Medium");
        hardItem = new JMenuItem("Hard");

        hintItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_H, ActionEvent.ALT_MASK));
        restartItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_R, ActionEvent.ALT_MASK));
        exitItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));

        newGameItem.add(easyItem);
        newGameItem.add(mediumItem);
        newGameItem.add(hardItem);
        gameMenu.add(newGameItem);
        gameMenu.add(restartItem);
        gameMenu.add(hintItem);
        gameMenu.add(exitItem);
        optionsMenu.add(showHistoryItem);
        optionsMenu.add(hideHistoryItem);
        helpMenu.add(aboutItem);
        menuBar.add(gameMenu);
        menuBar.add(optionsMenu);
        menuBar.add(helpMenu);

        mainFrame.setJMenuBar(menuBar);

        easyItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new NewGame(N, SqrN, "Easy");
            }
        });
        mediumItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new NewGame(N, SqrN, "Medium");
            }
        });
        hardItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new NewGame(N, SqrN, "Hard");
            }
        });       
        restartItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new RestartGame(N, K, SqrN);
            }
        });
        hintItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hint.getInstance().giveHint(N, K, SqrN);
            }
        });
        showHistoryItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new ShowHistory(N, K, SqrN);
            }
        });
        hideHistoryItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new HideHistory(N, K, SqrN);
            }
        });
        aboutItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutItemActionPerformed(mainFrame);
            }
        });
        exitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
    }

    /**
     * aboutItemActionPerformed function takes the mainFrame and shows a
     * messageDialogue on top of it
     *
     * @param mainFrame JFrame of the mainFrame
     */
    private void aboutItemActionPerformed(JFrame mainFrame) {

        JOptionPane.showMessageDialog(mainFrame,
                "To win the sudoku puzzle you need to fill all blank squares with the right numbers."
                + "You get a free hint each game, but after that each hint adds (1 minute * total of hints given(excluding the free hint)).",
                "About",
                JOptionPane.PLAIN_MESSAGE);

    }

}
