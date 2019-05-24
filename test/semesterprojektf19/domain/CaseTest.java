/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import semesterprojektf19.domain.Case;
import semesterprojektf19.domain.Citizen;
import semesterprojektf19.domain.Institution;
import semesterprojektf19.domain.Person;
import semesterprojektf19.domain.Worker;
import static org.junit.Assert.*;
import semesterprojektf19.domain.accesscontrol.Role;

/**
 *
 * @author eybye
 */
public class CaseTest {

    private static Institution institution = new Institution("SDU", "Campusvej");
    private static Person person = new Worker(UUID.randomUUID(), "Jens", "Vejmand",
            Role.SOCIALWORKER, institution);
    private static Citizen citizen = new Citizen(UUID.randomUUID(), "Peter", 
            "Jørgensen", "01-01-70", "1111", "Niels Bohrs Alle 1", "22222222");
    private static Map<String, String> caseDetails = new HashMap<>();
    private static Case instance;

    public CaseTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        caseDetails.clear();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isConsentObtained method, of class Case.
     */
    @Test
    public void testIsConsentObtained() {
        System.out.println("isConsentObtained");
        boolean consentObtained = true;
        instance = new Case(UUID.randomUUID(), person.getUuid(), citizen, consentObtained, institution);
        boolean expResult = consentObtained;
        boolean result = instance.isConsentObtained();
        assertEquals(expResult, result);
    }

    /**
     * Test of setConsentObtained method, of class Case.
     */
    @Test
    public void testSetConsentObtained() {
        System.out.println("setConsentObtained");
        boolean consentObtained = false;
        instance = new Case(UUID.randomUUID(), person.getUuid(), citizen, consentObtained, institution);
        instance.setConsentObtained(consentObtained);
        assertEquals(consentObtained, instance.isConsentObtained());
    }

    /**
     * Test of setInstitution method, of class Case.
     */
    @Test
    public void testSetInstitution() {
        System.out.println("setInstitution");
        instance = new Case(UUID.randomUUID(), person.getUuid(), citizen, true, institution);
        instance.setInstitution(institution);
        assertEquals(institution, instance.getInstitution());
    }

    /**
     * Test of getInstitution method, of class Case.
     */
    @Test
    public void testGetInstitution() {
        System.out.println("getInstitution");
        instance = new Case(UUID.randomUUID(), person.getUuid(), citizen, true, institution);
        Institution expResult = institution;
        Institution result = instance.getInstitution();
        assertEquals(expResult, result);
    }
    
}