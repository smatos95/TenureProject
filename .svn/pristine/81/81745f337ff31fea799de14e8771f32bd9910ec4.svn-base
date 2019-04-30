package testdata;

import java.io.IOException;

/**
 * Tests the classes used to generate sample data.
 * @author Shane Panagakos (2019)
 */
public class TestData
{
    public static void main(String[] args) throws IOException 
    {
        Professor testProf = new Professor();
        testProf.createCSV();
        System.out.println("CSV is ready.");
        
        Semester s = new Semester();
        System.out.println(s);
        System.out.println(s.determineTerm(2017, "spring"));
    }
}
