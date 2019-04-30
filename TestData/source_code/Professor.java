package testdata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Generates a profile for a hypothetical professor. The professor will randomly 
 * have between 4 and 20 years worth of classes. Each semester will randomly have 
 * between 0 and 6 classes taught with the probability of the number of classes 
 * being 4 set at 90% and the remaining 10% evenly distributed between the other 
 * values.
 * Requires the Semester class and Course class.
 * @author Shane Panagakos (2019)
 */
public class Professor 
{
    private String firstName;
    private String lastName;
    private String email;
    private int yearsExp;
    private int numberOfClasses;
    private Map<Integer, Integer> semesters;//key: semester value: number of classes taught
    private Course[] courses;//courses taught
    
    /**
     * creates a random professor that meets the statistical requirements.
     */
    public Professor()
    {
        Random r = new Random();
        numberOfClasses = 0;
        firstName = generateName(r, "popularFirstNames.txt");
        lastName = generateName(r, "popularLastNames.txt");
        email = generateEmail();
        yearsExp = generateYearsExp(r);
        semesters = generateSemesters(r);
        
        int j = 0;//number of courses already added to courses array
        courses = new Course[numberOfClasses];
        for (Integer semester : semesters.keySet())
        {
            for(int i=j;i<semesters.get(semester)+j;i++)
                courses[i] = new Course(getName(), semester);
            j+=semesters.get(semester);
        }
    }
    
    /**
     * Randomly selects a name from a list.
     * @param r - a random object
     * @param filePath - a text file that stores names. Each name should be on a 
     * separate line with no extra symbols added.
     * @return a random name
     */
    private String generateName(Random r, String filePath) 
    {
        File file = new File(filePath);
        ArrayList<String> names = new ArrayList<String>();
        
        try 
        {
            Scanner s = new Scanner(file);

            while (s.hasNextLine()) 
            {
                names.add(s.nextLine());
            }
            s.close();
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
        
        return names.get(r.nextInt(names.size()));
    }
    
    /**
     * Generates an email for the professor using the following format:
     * <first initial><first seven characters of last name><@bloomu.edu>
     * @return a bloomu email address.
     */
    private String generateEmail() 
    {
        String s=firstName.substring(0,1);
        
        
        if(lastName.length()<7)
            s+=lastName;
        else
            s+=lastName.substring(0, 6);
        s+="@bloomu.edu";
        
        return s.toLowerCase();
    }

    /**
     * Generates the number of years the professor has been teaching. 
     * Randomly assigned from 4 to 20 years.
     * @param r - random object
     * @return years of experience.
     */
    private int generateYearsExp(Random r) 
    {
        return r.nextInt(17)+4;
    }

    /**
     * Randomly generates Spring and Fall semesters for every year that the 
     * professor taught classes. The semesters will have courses, created by the
     * Course class, associated with them. The Map key is the semester name
     * for example: Spring 2018. The value will be a Course array.
     * @param r - a random object.
     * @return a Map of semesters and the courses taught per semester.
     */
    private Map<Integer, Integer> generateSemesters(Random r) 
    {
        //handles conversion of semester data into semester code
        Semester semester = new Semester();
        //key is the semester code, value is an array of the courses taught.
        HashMap<Integer, Integer> semesters = new HashMap<>();
        //the current year
        int year = semester.getYear();
        //the current month
        int month = Calendar.getInstance().get(Calendar.MONTH);
        //the current day
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        //true represents fall, false represents spring
        boolean sf = true;
        
        //If false, the method did not yet check if the current semester is spring of fall.
        boolean currentSemesterChecked = false;
        //Loops once for every fall and spring semester
        for(int i=0; i<yearsExp*2; i++)
        {
            if(!currentSemesterChecked)
            {
                if(month < 12|| (month == 12 &&day < 15))
                    semesters.put(semester.determineTerm(year, "Fall"), numberOfClasses(r));
                currentSemesterChecked = true;
                sf = !sf;     
            }
            if(sf)
            {
                semesters.put(semester.determineTerm(year, "Fall"), numberOfClasses(r));
                sf = !sf;
            }
            else
            {
                semesters.put(semester.determineTerm(year, "Spring"), numberOfClasses(r));
                year--;
                sf = !sf;
            }
        }
        return semesters;
    }
    
    /**
     * There will randomly be between 0 and 6 
     * classes taught per semester with a 90% chance of there being 4 classes taught 
     * and a 10% chance of the remaining options.
     * @param r - random object
     * @return a random number of years of experience.
     */
    public int numberOfClasses(Random r)
    {
        int courseCountThisSemester;
        
        if(r.nextDouble()<0.9) //90% chance of 4 courses.
        {
            courseCountThisSemester = 4;
        }
        else
        {
            switch(r.nextInt(6))
            {
                case 0: courseCountThisSemester=0;break;
                case 1: courseCountThisSemester=1;break;
                case 2: courseCountThisSemester=2;break;
                case 3: courseCountThisSemester=3;break;
                case 4: courseCountThisSemester=5;break;//has to skip 4 courses.
                default: courseCountThisSemester=6;break;
            }
        }
        numberOfClasses+=courseCountThisSemester;
        return courseCountThisSemester;
    }

    /**
     * @return the full name of the professor.
     */
    public String getName() 
    {
        return firstName+" "+lastName;
    }
    
    /**
     * @return the professor's first name.
     */
    public String getFirstName(){return firstName;}
    
    /**
     * @return the professor's last name.
     */
    public String getLastName(){return lastName;}
    
    /**
     * @return the professor's email address.
     */
    public String getEmail(){return email;}
            
    /**
     * @return the number of years the professor has been teaching.
     */
    public int getYearsExp(){return yearsExp;}
            
    /**
     * @return total number of classes taught.
     */
    public int getClassCount(){return numberOfClasses;}
            
    /**
     * @return a Map of the courses taught by semester. 
     * Key is the semester, values are the courses.
     */
    public Map getSemesters(){return semesters;}
    
    /**
     * @return the array of courses taught by the professors.
     */
    public Course[] getCourses(){return courses;}
    
    /**
     * creates a .csv file of the professor's information
     * Format:
     *  Semester Code,Course Name,A,A-,B+,B,B-,C+,C,C-,D+,D,F,R,W,Total
     *  semesterCode,courseNumber,#,#,#,#,#,#,#,#,#,#,#,#,#,#
     */
    public void createCSV() throws IOException
    {
        FileWriter writer = new FileWriter("testData.csv");
        String[] grades = {"A","A-","B+","B","B-","C+","C","C-","D+","D","F","R","W","Total"};
        
        //sets up the first row of the spreadsheet
        writer.append("Semester,Course,");
        for(int i=0; i<13; i++)
            writer.append(grades[i]+',');
        writer.append(grades[13]+'\n');
        
        //writes the information for each course taught
        for(Course c : courses)
        {
            //starts the next row with the semester code
            writer.append(c.getSemesterCode()+","+c.getCourseName()+',');
            
            //adds the number of grades for the course in the correct column
            for(int i=0; i<13; i++)
                writer.append(Integer.toString(c.getGrade(i))+',');
            writer.append(Integer.toString(c.getGrade(13))+'\n'); 
            
        }
        writer.close();
    }
    
    /**
     * creates a String to output the information of the professor formatted as follows:
     * 
     *  Name: first last
     *  Email: email@bloomu.edu
     *  Experience: # years
     *  Class count: #
     *  Courses by semester:
     */
    @Override
    public String toString()
    {
        String s = "Name: "+getName()+
                   "\nEmail: "+getEmail()+
                   "\nExperience: "+getYearsExp()+
                   " years\nClass count: "+getClassCount()+
                   "\nCourses by semester: \n\n";
        for(Course c : courses)
        {
            s+= c+"\n\n";
        }
        
        return s;
    }
    //Figure out why email is not working.
}
