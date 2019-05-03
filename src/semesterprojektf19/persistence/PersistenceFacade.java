/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.persistence;

import java.util.Map;
import java.util.UUID;

/**
 *
 * @author sofielouise
 */
public interface PersistenceFacade {
    public UUID authenticate(String username, String password);
    
    public Map<String, String> getWorkerDetails(String uuid);
    
    public boolean register(String username, String password, UUID uuid, Object person);
}
