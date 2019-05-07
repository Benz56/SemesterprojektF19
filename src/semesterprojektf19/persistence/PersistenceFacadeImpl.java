/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import semesterprojektf19.aquaintance.Column;

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
                if (username.equals(rs.getString(Column.username.toString())) && password.equalsIgnoreCase(rs.getString(Column.password.toString()))) {
                    System.out.println("Authenticated.");
                    String uuid = rs.getString(Column.uuid.toString());
                    System.out.println(uuid);
                    return getWorkerDetails(uuid);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.closeDb();
        return null;
    }

    @Override
    public boolean register(String username, String password, UUID uuid, Map<String, String> personInfo) {
        System.out.println("Registering...");
        try {
            System.out.println("Trying to register...");
            Statement st = connection.getDb().createStatement();
            String queryWorker = "INSERT INTO worker VALUES ('"
                    + uuid.toString() + "', '"
                    + personInfo.get(Column.fname.toString()) + "', '"
                    + personInfo.get(Column.lname.toString()) + "', '"
                    + personInfo.get(Column.bday.toString()) + "', '"
                    + personInfo.get(Column.cnumber.toString()) + "', '"
                    + personInfo.get(Column.addr.toString()) + "', '"
                    + personInfo.get(Column.phone.toString()) + "', '"
                    + personInfo.get(Column.role.toString()) + "', '"
                    + personInfo.get(Column.institution.toString()) + "', '"
                    + personInfo.get(Column.institutionaddr.toString()) +"';'";
            int i = st.executeUpdate(queryWorker);
            System.out.println("Row/s affected in table worker: " + i);
            String queryAccount = "INSERT INTO account VALUES ('" + uuid + "', '" + username + "', '" + password + "');";
            int j = st.executeUpdate(queryAccount);
            System.out.println("Row/s affected in table account: " + j);
            st.close();
            connection.closeDb();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception caught while executing register employee.");
        }
        return false;
    }

    @Override
    public boolean registerCitizen(Map<String, String> personInfo) {
        try {
            Statement st = connection.getDb().createStatement();
            String query = "INSERT INTO citizen VALUES ('"
                    + personInfo.get(Column.uuid.toString()) + "', '"
                    + personInfo.get(Column.fname.toString()) + "', '"
                    + personInfo.get(Column.lname.toString()) + "', '"
                    + personInfo.get(Column.bday.toString()) + "', '"
                    + personInfo.get(Column.cnumber.toString()) + "', '"
                    + personInfo.get(Column.addr.toString()) + "', '"
                    + personInfo.get(Column.phone.toString()) + "');";
            int i = st.executeUpdate(query);
            System.out.println("Row/s affected in table citizen: " + i);
            st.close();
            connection.closeDb();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Map<String, String> getWorkerDetails(String uuid) {
        Map<String, String> workerDetails = new HashMap<>();
        try {
            Statement st = connection.getDb().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM worker WHERE uuid = '" + uuid + "';");
            rs.next();
            workerDetails.put(Column.uuid.toString(), rs.getString(Column.uuid.toString()));
            workerDetails.put(Column.fname.toString(), rs.getString(Column.fname.toString()));
            workerDetails.put(Column.lname.toString(), rs.getString(Column.lname.toString()));
            workerDetails.put(Column.cnumber.toString(), rs.getString(Column.cnumber.toString()));
            workerDetails.put(Column.addr.toString(), rs.getString(Column.addr.toString()));
            workerDetails.put(Column.phone.toString(), rs.getString(Column.phone.toString()));
            workerDetails.put(Column.role.toString(), rs.getString(Column.role.toString()));
            workerDetails.put(Column.institution.toString(), rs.getString(Column.institution.toString()));
            workerDetails.put(Column.institutionaddr.toString(), rs.getString(Column.institutionaddr.toString()));
            rs.close();
            st.close();
            connection.closeDb();
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return workerDetails;
    }

    @Override
    public List<Map<String, String>> getInstitutionNames() {
         List<Map<String, String>> institutions = new ArrayList<>();
        try {
            Statement st = connection.getDb().createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT institution, institutionaddr FROM worker;");
            while(rs.next()){
                Map<String, String> institutionDetails = new HashMap<>();
                institutionDetails.put(Column.institution.toString(), rs.getString(1));
                institutionDetails.put(Column.institutionaddr.toString(), rs.getString(2));
                institutions.add(institutionDetails);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return institutions;
    }

}
