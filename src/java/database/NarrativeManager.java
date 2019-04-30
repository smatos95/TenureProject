package database;

import common.Narrative;
import java.util.List;

/**
 * An <code>interface</code> that specifies allowable operations on narratives 
 * in the database.
 *
 * @author Gryphon Ayers (2019)
 */
public interface NarrativeManager {
    public Narrative getNarrative(int narrativeID);
    public List<Narrative> getNarrativesByApplication(int application);
    public Integer insertNarrative(Narrative narrative);
    public Narrative updateNarrative(Narrative narrative);
    public boolean deleteNarrative(Narrative narrative);
    public List<Narrative> getAllNarratives();
}
