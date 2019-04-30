package mysql;

import common.Narrative;
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
 * Represents a manager for the <code>Narrative</code> object, which 
 * implements the <code>NarrativeManager</code> interface.
 * 
 * @author Gryphon Ayers (2019)
 */
public class NarrativeManager implements database.NarrativeManager {

    /**
     * Returns the <code>Narrative</code> object corresponding to the 
     * provided primary key in the user's database.
     * 
     * @param narrativeID The primary key of the record to fetch from
     * the user's database.
     * @return A <code>Narrative</code> object which corresponds to the 
     * record with the provided primary key in the user's database.
     */
    @Override
    public Narrative getNarrative(int narrativeID) {
        String sql = "SELECT * FROM narrative WHERE narrativeID = ?;";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        PreparedStatement stmt2 = null;
        Narrative nar;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, narrativeID);
            
            rs = stmt2.executeQuery();
            if (rs == null || rs.next() == false) {
                Web_MYSQL_Helper.closePreparedStatement(stmt2);
                Web_MYSQL_Helper.returnConnection(conn);
                return null;
            }
            nar = SQLUtility.convertResultSetToNarrative(rs);
            
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getNarrative(int narrativeID) "
                    + "narrativeID="+narrativeID + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
        
        return nar;
    }

    /**
     * Returns the <code>Narrative</code> objects associated with the provided
     * user ID in the user's database.
     * 
     * @param application The user ID associated with the degree records to 
     * fetch from the user's database.
     * @return A List of <code>Narrative</code> objects corresponding to 
     * the narrative records associated with the provided application ID in the user's 
     * database.
     */
    @Override
    public List<Narrative> getNarrativesByApplication(int application) {
        String sql = "SELECT * FROM narrative WHERE application = ?;";
         Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        PreparedStatement stmt2 = null;
        LinkedList<Narrative> nars = new LinkedList<>();
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, application);
            
            rs = stmt2.executeQuery();
            if (rs == null || rs.next() == false) {
                Web_MYSQL_Helper.closeStatement(stmt2);
                Web_MYSQL_Helper.returnConnection(conn);
                return null;
            }
            while (rs.next()) {
                Narrative nar = SQLUtility.convertResultSetToNarrative(rs);
                nars.add(nar);
            }
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getNarrativesByApplication(int application) "
                    + "application=" + application + " error: " + ex);
            Web_MYSQL_Helper.closeStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closeStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
        
        return nars;
    }

    /**
     * Attempts to insert the contents of a <code>Narrative</code> object
     * into the user's database.
     *
     * @param narrative An <code>Narrative</code> object which will be used 
     * to add to the user's database.
     * @return The primary key of the <code>Narrative</code> in the database 
     * if insertion was successful, <code>null</code> otherwise.
     */
    @Override
    public Integer insertNarrative(Narrative narrative) {
        String sql = "INSERT INTO `Tenure`.`narrative` "
                + "(`application`, `narrativeText`, `type`, "
                + "`narrativeMedia`) "
                + "VALUES (?, ?, ?, ?);";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        Integer returnValue = null;
        try {
            stmt2 = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt2.setInt(1, narrative.getApplication());
            stmt2.setString(2, narrative.getNarrativeText());
            stmt2.setString(3, narrative.getType());
            if(narrative.getNarrativeMedia() != null) {
                stmt2.setInt(4, narrative.getNarrativeMedia());
            } else { stmt2.setNull(4, java.sql.Types.INTEGER); }
            
            if (stmt2.executeUpdate() > 0) {
                ResultSet rs = stmt2.getGeneratedKeys();
                if(rs.next()) {
                    returnValue = rs.getInt(1);
                }
            }
            
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in insertNarrative(Narrative narrative) " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
        
        return returnValue;
    }
    /**
     * Updates the user's database such that the <code>Narrative</code> object 
     * is consistent with the corresponding narrative record in the database.
     *
     * @param narrative An <code>Narrative</code> object containing the
     * information to be used for the update.
     * @return An <code>Narrative</code> object corresponding to the updated
     * state of the narrative record stored in the user's database.
     */
    @Override
    public Narrative updateNarrative(Narrative narrative) {
        String sql = "UPDATE narrative SET application=?, narrativeText=?, "
                + "type=?, narrativeMedia=? WHERE narrativeID=?;";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, narrative.getApplication());
            stmt2.setString(2, narrative.getNarrativeText());
            stmt2.setString(3, narrative.getType());
            if(narrative.getNarrativeMedia() != null) {
                stmt2.setInt(4, narrative.getNarrativeMedia());
            } else { stmt2.setNull(4, java.sql.Types.INTEGER); }
            stmt2.setInt(5, narrative.getNarrativeID());
            stmt2.executeUpdate();
            
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in updateGrades(Grades grades) " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closePreparedStatement(stmt2);
        Web_MYSQL_Helper.returnConnection(conn);
        
        return getNarrative(narrative.getNarrativeID());
    }

    /**
     * Return true if the given narrative record has been deleted from the database.
     * Otherwise, return false.
     *
     * @param narrative The identifier of the degree record to be deleted
     * from the database.
     * @return <code>true</code> if the given degree record has been deleted  
     * from the database successfully, <code>false</code> otherwise.
     */
    @Override
    public boolean deleteNarrative(Narrative narrative) {
        String sql = "DELETE FROM narrative WHERE narrativeID =?; ";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        PreparedStatement stmt2 = null;
        try {
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, narrative.getNarrativeID());
            stmt2.executeUpdate();
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return true;

        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in deleteNarrative(Narrative narrative) " + narrative.getNarrativeID() + " error: " + ex);
            Web_MYSQL_Helper.closePreparedStatement(stmt2);
            Web_MYSQL_Helper.returnConnection(conn);
            return false;
        }
    }

    /**
     * Returns a List of all narrative records present in the database.
     * @return A List of all narrative records present, 
     * or <code>null</code> if no degree records are present or an error
     * condition has occurred.
     */
    @Override
    public List<Narrative> getAllNarratives() {
        String sql = "SELECT * FROM narrative;";
        Connection conn = mysql.Web_MYSQL_Helper.getConnection();
        ResultSet rs;
        Statement stmt = null;
        LinkedList<Narrative> nars = new LinkedList<>();
        try {
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(sql);
            if (rs == null || rs.next() == false) {
                Web_MYSQL_Helper.closeStatement(stmt);
                Web_MYSQL_Helper.returnConnection(conn);
                return null;
            }
            while (rs.next()) {
                Narrative nar = SQLUtility.convertResultSetToNarrative(rs);
                nars.add(nar);
            }
        } catch (SQLException ex) {
            WebErrorLogger.log(Level.SEVERE, "SQLException in getAllNarratives() "
                    + " error: " + ex);
            Web_MYSQL_Helper.closeStatement(stmt);
            Web_MYSQL_Helper.returnConnection(conn);
            return null;
        }
        Web_MYSQL_Helper.closeStatement(stmt);
        Web_MYSQL_Helper.returnConnection(conn);
        
        return nars;
    }
    
}
