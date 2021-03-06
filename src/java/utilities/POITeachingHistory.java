package utilities;

import common.Application;
import common.LocalExp;
import java.util.List;
import mysql.LocalExpManager;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;

/**
 *
 * @author Riley Hughes (2019)
 */
public class POITeachingHistory {
    public static XWPFDocument generateDoc(Application app, XWPFDocument source, boolean finalSection) {
        LocalExpManager tem = new LocalExpManager();
        List<LocalExp> exps = tem.getLocalExpsByApplication(app.getApplicationID());
        
        XWPFDocument document;
        if (source == null) {
            document = new XWPFDocument();
        } else {
            document = source;
        }
        
        // This code generates a header. Different headers can't exist on different
        // pages. Discuss with Brunskill.
//        CTP ctp3 = CTP.Factory.newInstance();
//        // this adds page number incremental
//        CTR r3 = ctp3.addNewR();
//        if (app.getCustomFirstName() != null && app.getCustomLastName() != null)
//            r3.addNewT().setStringValue(app.getCustomFirstName() + " " + app.getCustomLastName() + " 1-");
//        else
//            r3.addNewT().setStringValue("Your Name 1-");
//        r3.addNewPgNum();
//        
//        CTP ctp4 = CTP.Factory.newInstance();
//        CTR r4 = ctp4.addNewR();
//        r4.addNewT().setStringValue("Teaching History");
//
//        XWPFParagraph codePara3 = new XWPFParagraph(ctp3, document);
//        codePara3.setSpacingAfter(0);
//        codePara3.setSpacingBefore(0);
//        codePara3.setSpacingBetween(1);
//        XWPFParagraph codePara4 = new XWPFParagraph(ctp4, document);
//        codePara4.setSpacingAfter(0);
//        codePara4.setSpacingBefore(0);
//        codePara4.setSpacingBetween(1);
//        XWPFRun xrun2 = codePara4.getRun(r4);
//        xrun2.setBold(true);
//        xrun2.addCarriageReturn();
//        XWPFParagraph[] paragraphs2 = new XWPFParagraph[2];
//        paragraphs2[0] = codePara3;
//        paragraphs2[1] = codePara4;
//        // position of number
//        codePara3.setAlignment(ParagraphAlignment.RIGHT);
//        codePara4.setAlignment(ParagraphAlignment.RIGHT);
//
//        CTSectPr sectPr2 = document.getDocument().getBody().addNewSectPr();
//
//        XWPFHeaderFooterPolicy headerFooterPolicy2 = new XWPFHeaderFooterPolicy(document, sectPr2);
//        headerFooterPolicy2.createHeader(STHdrFtr.DEFAULT, paragraphs2);
        
        XWPFParagraph teachExpTitle = document.createParagraph();
        teachExpTitle.setSpacingAfter(0);
        teachExpTitle.setSpacingBefore(0);
        teachExpTitle.setSpacingBetween(2);
        XWPFRun teachExpRun = teachExpTitle.createRun();
        teachExpRun.setText("B.");
        XWPFRun teachExpRun2 = teachExpTitle.createRun();
        teachExpRun2.setBold(true);
        teachExpRun2.setText("   Bloomsburg University Teaching History");
        
        if (exps != null && exps.isEmpty() == false) {
            // create the table and create column labels
            XWPFTable table = document.createTable(1, 4);
            table.setWidth("90.00%");
            table.removeBorders();
            table.setTableAlignment(TableRowAlign.CENTER);
            XWPFTableRow rcursor = table.getRow(0);
            XWPFRun c1 = rcursor.getCell(0).getParagraphArray(0).createRun();
            c1.setBold(true);
            c1.setText("Course");
            XWPFRun c2 = rcursor.getCell(1).getParagraphArray(0).createRun();
            c2.setBold(true);
            c2.setText("Course Name");
            XWPFRun c3 = rcursor.getCell(2).getParagraphArray(0).createRun();
            c3.setBold(true);
            c3.setText("Period Taught");
            XWPFRun c4 = rcursor.getCell(3).getParagraphArray(0).createRun();
            c4.setBold(true);
            c4.setText("Sections Taught");

            // insert the actual course data
            for(LocalExp l: exps) {
                rcursor = table.createRow();
                rcursor.getCell(0).setText(l.getCourseCode());
                rcursor.getCell(1).setText(l.getCourseName());
                rcursor.getCell(2).setText(l.getSemester() + " " + l.getYear());
                rcursor.getCell(3).setText(l.getSections());
            }
        } else {
            XWPFParagraph p = document.createParagraph();
            p.setSpacingAfter(0);
            p.setSpacingBefore(0);
            p.setSpacingBetween(1);
            XWPFRun r = p.createRun();
            r.addTab();
            r.setItalic(true);
            r.setText("Your Teaching History Here");
            r.addCarriageReturn();
        }
        
        if (finalSection) {
            XWPFRun teachExpBreak = document.createParagraph().createRun();
            teachExpBreak.addBreak(BreakType.PAGE);
        }
        
        return document;
    }
}
