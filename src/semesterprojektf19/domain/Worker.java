/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Glumby
 */
public class Worker extends Person {

    // TODO implementer rolle.
    private List<Case> cases;

    public Worker(UUID uuid, String firstName, String lastName, int phoneNumber, String address) {
        super(uuid, firstName, lastName, phoneNumber, address, Role.EMPLOYEE);
        cases = new ArrayList<>();
    }
  
    public Case addCase(Case case1){
        cases.add(case1);
        return case1;
    }
}
