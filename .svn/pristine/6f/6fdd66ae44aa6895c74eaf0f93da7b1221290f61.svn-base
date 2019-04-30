package common;

import java.io.Serializable;
import utilities.Debug;

/**
 * Represents the role with which a <code> User </code> in this system. Different
 * users have different access levels and different levels of functionality and
 * responsibility within the system.  
 * 
 * @author Curt Jones (2017)
 * UPDATE: Recreated the user roles for the tenure document users
 */
public enum UserRole implements Serializable{
    
    /**
     * The administrator for the entire program, has complete control of the
     * system. 
     */
    SystemAdmin("SystemAdmin"),
    
    /**
     * An administrator at BU, One who is in charge of the tenure committee and appoints members to the committee
     */
    TenureCommitteeAdministrator("TenureCommitteeAdministrator"),
    
    /**
     * Member of the tenure committee, responsible for reviewing tenure portfolios of professors 
     */
    TenureCommitteeMember("TenureCommitteeMember"),
    
    /**
     * A professor is a common user, anyone who will create a portfolio to apply for tenure (potentially promotion) 
     * 
     */
   Professor("Professor"),
   
   /**
    * A chair is the head of a department
    */
   Chair("Chair"),
   
   /**
    * A user who attends to university and takes classes
    */
   Student("Student"),
   
   /**
    * Someone who is in charge of all other roles, a dean
    */
   Administrator("Administrator"),
   
   /**
    * A user who advises students
    */
   Advisor("Advisor"),
   
   /**
    * Someone who instructs
    */
   Instructor("Instructor"),
   
   /**
    * Someone who works for the university but is not directly in charge of students or other roles
    */
   Staff("Staff");   
    
    private final String roleName;
    
    /**
     * Constructs a <code> UserRole </code> given a role name. A user role
     * represents their position in the college and does not require them to be
     * a member of the <code> Faculty </code>.
     * 
     * @param roleName The name of this <code> UserRole </code> position.
     */
    UserRole(String roleName){
        this.roleName=roleName.trim();
    }
    
    /**
     * Returns the <code> UserRole </code> of the given role name. If the role name
     * given is not one of the roles in the enumerated type, the default is set to "Guest."
     * 
     * @param roleName The name of this role, preferably one already in the enumerated type
     * (SystemAdmin, Administrator, Chair, Advisor, Instructor, Staff, or Student).
     * 
     * @return The <code> UserRole </code> corresponding to the role name given.
     */
     public static UserRole getUserRole (String roleName) {
       UserRole name = Professor; // default is guest 

        for (UserRole e : UserRole.values ()) {
            if (e.roleName.equalsIgnoreCase(roleName.trim())){
                name = e;
                break;
            }
        }
        return (name);
    }

     /**
     * Returns the role name of this <code> UserRole </code>.
     * 
     * @return This <code> UserRole </code>'s name.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Returns this <code> UserRole </code>'s name as a string.
     * 
     * @return The name of this <code> UserRole </code> as a string.
     */
    @Override
    public String toString() {
        return "UserRole{" + "roleName=" + roleName + '}';
    }
    
    /**
     * Tests the <code>UserRole</code> class. Reviews the toString(), 
     * getRoleName(), getEnum(), and UserRole().
     * 
     * @param args Command line arguments.
     */
    public static void main (String args[]){
        Debug.setEnabled(true);
        Debug.println(getUserRole("professor"));
        Debug.println(getUserRole("P").toString());
        
        
        Debug.println(SystemAdmin.getRoleName());
        Debug.println(TenureCommitteeAdministrator.getRoleName());
        Debug.println(TenureCommitteeMember.getRoleName());
        Debug.println(Professor.getRoleName());
        Debug.println(Chair.getRoleName());
        Debug.println(Professor.getRoleName());
        Debug.println(Student.getRoleName());
        Debug.println(Administrator.getRoleName());
        Debug.println(Instructor.getRoleName());
        Debug.println(Staff.getRoleName());
        Debug.println(Advisor.getRoleName());
        
        
        
        Debug.println(getUserRole(SystemAdmin.getRoleName()));
        Debug.println(getUserRole(TenureCommitteeAdministrator.getRoleName()));
        Debug.println(getUserRole(TenureCommitteeMember.getRoleName()));
        Debug.println(getUserRole(Professor.getRoleName()));
        Debug.println(getUserRole(Chair.getRoleName()));
        Debug.println(getUserRole(Professor.getRoleName()));
        Debug.println(getUserRole(Student.getRoleName()));
        Debug.println(getUserRole(Administrator.getRoleName()));
        Debug.println(getUserRole(Instructor.getRoleName()));
        Debug.println(getUserRole(Staff.getRoleName()));
        Debug.println(getUserRole(Advisor.getRoleName()));
        
        
        Debug.println();
    }
}
