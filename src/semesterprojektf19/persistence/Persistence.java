//package semesterprojektf19.persistence;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//import java.util.UUID;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
// */
//public enum Persistence {
//    INSTANCE;
//
//    private final File accounts;
//    private final Postgres conn = new Postgres();
//
//    private Persistence() {
//        new File("persons").mkdirs();
//        new File("citizens").mkdirs();
//        new File("institutions").mkdirs();
//        accounts = new File("./accounts.csv");
//        if (!accounts.exists()) {
//            try {
//                accounts.createNewFile();
//            } catch (IOException ex) {
//                Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    public Object authenticate(String username, String password) {
//        try (Scanner scanner = new Scanner(accounts)) {
//            while (scanner.hasNextLine()) {
//                String[] tokens = scanner.nextLine().split(",");
//                if (username.equals(tokens[1]) && password.equalsIgnoreCase(tokens[2])) {
//                    return SerializableUtil.readObject("persons/" + tokens[0] + ".ser");
//                }
//            }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    public boolean authenticateDB(String username, String password) {
//        try {
//            Statement st = conn.getConnection().createStatement();
//            ResultSet rs = st.executeQuery("Select * FROM account");
//            while (rs.next()) {
//                if (username.equals(rs.getString("username")) && password.equalsIgnoreCase(rs.getString("password"))) {
//                    System.out.println("Authenticated.");
//                    return true;
//                }
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//
//    public boolean register(String username, String password, UUID uuid, Object person) {
//        try (Scanner scanner = new Scanner(accounts);
//                BufferedWriter writer = new BufferedWriter(new FileWriter(accounts, true))) {
//            while (scanner.hasNextLine()) {
//                if (username.equalsIgnoreCase(scanner.nextLine().split(",")[1])) {
//                    return false;
//                }
//            }
//            writer.write((accounts.length() == 0 ? "" : "\n") + uuid.toString() + "," + username + "," + password);
//            SerializableUtil.writeObject("persons/" + uuid.toString() + ".ser", person, false);
//        } catch (IOException ex) {
//            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//        return true;
//    }
//
////    public Object readObjectFromFile(String file) {
////        return SerializableUtil.readObject(file);
////    }
////
////    public List<Object> readObjectsFromFile(String file) {
////        return SerializableUtil.readObjects(file);
////    }
////
////    public void writeObjectToFile(String file, Object object, boolean append) {
////        SerializableUtil.writeObject(file, object, append);
////    }
//
//    public List<String> readFileNamesInDir(String dir) {
//        List<String> filenames = new ArrayList<>();
//        File[] files;
//        File file = new File(dir);
//        if (file.isDirectory()) {
//            files = file.listFiles();
//            for (File file1 : files) {
//                filenames.add(file1.getName().replace(".ser", ""));
//            }
//        }
//        return filenames;
//    }
//}
