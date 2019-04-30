package database;

import common.BuUser;
import common.User;
import java.util.Collection;

/**
 * A <code>interface</code> that specifies the allowable database operations 
 *  on Bloomsburg University users in our system. 
 * 
 * 
 * @author Curt Jones (2017)
 */
public interface BuUserManager {
    public BuUser getBuUser(int idNumber);
    public BuUser getBuUserByEmailAddress(String emailAddress);
    public BuUser addUser(BuUser BuUser); 
    public BuUser updateUser(BuUser BuUser);
    public boolean deleteUser(BuUser BuUser);
    public Collection<BuUser> getAllBuUsers();
    public Collection<BuUser> getSystemAdmins();
}