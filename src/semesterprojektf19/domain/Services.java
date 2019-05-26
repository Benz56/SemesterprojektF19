package semesterprojektf19.domain;

/**
 *
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
 */
public enum Services {
    HOUSING("midlertidigt ophold", "80");

    private String service, paragraph;

    private Services(String offer, String paragraph) {
        this.service = offer;
        this.paragraph = paragraph;
    }

    public String getParagraph() {
        return paragraph;
    }

    public String getService() {
        return service;
    }

}
