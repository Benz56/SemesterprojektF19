package semesterprojektf19.persistence;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
 */
public final class Postgres {

    //url syntax for database connection: (driver:sqltype://server:port/)
    private final static String URL = "jdbc:postgresql://egboosted-beehive-do-user-6026035-0.db.ondigitalocean.com:25060/defaultdb?autoReconnect=true&sslmode=require";
    private final static String USERNAME = "doadmin";
    private final static String PASSWORD = "ij7o5cn750qeaz46";
    private Connection connection;

    public Postgres() {
        openConnection();
    }

    public void openConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                this.connection = DriverManager.getConnection(Postgres.URL, Postgres.USERNAME, Postgres.PASSWORD);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Postgres.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        try {
            connection.createStatement().executeQuery("SELECT 1");
        } catch (SQLRecoverableException ignored) {
            //Connection automatically reconnects. Hopefully.
        } catch (SQLException ex) {
            Logger.getLogger(Postgres.class.getName()).log(Level.SEVERE, null, ex);
        }
        openConnection(); //Reopens if closed.
        return connection;
    }

    public void closeDb() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Postgres.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
