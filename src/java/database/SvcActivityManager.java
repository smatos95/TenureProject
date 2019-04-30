package database;

import common.SvcActivity;
import java.util.List;

/**
 * An <code>interface</code> that specifies allowed operations on service 
 * activity records in the database.
 *
 * @author Gryphon Ayers (2019)
 */
public interface SvcActivityManager {
    public SvcActivity getSvcActivity(int svcActivityID);
    public List<SvcActivity> getSvcActivitysByApplication(int application);
    public Integer insertSvcActivity(SvcActivity svcActivity);
    public SvcActivity updateSvcActivity(SvcActivity svcActivity);
    public boolean deleteSvcActivity(int svcActivityID);
    public List<SvcActivity> getAllSvcActivitys();
}
