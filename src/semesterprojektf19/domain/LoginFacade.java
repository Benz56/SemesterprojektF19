package semesterprojektf19.domain;

import java.util.List;
import java.util.Map;

public interface LoginFacade {

    Map<String, String> login(String username, String password);

    //void createCase(String firstName, String lastName, String birthday, String controlNumber, int phoneNumber, String address, String shortInfo);
}
