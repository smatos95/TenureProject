package mysql;

import common.SvcActivity;
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
 * Represents a manager for the <code>SvcActivity</code> object, which 
 * implements the <code>SvcActivityManager</code> interface.
 * 
 * @author Gryphon Ayers (2019)
 */
public class SvcActivityManager implements database.SvcActivityManager {

    /**
     * Returns the <code>SvcActivity</code> object corresponding to the 
     * provided primary key in the user's database.
     * 
     * @param svcActivityID  The primary key of the record to fetch from
     * the user's database.
     * @return A <code>SvcActivity</code> object which corresponds to the 
     * application with the provided primary key in the user's database.
     */
    @Override
    public SvcActivity getSvcActivity(int svcActivityID) {
        String sql = "SELECT * FROM svcactivity WHERE svcActivityID = ? ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        SvcActivity svc;
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, svcActivityID);

            rs = stmt2.executeQuery();
            if (rs == null || rs.next() == false) {
                Web_MYSQL_Helper.closePreparedStatement(stmt2);
                Web_MYSQL_Helper.returnConnection(conn);
                return null;
            }
            svc = SQLUtility.convertResultSetToSvcActivity(rs);
            
            rs.close();

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getSvcActivity(int svcActivityID) "
                    + "svcActivityID="+svcActivityID + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);

        return svc;
    }

    /**
     * Returns the <code>SvcActivity</code> objects associated with the provided
     * application ID in the user's database.
     * 
     * @param application The application ID associated with the records to 
     * fetch from the user's database.
     * @return A List of <code>SvcActivity</code> objects corresponding to 
     * the records associated with the provided application ID in the user's 
     * database.
     */
    @Override
    public List<SvcActivity> getSvcActivitysByApplication(int application) {
        String sql = "SELECT * FROM svcactivity WHERE application = ? ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        LinkedList<SvcActivity> svcs = new LinkedList<>();
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
                SvcActivity svc = SQLUtility.convertResultSetToSvcActivity(rs);
                svcs.add(svc);
            } while(rs.next());
            
            rs.close();

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getSvcActivitysByApplication(int application) "
                    + "application="+application + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);

        return svcs;
    }

    /**
     * Attempts to insert the contents of a <code>SvcActivity</code> object
     * into the user's database.
     *
     * @param svcActivity  An <code>SvcActivity</code> object which will be used 
     * to add to the user's database.
     * @return The primary key of the <code>SvcActivity</code> in the database 
     * if insertion was successful, <code>null</code> otherwise.
     */
    @Override
    public Integer insertSvcActivity(SvcActivity svcActivity) {
        String sql = "INSERT INTO `Tenure`.`svcactivity` "
                + "(`application`, `type`, `supportDocument`) "
                + "VALUES (?, ?, ?);";
        Integer returnValue = null;
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt2.setInt(1, svcActivity.getApplication());
            stmt2.setString(2, svcActivity.getType());
            stmt2.setInt(3, svcActivity.getSupportDocument());
            
            if (stmt2.executeUpdate() > 0) {
                ResultSet rs = stmt2.getGeneratedKeys();
                if(rs.next()) {
                    returnValue = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in insertSvcActivity(SvcActivity svcActivity) " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
        return returnValue;
    }

     /**
     * Updates the user's database such that the <code>SvcActivity</code> object 
     * is consistent with the corresponding degree record in the database.
     *
     * @param svcActivity  An <code>SvcActivity</code> object containing the
     * information to be used for the update.
     * @return An <code>SvcActivity</code> object corresponding to the updated
     * state of the record stored in the user's database.
     */
    @Override
    public SvcActivity updateSvcActivity(SvcActivity svcActivity) {
        String sql = "UPDATE svcactivity SET "
                + "application=? type=? supportDocument=? "
                + "WHERE svcActivityID=?;";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, svcActivity.getApplication());
            stmt2.setString(2, svcActivity.getType());
            stmt2.setInt(3, svcActivity.getSupportDocument());
            
            stmt2.executeUpdate();
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in updateSvcActivity(SvcActivity svcActivity) " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
        return getSvcActivity(svcActivity.getSvcActivityID());
    }

    /**
     * Return true if the given record has been deleted from the database.
     * Otherwise, return false.
     *
     * @param svcActivityID  The identifier of the record to be deleted
     * from the database.
     * @return <code>true</code> if the given record has been deleted  
     * from the database successfully, <code>false</code> otherwise.
     */
    @Override
    public boolean deleteSvcActivity(int svcActivityID) {
        String sql = "DELETE FROM svcactivity WHERE svcActivityID = ? ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        boolean returnValue;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, svcActivityID);

            returnValue = stmt2.executeUpdate() > 0;
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in deleteSvcActivity(int svcActivityID) "
                    + "svcActivityID="+svcActivityID + " error: " + ex);
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
     * @return A List of all records present if any records are
     * present, or <code>null</code> if no records are present or an error
     * condition has occurred.
     */
    @Override
    public List<SvcActivity> getAllSvcActivitys() {
        String sql = "SELECT * FROM svcactivity ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        LinkedList<SvcActivity> svcs = new LinkedList<>();
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
                SvcActivity svc = SQLUtility.convertResultSetToSvcActivity(rs);
                svcs.add(svc);
            } while(rs.next());
            
            rs.close();

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getAllSvcActivitys() "
                    + " error: " + ex);
            Web_MYSQL_Helper.closeStatement(stmt);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closeStatement(stmt);
        Web_MYSQL_Helper.returnConnection(conn);

        return svcs;
    }
}
