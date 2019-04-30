
package database;

/**
 * <code>DatabaseManagement</code> is a <code>interface</code> designed to
 * specify the database managers  
 * along with high level operations available for the database we are 
 * using in this application. 
 * Examples include:
 * <pre>
 * public void CreateTables();
 * public void initializeDatabaseManagement();
 * public UserManager getUserManager();
 * </pre>
 * 
 * @author Curt Jones (2017)
 * @author Gryphon Ayers (2019)
 */
public interface DatabaseManagement {
    
    public void CreateTables();
    public void initializeDatabaseManagement();
    public UserManager getUserManager();
    public DabasePropertyManager getDatabasePropertyManager();
    public ApplicationManager getApplicationManager();
    public DegreeManager getDegreeManager();
    public GradesManager getGradesManager();
    public LocalExpManager getlocalExpManager();
    public MediaManager getMediaManager();
    public NarrativeManager getNarrativeManager();
    public ProfileManager getProfileManager();
    public SchActivityManager getSchActivityManager();
    public SvcActivityManager getSvcActivityManager();
    public WorkExpManager getWorkExpManager();
    

}
