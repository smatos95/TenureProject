package database;

import common.Media;
import java.util.List;

/**
 * An <code>interface</code> that specifies allowable operations on media files 
 * in the database.
 *
 * @author Gryphon Ayers (2019)
 */
public interface MediaManager {
    public Media getMedia(int mediaID);
    public Media getUnhydratedMedia(int mediaID);
    public boolean hydrateMedia(Media media);
    public Integer insertMedia(Media media);
    public boolean deleteMedia(Media media);
    public boolean deleteMediaByID(int mediaID);
    public List<Media> getAllMedias();
}
