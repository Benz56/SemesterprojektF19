package semesterprojektf19.domain;

public class TargetArea {

    private String informationFromCitizen, informationFromOthers, comments;
    private int functionalAbility;

    public TargetArea(String informationFromCitizen, String informationFromOthers, String comments, int functionalAbility) {
        this.informationFromCitizen = informationFromCitizen;
        this.informationFromOthers = informationFromOthers;
        this.comments = comments;
        this.functionalAbility = functionalAbility;
    }

    public String getInformationFromCitizen() {
        return informationFromCitizen;
    }

    public void setInformationFromCitizen(String informationFromCitizen) {
        this.informationFromCitizen = informationFromCitizen;
    }

    public String getInformationFromOthers() {
        return informationFromOthers;
    }

    public void setInformationFromOthers(String informationFromOthers) {
        this.informationFromOthers = informationFromOthers;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getFunctionalAbility() {
        return functionalAbility;
    }

    public void setFunctionalAbility(int functionalAbility) {
        this.functionalAbility = functionalAbility;
    }

}
