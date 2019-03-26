/* 
 * Developed by SDU OOP E18 SE/ST grp 21
 * Frederik Alexander Hounsvad, Andreas Kaer Lauritzen,  Patrick Nielsen, Oliver Lind Nordestgaard, Benjamin Eichler Staugaard
 * The use of this work is limited to educational purposes
 */
package semesterprojektf19.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * A class to store and load strings delimited by '\n'
 * 
 */
public class StorageImpl implements Storage {

    private final File file;

    public StorageImpl(String fileName) throws IOException {
        this.file = new File("./", fileName + ".csv");
        if (!this.file.exists()) {
            this.file.createNewFile();
        } else if (this.file.isDirectory()) {
            throw new IOException("The file(" + fileName + ") currently exists as a directory!");
        }
    }

    @Override
    public void save(String result) throws FileNotFoundException, IOException {
    }

    @Override
    public List<String> load() throws FileNotFoundException {
        return null;
    }
}
