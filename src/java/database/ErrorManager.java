package database;

import common.ErrorLog;
import java.util.Collection;

/**
 *
 * @author sm26511
 */
public interface ErrorManager {
    public ErrorLog addErr(ErrorLog err);
    public boolean deleteErr(ErrorLog err);
    public ErrorLog updateErr(ErrorLog err);
    public ErrorLog getLogById(int eventID);
    public Collection<ErrorLog> getAllLogs();
    public Collection<ErrorLog> getAllLogsWithLevel(String eventLvl);
}
