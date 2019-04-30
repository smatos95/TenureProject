package common;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;

/** 
 * Represents a record of experience earned teaching courses at Bloomsburg 
 * University.
 *
 * @author Gryphon Ayers (2019)
 */
public class LocalExp implements Tableable {
    private final int localExpID; // primary key
    private int application; // foreign key - refers to applicationID
    private String courseName;
    private String courseCode;
    private String semester;
    private String year;
    private String sections;

    public LocalExp(int localExpID) {
        this.localExpID = localExpID;
    }

    public int getLocalExpID() {
        return localExpID;
    }

    public int getApplication() {
        return application;
    }

    public void setApplication(int application) {
        this.application = application;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSections() {
        return sections;
    }

    public void setSections(String sections) {
        this.sections = sections;
    }

    @Override
    public void printSummaryLabels(JspWriter out) throws IOException {
        out.println("<td>"+"Semester"+"</td>");
        out.println("<td>"+"Year"+"</td>");
        out.println("<td>"+"Course"+"</td>");
        out.println("<td>"+"Course Name"+"</td>");
        out.println("<td>"+"Sections"+"</td>");
    }

    @Override
    public void printCompleteLabels(JspWriter out) throws IOException {
        out.println("<td>"+"ID"+"</td>");
        out.println("<td>"+"Semester"+"</td>");
        out.println("<td>"+"Year"+"</td>");
        out.println("<td>"+"Course"+"</td>");
        out.println("<td>"+"Course Name"+"</td>");
        out.println("<td>"+"Sections"+"</td>");
    }

    @Override
    public void printSummaryRow(JspWriter out) throws IOException {
        out.println("<td>"+semester+"</td>");
        out.println("<td>"+year+"</td>");
        out.println("<td>"+courseCode+"</td>");
        out.println("<td>"+courseName+"</td>");
        out.println("<td>"+sections+"</td>");
        
    }

    @Override
    public void printCompleteRow(JspWriter out) throws IOException {
        out.println("<td>"+localExpID+"</td>");
        out.println("<td>"+application+"</td>");
        out.println("<td>"+semester+"</td>");
        out.println("<td>"+year+"</td>");
        out.println("<td>"+courseCode+"</td>");
        out.println("<td>"+courseName+"</td>");
        out.println("<td>"+sections+"</td>");
    }
}
