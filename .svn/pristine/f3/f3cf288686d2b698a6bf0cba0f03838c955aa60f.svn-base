package common;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;

/**
 * Represents an item of work experience submitted by the user.
 * 
 * @author Gryphon Ayers (2019)
 */
public class WorkExp {
    private int workID; // primary key
    private int application;
    private String institution;
    private String title;
    private String experienceDates;
    private boolean isFullTime;
    private int yearsOfService;

    public WorkExp(int workID) {
        this.workID = workID;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExperienceDates() {
        return experienceDates;
    }

    public void setExperienceDates(String experienceDates) {
        this.experienceDates = experienceDates;
    }

    public boolean isFullTime() {
        return isFullTime;
    }

    public void setIsFullTime(boolean isFullTime) {
        this.isFullTime = isFullTime;
    }

    public int getWorkID() {
        return workID;
    }

    public int getYearsOfService() {
        return yearsOfService;
    }

    public void setYearsOfService(int yearsOfService) {
        this.yearsOfService = yearsOfService;
    }
    
    public void printSummaryLabels(JspWriter out) throws IOException {
        out.println("<th>"+"Institution"+"</th>");
        out.println("<th>"+"Title"+"</th>");
        out.println("<th>"+"Dates"+"</th>");
        out.println("<th>"+"Years"+"</th>");
        out.println("<th>"+"Full-time"+"</th>");
        out.println("<th>"+"Action"+"</th>");
    }

    public void printCompleteLabels(JspWriter out) throws IOException {
        out.println("<td>"+"ID"+"</td>");
        out.println("<td>"+"AppID"+"</td>");
        out.println("<td>"+"Institution"+"</td>");
        out.println("<td>"+"Title"+"</td>");
        out.println("<td>"+"Dates"+"</td>");
        out.println("<td>"+"Years"+"</td>");
        out.println("<td>"+"Full-time"+"</td>");
    }

    public void printSummaryRow(JspWriter out) throws IOException {
        out.println("<td>"+institution+"</td>");
        out.println("<td>"+title+"</td>");
        out.println("<td>"+
                utilities.FormattingUtility.rearrangeDate(experienceDates.substring(0, experienceDates.indexOf("%")))
                +" - "+utilities.FormattingUtility.rearrangeDate(experienceDates.substring(experienceDates.indexOf("%")+1))+"</td>");
        out.println("<td>"+yearsOfService+"</td>");
        out.println("<td>"+(isFullTime ? "Y" : "N")+"</td>");
    }

    public void printCompleteRow(JspWriter out) throws IOException {
        out.println("<td>"+workID+"</td>");
        out.println("<td>"+application+"</td>");
        out.println("<td>"+institution+"</td>");
        out.println("<td>"+title+"</td>");
        out.println("<td>"+
                utilities.FormattingUtility.rearrangeDate(experienceDates.substring(0, experienceDates.indexOf("%")))
                +" - "+utilities.FormattingUtility.rearrangeDate(experienceDates.substring(experienceDates.indexOf("%")+1))+"</td>");
        out.println("<td>"+yearsOfService+"</td>");
        out.println("<td>"+(isFullTime ? "Y" : "N")+"</td>");
    }
}
    
