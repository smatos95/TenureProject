package utilities;

import common.Application;
import common.Narrative;
import java.util.Iterator;
import java.util.List;
import mysql.NarrativeManager;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author Riley Hughes (2019)
 */
public class POIProfessionalFulfillment {
    public static XWPFDocument generateDoc(Application app, XWPFDocument source, boolean finalSection) {
        NarrativeManager narrm = new NarrativeManager();
        List<Narrative> narrs = narrm.getNarrativesByApplication(app.getApplicationID());
        
        // Temp variable to hold current narrative (iterative) with iterator
        Narrative narr = null;
        Iterator<Narrative> itann = null;
        
        // If there are narratives for this app, go through them to see if there
        // is a professional responsibilities narrative. If there is one, narr
        // will become it, and if not, narr is null.
        if (narrs != null && narrs.isEmpty() == false) {
            itann = narrs.iterator();
            
            while (itann.hasNext()) {
                narr = itann.next();
                
                if (narr.getType().equals("SA"))
                    break;
                else
                    narr = null;
            }
        }
        
        XWPFDocument document;
        
        if (source == null)
            document = new XWPFDocument();
        else
            document = source;
        
        XWPFParagraph narrTitle = document.createParagraph();
        narrTitle.setSpacingAfter(0);
        narrTitle.setSpacingBefore(0);
        narrTitle.setSpacingBetween(2);
        XWPFRun narrRun = narrTitle.createRun();
        narrRun.setText("D.   Fulfillment of Professional Responsibilities");
        narrRun.setBold(true);
        
        XWPFParagraph saTitle = document.createParagraph();
        saTitle.setSpacingAfter(0);
        saTitle.setSpacingBefore(0);
        saTitle.setSpacingBetween(2);
        XWPFRun saRun = saTitle.createRun();
        saRun.setText("       a.   Student Advisement");
        saRun.setBold(true);

        String[] paras = null;
        if (narr != null && narr.getNarrativeText() != null)
            paras = narr.getNarrativeText().split("" + (char) 13);
        else {
            paras = new String[1];
            paras[0] = "Not Applicable";
        }
        XWPFParagraph[] narrParas = new XWPFParagraph[paras.length];
        XWPFRun[] narrRuns = new XWPFRun[paras.length];
        for (int i = 0; i < narrParas.length; i++) {
            narrParas[i] = document.createParagraph();
            narrParas[i].setSpacingAfter(0);
            narrParas[i].setSpacingBefore(0);
            narrParas[i].setSpacingBetween(1);
            narrRuns[i] = narrParas[i].createRun();
            narrRuns[i].addTab();
            narrRuns[i].setText(paras[i]);
        }
        
        narrRuns[paras.length - 1].addCarriageReturn();
        narrRuns[paras.length - 1].addCarriageReturn();
        
        narr = null;
        itann = null;
        
        // If there are narratives for this app, go through them to see if there
        // is a professional responsibilities narrative. If there is one, narr
        // will become it, and if not, narr is null.
        if (narrs != null && narrs.isEmpty() == false) {
            itann = narrs.iterator();
            
            while (itann.hasNext()) {
                narr = itann.next();
                
                if (narr.getType().equals("AD"))
                    break;
                else
                    narr = null;
            }
        }
        
        XWPFParagraph adTitle = document.createParagraph();
        adTitle.setSpacingAfter(0);
        adTitle.setSpacingBefore(0);
        adTitle.setSpacingBetween(2);
        XWPFRun adRun = adTitle.createRun();
        adRun.setText("       b.   Acceptance of Departmental Assignments");
        adRun.setBold(true);
        
        String[] adParas = null;
        if (narr != null && narr.getNarrativeText() != null)
            adParas = narr.getNarrativeText().split("" + (char) 13);
        else {
            adParas = new String[1];
            adParas[0] = "Not Applicable";
        }
        XWPFParagraph[] adNarrParas = new XWPFParagraph[adParas.length];
        XWPFRun[] adNarrRuns = new XWPFRun[adParas.length];
        for (int i = 0; i < adNarrParas.length; i++) {
            adNarrParas[i] = document.createParagraph();
            adNarrParas[i].setSpacingAfter(0);
            adNarrParas[i].setSpacingBefore(0);
            adNarrParas[i].setSpacingBetween(1);
            adNarrRuns[i] = adNarrParas[i].createRun();
            adNarrRuns[i].addTab();
            adNarrRuns[i].setText(adParas[i]);
        }
        
        adNarrRuns[adParas.length - 1].addCarriageReturn();
        adNarrRuns[adParas.length - 1].addCarriageReturn();
        
        narr = null;
        itann = null;
        
        // If there are narratives for this app, go through them to see if there
        // is a professional responsibilities narrative. If there is one, narr
        // will become it, and if not, narr is null.
        if (narrs != null && narrs.isEmpty() == false) {
            itann = narrs.iterator();
            
            while (itann.hasNext()) {
                narr = itann.next();
                
                if (narr.getType().equals("TE"))
                    break;
                else
                    narr = null;
            }
        }
        
        XWPFParagraph teTitle = document.createParagraph();
        teTitle.setSpacingAfter(0);
        teTitle.setSpacingBefore(0);
        teTitle.setSpacingBetween(2);
        XWPFRun teRun = teTitle.createRun();
        teRun.setText("       c.   Timely Execution of Work");
        teRun.setBold(true);
        
        String[] teParas = null;
        
        if (narr != null && narr.getNarrativeText() != null)
            teParas = narr.getNarrativeText().split("" + (char) 13);
        else {
            teParas = new String[1];
            teParas[0] = "Not Applicable";
        }
        XWPFParagraph[] teNarrParas = new XWPFParagraph[teParas.length];
        XWPFRun[] teNarrRuns = new XWPFRun[teParas.length];
        for (int i = 0; i < teNarrParas.length; i++) {
            teNarrParas[i] = document.createParagraph();
            teNarrParas[i].setSpacingAfter(0);
            teNarrParas[i].setSpacingBefore(0);
            teNarrParas[i].setSpacingBetween(1);
            teNarrRuns[i] = teNarrParas[i].createRun();
            teNarrRuns[i].addTab();
            teNarrRuns[i].setText(teParas[i]);
        }
        
        teNarrRuns[teParas.length - 1].addCarriageReturn();
        teNarrRuns[teParas.length - 1].addCarriageReturn();
        
        if (finalSection) {
            teNarrRuns[teParas.length - 1].addBreak(BreakType.PAGE);
        }

        return document;
    }
}
