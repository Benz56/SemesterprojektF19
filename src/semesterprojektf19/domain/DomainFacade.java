package semesterprojektf19.domain;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
 */
public interface DomainFacade {

    public void createCase(Map<String, String> caseDetails);

    public List<String> matchCitizens(String contains);

    public void refresh();

    public List<String> getConnectedCitizens();
    
    public Map<String, String> getCitizenDetails(String citizen);

    public List<List<Map<String, String>>> getDiaryDetails(String citizen, int index);

    public Map<String, String> addDiaryNoteVersion(String citizenString, int caseIndex, Map<String, String> details);

}
