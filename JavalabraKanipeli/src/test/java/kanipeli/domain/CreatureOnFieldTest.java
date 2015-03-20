/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sjsarsa
 */
public class CreatureOnFieldTest {
    
    public CreatureOnFieldTest() {
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
    @Test
    public void getters() {
        CreatureOnField joppe = new CreatureOnField(10, 10, "Joppe", 30, 10, 0);
        assertEquals(joppe.getX(), 10);
        assertEquals(joppe.getY(), 10);
    }
    
    @Test
    public void moveDown() {
        CreatureOnField joppe = new CreatureOnField(10, 10, "Joppe", 30, 10, 0);
        joppe.moveDown();
        joppe.moveDown();
        assertEquals(joppe.getX(), 10);
        assertEquals(joppe.getY(), 12);
    }
    
    @Test
    public void moveUp() {
        CreatureOnField joppe = new CreatureOnField(10, 10, "Joppe", 30, 10, 0);
        joppe.moveUp();
        assertEquals(joppe.getX(), 10);
        assertEquals(joppe.getY(), 9);
    }
    
    @Test
    public void moveLeft() {
        CreatureOnField joppe = new CreatureOnField(10, 10, "Joppe", 30, 10, 0);
        joppe.moveLeft();
        assertEquals(joppe.getX(), 9);
        assertEquals(joppe.getY(), 10);
    }
    
    @Test
    public void moveRight() {
        CreatureOnField joppe = new CreatureOnField(10, 10, "Joppe", 30, 10, 0);
        joppe.moveRight();
        assertEquals(joppe.getX(), 11);
        assertEquals(joppe.getY(), 10);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
