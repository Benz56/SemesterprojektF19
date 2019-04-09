package semesterprojektf19.persistence;

import java.util.UUID;

/**
 *
 * An interface to contract the methods for storing the highscore
 *
 */
public interface Storage {

    Object authenticate(String username, String password);

    public boolean register(String username, String password, UUID uuid, Object person);
}
