/* 
 * Developed by SDU OOP E18 SE/ST grp 21
 * Frederik Alexander Hounsvad, Andreas Kaer Lauritzen,  Patrick Nielsen, Oliver Lind Nordestgaard, Benjamin Eichler Staugaard
 * The use of this work is limited to educational purposes
 */
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
