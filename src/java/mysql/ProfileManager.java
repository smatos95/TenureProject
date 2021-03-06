package mysql;

import common.Profile;
import database.SQLUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.LinkedList;
import java.util.logging.Level;
import utilities.WebErrorLogger;

/**
 * Represents a manager for the <code>Profile</code> object, which 
 * implements the <code>ProfileManager</code> interface.
 * @author Gryphon Ayers (2019)
 */
public class ProfileManager implements database.ProfileManager {

    /**
     * Returns a <code>Profile</code> object corresponding to the provided
     * primary key in the user's database.
     * 
     * @param profileID The primary key of the application to fetch from
     * the user's database.
     * @return A <code>Profile</code> object which corresponds to the 
     * profile with the provided primary key in the user's database.
     */
    @Override
    public Profile getProfile(int profileID) {
        String sql = "SELECT * FROM profile WHERE profileID = ? ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        Profile pro = null;
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, profileID);

            rs = stmt2.executeQuery();
            if (rs == null || rs.next() == false) {
                Web_MYSQL_Helper.closePreparedStatement(stmt2);
                Web_MYSQL_Helper.returnConnection(conn);
                return null;
            }
            pro = SQLUtility.convertResultSetToProfile(rs);
            
            rs.close();

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getProfile(int profileID) "
                    + "profileID="+profileID + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);

        return pro;
    }

    /**
     * Returns the <code>Profile</code> object associated with the provided
     * user ID in the user's database.
     * 
     * @param userID The user ID associated with the profile to fetch from
     * the user's database.
     * @return A <code>Profile</code> object corresponding to the profile
     * associated with the provided user ID in the user's database.
     */
    @Override
    public Profile getProfileByUserID(int userID) {
        String sql = "SELECT * FROM profile WHERE profileUser = ? ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        Profile pro = null;
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, userID);

            rs = stmt2.executeQuery();
            if (rs == null || rs.next() == false) {
                Web_MYSQL_Helper.closePreparedStatement(stmt2);
                Web_MYSQL_Helper.returnConnection(conn);
                return null;
            }
            pro = SQLUtility.convertResultSetToProfile(rs);
            
            rs.close();

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getProfile(int profileID) "
                    + "userID="+userID + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);

        return pro;
    }

    /**
     * Attempts to insert the contents of a <code>Profile</code> object into the
     * user's database.
     * @param profile A <code>Profile</code> object which will be used to add 
     * to the user's database.
     * @return The <code>Profile</code> object that was inserted into the user's
     * database.
     */
    @Override
    public Profile insertProfile(Profile profile) {
        String sql = "INSERT INTO `Tenure`.`profile` "
                + "(`profileUser`, "
                + "`campusPhone`, "
                + "`yearsOfService`, "
                + "`rank`, "
                + "`appointmentDate`, "
                + "`lastPromotionDate`, "
                + "`lastApplication`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?) ;";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, profile.getProfileUser());
            stmt2.setString(2, profile.getCampusPhone());
            stmt2.setInt(3, profile.getYearsOfService());
            stmt2.setString(4, profile.getRank());
            if (profile.getAppointmentDate() == null)
                stmt2.setString(5, null);
            else
                stmt2.setString(5, profile.getAppointmentDate().toString());
            
            if (profile.getLastPromotionDate() == null)
                stmt2.setString(6, null);
            else
                stmt2.setString(6, profile.getLastPromotionDate().toString());
            
            stmt2.setInt(7, profile.getLastApplication());
            stmt2.executeUpdate();
            
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in insertProfile(Profile profile) " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
        
        return getProfileByUserID(profile.getProfileUser());
    }

    /**
     * Returns a <code>Profile</code> object corresponding to the updated 
     * state of the profile present in the user's database.
     *
     * @param profile A <code>Profile</code> object containing the
     * information to be used for the update.
     * @return A <code>Profile</code> object corresponding to the updated
     * state of the profile stored in the user's database.
     */
    @Override
    public Profile updateProfile(Profile profile) {
        String sql = "UPDATE profile SET campusPhone = ?, "
                + "yearsOfService = ?, rank = ?, "
                + "appointmentDate = ?, lastPromotionDate = ?, "
                + "lastApplication = ? WHERE profileID = ? ; ";
        
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setString(1, profile.getCampusPhone());
            stmt2.setInt(2, profile.getYearsOfService());
            stmt2.setString(3, profile.getRank());
            
            LocalDateTime appointmentDate = profile.getAppointmentDate();
            if(appointmentDate == null) {
                stmt2.setString(4, null);
            }
            else {
                stmt2.setString(4, profile.getAppointmentDate().toString());
            }
            
            LocalDateTime lastPromotionDate = profile.getLastPromotionDate();
            if (lastPromotionDate == null) {
                stmt2.setString(5, null);
            } else {
                stmt2.setString(5, profile.getLastPromotionDate().toString());
            }
            
            stmt2.setInt(6, profile.getLastApplication());
            stmt2.setInt(7, profile.getProfileID());
            stmt2.executeUpdate();
            
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in updateProfile(Profile profile) " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
        
        return getProfileByUserID(profile.getProfileUser());
    }

    /**
     * Return true if the given profile has been deleted from the database.
     * Otherwise, return false.
     *
     * @param profile A <code>Profile</code> object to be deleted
     * from the database.
     * @return <code>true</code> if the given profile has been deleted from 
     * the database successfully, <code>false</code> otherwise.
     */
    @Override
    public boolean deleteProfile(Profile profile) {
        String sql = "DELETE FROM profile WHERE profileID = ?; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, profile.getProfileID());
            stmt2.executeUpdate();
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return true;

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in deleteProfile(Profile profile) " + profile + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return false;
        }
    }

    /**
     * Returns a list of all user profiles in the user's database.
     * @return A list of all user profiles in the user's database, or null if
     * an error has occurred.
     */
    @Override
    public List<Profile> getAllProfiles() {
        String sql = "SELECT * FROM profile ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        LinkedList<Profile> pros = new LinkedList<>();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);
            if (rs == null || rs.next() == false) {
                Web_MYSQL_Helper.closeStatement(stmt);
                Web_MYSQL_Helper.returnConnection(conn);
                return null;
            }
            do {
                Profile pro = SQLUtility.convertResultSetToProfile(rs);
                pros.add(pro);
            } while(rs.next());
            
            rs.close();

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getAllProfiles() "
                    + " error: " + ex);
            Web_MYSQL_Helper.closeStatement(stmt);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closeStatement(stmt);
        Web_MYSQL_Helper.returnConnection(conn);

        return pros;
    }
}
