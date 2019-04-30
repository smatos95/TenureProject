package mysql;

import common.TeachingExp;
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
 *
 * @author Gryphon Ayers (2019)
 */
public class TeachingExpManager implements database.TeachingExpManager {

    @Override
    public TeachingExp getTeachingExp(int teachingID) {
        String sql = "SELECT * FROM teachingexp WHERE teachingID = ? ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        TeachingExp exp = null;
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, teachingID);

            rs = stmt2.executeQuery();
            if (rs == null || rs.next() == false) {
                Web_MYSQL_Helper.closePreparedStatement(stmt2);
                Web_MYSQL_Helper.returnConnection(conn);
                return null;
            }
            exp = SQLUtility.convertResultSetToTeachingExp(rs);
            
            rs.close();

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getTeachingExp(int teachingID) "
                    + "teachingID="+teachingID + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);

        return exp;
    }

    @Override
    public List<TeachingExp> getTeachingExpsByApplication(int application) {
        String sql = "SELECT * FROM teachingexp WHERE application = ? ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        LinkedList<TeachingExp> exps = new LinkedList<>();
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
                TeachingExp exp = SQLUtility.convertResultSetToTeachingExp(rs);
                exps.add(exp);
            } while(rs.next());
            
            rs.close();

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getTeachingExpByApplication(int application) "
                    + "application="+application + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);

        return exps;
    }

    @Override
    public Integer insertTeachingExp(TeachingExp teachingExp) {
        String sql = "INSERT INTO `Tenure`.`teachingexp` "
                + "(`application`, `institution`, `rank`, "
                + "`teachingDates`, `yearsOfService`, `isFullTime`) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        Integer returnValue = null;
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt2.setInt(1, teachingExp.getApplication());
            stmt2.setString(2, teachingExp.getInstitution());
            stmt2.setString(3, teachingExp.getRank());
            stmt2.setString(4, teachingExp.getTeachingDates());
            stmt2.setString(5, teachingExp.getYearsOfService());
            stmt2.setBoolean(6, teachingExp.isFullTime());
            
            if (stmt2.executeUpdate() > 0) {
                ResultSet rs = stmt2.getGeneratedKeys();
                if(rs.next()) {
                    returnValue = rs.getInt(1);
                }
            }
            
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in insertTeachingExp(TeachingExp teachingExp) " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
        return returnValue;
    }

    @Override
    public TeachingExp updateTeachingExp(TeachingExp teachingExp) {
        String sql = "UPDATE teachingexp SET"
                + "`application`=?, `institution`=?, `rank`=?, "
                + "`teachingDates`=?, `yearsOfService`=?, `isFullTime`=? "
                + "WHERE teachingID=?;";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, teachingExp.getApplication());
            stmt2.setString(2, teachingExp.getInstitution());
            stmt2.setString(3, teachingExp.getRank());
            stmt2.setString(4, teachingExp.getTeachingDates());
            stmt2.setString(5, teachingExp.getYearsOfService());
            stmt2.setBoolean(6, teachingExp.isFullTime());
            stmt2.setInt(7, teachingExp.getTeachingID());
            
            stmt2.executeUpdate();
            
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in updateTeachingExp(TeachingExp teachingExp) " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
        return getTeachingExp(teachingExp.getTeachingID());
    }

    @Override
    public boolean deleteTeachingExp(int teachingID) {
        String sql = "DELETE FROM teachingexp WHERE teachingID = ? ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        TeachingExp exp = null;
        PreparedStatement stmt2 = null;
        boolean returnValue;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, teachingID);

            returnValue = stmt2.executeUpdate() > 0;
            

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getTeachingExp(int teachingID) "
                    + "teachingID="+teachingID + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return false;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
        return returnValue;
    }

    @Override
    public List<TeachingExp> getAllTeachingExps() {
        String sql = "SELECT * FROM teachingexp ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        LinkedList<TeachingExp> exps = new LinkedList<>();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);
            
            do {
                TeachingExp exp = SQLUtility.convertResultSetToTeachingExp(rs);
                exps.add(exp);
            } while(rs.next());
            
            rs.close();

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getAllTeachingExps() "
                    + "error: " + ex);
            Web_MYSQL_Helper.closeStatement(stmt);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closeStatement(stmt);
        Web_MYSQL_Helper.returnConnection(conn);

        return exps;
    }
    
}
