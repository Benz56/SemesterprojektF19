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
