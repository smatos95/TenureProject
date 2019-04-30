package mysql;

import common.LocalExp;
import database.SQLUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.LinkedList;
import java.util.logging.Level;
import utilities.WebErrorLogger;

/**
 * Represents a manager for the <code>LocalExp</code> object, which 
 * implements the <code>LocalExpManager</code> interface.
 * 
 * @author Gryphon Ayers (2019)
 */
public class LocalExpManager implements database.LocalExpManager {

    /**
     * Returns the <code>LocalExp</code> object corresponding to the provided
     * primary key in the user's database.
     * @param localExpID The primary key of the record to fetch from
     * the user's database.
     * @return A <code>LocalExp</code> object which corresponds to the 
     * record with the provided primary key in the user's database.
     */
    @Override
    public LocalExp getLocalExp(int localExpID) {
        String sql = "SELECT * FROM localexp WHERE localExpID = ? ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        LocalExp loc;
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, localExpID);

            rs = stmt2.executeQuery();
            if (rs == null || rs.next() == false) {
                Web_MYSQL_Helper.closePreparedStatement(stmt2);
                Web_MYSQL_Helper.returnConnection(conn);
                return null;
            }
            loc = SQLUtility.convertResultSetToLocalExp(rs);
            
            rs.close();

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getLocalExp(int localExpID) "
                    + "localExpID="+localExpID + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);

        return loc;
    }

    /**
     * Returns the <code>LocalExp</code> objects associated with the provided
     * user ID in the user's database.
     * 
     * @param application The user ID associated with the records to 
     * fetch from the user's database.
     * @return A List of <code>LocalExp</code> objects corresponding to 
     * the records associated with the provided application ID in the user's 
     * database.
     */
    @Override
    public List<LocalExp> getLocalExpsByApplication(int application) {
        String sql = "SELECT * FROM localexp WHERE application = ? ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        LinkedList<LocalExp> locs = new LinkedList<>();
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, application);

            rs = stmt2.executeQuery();
            if (rs == null || rs.next() == false) {
                Web_MYSQL_Helper.closePreparedStatement(stmt2);
                Web_MYSQL_Helper.returnConnection(conn);
                return null;
            }
            do {
                LocalExp loc = SQLUtility.convertResultSetToLocalExp(rs);
                locs.add(loc);
            } while(rs.next());
            
            rs.close();

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getLocalExpsByApplication(int application) "
                    + "application="+application + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);

        return locs;
    }

    /**
     * Attempts to insert the contents of a <code>LocalExp</code> object
     * into the user's database.
     *
     * @param localExp An <code>LocalExp</code> object which will be used 
     * to add to the user's database.
     * @return true if the <code>LocalExp</code> was inserted successfully, 
     * false otherwise.
     */
    @Override
    public Integer insertLocalExp(LocalExp localExp) {
        String sql = "INSERT INTO `Tenure`.`localexp` "
                + "(application, semester, year, "
                + "courseCode, courseName, sections) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        Integer returnValue = null;
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt2.setInt(1, localExp.getApplication());
            stmt2.setString(2, localExp.getSemester());
            stmt2.setShort(3, Short.parseShort(localExp.getYear()));
            stmt2.setString(4, localExp.getCourseCode());
            stmt2.setString(5, localExp.getCourseName());
            stmt2.setString(6, localExp.getSections());
            
            if (stmt2.executeUpdate() > 0) {
                ResultSet rs = stmt2.getGeneratedKeys();
                if(rs.next()) {
                    returnValue = rs.getInt(1);
                }
            }
            
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in insertLocalExp(LocalExp localExp " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
        return returnValue;
    }

     /**
     * Updates the user's database such that the <code>LocalExp</code> object 
     * is consistent with the corresponding record in the database.
     *
     * @param localExp A <code>LocalExp</code> object containing the
     * information to be used for the update.
     * @return An <code>LocalExp</code> object corresponding to the updated
     * state of the record stored in the user's database.
     */
    @Override
    public LocalExp updateLocalExp(LocalExp localExp) {
        String sql = "UPDATE localexp SET application=?, "
                + "semester=?, year=?, courseCode=?, courseName=?, "
                + "sections=? WHERE localExpID=?; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, localExp.getApplication());
            stmt2.setString(2, localExp.getSemester());
            stmt2.setString(3, localExp.getYear());
            stmt2.setString(4, localExp.getCourseCode());
            stmt2.setString(5, localExp.getCourseName());
            stmt2.setString(6, localExp.getSections());
            stmt2.setInt(7, localExp.getLocalExpID());
            
            stmt2.executeUpdate();
            
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in updateLocalExp(LocalExp localExp) " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
        
        return getLocalExp(localExp.getLocalExpID());
    }

    /**
     * Return true if the given degree record has been deleted from the database.
     * Otherwise, return false.
     *
     * @param localExpID The identifier of the degree record to be deleted
     * from the database.
     * @return <code>true</code> if the given degree record has been deleted  
     * from the database successfully, <code>false</code> otherwise.
     */
    @Override
    public boolean deleteLocalExp(int localExpID) {
        String sql = "DELETE FROM localexp WHERE localExpID = ?;";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        boolean returnValue;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, localExpID);
            
            returnValue = stmt2.executeUpdate() > 0;
            
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in deleteLocalExp(LocalExp localExp) " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return false;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
        
        return returnValue;
    }

    /**
     * Returns a List of all records present in the database.
     * @return A List of all records present if any applications are
     * present, or <code>null</code> if no records are present or an error
     * condition has occurred.
     */
    @Override
    public List<LocalExp> getAllLocalExps() {
        String sql = "SELECT * FROM localexp ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        LinkedList<LocalExp> locs = new LinkedList<>();
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
                LocalExp loc = SQLUtility.convertResultSetToLocalExp(rs);
                locs.add(loc);
            } while(rs.next());
            
            rs.close();

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getAllLocalExps() "
                    + " error: " + ex);
            Web_MYSQL_Helper.closeStatement(stmt);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closeStatement(stmt);
        Web_MYSQL_Helper.returnConnection(conn);

        return locs;
    }
}
