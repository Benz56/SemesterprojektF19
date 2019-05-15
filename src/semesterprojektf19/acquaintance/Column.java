/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.acquaintance;

/**
 *
 * @author sofielouise
 */
public enum Column {
    USERNAME("username"), PASSWORD("password"), UUID("uuid"), FNAME("fName"),
    LNAME("lName"), BDAY("bDay"), CNUMBER("cNumber"), ADDR("addr"), PHONE("phone"),
    ROLE("role"), INSTITUTION("institution"), INSTITUTIONADDR("institutionAddr"),
    CITIZEN("citizenuuid"), CASEWORKER("caseworkeruuid"), SHORTINFO("shortInfo"),
    GUARDIAN("guardianship"), EXECUTINGMUNICIPALITY("executingMunicipality"),
    PAYINGMUNICIPALITY("payingMunicipality"), REPRESENTATION("representation"),
    CONSENTRELEVANT("consentRelevant"), CONSENTGIVEN("consentObtained"),
    RIGHTTOREPRESENTATION("rightToRep"), INFORMEDONELECTRONICINFO("informed"),
    AGREEMENTSONFURTHERPROCESS("agreementProcess"),
    SPECIALCURCUMSTANCES("specialCircumstances"), TITLE("title"), 
    DATE_OF_OBS("dateofobs"), DATE_OF_EDIT("dateofedit"),CONTENT("content"),
    CREATOR("creator");
    private final String name;

    private Column(String name) {
        this.name = name;
    }

    public String getColumnName() {
        return name;
    }
}
