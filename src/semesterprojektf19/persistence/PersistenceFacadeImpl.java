/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sofielouise
 */
public class PersistenceFacadeImpl implements PersistenceFacade {

    private final Postgres connection;

    public PersistenceFacadeImpl() {
        connection = new Postgres();
    }

    @Override
    public Map<String, String> authenticate(String username, String password) {
        try {
            Statement st = connection.getDb().createStatement();
            ResultSet rs = st.executeQuery("Select * FROM account");
            while (rs.next()) {
                if (username.equals(rs.getString("username")) && password.equalsIgnoreCase(rs.getString("password"))) {
                    System.out.println("Authenticated.");
                    return getWorkerDetails(rs.getString("uuid"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.closeDb();
        return null;
    }

    @Override
    public boolean register(String username, String password, UUID uuid, Object person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, String> getWorkerDetails(String uuid) {
        String query = "SELECT * FROM worker WHERE uuid = '" + uuid + "';";
        Map<String, String> workerDetails = new HashMap<>();
        try {
            Statement st = connection.getDb().createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            workerDetails.put("uuid", rs.getString("uuid"));
            workerDetails.put("fname", rs.getString("fname"));
            workerDetails.put("lname", rs.getString("lname"));
            workerDetails.put("cnumber", rs.getString("cnumber"));
            workerDetails.put("addr", rs.getString("addr"));
            workerDetails.put("phone", rs.getString("phone"));
            workerDetails.put("role", rs.getString("role"));
            workerDetails.put("institution", rs.getString("institution"));
            workerDetails.put("institutionaddr", rs.getString("institutionaddr"));
            System.out.println(workerDetails.toString());
            cleanUpDB(rs, st);
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return workerDetails;
    }

    @Override
    public String getInstitutionAddress(String name) {
        String query = "SELECT institutionaddr FROM worker WHERE name = '" + name + "';";
        String institutionAddress = "";
        try {
            Statement st = connection.getDb().createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            institutionAddress = rs.getString(1);
            cleanUpDB(rs, st);
        } catch (SQLException e) {
        }
        return institutionAddress;
    }

    private void cleanUpDB(ResultSet rs, Statement st) throws SQLException {
        rs.close();
        st.close();
        connection.closeDb();
    }

    public static void main(String[] args) {
        String id = "000000000000000000000000000000000000";
        PersistenceFacade persistence = new PersistenceFacadeImpl();
        persistence.getWorkerDetails(id);
    }

}
