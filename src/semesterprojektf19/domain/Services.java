/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

/**
 *
 * @author hala_
 */
public enum Services {
    HOUSING("midlertidigt ophold", "80");
    
    private String offer; 
    private String paragraph;

    private Services (String offer, String paragraph) {
        this.offer = offer;
        this.paragraph = paragraph;
    }

    public String getParagraph() {
        return paragraph;
    }
    
}
