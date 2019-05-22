package semesterprojektf19.persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import semesterprojektf19.acquaintance.Column;
import semesterprojektf19.acquaintance.UserContainer;

public class PersistenceFacadeImpl implements PersistenceFacade {

    private final Postgres connection;

    public PersistenceFacadeImpl() {
        connection = new Postgres();
    }

    @Override
    public Map<String, String> authenticate(String username, String password) {
        try (PreparedStatement pst = connection.getConnection().prepareStatement("SELECT * FROM account WHERE username = ? AND password = crypt(?, password)")) {
            pst.setString(1, username);
            pst.setString(2, password);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    UUID uuid = (UUID) rs.getObject(Column.UUID.getColumnName());
                    System.out.println("UUID got: " + uuid);
                    if (uuid != null) {
                        System.out.println("Authenticated.");
                        return getWorkerDetails(uuid);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public boolean registerEmployee(String username, String password, UUID uuid, Map<String, String> personInfo) {
        System.out.println("Registering...");
        try {
            System.out.println("Trying to register...");
            try (PreparedStatement pstAccount = connection.getConnection().prepareStatement("INSERT INTO account VALUES (?, ?, crypt(?, gen_salt('bf')));")) {
                pstAccount.setObject(1, uuid);
                pstAccount.setString(2, username);
                pstAccount.setString(3, password);
                pstAccount.executeUpdate();
            }
            try (PreparedStatement pst = connection.getConnection().prepareStatement("INSERT INTO worker VALUES (?,?,?,?,?)")) {
                int i = 1;
                pst.setObject(i++, uuid);
                pst.setString(i++, personInfo.get(Column.FNAME.getColumnName()));
                pst.setString(i++, personInfo.get(Column.LNAME.getColumnName()));
                pst.setString(i++, personInfo.get(Column.ROLE.getColumnName()));
                pst.setString(i++, personInfo.get(Column.INSTITUTION.getColumnName()));
                pst.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL Exception caught while executing register employee.");
        }
        return false;
    }

    @Override
    public boolean registerCitizen(Map<String, String> personInfo) {
        try (PreparedStatement pst = connection.getConnection().prepareStatement("INSERT INTO citizen VALUES (?,?,?,?,?,?,?)")) {
            int i = 1;
            pst.setObject(i++, UUID.fromString(personInfo.get(Column.UUID.getColumnName())));
            pst.setString(i++, personInfo.get(Column.FNAME.getColumnName()));
            pst.setString(i++, personInfo.get(Column.LNAME.getColumnName()));
            pst.setString(i++, personInfo.get(Column.BDAY.getColumnName()));
            pst.setString(i++, personInfo.get(Column.CNUMBER.getColumnName()));
            pst.setString(i++, personInfo.get(Column.ADDR.getColumnName()));
            pst.setString(i, personInfo.get(Column.PHONE.getColumnName()));
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean registerCase(Map<String, String> caseDetails, UUID caseUUID, UUID citizenUUID, UUID workerUUID, UUID diaryUUID) {
        try (PreparedStatement pst = connection.getConnection().prepareStatement("INSERT INTO casefile VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                PreparedStatement pst2 = connection.getConnection().prepareStatement("INSERT INTO diary VALUES (?,?)")) {
            int i = 1;
            pst.setObject(i++, caseUUID);
            pst.setObject(i++, citizenUUID);
            pst.setObject(i++, UserContainer.getUser().getUuid());
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
            pst.setString(i++, caseDetails.get(Column.SHORTINFO.getColumnName()));
            pst.executeUpdate();
            pst2.setObject(1, diaryUUID);
            pst2.setObject(2, caseUUID);
            pst2.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("SQL exception catched: CaseID: " + caseUUID.toString());
            return true;
        }
        return false;
    }

    @Override
    public boolean createDiaryNote(UUID uuid, UUID diaryuuid, UUID editoruuid, long dateOfObs, long dateOfEdit, String title, String content) {
        try (PreparedStatement pst = connection.getConnection().prepareStatement("INSERT INTO diarynote VALUES (?,?,?,?,?,?,?)")) {
            int i = 1;
            pst.setObject(i++, uuid);
            pst.setObject(i++, diaryuuid);
            pst.setDate(i++, new Date(dateOfObs));
            pst.setTimestamp(i++, new Timestamp(dateOfEdit));
            pst.setObject(i++, editoruuid);
            pst.setString(i++, title);
            pst.setString(i++, content);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Map<String, String> getWorkerDetails(UUID uuid) {
        Map<String, String> workerDetails = new HashMap<>();
        try (PreparedStatement pst = connection.getConnection().prepareStatement("SELECT * FROM worker WHERE uuid = ?")) {
            pst.setObject(1, uuid);
            try (ResultSet rs = pst.executeQuery()) {
                rs.next();
                System.out.println("workerDetails: UUID: " + rs.getObject(Column.UUID.getColumnName()).toString());
                workerDetails.put(Column.UUID.getColumnName(), rs.getObject(Column.UUID.getColumnName()).toString());
                workerDetails.put(Column.FNAME.getColumnName(), rs.getString(Column.FNAME.getColumnName()));
                workerDetails.put(Column.LNAME.getColumnName(), rs.getString(Column.LNAME.getColumnName()));
                workerDetails.put(Column.ROLE.getColumnName(), rs.getString(Column.ROLE.getColumnName()));
                workerDetails.put(Column.INSTITUTION.getColumnName(), rs.getString(Column.INSTITUTION.getColumnName()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return workerDetails;
    }

    @Override
    public Map<String, String> getInstitutions() {
        Map<String, String> institutions = new HashMap<>();
        try {
            try (Statement st = connection.getConnection().createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM institution;")) {
                while (rs.next()) {
                    institutions.put(rs.getString(1), rs.getString(2));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return institutions;
    }

    @Override
    public List<Map<String, String>> getCitizens() {
        List<Map<String, String>> citizens = new ArrayList<>();
        try (Statement st = connection.getConnection().createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM citizen")) {
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
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return citizens;
    }

    @Override
    public List<Map<String, String>> getCases() {
        List<Map<String, String>> cases = new ArrayList<>();
        try {
            try (Statement st = connection.getConnection().createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM caseFile")) {
                while (rs.next()) {
                    Map<String, String> caseDetails = new HashMap<>();
                    caseDetails.put(Column.UUID.getColumnName(), rs.getString(Column.UUID.getColumnName()));
                    caseDetails.put(Column.CITIZEN.getColumnName(), rs.getString(Column.CITIZEN.getColumnName()));
                    caseDetails.put(Column.CASEWORKER.getColumnName(), rs.getString(Column.CASEWORKER.getColumnName()));
                    caseDetails.put(Column.INSTITUTION.getColumnName(), rs.getString(Column.INSTITUTION.getColumnName()));
                    caseDetails.put(Column.GUARDIAN.getColumnName(), rs.getString(Column.GUARDIAN.getColumnName()));
                    caseDetails.put(Column.REPRESENTATION.getColumnName(), rs.getString(Column.REPRESENTATION.getColumnName()));
                    caseDetails.put(Column.AGREEMENTSONFURTHERPROCESS.getColumnName(), rs.getString(Column.AGREEMENTSONFURTHERPROCESS.getColumnName()));
                    caseDetails.put(Column.SPECIALCURCUMSTANCES.getColumnName(), rs.getString(Column.SPECIALCURCUMSTANCES.getColumnName()));
                    caseDetails.put(Column.EXECUTINGMUNICIPALITY.getColumnName(), rs.getString(Column.EXECUTINGMUNICIPALITY.getColumnName()));
                    caseDetails.put(Column.PAYINGMUNICIPALITY.getColumnName(), rs.getString(Column.PAYINGMUNICIPALITY.getColumnName()));
                    caseDetails.put(Column.RIGHTTOREPRESENTATION.getColumnName(), rs.getString(Column.RIGHTTOREPRESENTATION.getColumnName()));
                    caseDetails.put(Column.INFORMEDONELECTRONICINFO.getColumnName(), rs.getString(Column.INFORMEDONELECTRONICINFO.getColumnName()));
                    caseDetails.put(Column.CONSENTRELEVANT.getColumnName(), rs.getString(Column.CONSENTRELEVANT.getColumnName()));
                    caseDetails.put(Column.CONSENTGIVEN.getColumnName(), rs.getString(Column.CONSENTGIVEN.getColumnName()));
                    caseDetails.put(Column.SHORTINFO.getColumnName(), rs.getString(Column.SHORTINFO.getColumnName()));
                    cases.add(caseDetails);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cases;
    }

    @Override
    public boolean editCitizen(Map<String, String> personInfo) {
        try {
            try (PreparedStatement pst = connection.getConnection().prepareStatement("UPDATE citizen SET fname = ?, lname = ?, addr = ?, phone = ? WHERE uuid = ?")) {
                int i = 1;
                pst.setString(i++, personInfo.get(Column.FNAME.getColumnName()));
                pst.setString(i++, personInfo.get(Column.LNAME.getColumnName()));
                pst.setString(i++, personInfo.get(Column.ADDR.getColumnName()));
                pst.setString(i++, personInfo.get(Column.PHONE.getColumnName()));
                pst.setObject(i++, UUID.fromString(personInfo.get(Column.UUID.getColumnName())));
                pst.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @Override
    public Map<UUID, List<Map<String, String>>> getDiaryNotes(UUID diaryUUID) {
        Map<UUID, List<Map<String, String>>> notes = new HashMap<>();
        Map<UUID, String> workerNameCache = new HashMap<>();
        try (PreparedStatement pst = connection.getConnection().prepareStatement("SELECT * FROM diarynote WHERE diaryuuid = ? ORDER BY dateofedit DESC")) {
            pst.setObject(1, diaryUUID);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Map<String, String> map = new HashMap<>();
                    map.put(Column.UUID.getColumnName(), rs.getString(Column.UUID.getColumnName()));
                    map.put(Column.TITLE.getColumnName(), rs.getString(Column.TITLE.getColumnName()));
                    String date = rs.getString(Column.DATE_OF_OBS.getColumnName());
                    map.put(Column.DATE_OF_OBS.getColumnName(), date.substring(8, 10) + "-" + date.substring(5, 7) + "-" + date.substring(0, 4));
                    map.put(Column.DATE_OF_EDIT.getColumnName(), String.valueOf(rs.getTimestamp(Column.DATE_OF_EDIT.getColumnName()).getTime()));
                    map.put(Column.CONTENT.getColumnName(), rs.getString(Column.CONTENT.getColumnName()));
                    map.put(Column.CREATOR.getColumnName(), workerNameCache.computeIfAbsent(UUID.fromString(rs.getString(Column.EDITOR_UUID.getColumnName())), uuid -> {
                        try (PreparedStatement pst2 = connection.getConnection().prepareStatement("SELECT " + Column.FNAME.getColumnName() + ", " + Column.LNAME.getColumnName() + " FROM worker WHERE " + Column.UUID.getColumnName() + " = '" + uuid + "'"); ResultSet rs2 = pst2.executeQuery()) {
                            rs2.next();
                            return rs2.getString(Column.FNAME.getColumnName()) + " " + rs2.getString(Column.LNAME.getColumnName());
                        } catch (SQLException ex) {
                            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        return "";
                    }));
                    notes.computeIfAbsent(UUID.fromString(map.get(Column.UUID.getColumnName())), k -> new ArrayList<>()).add(map);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notes;
    }

    @Override
    public void registerInstitution(String name, String address) {
        try (PreparedStatement pst = connection.getConnection().prepareStatement("INSERT INTO institution VALUES (?,?) ON CONFLICT (name) DO UPDATE "
                + "SET name = excluded.name, addr = excluded.addr")) {
            int i = 1;
            pst.setString(i++, name);
            pst.setString(i++, address);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
