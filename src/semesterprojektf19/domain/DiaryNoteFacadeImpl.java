/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import java.util.Map;
import semesterprojektf19.presentation.CreateNoteUIController;
import semesterprojektf19.presentation.MainUIController;

/**
 *
 * @author Glumby
 */
public class DiaryNoteFacadeImpl implements DiaryNoteFacade {
    
    
    

    private final DomainFacadeImpl domainFacadeImpl; 
    private final CitizenManager citizenManager;
    
    public DiaryNoteFacadeImpl(DomainFacadeImpl domainFacadeImpl){
        this.domainFacadeImpl = domainFacadeImpl;
        this.citizenManager = domainFacadeImpl.getCitizenManager();
    }
    
    @Override
    public void createNote(Map<String, String> noteDetails) {

        Citizen citizen = citizenManager.getCitizen(noteDetails.get("citizenInfo"));
        int index = Integer.parseInt(noteDetails.get("index"));
        Case casefile = citizen.getCase(index);
        casefile.getDiary().createNote((Worker)UserContainer.getUser(), noteDetails.get("note"), noteDetails.get("titel"));
        citizen.saveToFile();
        //For debugging
        System.out.println("Note created for: "+ citizen.getFirstName() + "on case index: " +index);
        System.out.println(citizen.getCase(index).getDiary().getNotes());
    }

    
    
}
