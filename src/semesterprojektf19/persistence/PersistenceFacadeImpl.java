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
    public boolean registerEmployee(String username, String password, UUID uuid, Map<String, String> personInfo) {
        System.out.println("Registering...");
        try {
            System.out.println("Trying to register...");
            PreparedStatement pst = connection.getDb().prepareStatement("INSERT INTO worker VALUES (?,?,?,?,?,?,?,?,?,?)");
            int i = 1;
            pst.setString(i++, uuid.toString());
            pst.setString(i++, personInfo.get(Column.FNAME.getColumnName()));
            pst.setString(i++, personInfo.get(Column.LNAME.getColumnName()));
            pst.setDate(i++, Date.valueOf(personInfo.get(Column.BDAY.getColumnName())));
            pst.setString(i++, personInfo.get(Column.CNUMBER.getColumnName()));
            pst.setString(i++, personInfo.get(Column.ADDR.getColumnName()));
            pst.setString(i++, personInfo.get(Column.PHONE.getColumnName()));
            pst.setString(i++, personInfo.get(Column.ROLE.getColumnName()));
            pst.setString(i++, personInfo.get(Column.INSTITUTION.getColumnName()));
            pst.setString(i, personInfo.get(Column.INSTITUTIONADDR.getColumnName()));
            pst.executeUpdate();
            pst.close();

            PreparedStatement pstAccount = connection.getDb().prepareStatement("INSERT INTO account VALUES (?,?,?)");
            pstAccount.setString(1, uuid.toString());
            pstAccount.setString(2, username);
            pstAccount.setString(3, password);
            pstAccount.executeUpdate();

            pstAccount.close();
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
            PreparedStatement pst = connection.getDb().prepareStatement("INSERT INTO citizen VALUES (?,?,?,?,?,?,?)");
            int i = 1;
            pst.setString(i++, personInfo.get(Column.UUID.getColumnName()));
            pst.setString(i++, personInfo.get(Column.FNAME.getColumnName()));
            pst.setString(i++, personInfo.get(Column.LNAME.getColumnName()));
            pst.setDate(i++, Date.valueOf(personInfo.get(Column.BDAY.getColumnName())));
            pst.setString(i++, personInfo.get(Column.CNUMBER.getColumnName()));
            pst.setString(i++, personInfo.get(Column.ADDR.getColumnName()));
            pst.setString(i, personInfo.get(Column.PHONE.getColumnName()));
            pst.executeUpdate();
            pst.close();
            connection.closeDb();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean registerCase(Map<String, String> caseDetails, UUID caseUUID,UUID citizenUUID, UUID workerUUID) {
        try {
            PreparedStatement pst = connection.getDb().prepareStatement("INSERT INTO casefile VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            int i = 1;
            pst.setString(i++, caseUUID.toString());
            pst.setString(i++, citizenUUID.toString());
            pst.setString(i++, workerUUID.toString());
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
            pst.close();
            connection.closeDb();
            System.out.println("CaseID: " + caseUUID.toString());
            return true;
        } catch (SQLException e) {
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
            workerDetails.put(Column.BDAY.getColumnName(), rs.getString(Column.BDAY.getColumnName()));
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
    public Map<String, String> getInstitutions() {
        Map<String, String> institutions = new HashMap<>();
        try {
            Statement st = connection.getDb().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM institution;");
            while (rs.next()) {
                institutions.put(rs.getString(1), rs.getString(2));
            }
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
            connection.closeDb();
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return citizens;
    }

}
