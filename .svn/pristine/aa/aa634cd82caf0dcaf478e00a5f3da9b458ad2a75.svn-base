package testdata;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

/**
 * <code>Semester</code> represents a semester in an academic institution. The 
 * semester includes the term number (2192), the session (1), the semester name (Fall, 
 * Spring, Summer, Winter), and the year (2019).
 * 

 * @author cjones
 */
public class Semester implements Comparable<Semester>, Serializable {
    private int term; // 2192 for Spring 2019
    private int session; // normally = 1, can be 2 or 3 in the summer
    private String semesterName; // Fall , Spring , Summer, Winter
    private int year;
    
    /**
     * Constructs a <code>Semester</code> object with the information coming
     * from the term closest to today.
     */
    public  Semester(){
      //consider term closest to today   
        LocalDate now = LocalDate.now();
        this.year= now.getYear();
        int month = now.getMonth().getValue();
        int day = now.getDayOfMonth();
        session = 1;
        if(month == 7|| (month == 8 && day< 15)) session = 3;
        if(month == 6 || (month == 5 && day >=15)) session = 2;
        term = (year / 1000 ) * 1000 +(year % 1000)*10;
        if(month == 1 && day<15) {semesterName = "Winter"; year = year -1; term += 8;}
        else if(month < 5 || (month == 5 &&day < 15)){semesterName = "Spring"; term +=2;}
        else if(month < 8 || (month == 8 &&day < 15)){semesterName = "Summer"; term += 4;}
        else if (month < 12|| (month == 12 &&day < 15)){semesterName = "Fall"; term +=6;}
        else {semesterName = "Winter"; term += 8;}
    }
    
    public Semester(String term){
        this(Integer.parseInt(term),1);
    }
    
    /**
     * Constructs a <code>Semester</code> object with a user given year, month
     * and day. If a month or day is incorrect, it will default to todays date.
     * @param year Given year
     * @param month Given year
     * @param day Given day
     */
    public  Semester(int year, int month, int day){
        if (month < 1 || month > 12 || day < 1 || day > 31)
        {
            LocalDate now = LocalDate.now();
            this.year= now.getYear();
            month = now.getMonth().getValue();
            day = now.getDayOfMonth();
        }
        else
            this.year = year;
        
        session = 1;
        if(month == 7|| (month == 8 && day< 15)) session = 3;
        if(month == 6 || (month == 5 && day >=15)) session = 2;
        term = (year / 1000 ) * 1000 +(year % 1000)*10;
        if(month == 1 && day<15) {semesterName = "Winter"; year = year -1; term += 8;}
        else if(month < 5 || (month == 5 &&day < 15)){semesterName = "Spring"; term +=2;}
        else if(month < 8 || (month == 8 &&day < 15)){semesterName = "Summer"; term += 4;}
        else if (month < 12|| (month == 12 &&day < 15)){semesterName = "Fall"; term +=6;}
        else {semesterName = "Winter"; term += 8;}
    }
    
   /**
     * Constructs a <code>Semester</code> using a term number and the session.
     * @param term The term number
     * @param session  The session, should be 1, can be 2 or 3 for summer.
     */
    public Semester(int term, int session){
        this.term = term;
        this.session = session;
        semesterName = determineSemesterName(term);
        term = term / 10;
        if (term < 1000) {
            year = 2000 + term%100;
        } else {
            year = 1900 + term%100;
        }
    }
    
   /**
     * Constructs a <code>Semester</code> using the semester name (Spring, 
     * Summer, Fall, Winter), the year, and the session.
     * @param semesterName The name of the <code>Semester</code>.
     * @param year The year of the <code>Semester</code>.
     * @param session The session of the <code>Semester</code>.
     */
    public  Semester(String semesterName, int year, int session){
        this.semesterName = semesterName;
        this.year = year;
        this.session = session;
        this.term = determineTerm(year,semesterName);
    }
    
    /**
     * Returns the current semester or the next semester to start if we are between semesters.
     * For example, in April of 2015 we are in term 2152 or Spring 2015. 
     * On May 15th the answer will be term 2154 (Session 1)
     * @return The current Semester or the next semester to start if we are between semesters. 
     */
    public static Semester getCurrentSemester(){
        Semester semester = new Semester();
        return semester; 
    }
    
    /**
     * Gets the  <code>Semester</code> we are currently scheduling. 
     * So if it is April 2015 students are creating schedules for Fall 2015. 
     * Returns either Fall or Spring Semesters only
     *
     * @return The next <code>Semester</code> excluding summer and winter
     * sessions
     */
    public static Semester getSemesterForScheduling() {
        Semester result = new Semester();
        result.setSession(1);
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonth().getValue();
        int day = now.getDayOfMonth();
        int term = (year / 1000) * 1000 + (year % 1000) * 10;
        String semesterName;

        if (month == 1) {
            semesterName = "Spring";
            term += 2;
        //    System.out.println("month == 1");
        } 
        else if (month <= 8 || (month == 9 && day < 15)) {
            semesterName = "Fall";
            term += 6;//2150 becomes 2162
        //    System.out.println("month <= 8 or month ==9");
        } 
        else {
            semesterName = "Spring";
            term += 12;//2150 becomes 2162
            year += 1;
      //      System.out.println("next spring");
        } 

        result.setSemesterName(semesterName);
        result.setYear(year);
        result.setTerm(term);
        return result;
    }

    /**
     * Calculates the <code>Semester</code> that comes after this one. Session
     * currently will stay 1 even if in summer (ie. if current 
     * semester is Fall 2015 next semester will be Winter 2015) 
     * @return <code>Semester</code> that comes after this one.
     */
    public Semester getNextSemester() {
        int nextTerm = this.term % 10;
        
        if(nextTerm != 8) {
            nextTerm = this.term + 2;
        }
        else  {            
            nextTerm = (this.term / 10 + 1) * 10 + 2;
        }
        
        return new Semester(nextTerm, 1);
    }
    
    /**
     * Returns a semester name given a term number.
     * @param term The term number used to determine the semester name.
     * @return The semester name for the given term.
     */
    public static final String determineSemesterName(int term) {
       String termName="";
       switch(term%10){
            case 2: termName = "Spring"; break;
            case 4: termName = "Summer"; break;
            case 6: termName = "Fall"; break;
            case 8: termName = "Winter"; break;
        }
       return termName; 
    }
    
       
    /**
     * Returns a term number given a year and a semester name.
     * @param year The year this semester takes place in.
     * @param semesterName The name of the semester in question.
     * @return The term number for the semester in question.
     */   
    public int determineTerm(int year, String semesterName){
        int localTerm = (year/1000)*1000 +(year%1000)*10   ;
        switch (semesterName){
            case "Spring": localTerm+=2;break;
            case "Summer": localTerm+=4;break; 
            case "Fall": localTerm+=6;break;
            case "Winter": localTerm+=8;break;
        }
        return localTerm;
    }

    /**
     * Returns the term for this <code>Semester</code>  an example is 2152.
     * @return The term for this <code>Semester</code>.
     */
    public int getTerm(){
        return this.term;
    }
    
    /**
     * Sets the term for this <code>Semester</code>.
     * No error checking is performed.
     * @param term The new term for this <code>Semester</code>.
     */
    public void setTerm(int term){
        this.term = term; 
    }
    
    /**
     * Returns the session for this <code>Semester</code>. Should be 1, can be 
     * 2 or 3 for the Summer.
     * @return The session for this <code>Semester</code>.
     */
    public int getSession() {
        return session;
    }

    /**
     * Sets the session for this <code>Semester</code>. Should be 1, can be 2 
     * or 3 for the Summer.
     * No error checking is performed.
     * @param session The new session for this <code>Semester</code>.
     */
    public void setSession(int session) {
        this.session = session;
    }
    
    /**
     * Returns the semester name for this <code>Semester</code>.
     * @return The semester name for this <code>Semester</code>.
     */
    public String getSemesterName(){
        return this.semesterName;
    }


    /**
     * Sets the semester name for this <code>Semester</code>. Should be one of
     * the following: Spring, Summer, Fall, Winter.
     * No error checking is performed.
     * @param semesterName The name of the Semester.
     */
    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    /**
     * Returns the year of this <code>Semester</code>.
     * @return The year of this <code>Semester</code>.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year of this <code>Semester</code>.
     * No error checking is performed.
     * @param year The new year of this <code>Semester</code>.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns a string representation of this <code>Semester</code>.
     * @return A string representation of this this <code>Semester</code>.
     */
    @Override
    public String toString() {
        return semesterName +" " +  year;
    }

    /**
     * Returns a hash code for this <code>Semester</code>. This hash 
     * code is dependent on the term, the session, and the year.
     * 
     * @return A unique value representing this <code>Semester</code>. 
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.term;
        hash = 41 * hash + this.session;
        hash = 41 * hash + this.year;
        return hash;
    }

    /**
     * Determines if this <code>Semester</code> is the same as another 
     * object. Equality is defined by having the same term, session, and year.
     * @param obj The object to compare with this <code>Semester</code>.
     * @return True if obj is not null, is a <code>Semester</code>, and this 
     * <code>Semester</code> and obj have the same term, session, and year. 
     * False if otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
   //     Debug.println("1");
        if (getClass() != obj.getClass()) return false;
   //    Debug.println("2");
        final Semester other = (Semester) obj;
        if (this.term != other.term) return false;
   //    Debug.println("3");
        if (this.session != other.session) return false;
    //    Debug.println("4");
        return this.year == other.year;
    }

    /**
     * Determines a natural order among objects of type <code>Semester</code>.
     * @param sem The <code>Semester</code> to compare this 
     * <code>Semester</code> with.
     * @return 0 if <code>sem</code> and this <code>Semester</code> are equal, a positive 
     * integer if this <code>Semester</code> happened after <code>sem</code>, and a 
     * negative integer if this <code>Semester</code> happened before <code>sem</code>.
     */
    @Override
    public int compareTo(Semester sem) {
        Semester semester = (Semester) sem;
        if(this.year != semester.year) return this.year - semester.year;
        if(this.term != semester.term) return this.term - semester.term;
        if(this.session != semester.session) return this.session - semester.session;
        return 0; 
    }
    
//    /**
//     * Performs a number of tests on the Semester class.
//     * @param args The command line arguments.
//     */
//    public static void main(String[] args) {
//        Semester semester_test = new Semester(2016, 4, 15);
//        Debug.println(Semester.determineSemesterName(semester_test.term));
//        Debug.println(semester_test.term);
//        Debug.println("Constructor Testing");
//        // testing all three constructors for this semester.
//        Semester semester1 = new Semester();
//        Debug.println(semester1);
//        Semester semester2 = new Semester(2152,1);
//        Debug.println(semester2);
//        Semester semester3 = new Semester("Spring", 2015, 1);
//        Debug.println(semester3);
//        
//        Debug.println("**********");
//        
//        // testing the (name,year,session) constructor for the last 4 semesters.
//        Semester spring2015_1 = new Semester("Spring", 2015, 1);
//        Semester winter2014_1 = new Semester("Winter", 2014, 1);
//        Semester fall2014_1 = new Semester("Fall", 2014, 1);
//        Semester summer2014_1 = new Semester("Summer", 2014, 1);
//        
//        LinkedList<Semester> semesters1 = new LinkedList<>();
//        semesters1.add(spring2015_1);
//        semesters1.add(winter2014_1);
//        semesters1.add(fall2014_1);
//        semesters1.add(summer2014_1);
//        
//        for (Semester sem : semesters1) {
//            Debug.println(sem);
//        }
//        
//        Debug.println("**********");
//        
//        // testing the (term,session) constructor for the last 4 semesters.
//        Semester spring2015_2 = new Semester(2152,1);
//        Semester winter2014_2 = new Semester(2148,1);
//        Semester fall2014_2 = new Semester(2146,1);
//        Semester summer2014_2 = new Semester(2144,1);
//        
//        LinkedList<Semester> semesters2 = new LinkedList<>();
//        semesters2.add(spring2015_2);
//        semesters2.add(winter2014_2);
//        semesters2.add(fall2014_2);
//        semesters2.add(summer2014_2);
//        
//        for (Semester sem : semesters2)
//        {
//            Debug.println(sem);
//        }
//        
//        Debug.println("***************");
//        Debug.println("Testing the equals method");
//        for (Semester sem1 : semesters1)
//        {
//            for (Semester sem2 : semesters2)
//            {
//                if (sem1.equals(sem2))
//                {
//                    Debug.print("These should be the same:");
//                    Debug.println(sem1 + " " + sem2);
//                }
//                else
//                {
//                    Debug.print("These should be different:");
//                    Debug.println(sem1 + " " + sem2);
//                }
//            }
//        }
//        Debug.println("***************");
//        Debug.println("Testing the compareTo method");
//        for (Semester sem1 : semesters1)
//        {
//            for (Semester sem2 : semesters2)            {
//                Debug.println(sem1 + " " + sem2 + " " + sem1.compareTo(sem2));
//            }
//        }
//    }
}
