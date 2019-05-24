package semesterprojektf19.domain;

import java.util.Map;

/**
 *
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
 */
public interface LoginFacade {

    Map<String, String> login(String username, String password);
}
