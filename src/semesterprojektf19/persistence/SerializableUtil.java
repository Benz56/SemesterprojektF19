package semesterprojektf19.persistence;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class SerializableUtil {

    public static void writeObject(String file, Object object, boolean append) {
        try (ObjectOutputStream out = !append ? new ObjectOutputStream(new FileOutputStream(file)) : new ObjectOutputStream(new FileOutputStream(file, append)) {
            @Override
            protected void writeStreamHeader() throws IOException {
                reset();
            }
        }) {
            out.writeObject(object);
        } catch (IOException ex) {
            Logger.getLogger(SerializableUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Object readObject(String file) {
        Object object = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));) {
            object = in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(SerializableUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return object;
    }

    public static List<Object> readObjects(String file) {
        List<Object> objects = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));) {
            while (true) {
                try {
                    objects.add(in.readObject());
                } catch (EOFException ex) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(SerializableUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }
}
