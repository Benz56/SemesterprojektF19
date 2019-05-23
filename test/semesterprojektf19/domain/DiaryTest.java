/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import junit.framework.Assert;
import static org.hamcrest.CoreMatchers.is;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import semesterprojektf19.domain.accesscontrol.Role;

/**
 *
 * @author Jacob
 */
public class DiaryTest {

    private static Institution institution = new Institution("SDU", "Campusvej");
    private static Person person = new Worker(UUID.randomUUID(), "Jens", "Vejmand", Role.SOCIALWORKER, institution);
    private static Diary instance = new Diary(UUID.randomUUID());

    public DiaryTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createNote method, of class Diary.
     */
    @Test
    public void testCreateNote() {
        System.out.println("createNote");
        instance.createNote(person, "en note", "en titel", "21-05-2019");
        DiaryNote note1 = instance.getNotes().get(0);
        String noteActual = note1.getNote();
        String noteExpected = "en note";

        Assert.assertEquals(noteExpected, noteActual);

    }

    /**
     * Test of editNote method, of class Diary.
     */
    /**
     * Test of getNotes method, of class Diary.
     */
    @Test
    public void testGetNotes() {

        System.out.println("getNotes");
        instance.createNote(person, "en note", "en titel", "21-05-2019");
        instance.createNote(person, "endnu en note", "endnu en titel", "22-05-2019");
        DiaryNote note1 = instance.getNotes().get(0);
        DiaryNote note2 = instance.getNotes().get(1);
        List<DiaryNote> actual = instance.getNotes();
        List<DiaryNote> expected = Arrays.asList(note1, note2);

        assertThat(actual, is(expected));
        assertThat(actual, Matchers.hasSize(2));
    }
}
