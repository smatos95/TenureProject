package common;

import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.jsp.JspWriter;

/** Represents a record of the grades for a course section.
 * 
 * @author Gryphon Ayers (2019)
 */
public class Grades implements Tableable {
    private final int gradesID; //primary key
    private final int application; // foreign key - refers to applicationID
    private String semester;
    private Short year; // 4 digit year
    private int course;
    private String section;
    private int instructor; // foreign key - refers to userNumber
    private int gradeA;
    private int gradeAminus;
    private int gradeBplus;
    private int gradeB;
    private int gradeBminus;
    private int gradeCplus;
    private int gradeC;
    private int gradeCminus;
    private int gradeDplus;
    private int gradeD;
    private int gradeF;
    private int gradeR;
    private int gradeW;
    
    /**
     * The column index of the first column to contain grade data.
     * Use this constant when storing and loading grade statistics from the
     * database.
     */
    public static final int GRADE_DATA_COLUMN_OFFSET = 8;
    /**
     * The number of columns used to store grade data.
     * Use this constant when storing and loading grade statistics from the
     * database.
     */
    public static final int GRADE_DATA_COLUMN_COUNT = 13;

    public Grades(int gradesID, int application) {
        this.gradesID = gradesID;
        this.application = application;
    }

    public int getGradesID() {
        return gradesID;
    }

    public int getApplication() {
        return application;
    }
    
    public void setGradeData(int[] grades) {
        this.gradeA = grades[0];
        this.gradeAminus = grades[1];
        this.gradeBplus = grades[2];
        this.gradeB = grades[3];
        this.gradeBminus = grades[4];
        this.gradeCplus = grades[5];
        this.gradeC = grades[6];
        this.gradeCminus = grades[7];
        this.gradeDplus = grades[8];
        this.gradeD = grades[9];
        this.gradeF = grades[10];
        this.gradeR = grades[11];
        this.gradeW = grades[12];
    }
    
    public int[] getGradeData() {
        return new int[]{ this.gradeA, this.gradeAminus, this.gradeBplus, this.gradeB,
        this.gradeBminus, this.gradeCplus, this.gradeC, this.gradeCminus,
        this.gradeDplus, this.gradeD, this.gradeF, this.gradeR, this.gradeW };
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getInstructor() {
        return instructor;
    }

    public void setInstructor(int instructor) {
        this.instructor = instructor;
    }

    @Override
    public void printSummaryLabels(JspWriter out) throws IOException {
        out.println("<td>" + "Course" + "</td>");
        out.println("<td>" + "Semester" + "</td>");
        out.println("<td>" + "Year" + "</td>");
        out.println("<td>" + "Section" + "</td>");
        out.println("<td>" + "A" + "</td>");
        out.println("<td>" + "A-" + "</td>");
        out.println("<td>" + "B+" + "</td>");
        out.println("<td>" + "B" + "</td>");
        out.println("<td>" + "B-" + "</td>");
        out.println("<td>" + "C+" + "</td>");
        out.println("<td>" + "C" + "</td>");
        out.println("<td>" + "C-" + "</td>");
        out.println("<td>" + "D" + "</td>");
        out.println("<td>" + "F" + "</td>");
        out.println("<td>" + "R" + "</td>");
        out.println("<td>" + "W" + "</td>");
    }

    @Override
    public void printCompleteLabels(JspWriter out) throws IOException {
        out.println("<td>" + "ID" + "</td>");
        out.println("<td>" + "Application" + "</td>");
        out.println("<td>" + "Instructor" + "</td>");
        out.println("<td>" + "Course" + "</td>");
        out.println("<td>" + "Semester" + "</td>");
        out.println("<td>" + "Year" + "</td>");
        out.println("<td>" + "Section" + "</td>");
        out.println("<td>" + "A" + "</td>");
        out.println("<td>" + "A-" + "</td>");
        out.println("<td>" + "B+" + "</td>");
        out.println("<td>" + "B" + "</td>");
        out.println("<td>" + "B-" + "</td>");
        out.println("<td>" + "C+" + "</td>");
        out.println("<td>" + "C" + "</td>");
        out.println("<td>" + "C-" + "</td>");
        out.println("<td>" + "D" + "</td>");
        out.println("<td>" + "F" + "</td>");
        out.println("<td>" + "R" + "</td>");
        out.println("<td>" + "W" + "</td>");
    }

    @Override
    public void printSummaryRow(JspWriter out) throws IOException {
        out.println("<td>" + course + "</td>");
        out.println("<td>" + semester + "</td>");
        out.println("<td>" + year + "</td>");
        out.println("<td>" + section + "</td>");
        out.println("<td>" + gradeA + "</td>");
        out.println("<td>" + gradeAminus + "</td>");
        out.println("<td>" + gradeBplus + "</td>");
        out.println("<td>" + gradeB + "</td>");
        out.println("<td>" + gradeBminus + "</td>");
        out.println("<td>" + gradeCplus + "</td>");
        out.println("<td>" + gradeC + "</td>");
        out.println("<td>" + gradeCminus + "</td>");
        out.println("<td>" + gradeD + "</td>");
        out.println("<td>" + gradeF + "</td>");
        out.println("<td>" + gradeR + "</td>");
        out.println("<td>" + gradeW + "</td>");
    }

    @Override
    public void printCompleteRow(JspWriter out) throws IOException {
        out.println("<td>" + gradesID + "</td>");
        out.println("<td>" + application + "</td>");
        out.println("<td>" + instructor + "</td>");
        out.println("<td>" + course + "</td>");
        out.println("<td>" + semester + "</td>");
        out.println("<td>" + year + "</td>");
        out.println("<td>" + section + "</td>");
        out.println("<td>" + gradeA + "</td>");
        out.println("<td>" + gradeAminus + "</td>");
        out.println("<td>" + gradeBplus + "</td>");
        out.println("<td>" + gradeB + "</td>");
        out.println("<td>" + gradeBminus + "</td>");
        out.println("<td>" + gradeCplus + "</td>");
        out.println("<td>" + gradeC + "</td>");
        out.println("<td>" + gradeCminus + "</td>");
        out.println("<td>" + gradeD + "</td>");
        out.println("<td>" + gradeF + "</td>");
        out.println("<td>" + gradeR + "</td>");
        out.println("<td>" + gradeW + "</td>");
    }
}
