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
    public Map<String, String> authenticate(String username, String password);
    
    public Map<String, String> getWorkerDetails(String uuid);
    
    public boolean registerEmployee(String username, String password, UUID uuid, Map<String, String> personInfo);
    
    public boolean registerCitizen(Map<String, String> personInfo);
    
    public Map<String, String> getInstitutions();
    
    public List<Map<String, String>> getCitizens();

    public boolean registerCase(Map<String, String> caseDetails, UUID caseUUID, UUID citizenUUID, UUID uuid);
}
