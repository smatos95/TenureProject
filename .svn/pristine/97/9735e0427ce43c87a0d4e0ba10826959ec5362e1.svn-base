package common;

/** Represents a record of scholarly activity documented by the user.
 *
 * @author Gryphon Ayers (2019)
 */
public class SchActivity {
    private final int schActivityID; // primary key
    private int application; // foreign key - refers to applicationID
    private String category;
    private String activityName;
    private String activityInfo; // foreign key - refers to narrativeID

    public SchActivity(int schActivityID) {
        this.schActivityID = schActivityID;
    }

    public int getApplication() {
        return application;
    }

    public void setApplication(int application) {
        this.application = application;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(String activityInfo) {
        this.activityInfo = activityInfo;
    }

    public int getSchActivityID() {
        return schActivityID;
    }
}
