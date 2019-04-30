package mysql;

import common.BuUser;
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
 *
 * @author Curt Jones
 */
public class BuUserManager implements database.BuUserManager{

    @Override
    public BuUser getBuUser(int idNumber) {
        String sql = "SELECT * FROM bu_users WHERE userNumber = ? ;";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs = null;
        PreparedStatement stmt2 = null;
        BuUser user = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1,idNumber);
            rs = stmt2.executeQuery();
            if (rs == null || rs.next() == false) {
                Web_MYSQL_Helper.returnConnection(conn);
                return null;
            }
            user = SQLUtility.convertResultSetToBuUser(rs);
           
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getBuUser( " +  idNumber + ") --> error: " + ex);
            return null;
        } finally {
            Web_MYSQL_Helper.closeResultSet(rs);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
        }

        return user;
    }

    @Override
    public BuUser addUser(BuUser buUser) {
        String sql = "INSERT INTO bu_users (userNumber, loginName, firstName, lastName, emailAddress, displayName,"
                + "userRole, lastLoginTime, loginCount) VALUES (?,?,?,?,?,?,?,?,?); ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2=null;
        try {
            stmt2 = conn.prepareStatement(sql);//Stop SQL insertion issues with prepared statements 
            stmt2.setInt(1,buUser.getUserNumber());
            stmt2.setString(2, buUser.getLoginName().trim());
            stmt2.setString(3, buUser.getFirstName().trim());
            stmt2.setString(4, buUser.getLastName().trim());
            stmt2.setString(5, buUser.getEmailAddress().trim());
            stmt2.setString(6, buUser.getDisplayName().trim());
            stmt2.setString(7, buUser.getUserRole().getRoleName());
            stmt2.setString(8, buUser.getLastLoginTime().toString());
            stmt2.setInt(9, buUser.getLoginCount());

            stmt2.executeUpdate();

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in addUser(BuUser user) user="+buUser+" error: "+ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return buUser;
        }
           Web_MYSQL_Helper.closePreparedStatement(stmt2);
           Web_MYSQL_Helper.returnConnection(conn);
           return getBuUser(buUser.getUserNumber());

    }


    @Override
    public BuUser updateUser(BuUser buUser) {
        if(getBuUser(buUser.getUserNumber())!= null){
           updateBuUserByID(buUser);
        }
        else{
            updateBuUserByEmail(buUser);
        }
            
        return getBuUser(buUser.getUserNumber());
    }

    @Override
    public boolean deleteUser(BuUser buUser) {
        String sql = "DELETE FROM bu_users WHERE userNumber =?; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2=null;

        try {
             stmt2 = conn.prepareStatement(sql);
             stmt2.setInt(1, buUser.getUserNumber());
             stmt2.executeUpdate();
             Web_MYSQL_Helper.closePreparedStatement(stmt2);
             Web_MYSQL_Helper.returnConnection(conn);
             return getBuUser(buUser.getUserNumber())==null;

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in deleteUser(BuUser user) " + buUser + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return false;
        }
    }

    @Override
    public Collection<BuUser> getAllBuUsers() {
        Collection<BuUser> users = new ArrayList<>();
        String sql = "SELECT * FROM bu_users ; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        PreparedStatement stmt2=null;
        BuUser user = null;

        try {
             stmt2 = conn.prepareStatement(sql);
            rs = stmt2.executeQuery();
            if (rs == null) {
                Web_MYSQL_Helper.closePreparedStatement(stmt2);
                Web_MYSQL_Helper.returnConnection(conn);
                return users;
            }
            while (rs.next()) {
                user = SQLUtility.convertResultSetToBuUser(rs);
                users.add(user);
            }
            
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getAllBuUsers() error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return users;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);

        return users;
    }


    @Override
    public Collection<BuUser> getSystemAdmins() {
       Collection<BuUser> users = new ArrayList<>();
        String sql = "SELECT * FROM bu_users WHERE userRole = ?; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        BuUser user = null;
        PreparedStatement stmt2=null;

        try {
             stmt2 = conn.prepareStatement(sql);
            stmt2.setString(1, common.UserRole.SystemAdmin.getRoleName());
            rs = stmt2.executeQuery();
            if (rs == null) {
                Web_MYSQL_Helper.closePreparedStatement(stmt2);
                Web_MYSQL_Helper.returnConnection(conn);
                return users;
            }
            while (rs.next()) {
                user = SQLUtility.convertResultSetToBuUser(rs);
                users.add(user);
            }
            
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in BuUser.getSystemAdmins() error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return users;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);

        return users;
    }

    @Override
    public BuUser getBuUserByEmailAddress(String emailAddress) {
        String sql = "SELECT * FROM bu_users WHERE emailAddress = ? ;";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs = null;
        PreparedStatement stmt2 = null;
        BuUser user = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setString(1,emailAddress);
            rs = stmt2.executeQuery();
            if (rs == null || rs.next() == false) {
                Web_MYSQL_Helper.returnConnection(conn);
                return null;
            }
            user = SQLUtility.convertResultSetToBuUser(rs);
           
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getBuUserByEmailAddress( " +  emailAddress + ") --> error: " + ex);
            return null;
        } finally {
            Web_MYSQL_Helper.closeResultSet(rs);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
        }

        return user;
    }
    
    private void  updateBuUserByID(BuUser buUser){
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2=null;
        String sql = "UPDATE bu_users SET loginName =?,"
                + "firstName = ?, lastName=?, emailAddress=?,"
                + "userRole=?, loginCount = ?,"
                + " displayName=? where userNumber = ? ; ";
        try {
                stmt2 = conn.prepareStatement(sql);
                stmt2.setString(1, buUser.getLoginName().trim());
                stmt2.setString(2, buUser.getFirstName().trim());
                stmt2.setString(3, buUser.getLastName().trim());
                stmt2.setString(4, buUser.getEmailAddress().trim());
                stmt2.setString(5, buUser.getUserRole().getRoleName());
                stmt2.setInt(6, buUser.getLoginCount());
                stmt2.setString(7, buUser.getDisplayName().trim());

                stmt2.setInt(8, buUser.getUserNumber());
                stmt2.executeUpdate();
        } catch (SQLException ex) {
                WebErrorLogger.log(Level.SEVERE, "SQLException in updateBuUserByID(BuUser user) " + buUser + " error: " + ex);
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
    }
    
    private void  updateBuUserByEmail(BuUser buUser){
                Connection conn = mysql.Web_MYSQL_Helper.getConnection();
                PreparedStatement stmt2 = null;
        String sql = "UPDATE bu_users SET loginName =?,"
                + "firstName = ?, lastName=?, userNumber=?,"
                + "userRole=?, loginCount = ?,"
                + " displayName=? where emailAddress = ? ; ";
        try {
                stmt2 = conn.prepareStatement(sql);
                stmt2.setString(1, buUser.getLoginName().trim());
                stmt2.setString(2, buUser.getFirstName().trim());
                stmt2.setString(3, buUser.getLastName().trim());
                stmt2.setInt(4, buUser.getUserNumber());
                stmt2.setString(5, buUser.getUserRole().getRoleName());
                stmt2.setInt(6, buUser.getLoginCount());
                stmt2.setString(7, buUser.getDisplayName().trim());

                stmt2.setString(8, buUser.getEmailAddress().trim());
                stmt2.executeUpdate();

        } catch (SQLException ex) {
                WebErrorLogger.log(Level.SEVERE, "SQLException in updateBuUserByEmail(BuUser user) " + buUser + " error: " + ex);
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
    }
    
}
