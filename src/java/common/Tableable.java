package common;

import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.jsp.JspWriter;

/**
 * The <code>Tableable</code> interface indicates that a data type is suitable
 * for packed output as an HTML table row. Note that all methods in this 
 * interface produce output that is expected into 
 * 
 * @author Gryphon Ayers (2019)
 */
public interface Tableable {
    /**
     * Prints a row of column labels consistent with the summary view of this 
     * object.
     * @param out the stream to use when writing HTML
     * @throws java.io.IOException
     */
    public void printSummaryLabels(JspWriter out) throws IOException;
    
    /**
     * Prints a row of column labels consistent with the complete view of this 
     * object.
     * @param out the stream to use when writing HTML
     * @throws java.io.IOException
     */
    public void printCompleteLabels(JspWriter out) throws IOException;
    
    /**
     * Prints a row of data values consistent with the summary view of this
     * @param out the stream to use when writing HTML
     * @throws java.io.IOException
     */
    public void printSummaryRow(JspWriter out) throws IOException;
    
    /**
     * Prints a row of data values consistent with the complete view of this
     * object.
     * @param out the stream to use when writing HTML
     * @throws java.io.IOException
     */
    public void printCompleteRow(JspWriter out) throws IOException;
}
