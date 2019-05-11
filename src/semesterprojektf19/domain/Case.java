package semesterprojektf19.domain;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import semesterprojektf19.acquaintance.Column;
import semesterprojektf19.acquaintance.UserContainer;

public class Case implements Serializable {

    private final Worker caseWorker;
    private final Citizen citizen;
    private String guardianship, representation, agreementsAboutFurtherProcess, specialCircumstances, executingMunicipality, payingMunicipality;
    private boolean rightToRepresentation, informedOnElectronicInfo, consentRelevant, consentObtained;
    private Inquiry inquiry;
    private Elucidation elucidation;
    private Institution institution;
    private UUID uuid;
    private Diary diary;
    private static final long serialVersionUID = 5173068602800697696L;

    public Case(Worker caseWorker, Citizen citizen, Inquiry inquiry) {
        this.citizen = citizen;
        this.inquiry = inquiry;
        this.uuid = UUID.randomUUID();
        this.diary = new Diary(uuid); // Kan fjernes hvis dagbogen skal oprettes et andet sted, men det skal bare tilhøre sagen.
        this.caseWorker = caseWorker;
    }

    public Case(Map<String, String> caseDetails) {
        this((Worker) UserContainer.getUser(),
                CitizenManager.INSTANCE.getCitizen(caseDetails.get(Column.CITIZEN.getColumnName())),
                new Inquiry(caseDetails.get(Column.SHORTINFO.getColumnName()))
        );
        setGuardianship(caseDetails.get(Column.GUARDIAN.getColumnName()));
        setExecutingMunicipality(caseDetails.get(Column.EXECUTINGMUNICIPALITY.getColumnName()));
        setRepresentation(caseDetails.get(Column.REPRESENTATION.getColumnName()));
        setPayingMunicipality(caseDetails.get(Column.PAYINGMUNICIPALITY.getColumnName()));
        setConsentRelevant(Boolean.valueOf(caseDetails.get(Column.CONSENTRELEVANT.getColumnName())));
        setConsentObtained(Boolean.valueOf(caseDetails.get(Column.CONSENTGIVEN.getColumnName())));
        setRightToRepresentation(Boolean.valueOf(caseDetails.get(Column.RIGHTTOREPRESENTATION.getColumnName())));
        setInformedOnElectronicInfo(Boolean.valueOf(caseDetails.get(Column.INFORMEDONELECTRONICINFO.getColumnName())));
        setAgreementsAboutFurtherProcess(caseDetails.get(Column.AGREEMENTSONFURTHERPROCESS.getColumnName()));
        setSpecialCircumstances(caseDetails.get(Column.SPECIALCURCUMSTANCES.getColumnName()));
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

    public Worker getCaseWorker() {
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
