package semesterprojektf19.domain;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import semesterprojektf19.acquaintance.Column;
import semesterprojektf19.acquaintance.UserContainer;

public class Case implements Serializable {

    private final UUID uuid;
    private final UUID caseWorker;
    private final Citizen citizen;
    private final Diary diary;
    private String guardianship, representation, agreementsAboutFurtherProcess, specialCircumstances, executingMunicipality, payingMunicipality;
    private boolean rightToRepresentation, informedOnElectronicInfo, consentRelevant, consentObtained;
    private Inquiry inquiry;
    private Elucidation elucidation;
    private Institution institution;
    private static final long serialVersionUID = 5173068602800697696L;

    public Case(Map<String, String> caseDetails, Citizen citizen) {
        this.caseWorker = caseDetails.containsKey(Column.CASEWORKER.getColumnName()) ? UUID.fromString(caseDetails.get(Column.CASEWORKER.getColumnName())) : UserContainer.getUser().getUuid();
        this.citizen = citizen;
        this.inquiry = new Inquiry(caseDetails.get(Column.SHORTINFO.getColumnName()));
        this.uuid = caseDetails.containsKey(Column.UUID.getColumnName()) ? UUID.fromString(caseDetails.get(Column.UUID.getColumnName())) : UUID.randomUUID();
        this.diary = new Diary(uuid); // Kan fjernes hvis dagbogen skal oprettes et andet sted, men det skal bare tilhøre sagen.
        this.guardianship = caseDetails.get(Column.GUARDIAN.getColumnName());
        this.executingMunicipality = caseDetails.get(Column.EXECUTINGMUNICIPALITY.getColumnName());
        this.representation = caseDetails.get(Column.REPRESENTATION.getColumnName());
        this.payingMunicipality = caseDetails.get(Column.PAYINGMUNICIPALITY.getColumnName());
        this.consentRelevant = Boolean.valueOf(caseDetails.get(Column.CONSENTRELEVANT.getColumnName()));
        this.consentObtained = Boolean.valueOf(caseDetails.get(Column.CONSENTGIVEN.getColumnName()));
        this.rightToRepresentation = Boolean.valueOf(caseDetails.get(Column.RIGHTTOREPRESENTATION.getColumnName()));
        this.informedOnElectronicInfo = Boolean.valueOf(caseDetails.get(Column.INFORMEDONELECTRONICINFO.getColumnName()));
        this.agreementsAboutFurtherProcess = caseDetails.get(Column.AGREEMENTSONFURTHERPROCESS.getColumnName());
        this.specialCircumstances = caseDetails.get(Column.SPECIALCURCUMSTANCES.getColumnName());
        this.institution = new Institution(caseDetails.get(Column.INSTITUTION.getColumnName()), caseDetails.get(Column.INSTITUTIONADDR.getColumnName()));
    }

    public void startElucidation(String background) {
        elucidation = new Elucidation(background);
    }

    public String getGuardianship() {
        return guardianship;
    }

    public void setGuardianship(String guardianship) {
        this.guardianship = guardianship;
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public boolean isRightToRepresentation() {
        return rightToRepresentation;
    }

    public void setRightToRepresentation(boolean rightToRepresentation) {
        this.rightToRepresentation = rightToRepresentation;
    }

    public boolean isInformedOnElectronicInfo() {
        return informedOnElectronicInfo;
    }

    public void setInformedOnElectronicInfo(boolean informedOnElectronicInfo) {
        this.informedOnElectronicInfo = informedOnElectronicInfo;
    }

    public String getAgreementsAboutFurtherProcess() {
        return agreementsAboutFurtherProcess;
    }

    public void setAgreementsAboutFurtherProcess(String agreementsAboutFurtherProcess) {
        this.agreementsAboutFurtherProcess = agreementsAboutFurtherProcess;
    }

    public boolean isConsentRelevant() {
        return consentRelevant;
    }

    public void setConsentRelevant(boolean consentRelevant) {
        this.consentRelevant = consentRelevant;
    }

    public boolean isConsentObtained() {
        return consentObtained;
    }

    public void setConsentObtained(boolean consentObtained) {
        this.consentObtained = consentObtained;
    }

    public String getSpecialCircumstances() {
        return specialCircumstances;
    }

    public void setSpecialCircumstances(String specialCircumstances) {
        this.specialCircumstances = specialCircumstances;
    }

    public String getExecutingMunicipality() {
        return executingMunicipality;
    }

    public void setExecutingMunicipality(String executingMunicipality) {
        this.executingMunicipality = executingMunicipality;
    }

    public String getPayingMunicipality() {
        return payingMunicipality;
    }

    public void setPayingMunicipality(String payingMunicipality) {
        this.payingMunicipality = payingMunicipality;
    }

    public Inquiry getInquiry() {
        return inquiry;
    }

    public void setInquiry(Inquiry inquiry) {
        this.inquiry = inquiry;
    }

    public Elucidation getElucidation() {
        return elucidation;
    }

    public void setElucidation(Elucidation elucidation) {
        this.elucidation = elucidation;
    }

    public UUID getCaseWorker() {
        return caseWorker;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Institution getInstitution() {
        return institution;
    }

    public Diary getDiary() {
        return diary;
    }
}
