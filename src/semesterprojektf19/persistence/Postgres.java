/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.persistence;

/**
 *
 * @author sofielouise
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Postgres {

    //url syntax for database connection: (driver:sqltype://server:port/)
    final static String url = "jdbc:postgresql://manny.db.elephantsql.com:5432/";
    final static String username = "jwzwncwf";
    final static String password = "Hl-A8_Bb93HTAXJuBjDXRfPJtyU64vDL";

    private Connection db;

    public Postgres() {
        try {
            this.db = DriverManager.getConnection(Postgres.url, Postgres.username, Postgres.password);
        } catch (SQLException ex) {
            Logger.getLogger(Postgres.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getDb() {
        return db;
    }

    public void closeDb(){
        try {
            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Postgres.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

//    public static void main(String[] args) {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (java.lang.ClassNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            Connection db = DriverManager.getConnection(url, username, password);
//            System.out.println("Connected to database@" + url);
////            Statement st = db.createStatement();
////            ResultSet rs = st.executeQuery("SELECT * FROM account");
////            while (rs.next()) {
////                System.out.print("Column 1 returned ");
////                System.out.println(rs.getString(1));
////                System.out.print("Column 2 returned ");
////                System.out.println(rs.getString(2));
////            }
////            rs.close();
////            st.close();
//        } catch (java.sql.SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//}
