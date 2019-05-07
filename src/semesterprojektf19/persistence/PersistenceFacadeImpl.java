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
                if (username.equals(rs.getString(Column.USERNAME.getColumnName())) && password.equals(rs.getString(Column.PASSWORD.getColumnName()))) {
                    System.out.println("Authenticated.");
                    String uuid = rs.getString(Column.UUID.getColumnName());
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
                    + personInfo.get(Column.FNAME.toString()) + "', '"
                    + personInfo.get(Column.LNAME.toString()) + "', '"
                    + personInfo.get(Column.BDAY.toString()) + "', '"
                    + personInfo.get(Column.CNUMBER.toString()) + "', '"
                    + personInfo.get(Column.ADDR.toString()) + "', '"
                    + personInfo.get(Column.PHONE.toString()) + "', '"
                    + personInfo.get(Column.ROLE.toString()) + "', '"
                    + personInfo.get(Column.INSTITUTION.toString()) + "', '"
                    + personInfo.get(Column.INSTITUTIONADDR.toString()) +"';'";
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
                    + personInfo.get(Column.UUID.toString()) + "', '"
                    + personInfo.get(Column.FNAME.toString()) + "', '"
                    + personInfo.get(Column.LNAME.toString()) + "', '"
                    + personInfo.get(Column.BDAY.toString()) + "', '"
                    + personInfo.get(Column.CNUMBER.toString()) + "', '"
                    + personInfo.get(Column.ADDR.toString()) + "', '"
                    + personInfo.get(Column.PHONE.toString()) + "');";
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
            workerDetails.put(Column.UUID.getColumnName(), rs.getString(Column.UUID.getColumnName()));
            workerDetails.put(Column.FNAME.getColumnName(), rs.getString(Column.FNAME.getColumnName()));
            workerDetails.put(Column.LNAME.getColumnName(), rs.getString(Column.LNAME.getColumnName()));
            workerDetails.put(Column.CNUMBER.getColumnName(), rs.getString(Column.CNUMBER.getColumnName()));
            workerDetails.put(Column.ADDR.getColumnName(), rs.getString(Column.ADDR.getColumnName()));
            workerDetails.put(Column.PHONE.getColumnName(), rs.getString(Column.PHONE.getColumnName()));
            workerDetails.put(Column.ROLE.getColumnName(), rs.getString(Column.ROLE.getColumnName()));
            workerDetails.put(Column.INSTITUTION.getColumnName(), rs.getString(Column.INSTITUTION.getColumnName()));
            workerDetails.put(Column.INSTITUTIONADDR.getColumnName(), rs.getString(Column.INSTITUTIONADDR.getColumnName()));
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
            ResultSet rs = st.executeQuery("SELECT * FROM institution;");
            while(rs.next()){
                Map<String, String> institutionDetails = new HashMap<>();
                institutionDetails.put(Column.INSTITUTION.toString(), rs.getString(1));
                institutionDetails.put(Column.INSTITUTIONADDR.toString(), rs.getString(2));
                institutions.add(institutionDetails);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return institutions;
    }

}
