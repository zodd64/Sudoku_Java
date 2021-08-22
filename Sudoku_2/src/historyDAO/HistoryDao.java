package historyDAO;

import java.util.List;

public interface HistoryDao {

    public List<History> getAllHistory();

    public List<History> getPlayerHistory(String player);

    public void addHistory(History history);

    public void deletePlayerHistory(String player);

    public void deleteAllHistory();

}
