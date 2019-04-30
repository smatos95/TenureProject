/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import common.SchActivity;
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
 * Represents a manager for the <code>SchActivity</code> object, which 
 * implements the <code>SchActivityManager</code> interface.
 * @author Gryphon Ayers (2019)
 */
public class SchActivityManager implements database.SchActivityManager {

    /**
     * Returns the <code>SchActivity</code> object corresponding to the 
     * provided primary key in the user's database.
     * 
     * @param schActivityID The primary key of the record to fetch from
     * the user's database.
     * @return A <code>SchActivity</code> object which corresponds to the 
     * application with the provided primary key in the user's database.
     */
    @Override
    public SchActivity getSchActivity(int schActivityID) {
        String sql = "SELECT * FROM schactivity WHERE schActivityID = ? ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        SchActivity sch;
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, schActivityID);

            rs = stmt2.executeQuery();
            if (rs == null || rs.next() == false) {
                Web_MYSQL_Helper.closePreparedStatement(stmt2);
                Web_MYSQL_Helper.returnConnection(conn);
                return null;
            }
            sch = SQLUtility.convertResultSetToSchActivity(rs);
            
            rs.close();

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getSchActivity(int schActivityID) "
                    + "schActivityID="+schActivityID + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);

        return sch;
    }

    /**
     * Returns the <code>SchActivity</code> objects associated with the provided
     * application ID in the user's database.
     * 
     * @param application The application ID associated with the degree records to 
     * fetch from the user's database.
     * @return A List of <code>SchActivity</code> objects corresponding to 
     * the records associated with the provided application ID in the user's 
     * database.
     */
    @Override
    public List<SchActivity> getSchActivitysByApplication(int application) {
        String sql = "SELECT * FROM schactivity WHERE application = ? ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        LinkedList<SchActivity> schs = new LinkedList<>();
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
                SchActivity sch = SQLUtility.convertResultSetToSchActivity(rs);
                schs.add(sch);
            } while(rs.next());
            
            rs.close();

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getSchActivitysByApplication(int application) "
                    + "application="+application + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);

        return schs;
    }

    /**
     * Attempts to insert the contents of a <code>SchActivity</code> object
     * into the user's database.
     *
     * @param schActivity An <code>SchActivity</code> object which will be used 
     * to add to the user's database.
     * @return The primary key of the <code>SchActivity</code> in the database 
     * if insertion was successful, <code>null</code> otherwise.
     */
    @Override
    public Integer insertSchActivity(SchActivity schActivity) {
        String sql = "INSERT INTO `Tenure`.`schactivity` "
                + "(`application`, `category`, `activityName`, `activityInfo`) "
                + "VALUES (?, ?, ?, ?);";
        Integer returnValue = null;
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt2.setInt(1, schActivity.getApplication());
            stmt2.setString(2, schActivity.getCategory());
            stmt2.setString(3, schActivity.getActivityName());
            stmt2.setString(4, schActivity.getActivityInfo());
            
            if (stmt2.executeUpdate() > 0) {
                ResultSet rs = stmt2.getGeneratedKeys();
                if(rs.next()) {
                    returnValue = rs.getInt(1);
                }
            }
            
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in insertSchActivity(SchActivity schActivity) " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
        return returnValue;
    }

    /**
     * Updates the user's database such that the <code>SchActivity</code> object 
     * is consistent with the corresponding record in the database.
     *
     * @param schActivity An <code>SchActivity</code> object containing the
     * information to be used for the update.
     * @return An <code>SchActivity</code> object corresponding to the updated
     * state of the record stored in the user's database.
     */
    @Override
    public SchActivity updateSchActivity(SchActivity schActivity) {
        String sql = "UPDATE schactivity SET "
                + "application=? category=? activityName=? activityInfo=? "
                + "WHERE schActivityID=?;";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, schActivity.getApplication());
            stmt2.setString(2, schActivity.getCategory());
            stmt2.setString(3, schActivity.getActivityName());
            stmt2.setString(4, schActivity.getActivityInfo());
            stmt2.setInt(5, schActivity.getSchActivityID());
            
            stmt2.executeUpdate();
            
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in updateSchActivity(SchActivity schActivity) " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
        return getSchActivity(schActivity.getSchActivityID());
    }

    /**
     * Return true if the given record has been deleted from the database.
     * Otherwise, return false.
     *
     * @param schActivityID The identifier of the record to be deleted
     * from the database.
     * @return <code>true</code> if the given record has been deleted  
     * from the database successfully, <code>false</code> otherwise.
     */
    @Override
    public boolean deleteSchActivity(int schActivityID) {
        String sql = "DELETE FROM schactivity WHERE schActivityID = ? ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        boolean returnValue;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, schActivityID);

            returnValue = stmt2.executeUpdate() > 0;
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in deleteSchActivity(int schActivityID) "
                    + "schActivityID="+schActivityID + " error: " + ex);
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
    public List<SchActivity> getAllSchActivitys() {
        String sql = "SELECT * FROM schactivity ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        LinkedList<SchActivity> schs = new LinkedList<>();
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
                SchActivity sch = SQLUtility.convertResultSetToSchActivity(rs);
                schs.add(sch);
            } while(rs.next());
            
            rs.close();

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getAllSchActivitys() "
                    + " error: " + ex);
            Web_MYSQL_Helper.closeStatement(stmt);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closeStatement(stmt);
        Web_MYSQL_Helper.returnConnection(conn);

        return schs;
    }
    
}
