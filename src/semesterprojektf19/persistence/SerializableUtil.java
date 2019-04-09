package semesterprojektf19.persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class SerializableUtil {

    public static void writeObject(String file, Object object) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(file); ObjectOutputStream out = new ObjectOutputStream(fileOut);) {
            out.writeObject(object);
        } catch (IOException ex) {
            throw ex;
        }
    }

    public static Object readObject(String file) {
        Object readObject = null;
        try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn);) {
            readObject = in.readObject();
        } catch (Exception ex) {
        }
        return readObject;
    }
}
