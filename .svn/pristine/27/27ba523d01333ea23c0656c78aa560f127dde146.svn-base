package common;

import java.io.Serializable;
import java.time.LocalDateTime;
import utilities.Debug;

/**
 * Represents a user.
 *
 * @author Curt Jones (2017)
 */
public class User extends BuUser  implements Comparable<BuUser>, Serializable {

    private String userPassword;
    private String salt;
    private UserRole userRole;
    private LocalDateTime lastAttemptedLoginTime;
    private int attemptedLoginCount; // How many consecutive failed login attempts 
    private boolean locked;//If the account is locked out

    /**
     * Constructs an empty user.
     */
    public User() {
        super();
        setBuUser(false); 
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
     * @param password The password that this user uses to login to the system.
     *
     * @param salt The salt used to hash the password for this user
     *
     * @param lastName The last name of this user.
     *
     * @param firstName The first name of this user.
     *
     * @param emailAddress The university email of this user.
     *
     * @param userRole The <code>UserRole</code> of this user. This sets which
     * privileges user has. Determines which screens and controls the user will
     * have access to.
     *
     * @param lastLogin The last time this user logged in.
     *
     * @param lastAttemptedLogin The first time the user attempts to log in in
     * fails within 15 minutes.
     *
     * @param attemptedLoginCount Number of failed logins before user gets
     * locked out.
     *
     * @param loginCount The number of attempted logins by this user. If the
     * user fails three times to login, the account will be locked until a chair
     * unlocks it.
     *
     * @param locked Boolean that represents whether a user is locked out or
     * not.
     */
    public User(int userNumber, String loginName, String password, String salt, 
                String lastName, String firstName, String emailAddress, String displayName,
               UserRole userRole, LocalDateTime lastLogin, LocalDateTime lastAttemptedLogin, 
               int loginCount, int attemptedLoginCount, boolean locked) {
        super(userNumber, loginName, lastName, firstName, emailAddress,displayName, userRole,lastLogin,loginCount,false );
        this.userPassword = password;
        this.lastAttemptedLoginTime = lastAttemptedLogin;
        this.attemptedLoginCount = attemptedLoginCount;
        this.salt = salt;
        this.locked = locked;
        setBuUser(false); 
    }

    /**
     * Gets the number of times a user has attempted to log in and failed in the time
     * period specified in the property file.
     *
     * @return The number of login attempts.
     */
    public int getAttemptedLoginCount() {
        return attemptedLoginCount;
    }

    /**
     * Sets the number of times a user has attempted to log in and fail in a
     * time period specified in the project property file.
     *
     * @param attemptedLoginCount Sets the number of login attempts.
     */
    public void setAttemptedLoginCount(int attemptedLoginCount) {
        this.attemptedLoginCount = attemptedLoginCount;
    }



    /**
     * Gets the password for the login of this user.
     *
     * @return The password for this user. If no password exists null is
     * returned.
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets the password for the login of this user. No error checking is
     * performed.
     *
     * @param userPassword The password that is set for this user.
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Gets the salt for the login of this user.
     *
     * @return The salt for this user. If no password exists null is returned.
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Sets the salt for the login of this user. No error checking is performed.
     *
     * @param salt The salt that is set for this user.
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }


    /**
     * Gets the time of the first time a user attempted to log in and failed.
     * This value will be set when a user fails to log in not within a 15 minute
     * time interval of the last time they fail to log in.
     *
     * @return The last time this user failed to log in not within a certain
     * time interval. If user has not failed to log in this value returns null.
     */
    public LocalDateTime getLastAttemptedLoginTime() {
        return lastAttemptedLoginTime;
    }

    /**
     * Sets the time of the first time a user attempted to log in and failed.
     * This value will be set when a user fails to log in not within a 15 minute
     * time frame of the last time they fail to log in.
     *
     * @param lastAttemptedLogin The time the user failed to log in not within a
     * certain time interval.
     */
    public void setLastAttemptedLoginTime(LocalDateTime lastAttemptedLogin) {
        this.lastAttemptedLoginTime = lastAttemptedLogin;
    }



    /**
     * Returns a boolean value that represents whether the <code>User</code> is
     * locked out of having administrator privileges. If a <code>User</code>
     * attempts and fails to log in 5 consecutive times. They become locked-out.
     *
     * @return True if the user is locked out of the web site, false otherwise.
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Sets the user to be locked out of being able to log in.
     *
     * @param locked True if the <code>User</code> should become locked-out.
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }



    @Override
    public String toString() {
        return "User{" + "userNumber=" + getUserNumber() + ", loginName=" + getLoginName()
                + ", userPassword=" + userPassword + ", salt=" + salt
                + ", lastName=" + getLastName()
                + ", firstName=" + getFirstName() + ", emailAddress=" + getEmailAddress()
                + ", userRole="  + userRole + ", lastLogin=" + getLastLoginTime() 
                + ", attemptedLogin=" + lastAttemptedLoginTime + ", loginCount=" 
                + getLoginCount() + ", locked=" + locked + '}';
    }


    public static void main(String args[]) {
        Debug.setEnabled(true); // To allow output
        User u1 = new User();
        //Needs to be udated 
        u1.setFirstName("Dan");
        u1.setLastName("James");
        u1.setEmailAddress("drj@drj.com");
        u1.setLoginName("drj");
        u1.setLastLoginTime(LocalDateTime.now());
        u1.setLoginCount(30);
        u1.setUserNumber(007);
        u1.setUserPassword("123abc");
        u1.setUserRole(UserRole.Professor);

        User u2 = new User(8, "dtp", "mypasswordisbetter", "12asd21f8a", "Pany", "Dan", "dtp@dtp.com", "Dan Pany",
                UserRole.Professor, LocalDateTime.now(), null, 9001, 0, false);

        Debug.println("FNAME: " + u1.getFirstName());
        Debug.println("LNAME: " + u1.getLastName());
        Debug.println("EMAIL: " + u1.getEmailAddress());
        Debug.println("USERNUMBER: " + u1.getUserNumber());
        Debug.println("LOGINNAME: " + u1.getLoginName());
        Debug.println("PASSWORD: " + u1.getUserPassword());
        Debug.println("USERROLE: " + u1.getUserRole());
        Debug.println("LAST LOGIN: " + u1.getLastLoginTime());
        Debug.println("LOGIN COUNT: " + u1.getLoginCount());

        Debug.println();
        Debug.println("FNAME: " + u2.getFirstName());
        Debug.println("LNAME: " + u2.getLastName());
        Debug.println("EMAIL: " + u2.getEmailAddress());
        Debug.println("USERNUMBER: " + u2.getUserNumber());
        Debug.println("LOGINNAME: " + u2.getLoginName());
        Debug.println("PASSWORD: " + u2.getUserPassword());
        Debug.println("USERROLE: " + u2.getUserRole());
        Debug.println("LAST LOGIN: " + u2.getLastLoginTime());
        Debug.println("LOGIN COUNT: " + u2.getLoginCount());

        Debug.println();
        Debug.println(u1.equals(u1));
        Debug.println(u1.equals(u2));
        Debug.println(u1.compareTo(u1));
        Debug.println(u1.compareTo(u2));
        Debug.println(u2.compareTo(u1));

        Debug.println();
        Debug.println(u1.hashCode());
        Debug.println(u2.hashCode());
    }
}
