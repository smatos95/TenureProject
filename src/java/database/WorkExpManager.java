package database;

import common.WorkExp;
import java.util.List;

/**
 * An <code>interface</code> that specified allowed operations on work 
 * experience records in the database.
 * 
 * @author Gryphon Ayers (2019)
 */
public interface WorkExpManager {
    public WorkExp getWorkExp(int workExpID);
    public List<WorkExp> getWorkExpsByApplication(int application);
    public Integer insertWorkExp(WorkExp workExp);
    public WorkExp updateWorkExp(WorkExp workExp);
    public boolean deleteWorkExp(int workExpID);
    public List<WorkExp> getAllWorkExps();
}
