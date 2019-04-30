package database;

import common.SchActivity;
import java.util.List;

/**
 * An <code>interface</code> that specifies allowed operations on scholarly
 * activity records in the database.
 *
 * @author Gryphon Ayers (2019)
 */
public interface SchActivityManager {
    public SchActivity getSchActivity(int schActivityID);
    public List<SchActivity> getSchActivitysByApplication(int application);
    public Integer insertSchActivity(SchActivity schActivity);
    public SchActivity updateSchActivity(SchActivity schActivity);
    public boolean deleteSchActivity(int schActivityID);
    public List<SchActivity> getAllSchActivitys();
}
