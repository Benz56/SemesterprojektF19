package semesterprojektf19.domain;

import java.util.Map;

/**
 *
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
 */
public interface DiaryNoteFacade {

    Map<String, String> createNote(Map<String, String> noteDetails);

}
