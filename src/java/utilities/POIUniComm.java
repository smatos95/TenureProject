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
public class POIUniComm {
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
                
                if (narr.getType().equals("UC"))
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
        narrTitle.setPageBreak(true);
        narrTitle.setSpacingAfter(0);
        narrTitle.setSpacingBefore(0);
        narrTitle.setSpacingBetween(2);
        XWPFRun narrRun = narrTitle.createRun();
        narrRun.setText("A.   Narrative on Contributions to University and Community");
        narrRun.setBold(true);
        
        String[] paras = null;
        if (narr != null && narr.getNarrativeText() != null)
            paras = narr.getNarrativeText().split("" + (char) 13);
        else {
            paras = new String[1];
            paras[0] = "Not Applicable";
        }
        XWPFParagraph[] narrParas = new XWPFParagraph[paras.length];
        XWPFRun[] narrRuns = new XWPFRun[paras.length];
        int i;
        for (i = 0; i < narrParas.length; i++) {
            narrParas[i] = document.createParagraph();
            narrParas[i].setSpacingAfter(0);
            narrParas[i].setSpacingBefore(0);
            narrParas[i].setSpacingBetween(1);
            narrRuns[i] = narrParas[i].createRun();
            narrRuns[i].addTab();
            narrRuns[i].setText(paras[i]);
        }
        
//        if (finalSection) {
//            narrRuns[i-1].addBreak(BreakType.PAGE);
//        }
        
        return document;
    }
}
