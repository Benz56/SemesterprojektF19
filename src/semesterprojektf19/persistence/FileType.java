/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.persistence;

/**
 *
 * @author Benjamin Staugaard | Benz56
 */
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
