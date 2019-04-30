package utilities;

import common.Application;
import common.Profile;
import mysql.ProfileManager;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author Riley Hughes (2019)
 */
public class POICoverPage {

    public static XWPFDocument generateDoc(Application app, XWPFDocument source, boolean finalSection) {
        ProfileManager pm = new ProfileManager();
        Profile profile = pm.getProfileByUserID(app.getApplicantUser());

        String customName = app.getCustomFirstName() + " " + app.getCustomLastName();
        String rank = profile.getRank();
        String department = app.getDepartment();
        
        XWPFDocument d;
        if (source == null) {
            d = new XWPFDocument();
        } else {
            d = source;
        }
        XWPFParagraph title = d.createParagraph();
        
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.addCarriageReturn();
        titleRun.addCarriageReturn();
        titleRun.addCarriageReturn();
        titleRun.setText(customName);
        titleRun.setFontFamily("Cambria");
        titleRun.setFontSize(40);

        XWPFParagraph subtitle = d.createParagraph();
        XWPFRun subtitleRun2 = subtitle.createRun();
        subtitleRun2.setText(rank);
        subtitleRun2.setFontFamily("Cambria");
        subtitleRun2.setFontSize(22);
        subtitleRun2.addCarriageReturn();

        subtitle.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun subtitleRun = subtitle.createRun();
        subtitleRun.setText(department);
        subtitleRun.setFontFamily("Cambria");
        subtitleRun.setFontSize(22);

        if (finalSection) {
           subtitleRun.addBreak(BreakType.PAGE);
        }
        
        return d;
    }
}
