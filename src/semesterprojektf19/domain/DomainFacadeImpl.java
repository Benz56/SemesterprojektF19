/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import semesterprojektf19.persistence.Persistence;

public class DomainFacadeImpl implements DomainFacade {

    private final CitizenManager citizenManager;

    public DomainFacadeImpl() {
        Persistence.INSTANCE.toString(); //Initialize persistence i.e. create required files.
        citizenManager = new CitizenManager();
    }

    @Override
    public void createCase(Map<String, String> caseDetails) {
        Citizen citizen = citizenManager.getCitizen(caseDetails.get("citizen"));
        Case c = new Case((Worker) UserContainer.getUser(), citizen, new Inquiry(caseDetails.get("shortInfo")));
        c.setGuardianship(caseDetails.get("guardian"));
        c.setExecutingMunicipality(caseDetails.get("executingMunicipality"));
        c.setRepresentation(caseDetails.get("representation"));
        c.setPayingMunicipality(caseDetails.get("payingMunicipality"));
        c.setConsentRelevant(Boolean.valueOf(caseDetails.get("consentRelevant")));
        c.setConsentObtained(Boolean.valueOf(caseDetails.get("consentGiven")));
        c.setRightToRepresentation(Boolean.valueOf(caseDetails.get("rightToRepresentation")));
        c.setInformedOnElectronicInfo(Boolean.valueOf(caseDetails.get("informedOnElectronicInfo")));
        c.setAgreementsAboutFurtherProcess(caseDetails.get("agreementsAboutFurtherProcess"));
        c.setSpecialCircumstances(caseDetails.get("specialCircumstances"));
        ((Worker) UserContainer.getUser()).addCase(c);
        UserContainer.getUser().saveToFile();
        citizen.addCase(c);
        citizen.saveToFile();
    }

    @Override
    public List<String> matchCitizens(String contains) {
        return citizenManager.getCitizens().values().stream().map(Citizen::toString).filter(citizen -> citizen.toLowerCase().contains(contains.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<String> getUserCitizens() {
        Person user = UserContainer.getUser();
        if (user != null) {
            return ((Worker) UserContainer.getUser()).getCitizens().stream().map(Citizen::toString).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void refresh() {
        citizenManager.refresh();
    }

    @Override
    public Map<String, String> getCitizenDetails(String citizenString) {
        Map<String, String> details = new HashMap<>();
        Citizen citizen = citizenManager.getCitizen(citizenString);
        details.put("name", citizen.getFirstName() + " " + citizen.getLastName());
        details.put("cpr", citizen.getCpr());
        details.put("address", citizen.getAddress());
        return details;
    }
}
