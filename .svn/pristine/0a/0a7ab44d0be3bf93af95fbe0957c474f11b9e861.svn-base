package common;

import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * Represents a media file uploaded by the user.
 * 
 * @author Gryphon Ayers (2019)
 */
public class Media {
    private final int mediaID; // primary key
    private byte[] mediaFile; // bytes of media file
    private String mediaFilename; // filename of file at upload time
    private String mediaName; // friendly name of media
    private LocalDateTime creationDate; // date of upload
    private String mediaHash; // SHA256 checksum of file

    public Media(int mediaID) {
        this.mediaID = mediaID;
    }

    public int getMediaID() {
        return mediaID;
    }

    public byte[] getMediaFile() {
        return mediaFile;
    }

    public void setMediaFile(byte[] mediaFile) {
        this.mediaFile = mediaFile;
    }

    public String getMediaFilename() {
        return mediaFilename;
    }

    public void setMediaFilename(String mediaFilename) {
        this.mediaFilename = mediaFilename;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getMediaHash() {
        return mediaHash;
    }

    public void setMediaHash(String mediaHash) {
        this.mediaHash = mediaHash;
    }
}
