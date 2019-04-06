package semesterprojektf19.domain;

/**
 *
 * @author sofielouise & hala
 */
public class Case {
    private String guardianship;
    private String representation;
    private boolean rightToRepresentation;
    private boolean informedOnElectronicInfo;
    private String agreementsAboutFurtherProcess;
    private boolean consentRelevant; 
    private boolean consentObtained; 
    private String specialCircumstances;
    private String executingMunicipality;
    private String payingMunicipality; 
    private Inquiry inquiry;
    private Elucidation elucidation;

    // Tilføjet Jacob
    Diary diary;
    private Worker worker;
    private final String shortinfo = inquiry.getShortInfo();
    // Tilføjet Jacob
            
    public Case(Inquiry inquiry) {
        this.inquiry = inquiry;
        this.diary = new Diary(); // Kan fjernes hvis dagbogen skal oprettes et andet sted, men det skal bare tilhøre sagen.
    }
    
    
    
    public void startElucidation (String background){
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
    
    
    
}
