/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.persistence;

import java.sql.PreparedStatement;
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

    private final Postgres conn;

    public PersistenceFacadeImpl() {
        conn = new Postgres();
    }

    @Override
    public UUID authenticate(String username, String password) {
        try {
            Statement st = conn.getDb().createStatement();
            ResultSet rs = st.executeQuery("Select * FROM account");
            while (rs.next()) {
                if (username.equals(rs.getString("username")) && password.equalsIgnoreCase(rs.getString("password"))) {
                    System.out.println("Authenticated.");
                    return UUID.fromString(rs.getString("uuid"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Statement st = conn.getDb().createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            workerDetails.put("uuid", rs.getString("uuid"));
            workerDetails.put("fName", rs.getString("fName"));
            workerDetails.put("lName", rs.getString("lName"));
            workerDetails.put("cNumber", rs.getString("cNumber"));
            workerDetails.put("addr", rs.getString("addr"));
            workerDetails.put("phone", rs.getString("phone"));
            workerDetails.put("role", rs.getString("role"));
            workerDetails.put("institution", rs.getString("institution"));
            System.out.println(workerDetails.get("fName") + workerDetails.get("lName") + " added to map: workerDetails");
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    return null;   
    }

    
    public static void main(String[] args) {
        String id = "000000000000000000000000000000000000";
        PersistenceFacade persistence = new PersistenceFacadeImpl();
        persistence.getWorkerDetails(id);
    }
}
