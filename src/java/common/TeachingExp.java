package common;

import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.jsp.JspWriter;

/**
 * Represents a record of teaching experience earned outside of Bloomsburg 
 * University.
 * 
 * @author Gryphon Ayers (2019)
 */
public class TeachingExp implements Tableable {
    private int teachingID; // primary key
    private int application; // foreign key - refers to applicationID
    private String institution;
    private String rank;
    private String teachingDates;
    private String yearsOfService;
    private boolean isFullTime;

    public TeachingExp(int teachingID) {
        this.teachingID = teachingID;
    }

    public int getTeachingID() {
        return teachingID;
    }

    public int getApplication() {
        return application;
    }

    public void setApplication(int application) {
        this.application = application;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTeachingDates() {
        return teachingDates;
    }

    public void setTeachingDates(String teachingDates) {
        this.teachingDates = teachingDates;
    }

    public String getYearsOfService() {
        return yearsOfService;
    }

    public void setYearsOfService(String yearsOfService) {
        this.yearsOfService = yearsOfService;
    }

    public boolean isFullTime() {
        return isFullTime;
    }

    public void setIsFullTime(boolean isFullTime) {
        this.isFullTime = isFullTime;
    }

    @Override
    public void printSummaryLabels(JspWriter out) throws IOException {
        out.println("<th>"+"Institution"+"</th>");
        out.println("<th>"+"Rank"+"</th>");
        out.println("<th>"+"Dates"+"</th>");
        out.println("<th>"+"Years"+"</th>");
        out.println("<th>"+"Full-time"+"</th>");
        out.println("<th>"+"Action"+"</th>");
    }

    @Override
    public void printCompleteLabels(JspWriter out) throws IOException {
        out.println("<td>"+"ID"+"</td>");
        out.println("<td>"+"AppID"+"</td>");
        out.println("<td>"+"Institution"+"</td>");
        out.println("<td>"+"Rank"+"</td>");
        out.println("<td>"+"Dates"+"</td>");
        out.println("<td>"+"Years"+"</td>");
        out.println("<td>"+"Full-time"+"</td>");
    }

    @Override
    public void printSummaryRow(JspWriter out) throws IOException {
        out.println("<td>"+institution+"</td>");
        out.println("<td>"+rank+"</td>");
        out.println("<td>"+
                utilities.FormattingUtility.rearrangeDate(teachingDates.substring(0, teachingDates.indexOf("%")))
                +" - "+utilities.FormattingUtility.rearrangeDate(teachingDates.substring(teachingDates.indexOf("%")+1))+"</td>");
        out.println("<td>"+yearsOfService+"</td>");
        out.println("<td>"+(isFullTime ? "Y" : "N")+"</td>");
    }

    @Override
    public void printCompleteRow(JspWriter out) throws IOException {
        out.println("<td>"+teachingID+"</td>");
        out.println("<td>"+application+"</td>");
        out.println("<td>"+institution+"</td>");
        out.println("<td>"+rank+"</td>");
        out.println("<td>"+
                utilities.FormattingUtility.rearrangeDate(teachingDates.substring(0, teachingDates.indexOf("%")))
                +" - "+utilities.FormattingUtility.rearrangeDate(teachingDates.substring(teachingDates.indexOf("%")+1))+"</td>");
        out.println("<td>"+yearsOfService+"</td>");
        out.println("<td>"+(isFullTime ? "Y" : "N")+"</td>");
    }
}
