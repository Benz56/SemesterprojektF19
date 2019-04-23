package semesterprojektf19.persistence;

public enum FileType {
    ACCOUNTS("accounts.csv"),
    CASES("cases.csv"); //<-Eksempel

    private final String fileName;

    private FileType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
