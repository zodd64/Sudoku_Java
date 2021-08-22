package historyDAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistoryDaoImpl implements HistoryDao {

    private List<History> historyLogs, historyLogsTemp;
    private String filePath;
    private String time, player, difficulty;

    /**
     * HistoryDaoImpl Constructor
     */
    public HistoryDaoImpl() {

        historyLogs = new ArrayList<History>();
        historyLogsTemp = new ArrayList<History>();
        filePath = new File("").getAbsolutePath();

    }

    /**
     * Function getAllHistory retrieves all history from file by calling
     * retrieveData(" ")
     *
     * @return historyLogs
     */
    @Override
    public List<History> getAllHistory() {
        retrieveData(" ");
        return (historyLogs);
    }

    /**
     * Function getPlayerHistory retrieves history based on player name from
     * file by calling retrieveData(name)
     *
     * @param name String name
     * @return historyLogs
     */
    @Override
    public List<History> getPlayerHistory(String name) { //pairnei istoriko me vash to onoma sugkekrimenou paixth 
        retrieveData(name);
        return (historyLogs);
    }

    /**
     * Function addHistory adds player history in the file
     *
     * @param history History history
     */
    @Override
    public void addHistory(History history) { //vazei istoriko sto arxeio
        try {
            FileWriter myWriter = new FileWriter(filePath + "/src/historyDAO/Log.txt", true);
            myWriter.write(history.getPlayer() + " " + history.getDifficulty() + " " + history.getTime());
            myWriter.write("\n");
            myWriter.close();
            System.out.println("New historic entry!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function deletePlayerHistory deletes a specific player's history in the
     * file
     *
     * @param player String player
     */
    @Override
    public void deletePlayerHistory(String player) {
        try {
            //diagrafei istoriko me vash to onoma tou paixth
            historyLogsTemp = getAllHistory();
            new FileWriter(filePath + "/src/historyDAO/Log.txt", false).close();//diagrafei ta panta
        } catch (IOException ex) {
            Logger.getLogger(HistoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (History i : historyLogsTemp) {
            if (!i.getPlayer().equals(player)) {
                addHistory(i);
            }
        }
        historyLogs = historyLogsTemp;
    }

    /**
     * Function deleteAllHistory deletes all history from the file
     */
    @Override
    public void deleteAllHistory() {
        try {
            //diagrafei olo to istoriko sto arxeio
            new FileWriter(filePath + "/src/historyDAO/Log.txt", false).close();
        } catch (IOException ex) {
            Logger.getLogger(HistoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Function retrieveData retrieves data from the file
     *
     * @param name String name
     */
    public void retrieveData(String name) {
        try {

            BufferedReader reader;

            reader = new BufferedReader(new FileReader(filePath + "/src/historyDAO/Log.txt"));
            String line = reader.readLine();
            while (line != null) {
                formatData(line);
                if (name.equals(" ") || name.equals(player)) {
                    historyLogs.add(new History(player, difficulty, time));
                }
                line = reader.readLine();// read next line
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function formatData splits a string and saves it
     *
     * @param line String line
     */
    private void formatData(String line) {

        String[] splitData;
        splitData = line.split(" ", 0);
        player = splitData[0];
        difficulty = splitData[1];
        time = splitData[2];

    }
}
