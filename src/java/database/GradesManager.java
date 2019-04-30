package database;

import common.Grades;
import java.util.List;

/**
 * An <code>interface</code> that specifies allowable operations on grades 
 * records in the database.
 *
 * @author Gryphon Ayers (2019)
 */
public interface GradesManager {
    public Grades getGrades(int gradesID);
    public List<Grades> getGradesByUserID(int userID);
    public Integer insertGrades(Grades grades);
    public boolean updateGrades(Grades grades);
    public List<Grades> getAllGrades();
}
