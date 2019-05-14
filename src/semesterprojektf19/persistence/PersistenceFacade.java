package semesterprojektf19.persistence;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PersistenceFacade {

    Map<String, String> authenticate(String username, String password);
    
    Map<String, String> getWorkerDetails(UUID uuid);

    boolean registerEmployee(String username, String password, UUID uuid, Map<String, String> personInfo);

    boolean registerCitizen(Map<String, String> personInfo);

    Map<String, String> getInstitutions();

    List<Map<String, String>> getCitizens();

    boolean registerCase(Map<String, String> caseDetails, UUID caseUUID, UUID citizenUUID, UUID diaryUUID, UUID uuid);

    boolean createDiaryNote(UUID uuid, UUID diaryUUID, UUID editorUUID, String dateOfObs, String dateOfEdit, String title, String content);
    
    List<Map<String, String>> getCases();

    boolean editCitizen(Map<String, String> map);
    
    Map<UUID,List<Map<String, String>>> getDiaryNotes(UUID diaryUUID);
}
