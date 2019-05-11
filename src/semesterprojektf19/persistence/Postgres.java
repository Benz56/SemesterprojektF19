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
    private final static String URL = "jdbc:postgresql://egboosted-beehive-do-user-6026035-0.db.ondigitalocean.com:25060/defaultdb?sslmode=require";
    private final static String USERNAME = "doadmin";
    private final static String PASSWORD = "ij7o5cn750qeaz46";
    private Connection db;

    public Postgres() {
        try {
            this.db = DriverManager.getConnection(Postgres.URL, Postgres.USERNAME, Postgres.PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(Postgres.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getDb() {
        return db;
    }

    public void closeDb() {
        try {
            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Postgres.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
