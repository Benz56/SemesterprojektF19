package semesterprojektf19.acquaintance;

/**
 *
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
 */
public enum Column {
    USERNAME("username"), PASSWORD("password"), UUID("uuid"), FNAME("fName"),
    LNAME("lName"), NAME("name"), BDAY("bDay"), CNUMBER("cNumber"), ADDR("addr"), PHONE("phone"),
    ROLE("role"), INSTITUTION("institution"), INSTITUTIONADDR("institutionAddr"),
    CITIZEN("citizenuuid"), CASEWORKER("caseworkeruuid"), SHORTINFO("shortInfo"),
    GUARDIAN("guardianship"), EXECUTINGMUNICIPALITY("executingMunicipality"),
    PAYINGMUNICIPALITY("payingMunicipality"), REPRESENTATION("representation"),
    CONSENTRELEVANT("consentRelevant"), CONSENTGIVEN("consentObtained"),
    RIGHTTOREPRESENTATION("rightToRep"), INFORMEDONELECTRONICINFO("informed"),
    AGREEMENTSONFURTHERPROCESS("agreementProcess"), DIARY("diaryuuid"),
    SPECIALCURCUMSTANCES("specialCircumstances"), TITLE("title"),
    DATE_OF_OBS("dateofobs"), DATE_OF_EDIT("dateofedit"), CONTENT("content"),
    CREATOR("creator"), EDITOR_UUID("editoruuid"), BACKGROUND("background"), CASES("cases");

    private final String name;

    private Column(String name) {
        this.name = name;
    }

    public String getColumnName() {
        return name;
    }
}
