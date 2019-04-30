package database;

import common.LocalExp;
import java.util.List;

/**
 * An <code>interface</code> that specifies allowed operations on local 
 *
 * @author Gryphon Ayers (2019)
 */
public interface LocalExpManager {
    public LocalExp getLocalExp(int localExpID);
    public List<LocalExp> getLocalExpsByApplication(int application);
    public Integer insertLocalExp(LocalExp localExp);
    public LocalExp updateLocalExp(LocalExp localExp);
    public boolean deleteLocalExp(int localExpID);
    public List<LocalExp> getAllLocalExps();
}
