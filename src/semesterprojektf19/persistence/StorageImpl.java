package semesterprojektf19.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StorageImpl implements Storage {

    private final Map<FileType, File> files = new HashMap<>();

    public StorageImpl() throws IOException {
        new File("persons").mkdirs();
        for (FileType fileType : FileType.values()) {
            File file = new File("./", fileType.getFileName());
            if (!file.exists()) {
                file.createNewFile();
            } else if (file.isDirectory()) {
                throw new IOException("The file(" + fileType.getFileName() + ") currently exists as a directory!");
            }
            files.put(fileType, file);
        }
    }

    @Override
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
            Logger.getLogger(StorageImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean register(String username, String password, UUID uuid, Object person) {
        File file = files.get(FileType.ACCOUNTS);
        try (Scanner scanner = new Scanner(file); BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            while (scanner.hasNextLine()) {
                if (username.equalsIgnoreCase(scanner.nextLine().split(",")[0])) {
                    return false;
                }
            }
            writer.write((file.length() == 0 ? "" : "\n") + uuid.toString() + "," + username + "," + password);
            SerializableUtil.writeObject("persons/" + uuid.toString() + ".ser", person);
        } catch (IOException ex) {
            Logger.getLogger(StorageImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
