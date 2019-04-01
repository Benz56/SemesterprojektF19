/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

/**
 *
 * @author hala_ & Soffi
 */
public class TargetArea {
    private String informationFromCitizen;
    private String informationFromOthers;
    private String comments;
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
