package semesterprojektf19.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * An interface to contract the methods for storing the highscore
 *
 */
public interface Storage {

    void save(String result) throws FileNotFoundException, IOException;

    List<String> load() throws FileNotFoundException;
}
