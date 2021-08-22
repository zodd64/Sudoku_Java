package extras;

public class SubMat {

    private int subMat[][];

    /**
     * SubMat Constructor
     *
     * @param SqrN integer containing square root of N
     */
    public SubMat(int SqrN) {
        subMat = new int[SqrN][SqrN];
    }

    public int[][] getSubMat() {
        return subMat;
    }

    public void setSubMat(int[][] subMat) {
        this.subMat = subMat;
    }

}
