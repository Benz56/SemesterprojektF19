package semesterprojektf19.domain;

public interface LoginFacade {

    boolean login(String username, String password);

    boolean register(String username, String password, String firstName, String lastName);

    //void createCase(String firstName, String lastName, String birthday, String controlNumber, int phoneNumber, String address, String shortInfo);
}
