package common;

import java.io.Serializable;
import java.time.LocalDateTime;
import utilities.Debug;

/**
 * Represents a user from Bloomsburg University.
 *
 * @author Curt Jones (2017)
 */
public class BuUser  implements Comparable<BuUser>, Serializable {

    private int userNumber; //Primary Key -- student id or employee id
    private String loginName; 
    private String lastName;
    private String firstName;
    private String emailAddress;
    private String displayName;
    private UserRole userRole;
    private LocalDateTime lastLoginTime;//time and date of last login
    private int loginCount; // how many successful logins
    private boolean buUser ;//True for BU User - false for all others 

    /**
     * Constructs an empty user.
     */
    public BuUser() {
        buUser= true;
    }

    /**
     * Constructs a new user with given properties such as a user number, login
     * name, password, last name, first name, email address, department, user
     * role, last login, and login count.
     *
     * @param userNumber A number that represents this user. This number is the
     * primary key in our database and is auto incremented.
     *
     * @param loginName The name that this user uses to login to the system.
     *
     * @param lastName The last name of this user.
     *
     * @param firstName The first name of this user.
     *
     * @param emailAddress The university email of this user.
     *
     * @param displayName The preferred way to display this user's name.
     * 
     * @param userRole The <code>UserRole</code> of this user. This sets which
     * privileges user has. Determines which screens and controls the user will
     * have access to.
     *
     * @param lastLogin The last time this user logged in.
     *
     * @param loginCount The number of attempted logins by this user. If the
     * user fails three times to login, the account will be locked until a chair
     * unlocks it.
     */
    public BuUser(int userNumber, String loginName, String lastName, String firstName, 
            String emailAddress, String displayName, UserRole userRole, 
            LocalDateTime lastLogin, int loginCount, boolean buUser){ 
        this.userNumber = userNumber;
        this.loginName = loginName.trim();
        this.lastName = lastName.trim();
        this.firstName = firstName.trim();
        this.emailAddress = emailAddress.trim();
        this.displayName = displayName.trim(); 
        this.userRole = userRole;
        this.lastLoginTime = lastLogin;
        this.loginCount = loginCount;
        this.buUser = buUser;
    }

    
    /**
     * Gets the email address of this user.
     *
     * @return The email address of this user. If there is no email address null
     * is returned.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the email address for this user. No error checking is performed.
     *
     * @param emailAddress The email address that is set for this user.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress.trim();
    }

    /**
     * Gets the user number of this user. This is the unique identifier for this
     * user.
     *
     * @return The user number of this user. If there is no user number null is
     * returned.
     */
    public int getUserNumber() {
        return userNumber;
    }

    /**
     * Sets the user number for this user. No error checking is performed.
     *
     * @param userNumber The user number that is set for this user.
     */
    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    /**
     * Gets the last name of this user.
     *
     * @return The last name of the user. If no last name exists null is
     * returned.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of this user. No error checking is performed.
     *
     * @param lastName The last name that is set to this user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    /**
     * Gets the first name of this user.
     *
     * @return The first name of this user. If no first name exists null is
     * returned.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of this user. No error checking is performed.
     *
     * @param firstName The first name that is set to this user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    /**
     * Gets the <code>UserRole</code> for this user. This consists of an
     * enumeration of whether the student is a SystemAdmin, Administrator,
     * Chair, Advisor, Instructor, Staff, Student, or Guest.
     *
     * @return The <code>UserRole</code> for this user. If no
     * <code>UserRole</code> is set null is returned.
     */
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * Sets the <code> UserRole </code> for this user. No error checking is
     * performed.
     *
     * @param userRole The <code>UserRole</code> that is set to this user.
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    /**
     * Gets the login name of this user.
     *
     * @return The login name of this user. If there is no login name null is
     * returned.
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * Sets the login name of this user. No error checking is performed.
     *
     * @param loginName The login name that is set for this user.
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName.trim();
    }


    /**
     * Gets the last time the user logged in.
     *
     * @return The last time this user logged in. If no last time logged in
     * exists null is returned.
     */
    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * Sets the last time the user logged in. This will be set right when the
     * user logs into the system.
     *
     * @param lastLoginTime The last time this user logged in.
     */
    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }



    /**
     * Gets the number of log in attempts from this user. Only failed attempts
     * count and this gets reset when user logins successfully.
     *
     * @return The number of failed login attempts.
     */
    public int getLoginCount() {
        return loginCount;
    }

    /**
     * Sets the number of failed login attempts. No error checking is performed.
     *
     * @param loginCount The number of failed attempts for this user.
     */
    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }



    /**
     * Returns the hash value of this user number.
     *
     * @return The hash value of this user number.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.userNumber;
        return hash;
    }

    /**
     * Overrides the equals method to compare two <code> User </code>s. If the
     * object parameter is not a <code> User </code> or is null return false.
     * Otherwise, if the <code> User </code> is equal to this <code> User
     * </code>, return true.
     *
     * @param obj The <code> User </code> object to be compared to.
     * @return Whether or not the <code> User </code> given is equal to this <code> User
     * </code>.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final BuUser other = (BuUser) obj;
        return this.userNumber == other.userNumber;
    }

    /**
     * Returns the order of a <code> User </code> compared to this <code> User
     * </code>.
     *
     * @param o The <code> User </code> to compare to this <code> User </code>.
     * @return The order of the given <code> User </code> relative to this <code> User
     * </code>. Positive for before, zero for equal, and negative for after.
     */
    @Override
    public int compareTo(BuUser o) {
        int order = lastName.compareToIgnoreCase(o.lastName);
        if (order != 0) {
            return order;
        }
        order = firstName.compareToIgnoreCase(o.firstName);
        return order;
    }

    @Override
    public String toString() {
        return "User{" + "userNumber=" + userNumber + ", loginName=" + loginName
                 + ", lastName=" + lastName
                + ", firstName=" + firstName + ", emailAddress=" + emailAddress
                + ", userRole="  + userRole + ", lastLogin=" + lastLoginTime + 
                ", Login Count="+loginCount +", BuUser = "+buUser
                +"}";
    }

    public boolean isBuUser() {
        return buUser;
    }

    public void setBuUser(boolean buUser) {
        this.buUser = buUser;
    }
    
        public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName.trim();
    }
   
}
