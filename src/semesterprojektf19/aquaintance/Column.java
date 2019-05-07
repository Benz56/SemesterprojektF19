/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.aquaintance;

/**
 *
 * @author sofielouise
 */
public enum Column {
    USERNAME("username"), PASSWORD("password"), UUID("uuid"), FNAME("fName"), LNAME("lName"), BDAY("bDay"), CNUMBER("cNumber"),
    ADDR("addr"), PHONE("phone"), ROLE("role"), INSTITUTION("institution"), INSTITUTIONADDR("institutionAddr");
    private final String name;
    
    private Column(String name){
        this.name = name;
    }
    
    public String getColumnName() {
        return name;
    }
}
