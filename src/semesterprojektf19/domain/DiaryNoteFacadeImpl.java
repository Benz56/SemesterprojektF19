/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import java.util.Map;

/**
 *
 * @author Glumby
 */
public class DiaryNoteFacadeImpl implements DiaryNoteFacade {
    
    

    @Override
    public void createNote(Map<String, String> noteDetails) {

        Citizen citizen = CitizenManager.INSTANCE.getCitizen(noteDetails.get("citizenInfo"));
        int index = Integer.parseInt(noteDetails.get("index"));
        Case casefile = citizen.getCase(index);
        casefile.getDiary().createNote((Worker)UserContainer.getUser(), noteDetails.get("note"), noteDetails.get("titel"),noteDetails.get("dateOfObservation"));
        citizen.saveToFile();
        //For debugging
        //System.out.println("Note created for: "+ citizen.getFirstName() + "on case index: " +index);
        //System.out.println(citizen.getCase(index).getDiary().getNotes());
    }

    
    
}
