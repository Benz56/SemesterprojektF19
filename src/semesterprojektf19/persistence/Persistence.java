/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Benjamin Staugaard | Benz56
 */
public enum Persistence {
    INSTANCE;

    private final Map<FileType, File> files = new HashMap<>();

    private Persistence() {
        new File("persons").mkdirs();
        new File("citizens").mkdirs();
        for (FileType fileType : FileType.values()) {
            File file = new File("./", fileType.getFileName());
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            files.put(fileType, file);
        }
    }

    public Object authenticate(String username, String password) {
        File file = files.get(FileType.ACCOUNTS);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split(",");
                if (username.equals(tokens[1]) && password.equalsIgnoreCase(tokens[2])) {
                    return SerializableUtil.readObject("persons/" + tokens[0] + ".ser");
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean register(String username, String password, UUID uuid, Object person) {
        File file = files.get(FileType.ACCOUNTS);
        try (Scanner scanner = new Scanner(file); BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            while (scanner.hasNextLine()) {
                if (username.equalsIgnoreCase(scanner.nextLine().split(",")[1])) {
                    return false;
                }
            }
            writer.write((file.length() == 0 ? "" : "\n") + uuid.toString() + "," + username + "," + password);
            SerializableUtil.writeObject("persons/" + uuid.toString() + ".ser", person, false);
        } catch (IOException ex) {
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public Object readObjectFromFile(String file) {
        return SerializableUtil.readObject(file);
    }

    public List<Object> readObjectsFromFile(String file) {
        return SerializableUtil.readObjects(file);
    }

    public void writeObjectToFile(String file, Object object, boolean append) {
        SerializableUtil.writeObject(file, object, append);
    }
}
