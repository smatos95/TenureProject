package utilities;

import common.Application;
import common.Profile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import mysql.ProfileManager;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author Riley Hughes (2019)
 */
public class POIFirstPage {
    public static XWPFDocument generateDoc(Application app, XWPFDocument source, boolean finalSection) {
        ProfileManager pm = new ProfileManager();
        Profile pro = pm.getProfileByUserID(app.getApplicantUser());
        LocalDateTime appointmentDate = pro.getAppointmentDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        String formatDateTime = appointmentDate.format(formatter);
        
        XWPFDocument document;
        if (source == null) {
            document = new XWPFDocument();
        } else {
            document = source;
        }
       
        XWPFParagraph firstPage1 = document.createParagraph();
        firstPage1.setSpacingAfter(-1);
        firstPage1.setSpacingBetween(1.5);
        firstPage1.setPageBreak(true);
        XWPFRun bolded1 = firstPage1.createRun();
        bolded1.setBold(true);
        bolded1.setText("Name:");
        bolded1.addTab();
        bolded1.addTab();
        bolded1.addTab();
        bolded1.addTab();
        bolded1.addTab();
        bolded1.addTab();
        bolded1.addTab();
        bolded1.addTab();
        XWPFRun info1 = firstPage1.createRun();
        info1.setText(app.getCustomFirstName() + " " + app.getCustomLastName());
        
        XWPFParagraph firstPage2 = document.createParagraph();
        firstPage2.setSpacingAfter(-1);
        firstPage2.setSpacingBefore(-1);
        firstPage2.setSpacingBetween(1.5);
        XWPFRun bolded2 = firstPage2.createRun();
        bolded2.setBold(true);
        bolded2.setText("Phone:");
        bolded2.addTab();
        bolded2.addTab();
        bolded2.addTab();
        bolded2.addTab();
        bolded2.addTab();
        bolded2.addTab();
        bolded2.addTab();
        bolded2.addTab();
        XWPFRun info2 = firstPage2.createRun();
        info2.setText(pro.getCampusPhone());
        
        XWPFParagraph firstPage3 = document.createParagraph();
        firstPage3.setSpacingAfter(-1);
        firstPage3.setSpacingBefore(-1);
        firstPage3.setSpacingBetween(1.5);
        XWPFRun bolded3 = firstPage3.createRun();
        bolded3.setBold(true);
        bolded3.setText("Rank Being Requested:");
        bolded3.addTab();
        bolded3.addTab();
        bolded3.addTab();
        bolded3.addTab();
        bolded3.addTab();
        bolded3.addTab();
        XWPFRun info3 = firstPage3.createRun();
        info3.setText(pro.getRank());
        
        XWPFParagraph firstPage4 = document.createParagraph();
        firstPage4.setSpacingAfter(-1);
        firstPage4.setSpacingBefore(-1);
        firstPage4.setSpacingBetween(1.5);
        XWPFRun bolded4 = firstPage4.createRun();
        bolded4.setBold(true);
        bolded4.setText("Department:");
        bolded4.addTab();
        bolded4.addTab();
        bolded4.addTab();
        bolded4.addTab();
        bolded4.addTab();
        bolded4.addTab();
        bolded4.addTab();
        XWPFRun info4 = firstPage4.createRun();
        info4.setText(app.getDepartment());
        
        XWPFParagraph firstPage5 = document.createParagraph();
        firstPage5.setSpacingAfter(-1);
        firstPage5.setSpacingBefore(-1);
        firstPage5.setSpacingBetween(1.5);
        XWPFRun bolded5 = firstPage5.createRun();
        bolded5.setBold(true);
        bolded5.setText("Chairperson:");
        bolded5.addTab();
        bolded5.addTab();
        bolded5.addTab();
        bolded5.addTab();
        bolded5.addTab();
        bolded5.addTab();
        bolded5.addTab();
        XWPFRun info5 = firstPage5.createRun();
        info5.setText(app.getDepartmentChair());
        
        XWPFParagraph firstPage6 = document.createParagraph();
        firstPage6.setSpacingAfter(-1);
        firstPage6.setSpacingBefore(-1);
        firstPage6.setSpacingBetween(1.5);
        XWPFRun bolded6 = firstPage6.createRun();
        bolded6.setBold(true);
        bolded6.setText("Chairperson Phone Number:");
        bolded6.addTab();
        bolded6.addTab();
        bolded6.addTab();
        bolded6.addTab();
        bolded6.addTab();
        XWPFRun info6 = firstPage6.createRun();
        info6.setText(app.getChairPhone());
        
        XWPFParagraph firstPage7 = document.createParagraph();
        firstPage7.setSpacingAfter(-1);
        firstPage7.setSpacingBefore(-1);
        firstPage7.setSpacingBetween(1.5);
        XWPFRun bolded7 = firstPage7.createRun();
        bolded7.setBold(true);
        bolded7.setText("Date of Appointment to BU:");
        bolded7.addTab();
        bolded7.addTab();
        bolded7.addTab();
        bolded7.addTab();
        bolded7.addTab();
        XWPFRun info7 = firstPage7.createRun();
        if (pro.getAppointmentDate() != null)
            info7.setText(utilities.FormattingUtility.formatDate(pro.getAppointmentDate().toString().substring(0, pro.getAppointmentDate().toString().indexOf("T"))));
        else
            info7.setText("Sometime Long Ago");
        
        XWPFParagraph firstPage8 = document.createParagraph();
        firstPage8.setSpacingAfter(-1);
        firstPage8.setSpacingBefore(-1);
        firstPage8.setSpacingBetween(1.5);
        XWPFRun bolded8 = firstPage8.createRun();
        bolded8.setBold(true);
        bolded8.setText("Total Anticipated Years of Academic Service:");
        bolded8.addTab();
        bolded8.addTab();
        bolded8.addTab();
        XWPFRun info8 = firstPage8.createRun();
        info8.setText(Integer.toString(pro.getYearsOfService()));
        
        XWPFParagraph firstPage9 = document.createParagraph();
        firstPage9.setSpacingAfter(-1);
        firstPage9.setSpacingBefore(-1);
        firstPage9.setSpacingBetween(1.5);
        XWPFRun bolded9 = firstPage9.createRun();
        bolded9.setBold(true);
        bolded9.setText("Date of Last Promotion at BU:");
        bolded9.addTab();
        bolded9.addTab();
        bolded9.addTab();
        bolded9.addTab();
        bolded9.addTab();
        XWPFRun info9 = firstPage9.createRun();
        info9.setItalic(true);
        if (pro.getLastPromotionDate() != null)
            info9.setText(utilities.FormattingUtility.formatDate(pro.getLastPromotionDate().toString().substring(0, pro.getLastPromotionDate().toString().indexOf("T"))));
        else
            info9.setText("Not Applicable");
        
        XWPFParagraph firstPage10 = document.createParagraph();
        firstPage10.setSpacingAfter(-1);
        firstPage10.setSpacingBefore(-1);
        firstPage10.setSpacingBetween(1.5);
        XWPFRun bolded10 = firstPage10.createRun();
        bolded10.setBold(true);
        bolded10.setText("Date Application Submitted to Department Chairperson:");
        bolded10.addTab();
        XWPFRun info10 = firstPage10.createRun();
        info10.setText("to be determined");
        
        if (finalSection) {
           info10.addBreak(BreakType.PAGE);
        }
        
        return document;
    }
}
