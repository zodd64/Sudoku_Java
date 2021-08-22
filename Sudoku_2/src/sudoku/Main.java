package sudoku;

public class Main {

    static IntroFrameSingle introFrame;

    /**
     * main Constructor creates a new introFrame,packs it and sets it Visible
     *
     * @param args String[] args
     */
    public static void main(String[] args) {
        introFrame = IntroFrameSingle.getInstance();
        introFrame.pack();
        introFrame.setVisible(true);
    }

}
