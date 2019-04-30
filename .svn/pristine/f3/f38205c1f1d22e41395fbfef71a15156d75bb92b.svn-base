package utilities;

import common.Application;
import common.WorkExp;
import java.util.List;
import mysql.WorkExpManager;
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
public class POIWorkExp {
    public static XWPFDocument generateDoc(Application app, XWPFDocument source, boolean finalSection) {
        WorkExpManager workManager = new WorkExpManager();
        List<WorkExp> workExps = workManager.getWorkExpsByApplication(app.getApplicationID());
        
        XWPFParagraph[][] workExpPara = new XWPFParagraph[1][5];
        XWPFRun[][] workExpRuns = new XWPFRun[1][10];
        String[] datesOfExp2 = new String[2];
        WorkExp workExp = new WorkExp(0);
        
        if (workExps != null && workExps.isEmpty() == false) {
            workExpPara = new XWPFParagraph[workExps.size()][5];
            workExpRuns = new XWPFRun[workExps.size()][10];
            workExp = workExps.get(0);
        }
        
        XWPFDocument document;
        if (source == null) {
            document = new XWPFDocument();
        } else {
            document = source;
        }
        
        // This code generates a header. Different headers can't exist on different
        // pages. Discuss with Brunskill.
//        CTP ctp5 = CTP.Factory.newInstance();
//        // this adds page number incremental
//        CTR r5 = ctp5.addNewR();
//        if (app.getCustomFirstName() != null && app.getCustomLastName() != null)
//            r5.addNewT().setStringValue(app.getCustomFirstName() + " " + app.getCustomLastName() + " 1-");
//        else
//            r5.addNewT().setStringValue("Your Name 1-");
//        r5.addNewPgNum();
//        
//        CTP ctp6 = CTP.Factory.newInstance();
//        CTR r6 = ctp6.addNewR();
//        r6.addNewT().setStringValue("Background Information");
//
//        XWPFParagraph codePara5 = new XWPFParagraph(ctp5, document);
//        codePara5.setSpacingAfter(0);
//        codePara5.setSpacingBefore(0);
//        codePara5.setSpacingBetween(1);
//        XWPFParagraph codePara6 = new XWPFParagraph(ctp6, document);
//        codePara6.setSpacingAfter(0);
//        codePara6.setSpacingBefore(0);
//        codePara6.setSpacingBetween(1);
//        XWPFRun xrun3 = codePara6.getRun(r6);
//        xrun3.setBold(true);
//        xrun3.addCarriageReturn();
//        XWPFParagraph[] paragraphs3 = new XWPFParagraph[2];
//        paragraphs3[0] = codePara5;
//        paragraphs3[1] = codePara6;
//        // position of number
//        codePara5.setAlignment(ParagraphAlignment.RIGHT);
//        codePara6.setAlignment(ParagraphAlignment.RIGHT);
//
//        CTSectPr sectPr3 = document.getDocument().getBody().addNewSectPr();
//
//        XWPFHeaderFooterPolicy headerFooterPolicy3 = new XWPFHeaderFooterPolicy(document, sectPr3);
//        headerFooterPolicy3.createHeader(STHdrFtr.DEFAULT, paragraphs3);
        
        XWPFParagraph workExpTitle = document.createParagraph();
        workExpTitle.setSpacingAfter(0);
        workExpTitle.setSpacingBefore(0);
        workExpTitle.setSpacingBetween(2);
        XWPFRun workExpRun = workExpTitle.createRun();
        workExpRun.setText("C.");
        XWPFRun workExpRun2 = workExpTitle.createRun();
        workExpRun2.setBold(true);
        workExpRun2.setText("   Other Work Experience");
        
        if (workExps != null) {
            for (int i = 0; i < workExpPara.length; i++) {
                workExp = workExps.get(i);

                workExpPara[i][0] = document.createParagraph();
                workExpPara[i][0].setSpacingAfter(0);
                workExpPara[i][0].setSpacingBefore(0);
                workExpPara[i][0].setSpacingBetween(1);
                workExpRuns[i][0] = workExpPara[i][0].createRun();
                workExpRuns[i][0].addTab();
                workExpRuns[i][0].setBold(true);
                workExpRuns[i][0].setText("Institution:");
                workExpRuns[i][0].addTab();
                workExpRuns[i][0].addTab();
                workExpRuns[i][1] = workExpPara[i][0].createRun();
                if (workExp.getInstitution() != null)
                    workExpRuns[i][1].setText(workExp.getInstitution());
                else
                    workExpRuns[i][1].setText("Your Institution Here");

                workExpPara[i][1] = document.createParagraph();
                workExpPara[i][1].setSpacingAfter(0);
                workExpPara[i][1].setSpacingBefore(0);
                workExpPara[i][1].setSpacingBetween(1);
                workExpRuns[i][2] = workExpPara[i][1].createRun();
                workExpRuns[i][2].addTab();
                workExpRuns[i][2].setBold(true);
                workExpRuns[i][2].setText("Title:");
                workExpRuns[i][2].addTab();
                workExpRuns[i][2].addTab();
                workExpRuns[i][2].addTab();
                workExpRuns[i][3] = workExpPara[i][1].createRun();
                if (workExp.getTitle() != null)
                    workExpRuns[i][3].setText(workExp.getTitle());
                else
                    workExpRuns[i][3].setText("Your Title Here");

                workExpPara[i][2] = document.createParagraph();
                workExpPara[i][2].setSpacingAfter(0);
                workExpPara[i][2].setSpacingBefore(0);
                workExpPara[i][2].setSpacingBetween(1);
                workExpRuns[i][4] = workExpPara[i][2].createRun();
                workExpRuns[i][4].addTab();
                workExpRuns[i][4].setBold(true);
                workExpRuns[i][4].setText("Dates of Experience:");
                workExpRuns[i][4].addTab();
                workExpRuns[i][5] = workExpPara[i][2].createRun();
                if (workExp.getExperienceDates() != null) {
                datesOfExp2 = utilities.FormattingUtility.dateRangeConvert(workExp.getExperienceDates());
                workExpRuns[i][5].setText(utilities.FormattingUtility.formatMonthyear(datesOfExp2[0]) 
                        + " - " + utilities.FormattingUtility.formatMonthyear(datesOfExp2[1]));
                } else {
                    workExpRuns[i][5].setText("Your Dates of Experience Here");
                }

                workExpPara[i][3] = document.createParagraph();
                workExpPara[i][3].setSpacingAfter(0);
                workExpPara[i][3].setSpacingBefore(0);
                workExpPara[i][3].setSpacingBetween(1);
                workExpRuns[i][6] = workExpPara[i][3].createRun();
                workExpRuns[i][6].addTab();
                workExpRuns[i][6].setBold(true);
                workExpRuns[i][6].setText("Years:");
                workExpRuns[i][6].addTab();
                workExpRuns[i][6].addTab();
                workExpRuns[i][6].addTab();
                workExpRuns[i][7] = workExpPara[i][3].createRun();
                if (workExp.getYearsOfService() != 0) {
                    if (workExp.getYearsOfService() > 1)
                        workExpRuns[i][7].setText(workExp.getYearsOfService() + " Years");
                    else
                        workExpRuns[i][7].setText(workExp.getYearsOfService() + " Year");
                } else
                    workExpRuns[i][7].setText("Your Years of Experience Here");

                workExpPara[i][4] = document.createParagraph();
                workExpPara[i][4].setSpacingAfter(0);
                workExpPara[i][4].setSpacingBefore(0);
                workExpPara[i][4].setSpacingBetween(1);
                workExpRuns[i][8] = workExpPara[i][4].createRun();
                workExpRuns[i][8].addTab();
                workExpRuns[i][8].setBold(true);
                workExpRuns[i][8].setText("Full/Part Time Status:");
                workExpRuns[i][8].addTab();
                workExpRuns[i][9] = workExpPara[i][4].createRun();
                if (workExp.isFullTime() == true)
                    workExpRuns[i][9].setText("Full Time");
                else
                    workExpRuns[i][9].setText("Part Time");
                workExpRuns[i][9].addCarriageReturn();
            }
        } else {
            XWPFParagraph notAppl = document.createParagraph();
            notAppl.setSpacingAfter(0);
            notAppl.setSpacingBefore(0);
            notAppl.setSpacingBetween(1);
            XWPFRun run = notAppl.createRun();
            run.addTab();
            run.setItalic(true);
            run.setText("Not Applicable");
        }
        if (finalSection) {
           XWPFRun breakRun = document.createParagraph().createRun();
           breakRun.addBreak(BreakType.PAGE);
        }
        
        return document;
    }
}
