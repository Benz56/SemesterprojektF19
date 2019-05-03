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

public class Postgres {

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        //url syntax for database connection: (driver : sqltype :// server : port/)
        String url = "jdbc:postgresql://manny.db.elephantsql.com:5432/";
        String username = "jwzwncwf";
        String password = "Hl-A8_Bb93HTAXJuBjDXRfPJtyU64vDL";

        try {
            Connection db = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database@" + url);
//            Statement st = db.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM account");
//            while (rs.next()) {
//                System.out.print("Column 1 returned ");
//                System.out.println(rs.getString(1));
//                System.out.print("Column 2 returned ");
//                System.out.println(rs.getString(2));
//            }
//            rs.close();
//            st.close();
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
