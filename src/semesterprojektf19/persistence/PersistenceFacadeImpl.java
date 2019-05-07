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
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
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

    private enum column {
        username, password, uuid, fname, lname, bday, cnumber,
        addr, phone, role, institution, institutionaddr
    };

    public PersistenceFacadeImpl() {
        connection = new Postgres();
    }

    @Override
    public Map<String, String> authenticate(String username, String password) {
        try {
            Statement st = connection.getDb().createStatement();
            ResultSet rs = st.executeQuery("Select * FROM account");
            while (rs.next()) {
                if (username.equals(rs.getString(column.username.toString())) && password.equalsIgnoreCase(rs.getString(column.password.toString()))) {
                    System.out.println("Authenticated.");
                    return getWorkerDetails(rs.getString(column.uuid.toString()));
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
        try {
            Statement st = connection.getDb().createStatement();
            String queryWorker = "INSERT INTO worker VALUES ('"
                    + uuid.toString() + "', ‘"
                    + personInfo.get(column.fname.toString()) + "’, '"
                    + personInfo.get(column.lname.toString()) + "', ‘"
                    + personInfo.get(column.bday.toString()) + "’, ‘"
                    + personInfo.get(column.cnumber.toString()) + "’, ‘"
                    + personInfo.get(column.addr.toString()) + "’, ’"
                    + personInfo.get(column.phone.toString()) + "’, ‘"
                    + personInfo.get(column.role.toString()) + "’, ‘"
                    + personInfo.get(column.institution.toString())
                    + personInfo.get(column.institutionaddr.toString()) + "’;";
            int i = st.executeUpdate(queryWorker);
            System.out.println("Row/s affected in table worker: " + i);
            String queryAccount = "INSERT INTO account VALUES (‘" + uuid + "’, ‘" + username + "’, '" + password + "');";
            int j = st.executeUpdate(queryAccount);
            System.out.println("Row/s affected in table account: " + j);
            st.close();
            connection.closeDb();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    @Override
    public boolean registerCitizen(Map<String, String> personInfo) {
        try {
            Statement st = connection.getDb().createStatement();
            String query = "INSERT INTO citizen VALUES ('"
                    + personInfo.get(column.uuid.toString()) + "', ‘"
                    + personInfo.get(column.fname.toString()) + "’, '"
                    + personInfo.get(column.lname.toString()) + "', ‘"
                    + personInfo.get(column.bday.toString()) + "’, ‘"
                    + personInfo.get(column.cnumber.toString()) + "’, ‘"
                    + personInfo.get(column.addr.toString()) + "’, ’"
                    + personInfo.get(column.phone.toString());
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
            workerDetails.put(column.uuid.toString(), rs.getString(column.uuid.toString()));
            workerDetails.put(column.fname.toString(), rs.getString(column.fname.toString()));
            workerDetails.put(column.lname.toString(), rs.getString(column.lname.toString()));
            workerDetails.put(column.cnumber.toString(), rs.getString(column.cnumber.toString()));
            workerDetails.put(column.addr.toString(), rs.getString(column.addr.toString()));
            workerDetails.put(column.phone.toString(), rs.getString(column.phone.toString()));
            workerDetails.put(column.role.toString(), rs.getString(column.role.toString()));
            workerDetails.put(column.institution.toString(), rs.getString(column.institution.toString()));
            workerDetails.put(column.institutionaddr.toString(), rs.getString(column.institutionaddr.toString()));
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
                institutionDetails.put(column.institution.toString(), rs.getString(1));
                institutionDetails.put(column.institutionaddr.toString(), rs.getString(2));
                institutions.add(institutionDetails);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return institutions;
    }

}
