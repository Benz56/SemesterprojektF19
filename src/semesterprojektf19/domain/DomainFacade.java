/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Benjamin Staugaard | Benz56
 */
public interface DomainFacade {

    public void createCase(Map<String, String> caseDetails);

    public List<String> matchCitizens(String contains);

    public void refresh();

    public List<String> getUserCitizens();

    public Map<String, String> getCitizenDetails(String citizen);
    
    public Map<String,String> getDiaryDetails(String citizen,int index);
    
    

    
}
