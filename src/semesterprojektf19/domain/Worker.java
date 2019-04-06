/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Glumby
 */
public class Worker extends Person {

    // TODO implementer rolle.
    
    private List<Case> cases;
    
    public Worker(String firstName, String lastName, int phoneNumber, String address) {
        super(firstName, lastName, phoneNumber, address);
        cases = new ArrayList<>();
    }
    
    
}
