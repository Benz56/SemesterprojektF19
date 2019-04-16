package semesterprojektf19.domain;

import java.util.List;

public interface LoginFacade {

    List<String> login(String username, String password);

    boolean register(String username, String password, String firstName, String lastName);

    //void createCase(String firstName, String lastName, String birthday, String controlNumber, int phoneNumber, String address, String shortInfo);
}
