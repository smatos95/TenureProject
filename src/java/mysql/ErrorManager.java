/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import common.ErrorLog;
import database.SQLUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import utilities.WebErrorLogger;

/**
 * Represents a manager of the error's database, which implement
 * <code>ErrorManager</code> interface.This class can be used to modify and
 * extract the error's data which is stored in the database.
 * @author Steven Matos (2019)
 */

public class ErrorManager implements database.ErrorManager{
    
    @Override
    public Collection<ErrorLog> getAllLogs() {
        Collection<ErrorLog> errors = new ArrayList<>();
        String sql = "SELECT * FROM error_logs ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        ErrorLog err = null;
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            rs = stmt2.executeQuery();
            if (rs == null) {
                Web_MYSQL_Helper.closePreparedStatement(stmt2);
                Web_MYSQL_Helper.returnConnection(conn);
                return errors;
            }
            while (rs.next()) {
                err = SQLUtility.convertResultSetToErrorLogs(rs);
                
                errors.add(err);
            }

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getAllLogs() error:" + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return errors;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);

        return errors;
    }

    @Override
    public ErrorLog addErr(ErrorLog err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteErr(ErrorLog err) {
        String sql = "DELETE FROM error_logs WHERE EVENT_ID = ? ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, err.getEventID());
            stmt2.executeUpdate();
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return true;
            
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in deleteErr(ErrorLog err) " + err + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return false;
        }
    }

    @Override
    public ErrorLog updateErr(ErrorLog err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ErrorLog getLogById(int eventID) {
        String sql = "SELECT * FROM error_logs WHERE EVENT_ID = ? ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        ErrorLog err = null;
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, eventID);
            
            rs = stmt2.executeQuery();
            if (rs == null || rs.next() == false) {
                Web_MYSQL_Helper.closePreparedStatement(stmt2);
                Web_MYSQL_Helper.returnConnection(conn);
                return null;
            }
            err = SQLUtility.convertResultSetToErrorLogs(rs);
            
            rs.close();
            
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getLogByID(int eventID) "
                    + "eventID="+eventID + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
        
        return err;
    }

    @Override
    public Collection<ErrorLog> getAllLogsWithLevel(String eventLvl) {
        Collection<ErrorLog> errors = new ArrayList<>();
        String sql = "SELECT * FROM error_logs WHERE LEVEL = ? ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        ErrorLog err = null;
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setString(1, eventLvl);
            
            rs = stmt2.executeQuery();
            if (rs == null) {
                Web_MYSQL_Helper.closePreparedStatement(stmt2);
                Web_MYSQL_Helper.returnConnection(conn);
                return errors;
            }
            while (rs.next()) {
                err = SQLUtility.convertResultSetToErrorLogs(rs);
                
                errors.add(err);
            }

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getAllLogs() error:" + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return errors;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);

        return errors;
    }
}