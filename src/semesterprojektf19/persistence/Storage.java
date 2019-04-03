package semesterprojektf19.persistence;

/**
 *
 * An interface to contract the methods for storing the highscore
 *
 */
public interface Storage {

    String[] authenticate(String username, String password);

    public boolean register(String username, String password, String role, String firstName, String lastName);
}
