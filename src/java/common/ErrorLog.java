package common;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents an error log.
 * 
 * @author Riley Hughes (2019)
 */
public class ErrorLog implements Comparable<ErrorLog>, Serializable{
    private int eventID;
    private LocalDateTime eventDate;
    private String level;
    private String logger;
    private String message;
    private String throwable;
    
    /**
     * Constructs an empty error log.
     */
    public ErrorLog() {
        
    }
    
    /**
     * Constructs a new error log with given properties such as an event ID, 
     * event date, error level, logger, error message, and throwable.
     * 
     * @param eventID The number that represents which number error this is
     * (relative to others).
     * 
     * @param eventDate The date and time that this error happened.
     * 
     * @param level The level of severity of this error.
     * 
     * @param logger The logger used to log this error.
     * 
     * @param message The message that appeared when this error happened.
     * 
     * @param throwable The error type thrown.
     */
    public ErrorLog(int eventID, LocalDateTime eventDate, String level,
            String logger, String message, String throwable)
    {
        this.eventID = eventID;
        this.eventDate = eventDate;
        this.level = level;
        this.logger = logger;
        this.message = message;
        this.throwable = throwable;
    }

    /**
     * Gets the event id for this error log. 
     * 
     * @return The event id of this error log. If there is no event id null
     * is returned.
     */
    public int getEventID() {
        return eventID;
    }
    
    /**
     * Sets the event id for this error log. No error checking is performed.
     * 
     * @param eventID 
     */
    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    /**
     * Gets the event date and time for this error log. 
     * 
     * @return The event date and time of this error log. If there is no date 
     * and time null is returned.
     */
    public LocalDateTime getEventDate() {
        return eventDate;
    }
    
    /**
     * Sets the event date for this error log. No error checking is performed.
     * 
     * @param eventDate 
     */
    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * Gets the severity level for this error log. 
     * 
     * @return The severity level of this error log. If there is no severity 
     * level null is returned.
     */
    public String getLevel() {
        return level;
    }
    
    /**
     * Sets the severity level for this error log. No error checking is performed.
     * 
     * @param level 
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * Gets the logger for this error log. 
     * 
     * @return The logger of this error log. If there is no logger null is 
     * returned.
     */
    public String getLogger() {
        return logger;
    }
    
    /**
     * Sets the logger for this error log. No error checking is performed.
     * 
     * @param logger 
     */
    public void setLogger(String logger) {
        this.logger = logger;
    }

    /**
     * Gets the error message for this error log. 
     * 
     * @return The message of this error log. If there is no error message null
     * is returned.
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Sets the message for this error log. No error checking is performed.
     * 
     * @param message 
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the throwable for this error log.
     * 
     * @return The throwable error of this error log. If there is no throwable 
     * error null is returned.
     */
    public String getThrowable() {
        return throwable;
    }

    /**
     * Sets the throwable for this error log. No error checking is performed.
     * 
     * @param throwable 
     */
    public void setThrowable(String throwable) {
        this.throwable = throwable;
    }
    
    /**
     * Returns the hash value of this event ID.
     * 
     * @return The hash value of this event ID. 
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.eventID;
        return hash;
    }
    
    /**
     * Overrides the equals method to compare two <code> ErrorLog</code>s. If
     * the object parameter is not a <code> ErrorLog </code> or is null return
     * false. Otherwise, if the <code> ErrorLog </code> is equal to this <code>
     * ErrorLog </code>, return true.
     * 
     * @param obj The <code> ErrorLog </code> object to be compared to.
     * @return Whether or not the <code> ErrorLog </code> given is equal to this
     * <code> ErrorLog </code>.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final ErrorLog other = (ErrorLog) obj;
        return this.eventID == other.eventID;
    }
    
    /**
     * Returns the order of an <code> ErrorLog </code> compared to this <code>
     * ErrorLog </code>.
     * 
     * @param o the <code> ErrorLog </code> to compare to this <code> ErrorLog
     * </code>.
     * @return The order of the given <code> ErrorLog </code> relative to this
     * <code> ErrorLog </code>. Positive for before, zero for equal, and
     * negative for after.
     */
    @Override
    public int compareTo(ErrorLog o) {
        int order;
        
        if (eventID > o.eventID)
            order = 1;
        else if (eventID == o.eventID)
            order = 0;
        else
            order = -1;
        
        if (order != 0) {
            return order;
        }
        return order;
    }
    
    @Override
    public String toString() {
        return "ErrorLog{" + "eventID=" + eventID + ", eventDate=" + eventDate
                + ", level=" + level + ", logger=" + logger + ", message=" +
                message + ", throwable=" + throwable + "}";
    }
}