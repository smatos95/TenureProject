package utilities;

import common.Application;
import common.Degree;
import java.util.List;
import java.util.Iterator;
import mysql.DegreeManager;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;

/**
 *
 * @author Riley Hughes (2019)
 */
public class POIEduCred {
    
    public static XWPFDocument generateDoc(Application app, XWPFDocument source, boolean finalSection) {
        DegreeManager dm = new DegreeManager();
        List<Degree> degrees = dm.getDegreesByApplication(app.getApplicationID());
        
        XWPFParagraph[][] degreePara;
        XWPFRun[][] degreeRuns;
        String[] attendanceDates;
        Iterator<Degree> itann = null;
        Degree degree = new Degree(0);

        // Initialize paragraphs and degrees for appropriate amount of EduCreds
        if (degrees != null && degrees.isEmpty() == false) {
            degreePara = new XWPFParagraph[degrees.size()][5];
            degreeRuns = new XWPFRun[degrees.size()][10];
            itann = degrees.iterator();
            degree = itann.next();
        } else {
            degreePara = new XWPFParagraph[1][5];
            degreeRuns = new XWPFRun[1][10];
        }

        XWPFDocument document;
        if (source == null) {
            document = new XWPFDocument();
        } else {
            document = source;
        }

        // This generates a header (different headers can't exist on different
        // pages, discuss with Brunskill
//        CTP ctp = CTP.Factory.newInstance();
//        // this adds page number incremental
//        CTR r = ctp.addNewR();
//        if (app.getCustomFirstName() != null && app.getCustomLastName() != null)
//            r.addNewT().setStringValue(app.getCustomFirstName() + " " + app.getCustomLastName() + " 1-");
//        else
//            r.addNewT().setStringValue("Your Name 1-");
//        r.addNewPgNum();
//        
//        CTP ctp2 = CTP.Factory.newInstance();
//        CTR r2 = ctp2.addNewR();
//        r2.addNewT().setStringValue("Background Information");
//
//        XWPFParagraph codePara = new XWPFParagraph(ctp, document);
//        codePara.setSpacingAfter(0);
//        codePara.setSpacingBefore(0);
//        codePara.setSpacingBetween(1);
//        XWPFParagraph codePara2 = new XWPFParagraph(ctp2, document);
//        codePara2.setSpacingAfter(0);
//        codePara2.setSpacingBefore(0);
//        codePara2.setSpacingBetween(1);
//        XWPFRun xrun = codePara2.getRun(r2);
//        xrun.setBold(true);
//        xrun.addCarriageReturn();
//        XWPFParagraph[] paragraphs = new XWPFParagraph[2];
//        paragraphs[0] = codePara;
//        paragraphs[1] = codePara2;
//        // position of number
//        codePara.setAlignment(ParagraphAlignment.RIGHT);
//        codePara2.setAlignment(ParagraphAlignment.RIGHT);
//
//        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
//
//        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);
//        headerFooterPolicy.createHeader(STHdrFtr.DEFAULT, paragraphs);

        XWPFParagraph eduCredTitle = document.createParagraph();
        eduCredTitle.setSpacingAfter(0);
        eduCredTitle.setSpacingBefore(0);
        eduCredTitle.setSpacingBetween(2);
        eduCredTitle.setPageBreak(true);
        XWPFRun eduCredRun = eduCredTitle.createRun();
        eduCredRun.setText("A.");
        XWPFRun eduCredRun2 = eduCredTitle.createRun();
        eduCredRun2.setBold(true);
        eduCredRun2.setText("   Education Credentials");
        
        // Institution Line
        degreePara[0][0] = document.createParagraph();
        degreePara[0][0].setSpacingAfter(0);
        degreePara[0][0].setSpacingBefore(0);
        degreePara[0][0].setSpacingBetween(1);
        //degreePara[0][0].setPageBreak(true);
        degreeRuns[0][0] = degreePara[0][0].createRun();
        degreeRuns[0][0].addTab();
        degreeRuns[0][0].setBold(true);
        degreeRuns[0][0].setText("Institution:");
        degreeRuns[0][0].addTab();
        degreeRuns[0][0].addTab();
        degreeRuns[0][1] = degreePara[0][0].createRun();
        if (degree.getInstitution() != null)
            degreeRuns[0][1].setText(degree.getInstitution());
        else
            degreeRuns[0][1].setText("Your Institution Here");

        // Degree Line (Credential)
        degreePara[0][1] = document.createParagraph();
        degreePara[0][1].setSpacingAfter(0);
        degreePara[0][1].setSpacingBefore(0);
        degreePara[0][1].setSpacingBetween(1);
        degreeRuns[0][2] = degreePara[0][1].createRun();
        degreeRuns[0][2].addTab();
        degreeRuns[0][2].setBold(true);
        degreeRuns[0][2].setText("Degree:");
        degreeRuns[0][2].addTab();
        degreeRuns[0][2].addTab();
        degreeRuns[0][2].addTab();
        degreeRuns[0][3] = degreePara[0][1].createRun();
        if (degree.getCredential() != null)
            degreeRuns[0][3].setText(degree.getCredential());
        else
            degreeRuns[0][3].setText("Your Degree Credential Here");

        // Field of Study Line
        degreePara[0][2] = document.createParagraph();
        degreePara[0][2].setSpacingAfter(0);
        degreePara[0][2].setSpacingBefore(0);
        degreePara[0][2].setSpacingBetween(1);
        degreeRuns[0][4] = degreePara[0][2].createRun();
        degreeRuns[0][4].addTab();
        degreeRuns[0][4].setBold(true);
        degreeRuns[0][4].setText("Field of Study:");
        degreeRuns[0][4].addTab();
        degreeRuns[0][4].addTab();
        degreeRuns[0][5] = degreePara[0][2].createRun();
        if (degree.getFieldOfStudy() != null && degree.getFieldOfStudy().equals("^") == false)
            degreeRuns[0][5].setText(degree.getFieldOfStudy());
        else
            degreeRuns[0][5].setText("Your Field of Study Here");

        // Dates of Attendance Line
        degreePara[0][3] = document.createParagraph();
        degreePara[0][3].setSpacingAfter(0);
        degreePara[0][3].setSpacingBefore(0);
        degreePara[0][3].setSpacingBetween(1);
        degreeRuns[0][6] = degreePara[0][3].createRun();
        degreeRuns[0][6].addTab();
        degreeRuns[0][6].setBold(true);
        degreeRuns[0][6].setText("Dates of Attendance:");
        degreeRuns[0][6].addTab();
        degreeRuns[0][7] = degreePara[0][3].createRun();
        if (degree.getAttendancePeriods() != null) {
            attendanceDates = utilities.FormattingUtility.dateRangeConvert(degree.getAttendancePeriods());
            degreeRuns[0][7].setText(utilities.FormattingUtility.formatDate(attendanceDates[0]) + " - "
                   + utilities.FormattingUtility.formatDate(attendanceDates[1]));
        } else
            degreeRuns[0][7].setText("Your Attendance Period Here");

        // Date of Degree Conferral Line
        degreePara[0][4] = document.createParagraph();
        degreePara[0][4].setSpacingAfter(0);
        degreePara[0][4].setSpacingBefore(0);
        degreePara[0][4].setSpacingBetween(1);
        degreeRuns[0][8] = degreePara[0][4].createRun();
        degreeRuns[0][8].addTab();
        degreeRuns[0][8].setBold(true);
        degreeRuns[0][8].setText("Date of Degree:");
        degreeRuns[0][8].addTab();
        degreeRuns[0][8].addTab();
        degreeRuns[0][9] = degreePara[0][4].createRun();
        if (degree.getDegreeDate() != null)
            degreeRuns[0][9].setText(utilities.FormattingUtility.formatDate(degree.getDegreeDate()));
        else
            degreeRuns[0][9].setText("Your Date of Degree Conferral Here");
        degreeRuns[0][9].addCarriageReturn();

        // Loop to print out multiple eduCreds
        int i;
        for (i = 1; i < degreePara.length; i++) {
            if (itann != null) {
                degree = itann.next();
            }

            // Institution Line
            degreePara[i][0] = document.createParagraph();
            degreePara[i][0].setSpacingAfter(0);
            degreePara[i][0].setSpacingBefore(0);
            degreePara[i][0].setSpacingBetween(1);
            degreeRuns[i][0] = degreePara[i][0].createRun();
            degreeRuns[i][0].addTab();
            degreeRuns[i][0].setBold(true);
            degreeRuns[i][0].setText("Institution:");
            degreeRuns[i][0].addTab();
            degreeRuns[i][0].addTab();
            degreeRuns[i][1] = degreePara[i][0].createRun();
            if (degree.getInstitution() != null)
                degreeRuns[i][1].setText(degree.getInstitution());
            else
                degreeRuns[i][1].setText("Your Institution Here");

            // Degree Credential Line
            degreePara[i][1] = document.createParagraph();
            degreePara[i][1].setSpacingAfter(0);
            degreePara[i][1].setSpacingBefore(0);
            degreePara[i][1].setSpacingBetween(1);
            degreeRuns[i][2] = degreePara[i][1].createRun();
            degreeRuns[i][2].addTab();
            degreeRuns[i][2].setBold(true);
            degreeRuns[i][2].setText("Degree:");
            degreeRuns[i][2].addTab();
            degreeRuns[i][2].addTab();
            degreeRuns[i][2].addTab();
            degreeRuns[i][3] = degreePara[i][1].createRun();
            if (degree.getCredential() != null)
                degreeRuns[i][3].setText(degree.getCredential());
            else
                degreeRuns[i][3].setText("Your Degree Credential Here");

            // Field of Study Line
            degreePara[i][2] = document.createParagraph();
            degreePara[i][2].setSpacingAfter(0);
            degreePara[i][2].setSpacingBefore(0);
            degreePara[i][2].setSpacingBetween(1);
            degreeRuns[i][4] = degreePara[i][2].createRun();
            degreeRuns[i][4].addTab();
            degreeRuns[i][4].setBold(true);
            degreeRuns[i][4].setText("Field of Study:");
            degreeRuns[i][4].addTab();
            degreeRuns[i][4].addTab();
            degreeRuns[i][5] = degreePara[i][2].createRun();
            if (degree.getFieldOfStudy() != null && degree.getFieldOfStudy().equals("^") == false)
                degreeRuns[i][5].setText(degree.getFieldOfStudy());
            else
                degreeRuns[i][5].setText("Your Field of Study Here");

            // Dates of Attendance Line
            degreePara[i][3] = document.createParagraph();
            degreePara[i][3].setSpacingAfter(0);
            degreePara[i][3].setSpacingBefore(0);
            degreePara[i][3].setSpacingBetween(1);
            degreeRuns[i][6] = degreePara[i][3].createRun();
            degreeRuns[i][6].addTab();
            degreeRuns[i][6].setBold(true);
            degreeRuns[i][6].setText("Dates of Attendance:");
            degreeRuns[i][6].addTab();
            degreeRuns[i][7] = degreePara[i][3].createRun();
            if (degree.getAttendancePeriods() != null) {
                attendanceDates = utilities.FormattingUtility.dateRangeConvert(degree.getAttendancePeriods());
                degreeRuns[i][7].setText(utilities.FormattingUtility.formatDate(attendanceDates[0]) + " - "
                       + utilities.FormattingUtility.formatDate(attendanceDates[1]));
            } else
                degreeRuns[i][7].setText("Your Attendance Period Here");

            // Date of Degree Conferral Line
            degreePara[i][4] = document.createParagraph();
            degreePara[i][4].setSpacingAfter(0);
            degreePara[i][4].setSpacingBefore(0);
            degreePara[i][4].setSpacingBetween(1);
            degreeRuns[i][8] = degreePara[i][4].createRun();
            degreeRuns[i][8].addTab();
            degreeRuns[i][8].setBold(true);
            degreeRuns[i][8].setText("Date of Degree:");
            degreeRuns[i][8].addTab();
            degreeRuns[i][8].addTab();
            degreeRuns[i][9] = degreePara[i][4].createRun();
            if (degree.getDegreeDate() != null)
                degreeRuns[i][9].setText(utilities.FormattingUtility.formatDate(degree.getDegreeDate()));
            else
                degreeRuns[i][9].setText("Your Date of Degree Conferral Here");
            degreeRuns[i][9].addCarriageReturn();
        }
        if (finalSection) {
            degreeRuns[i-1][9].addBreak(BreakType.PAGE);
        }
        
        return document;
    }
}
