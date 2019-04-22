/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DomainFacadeImpl implements DomainFacade {

    private final CitizenManager citizenManager = new CitizenManager();

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
        citizen.addCase(c);
    }

    @Override
    public List<String> matchCitizens(String contains) {
        return citizenManager.getCitizens().values().stream().map(Citizen::toString).filter(citizen -> citizen.toLowerCase().contains(contains.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public void refresh() {
        citizenManager.refresh();
    }
}
