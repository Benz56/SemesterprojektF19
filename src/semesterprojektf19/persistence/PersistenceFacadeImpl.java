/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
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
            String query = "SELECT * FROM account WHERE username = ? AND password = crypt(?, password)";
            PreparedStatement pst = connection.getDb().prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                UUID uuid = (UUID) rs.getObject(Column.UUID.getColumnName());
                System.out.println("UUID got: " + uuid);
                if (uuid != null) {
                    System.out.println("Authenticated.");
                    return getWorkerDetails(uuid);
                }
            }
            rs.close();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public boolean registerEmployee(String username, String password, UUID uuid, Map<String, String> personInfo) {
        System.out.println("Registering...");
        try {
            System.out.println("Trying to register...");
            try (PreparedStatement pstAccount = connection.getDb().prepareStatement("INSERT INTO account VALUES (?, ?, crypt(?, gen_salt('bf')));")) {
                pstAccount.setObject(1, uuid);
                pstAccount.setString(2, username);
                pstAccount.setString(3, password);
                pstAccount.executeUpdate();
            }
            try (PreparedStatement pst = connection.getDb().prepareStatement("INSERT INTO worker VALUES (?,?,?,?,?)")) {
                int i = 1;
                pst.setObject(i++, uuid);
                pst.setString(i++, personInfo.get(Column.FNAME.getColumnName()));
                pst.setString(i++, personInfo.get(Column.LNAME.getColumnName()));
                pst.setString(i++, personInfo.get(Column.ROLE.getColumnName()));
                pst.setString(i++, personInfo.get(Column.INSTITUTION.getColumnName()));
                pst.executeUpdate();
            }
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
            PreparedStatement pst = connection.getDb().prepareStatement("INSERT INTO citizen VALUES (?,?,?,?,?,?,?)");
            int i = 1;
            pst.setObject(i++, UUID.fromString(personInfo.get(Column.UUID.getColumnName())));
            pst.setString(i++, personInfo.get(Column.FNAME.getColumnName()));
            pst.setString(i++, personInfo.get(Column.LNAME.getColumnName()));
            pst.setString(i++, personInfo.get(Column.BDAY.getColumnName()));
            pst.setString(i++, personInfo.get(Column.CNUMBER.getColumnName()));
            pst.setString(i++, personInfo.get(Column.ADDR.getColumnName()));
            pst.setString(i, personInfo.get(Column.PHONE.getColumnName()));
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean registerCase(Map<String, String> caseDetails, UUID caseUUID, UUID citizenUUID, UUID workerUUID) {
        try {
            try (PreparedStatement pst = connection.getDb().prepareStatement("INSERT INTO casefile VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)")) {
                int i = 1;
                pst.setObject(i++, caseUUID);
                pst.setObject(i++, citizenUUID);
                pst.setObject(i++, workerUUID);
                pst.setString(i++, caseDetails.get(Column.INSTITUTION.getColumnName()));
                pst.setString(i++, caseDetails.get(Column.GUARDIAN.getColumnName()));
                pst.setString(i++, caseDetails.get(Column.REPRESENTATION.getColumnName()));
                pst.setString(i++, caseDetails.get(Column.AGREEMENTSONFURTHERPROCESS.getColumnName()));
                pst.setString(i++, caseDetails.get(Column.SPECIALCURCUMSTANCES.getColumnName()));
                pst.setString(i++, caseDetails.get(Column.EXECUTINGMUNICIPALITY.getColumnName()));
                pst.setString(i++, caseDetails.get(Column.PAYINGMUNICIPALITY.getColumnName()));
                pst.setBoolean(i++, Boolean.valueOf(caseDetails.get(Column.RIGHTTOREPRESENTATION.getColumnName())));
                pst.setBoolean(i++, Boolean.valueOf(caseDetails.get(Column.INFORMEDONELECTRONICINFO.getColumnName())));
                pst.setBoolean(i++, Boolean.valueOf(caseDetails.get(Column.CONSENTRELEVANT.getColumnName())));
                pst.setBoolean(i++, Boolean.valueOf(caseDetails.get(Column.CONSENTGIVEN.getColumnName())));
                pst.executeUpdate();
            }
            System.out.println("CaseID: " + caseUUID.toString());
            return true;
        } catch (SQLException e) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public Map<String, String> getWorkerDetails(UUID uuid) {
        Map<String, String> workerDetails = new HashMap<>();
        try {
            String query = "SELECT * FROM worker WHERE uuid = ?";
            PreparedStatement pst = connection.getDb().prepareStatement(query);
            pst.setObject(1, uuid);
            ResultSet rs = pst.executeQuery();
            rs.next();
            System.out.println("workerDetails: UUID: " + rs.getObject(Column.UUID.getColumnName()).toString());
            workerDetails.put(Column.UUID.getColumnName(), rs.getObject(Column.UUID.getColumnName()).toString());
            workerDetails.put(Column.FNAME.getColumnName(), rs.getString(Column.FNAME.getColumnName()));
            workerDetails.put(Column.LNAME.getColumnName(), rs.getString(Column.LNAME.getColumnName()));
            workerDetails.put(Column.ROLE.getColumnName(), rs.getString(Column.ROLE.getColumnName()));
            workerDetails.put(Column.INSTITUTION.getColumnName(), rs.getString(Column.INSTITUTION.getColumnName()));
            rs.close();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return workerDetails;
    }

    @Override
    public Map<String, String> getInstitutions() {
        Map<String, String> institutions = new HashMap<>();
        try {
            Statement st = connection.getDb().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM institution;");
            while (rs.next()) {
                institutions.put(rs.getString(1), rs.getString(2));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return institutions;
    }

    @Override
    public List<Map<String, String>> getCitizens() {
        List<Map<String, String>> citizens = new ArrayList<>();
        try {
            Statement st = connection.getDb().createStatement();
            String query = "SELECT * FROM citizen";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Map<String, String> citizenDetails = new HashMap<>();
                citizenDetails.put(Column.UUID.getColumnName(), rs.getString(Column.UUID.getColumnName()));
                citizenDetails.put(Column.FNAME.getColumnName(), rs.getString(Column.FNAME.getColumnName()));
                citizenDetails.put(Column.LNAME.getColumnName(), rs.getString(Column.LNAME.getColumnName()));
                citizenDetails.put(Column.BDAY.getColumnName(), rs.getString(Column.BDAY.getColumnName()));
                citizenDetails.put(Column.CNUMBER.getColumnName(), rs.getString(Column.CNUMBER.getColumnName()));
                citizenDetails.put(Column.ADDR.getColumnName(), rs.getString(Column.ADDR.getColumnName()));
                citizenDetails.put(Column.PHONE.getColumnName(), rs.getString(Column.PHONE.getColumnName()));
                citizens.add(citizenDetails);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return citizens;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        System.out.println(map);
    }
}
