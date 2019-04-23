package semesterprojektf19.domain;

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
