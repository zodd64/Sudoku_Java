package sudoku;

import java.awt.*;
import javax.swing.*;
import mainFrame.MainFrameSingle;

public class IntroFrameSingle extends JFrame {

    private JButton easyButton,mediumButton,hardButton;
    private javax.swing.JLabel helloPlayerJLabel;
    private javax.swing.JTextField userNameTextField;
    private int N = 9, K, SqrN;
    private Container pane;
    private static IntroFrameSingle instance;

    /**
     * IntroFrameSingle constructor calls pane_Layout and initComponents
     * function,also sets the JFrame's size and location
     */
    private IntroFrameSingle() {
        Double SRNd = Math.sqrt(N);
        SqrN = SRNd.intValue();
        easyButton = new JButton("Easy");
        mediumButton = new JButton("Medium");
        hardButton = new JButton("Hard");
        userNameTextField = new JTextField(15);
        helloPlayerJLabel = new JLabel();

        pane_Layout();
        initComponents();
        setSize(500, 200);
        setLocationRelativeTo(null);

    }

    /**
     * pane_Layout function arranges the IntroFrame Layout
     */
    private void pane_Layout() {

        pane = getContentPane();

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(15, 15, 15, 15);
        c.gridwidth = 3; //3 sthles na pianei
        c.gridx = 0; //8esh x sto grid
        c.gridy = 0; //8esh y sto grid
        pane.add(helloPlayerJLabel, c); //vale to JLabel

        c.gridx = 0; //8esh x
        c.gridy = 1; //8esh y      
        pane.add(userNameTextField, c); //vale to TextField

        //gia ta koumpia 
        c.gridwidth = 1; //mia sthlh xreiazomaste gia to ka8ena
        c.fill = GridBagConstraints.HORIZONTAL; //fillare orizontia
        c.weightx = 0.5; // se ola ta koumpia idio weight,gia na einai omoiomorfa
        c.gridx = 0;
        c.gridy = 2;
        pane.add(easyButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        pane.add(mediumButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 2;
        pane.add(hardButton, c);

    }

    /**
     * initComponents function initializes the components that will be added in
     * the IntoFrame JFrame
     */
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        setTitle("Sudoku Welcome Screen");

        setResizable(false);
      
        easyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt,5);
            }
        });

        mediumButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt,4);
            }
        });

        hardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt,3);
            }
        });
       
        helloPlayerJLabel.setText("Hello Player! Give us your name and then choose Sudoku difficulty.");
    }

    /**
     * easyButtonActionPerformed function sets a value for K,creates a
     * MainFrameSingle Instance and sets the IntroFrame invisible
     *
     * @param evt
     */
    private void buttonActionPerformed(java.awt.event.ActionEvent evt,int d) {
        if (!userNameTextField.getText().equals("")) {
            K = (N * N) / d;
            MainFrameSingle.getInstance(N, K, SqrN);            
            setVisible(false);
        }
    }

    public JTextField getUserNameTextField() {
        return userNameTextField;
    }

    public static IntroFrameSingle getInstance() {
        if (instance == null) {
            instance = new IntroFrameSingle();
        }
        return instance;
    }

}
