package database;

import common.Application;
import java.util.List;

/**
 * An <code>interface</code> that specifies allowable operations on applications 
 * in the database.
 *
 * @author Gryphon Ayers (2019)
 */
public interface ApplicationManager {
    public Application getApplication(int applicationID);
    public List<Application> getApplicationsByUserID(int userID);
    public Integer insertApplication(Application application);
    public Application updateApplication(Application application);
    public boolean deleteApplication(Application application);
    public List<Application> getAllApplications();
}
