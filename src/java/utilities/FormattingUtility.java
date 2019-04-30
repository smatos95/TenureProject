package utilities;

/**
 *
 * @author Riley Hughes (2019)
 */
public class FormattingUtility {
    
    /**
     * Takes a String representing a date in format YYYY-MM-DD and returns a
     * String representing a date in format Month Day, Year.
     */
    
    private final static String[] months = {"January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };
    
    public static String formatDate(String date) {
        String month = months[Integer.parseInt(date.substring(date.indexOf("-") + 1, date.indexOf("-") + 3)) - 1];
        int day = Integer.parseInt(date.substring(date.lastIndexOf("-") + 1));
        String dayString = Integer.toString(day);
        switch (day) {
            case 1:
            case 21:
            case 31:
                dayString += "st";
                break;
            case 2:
            case 22:
                dayString += "nd";
                break;
            case 3:
            case 23:
                dayString += "rd";
                break;
            default:
                dayString += "th";
        }
        String year = date.substring(0, date.indexOf("-"));
        
        return month + " " + dayString + ", " + year;
    }
    
    /**
     * Takes a string representing a range of dates (% is the delimiter) and
     * returns an array of strings containing the first date and second date
     * separately.
     */
    public static String[] dateRangeConvert(String date) {
        String dateRange[] = new String[2];
        
        dateRange[0] = date.substring(0, date.indexOf("%"));
        dateRange[1] = date.substring(date.indexOf("%") + 1);
        
        return dateRange;
    }
    
    /**
     * Takes a string representing a date (yyyy-mm-dd) and returns a string
     * representing the date with the day truncated and in month year form
     * (mm/yyyy).
     */
    public static String formatMonthyear(String date) {
        String monthYearForm = date.substring(date.indexOf("-") + 1, date.lastIndexOf("-")) 
                + "/" + date.substring(0, date.indexOf("-"));
        return monthYearForm;
    }
    
    /**
     * Takes a string representing a date (yyyy-mm-dd) and returns a string
     * representing the same date but reordered into (mm/dd/yyyy) form.
     */
    public static String rearrangeDate(String date) {
        String day = date.substring(date.lastIndexOf("-") + 1);
        String month = date.substring(date.indexOf("-") + 1, date.lastIndexOf("-"));
        String year = date.substring(0, date.indexOf("-"));
        
        return month + "/" + day + "/" + year;
    }
}
