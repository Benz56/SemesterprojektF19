package semesterprojektf19.persistence;

import java.util.UUID;

public interface Storage {

    Object authenticate(String username, String password);

    public boolean register(String username, String password, UUID uuid, Object person);
}
