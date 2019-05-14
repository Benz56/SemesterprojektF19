/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.persistence;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author sofielouise
 */
public interface PersistenceFacade {

    Map<String, String> authenticate(String username, String password);

    Map<String, String> getWorkerDetails(UUID uuid);

    boolean registerEmployee(String username, String password, UUID uuid, Map<String, String> personInfo);

    boolean registerCitizen(Map<String, String> personInfo);

    Map<String, String> getInstitutions();

    List<Map<String, String>> getCitizens();

    boolean registerCase(Map<String, String> caseDetails, UUID caseUUID, UUID citizenUUID, UUID uuid);

    List<Map<String, String>> getCases();

    public boolean editCitizen(Map<String, String> map);
}
