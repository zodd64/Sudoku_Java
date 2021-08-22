package sudokuMVC;

import extras.SubMat;
import mainFrame.PlayPanelSingle;

public class SudokuPanelModel {

    private int mat[][], subMat[][];
    private SubMat matOriginal[][];
    private int N;
    private int SqrN;
    private int K;

    /**
     * SudokuPanelModel Constructor calls the fillValues function
     *
     * @param N integer containing the size of the grid
     * @param K integer containing number of numbers removed for harder Sudoku
     */
    public SudokuPanelModel(int N, int K) {
        this.N = N;
        this.K = K;
        Double SRNd = Math.sqrt(N);
        SqrN = SRNd.intValue();
        mat = new int[N][N];
        matOriginal = new SubMat[SqrN][SqrN];
        fillValues();
    }

    private void fillValues() {
        // Gemise ta diagwnia twn SqrN x SqrN pinakwn
        fillDiagonal();
        // Gemise ta upoloipa 
        fillRemaining(0, SqrN);
        //initialize MatOriginal
        initMatOriginal();
        PlayPanelSingle.getInstance(N, K, SqrN).setMatOriginal(matOriginal);
        // Afairese K arithmo pshfiwn(Gia duskolia)
        removeKDigits();
    }

    /**
     * fillDiagonal function fills the diagonal parts of the grid
     */
    private void fillDiagonal() {

        for (int i = 0; i < N; i = i + SqrN) // for diagonal box, start coordinates->i==j 
        {
            fillBox(i, i);
        }
    }

    /**
     * unUsedInBox function checks if a number is inside a subgrid
     *
     * @param rowStart int rowStart
     * @param colStart int colStart
     * @param num int num
     */
    private boolean unUsedInBox(int rowStart, int colStart, int num) {
        for (int i = 0; i < SqrN; i++) {
            for (int j = 0; j < SqrN; j++) {
                if (mat[rowStart + i][colStart + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * fillBox function fills subgrid
     *
     * @param row int row
     * @param col int col
     */
    private void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < SqrN; i++) {
            for (int j = 0; j < SqrN; j++) {
                do {
                    num = randomGenerator(N);
                } while (!unUsedInBox(row, col, num));

                mat[row + i][col + j] = num;
            }
        }
    }

    /**
     * randomGenerator function generates a random number
     *
     * @param num int num
     * @return l int l
     */
    private int randomGenerator(int num) {
        int l = (int) Math.floor((Math.random() * num + 1));
        return l;
    }

    /**
     * checkIfSafe function checks if its safe to add a number
     *
     * @param i int i
     * @param j int j
     * @param num int num
     */
    private boolean checkIfSafe(int i, int j, int num) {
        return (unUsedInRow(i, num)
                && unUsedInCol(j, num)
                && unUsedInBox(i - i % SqrN, j - j % SqrN, num));
    }

    /**
     * unUsedinRow function checks if a number exists in a row
     *
     * @param i int i
     * @param num int num
     * @return boolean
     */
    private boolean unUsedInRow(int i, int num) {
        for (int j = 0; j < N; j++) {
            if (mat[i][j] == num) {
                return false;
            }
        }
        return true;
    }

    /**
     * unUsedinCol function checks if a number exists in a col
     *
     * @param j int j
     * @param num int num
     * @return boolean
     */
    private boolean unUsedInCol(int j, int num) {
        for (int i = 0; i < N; i++) {
            if (mat[i][j] == num) {
                return false;
            }
        }
        return true;
    }

    /**
     * fillRemaining is a recursive function that fills the rest of the Sudoku
     *
     * @param i int i
     * @param j int j
     * @return boolean
     */
    private boolean fillRemaining(int i, int j) {
        //  System.out.println(i+" "+j); 
        if (j >= N && i < N - 1) {
            i = i + 1;
            j = 0;
        }
        if (i >= N && j >= N) {
            return true;
        }

        if (i < SqrN) {
            if (j < SqrN) {
                j = SqrN;
            }
        } else if (i < N - SqrN) {
            if (j == (int) (i / SqrN) * SqrN) {
                j = j + SqrN;
            }
        } else {
            if (j == N - SqrN) {
                i = i + 1;
                j = 0;
                if (i >= N) {
                    return true;
                }
            }
        }

        for (int num = 1; num <= N; num++) {
            if (checkIfSafe(i, j, num)) {
                mat[i][j] = num;
                if (fillRemaining(i, j + 1)) {
                    return true;
                }

                mat[i][j] = 0;
            }
        }
        return false;
    }

    /**
     * removeKDigits removes K digits to adjust Sudoku difficulty
     */
    private void removeKDigits() {
        int count = K;
        while (count != 0) {
            int cellId = randomGenerator(N * N) - 1;

            int i = (cellId / N);
            int j = cellId % N;

            if (mat[i][j] != 0) {
                count--;
                mat[i][j] = 0;
            }
        }
    }

    /**
     * initMatOriginal function initializes matOriginal with the values from mat
     */
    private void initMatOriginal() {
        for (int row = 0; row < SqrN; row++) {
            for (int col = 0; col < SqrN; col++) {
                matOriginal[row][col] = new SubMat(SqrN);
                subMat = matOriginal[row][col].getSubMat();
                for (int row2 = 0; row2 < SqrN; row2++) {
                    for (int col2 = 0; col2 < SqrN; col2++) {
                        subMat[row2][col2] = mat[row2 + SqrN * row][col2 + SqrN * col];
                    }
                }
            }
        }
    }

    public int[][] getMat() {
        return mat;
    }

    public void setMat(int[][] mat) {
        this.mat = mat;
    }

}
