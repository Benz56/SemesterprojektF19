package semesterprojektf19.domain;

import java.util.Map;

public interface LoginFacade {

    Map<String, String> login(String username, String password);
}
