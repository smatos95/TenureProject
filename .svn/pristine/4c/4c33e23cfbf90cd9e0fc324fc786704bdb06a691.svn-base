package common;

/**
 * Represents a text narrative submitted by the user as part of an application.
 * Short narratives are stored as bare text, while long narratives can be 
 * uploaded as a media file.
 * 
 * @author Gryphon Ayers (2019)
 */
public class Narrative {
    private final int narrativeID; // primary key
    private int application; // foreign key - references applicationID
    private String narrativeText;
    private String type;
    private Integer narrativeMedia; // foreign key - references mediaID

    public Narrative(int narrativeID) {
        this.narrativeID = narrativeID;
    }

    public int getNarrativeID() {
        return narrativeID;
    }

    public int getApplication() {
        return application;
    }

    public void setApplication(int application) {
        this.application = application;
    }

    public String getNarrativeText() {
        return narrativeText;
    }

    public void setNarrativeText(String narrativeText) {
        this.narrativeText = narrativeText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNarrativeMedia() {
        return narrativeMedia;
    }

    public void setNarrativeMedia(Integer narrativeMedia) {
        this.narrativeMedia = narrativeMedia;
    }
    
    
}
