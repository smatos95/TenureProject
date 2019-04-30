package database;

import common.Profile;
import java.util.List;

/**
 * An <code>interface</code> that specifies allowable operations on profiles 
 * in the database.
 * 
 * @author Gryphon Ayers (2019)
 */
public interface ProfileManager {
    public Profile getProfile(int profileID);
    public Profile getProfileByUserID(int userID);
    public Profile insertProfile(Profile profile);
    public Profile updateProfile(Profile profile);
    public boolean deleteProfile(Profile profile);
    public List<Profile> getAllProfiles();
}
