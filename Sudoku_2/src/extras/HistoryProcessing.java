package extras;

import historyDAO.History;
import java.util.List;

public class HistoryProcessing {

    private String result;

    /**
     * HistoryProcessing Constructor
     */
    public HistoryProcessing() {
        result = "";
    }

    /**
     * sets result value
     *
     * @param historyLog a list of history elements historyLog
     * @return result a String named result
     */
    public String process(List<History> historyLog) {

        for (History i : historyLog) {
            result += (i.getPlayer() + " has won " + i.getDifficulty() + " mode with " + i.getTime() + " time.\n");
        }
        return (result);
    }

}
