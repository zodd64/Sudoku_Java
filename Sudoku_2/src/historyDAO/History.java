package historyDAO;

public class History {

    private String time, player, difficulty;

    /**
     * History Constructor
     *
     * @param player String containing player's name
     * @param difficulty String containing Sudoku's difficulty
     * @param time String containing Sudoku completion time
     */
    public History(String player, String difficulty, String time) {

        this.player = player;
        this.time = time;
        this.difficulty = difficulty;

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

}
