package mysql;

import common.Application;
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
 * Represents a manager for the <code>Application</code> object, which 
 * implements the <code>ApplicationManager</code> interface.
 * 
 * @author Gryphon Ayers (2019)
 */
public class ApplicationManager implements database.ApplicationManager {

    /**
     * Returns the <code>Application</code> object corresponding to the 
     * provided primary key in the user's database.
     * 
     * @param applicationID The primary key of the application to fetch from
     * the user's database.
     * @return An <code>Application</code> object which corresponds to the 
     * application with the provided primary key in the user's database.
     */
    @Override
    public Application getApplication(int applicationID) {
        String sql = "SELECT * FROM application WHERE applicationID = ? ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        Application app = null;
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, applicationID);

            rs = stmt2.executeQuery();
            if (rs == null || rs.next() == false) {
                Web_MYSQL_Helper.closePreparedStatement(stmt2);
                Web_MYSQL_Helper.returnConnection(conn);
                return null;
            }
            app = SQLUtility.convertResultSetToApplication(rs);
            
            rs.close();

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getApplicationByID(int applicationID) "
                    + "applicationID="+applicationID + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);

        return app;
    }

    /**
     * Returns the <code>Application</code> objects associated with the provided
     * user ID in the user's database.
     * 
     * @param userID The user ID associated with the applications to fetch from
     * the user's database.
     * @return A List of <code>Application</code> objects corresponding to 
     * the applications associated with the provided user ID in the user's 
     * database.
     */
    @Override
    public List<Application> getApplicationsByUserID(int userID) {
        String sql = "SELECT * FROM application WHERE applicantUser = ? ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        Application a;
        LinkedList<Application> apps = new LinkedList<>();
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
            
            a = SQLUtility.convertResultSetToApplication(rs);
            apps.add(a);
            
            while(rs.next()) {
                Application app = SQLUtility.convertResultSetToApplication(rs);
                apps.add(app);
            }
            
            rs.close();

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getApplicationsByUserID(int userID) "
                    + "applicationID="+userID + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);

        return apps;
    }

    /**
     * Attempts to insert the contents of an <code>Application</code> object
     * into the user's database.
     *
     * @param application An <code>Application</code> object which will be used 
     * to add to the user's database.
     * @return the primary key of the <code>Application</code> in the database if it was inserted successfully, 
     * if it was inserted successfully, null otherwise.
     */
    @Override
    public Integer insertApplication(Application application) {
        String sql = "INSERT INTO `Tenure`.`application` "
                + "(`applicantUser`, "
                + "`applicationType`, "
                + "`creationDate`, "
                + "`submissionDate`, "
                + "`department`, "
                + "`departmentChair`, "
                + "`chairPhone`, "
                + "`customFirstName`, "
                + "`customLastName`,"
                + "`friendlyName`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        Integer returnValue;
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt2.setInt(1, application.getApplicantUser());
            stmt2.setString(2, application.getApplicationType());
            stmt2.setString(3, application.getCreationDate().toString());
            
            LocalDateTime submissionDate = application.getSubmissionDate();
            if(submissionDate == null) {
                stmt2.setString(4, null);
            }
            else {
                stmt2.setString(4, application.getSubmissionDate().toString());
            }
            
            stmt2.setString(5, application.getDepartment());
            stmt2.setString(6, application.getDepartmentChair());
            stmt2.setString(7, application.getChairPhone());
            stmt2.setString(8, application.getCustomFirstName());
            stmt2.setString(9, application.getCustomLastName());
            stmt2.setString(10, application.getFriendlyName());
            
            if (stmt2.executeUpdate() > 0) {
                Web_MYSQL_Helper.closePreparedStatement(stmt2);
                Web_MYSQL_Helper.returnConnection(conn);
                return null;
            }
            
            ResultSet rs = stmt2.getGeneratedKeys();
            if(rs.next()) {
                returnValue = rs.getInt(1);
            } else {
                returnValue = null;
            }
            
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in insertApplication(Application application) " + ex, ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
        return returnValue;
    }

    /**
     * Returns a <code>Application</code> object corresponding to the updated 
     * state of the application present in the user's database.
     *
     * @param application An <code>Application</code> object containing the
     * information to be used for the update.
     * @return An <code>Application</code> object corresponding to the updated
     * state of the application stored in the user's database.
     */
    @Override
    public Application updateApplication(Application application) {
        String sql = "UPDATE application SET applicantUser=?,"
                + " applicationType=?,"
                + " creationDate=?,"
                + " submissionDate=?,"
                + " department=?,"
                + " departmentChair=?,"
                + " chairPhone=?,"
                + " customFirstName=?,"
                + " customLastName=?,"
                + " friendlyName=? WHERE applicationID=? ;";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, application.getApplicantUser());
            stmt2.setString(2, application.getApplicationType());
            stmt2.setString(3, application.getCreationDate().toString());
            
            LocalDateTime submissionDate = application.getSubmissionDate();
            if(submissionDate == null) {
                stmt2.setString(4, null);
            }
            else {
                stmt2.setString(4, application.getSubmissionDate().toString());
            }
            
            stmt2.setString(5, application.getDepartment());
            stmt2.setString(6, application.getDepartmentChair());
            stmt2.setString(7, application.getChairPhone());
            stmt2.setString(8, application.getCustomFirstName());
            stmt2.setString(9, application.getCustomLastName());
            stmt2.setString(10, application.getFriendlyName());
            
            stmt2.setInt(11, application.getApplicationID());
            stmt2.executeUpdate();
            
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in updateApplication(Application application) " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
        
        return getApplication(application.getApplicationID());
    }

    /**
     * Return true if the given application has been deleted from the database.
     * Otherwise, return false.
     *
     * @param application An <code>Application</code> object to be deleted
     * from the database.
     * @return <code>true</code> if the given application has been deleted from 
     * the database successfully, <code>false</code> otherwise.
     */
    @Override
    public boolean deleteApplication(Application application) {
        String sql = "DELETE FROM application WHERE applicationID =?; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, application.getApplicationID());
            stmt2.executeUpdate();
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return true;

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in deleteApplication(Application application) " + application + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return false;
        }
    }

    /**
     * Returns a List of all applications present in the database.
     * @return A List of all applications present if any applications are
     * present, or <code>null</code> if no applications are present or an error
     * condition has occurred.
     */
    @Override
    public List<Application> getAllApplications() {
        String sql = "SELECT * FROM application ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        LinkedList<Application> apps = new LinkedList<>();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);
            if (rs == null || rs.next() == false) {
                Web_MYSQL_Helper.closeStatement(stmt);
                Web_MYSQL_Helper.returnConnection(conn);
                return null;
            }
            while(rs.next()) {
                Application app = SQLUtility.convertResultSetToApplication(rs);
                apps.add(app);
            }
            
            rs.close();

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getAllApplications() "
                    + " error: " + ex);
            Web_MYSQL_Helper.closeStatement(stmt);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closeStatement(stmt);
        Web_MYSQL_Helper.returnConnection(conn);

        return apps;
    }
    
}
