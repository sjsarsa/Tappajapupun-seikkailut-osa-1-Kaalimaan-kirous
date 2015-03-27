<<<<<<< HEAD
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

    private CreatureOnField joppe;
    boolean[][] impassables;

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
        impassables = new boolean[16][16];
        joppe = new CreatureOnField(impassables, 1, 1, "Joppe", 30, 10, 0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getters() {
        assertEquals(joppe.getX(), 1);
        assertEquals(joppe.getY(), 1);
    }

    @Test
    public void moveDown() {
        joppe.moveDown(0);
        assertEquals(joppe.moved, true);
        assertEquals(joppe.getX(), 1);
        assertEquals(joppe.getY(), 1);
        joppe.moveDown(2);
        assertEquals(joppe.getX(), 1);
        assertEquals(joppe.getY(), 2);
        impassables[1][3] = true;
        joppe.setImpassables(impassables);
        joppe.moveDown(4);
        assertEquals(joppe.getX(), 1);
        assertEquals(joppe.getY(), 2);
    }

    @Test
    public void moveUp() {
        impassables[1][0] = true;
        joppe.setImpassables(impassables);
        joppe.moveUp();
        assertEquals(joppe.moved, true);
        assertEquals(joppe.getX(), 1);
        assertEquals(joppe.getY(), 1);
        joppe.moveLeft();
        joppe.moveUp();
        joppe.moveUp();
        joppe.moveUp();
        assertEquals(joppe.getY(), 0);
        
    }

    @Test
    public void moveLeft() {
        impassables[0][1] = true;
        joppe.setImpassables(impassables);
        joppe.moveLeft();
        assertEquals(joppe.moved, true);
        assertEquals(joppe.getX(), 1);
        assertEquals(joppe.getY(), 1);
        impassables[0][1] = false;
        joppe.setImpassables(impassables);
        joppe.moveLeft();
        joppe.moveLeft();
        assertEquals(joppe.getX(), 0);
    }

    @Test
    public void moveRight() {
        impassables[3][1] = true;
        joppe.setImpassables(impassables);
        joppe.moveRight(1);
        assertEquals(joppe.moved, true);
        assertEquals(joppe.getX(), 1);
        assertEquals(joppe.getY(), 1);
        joppe.moveRight(2);
        assertEquals(joppe.getX(), 2);
        joppe.moveRight(4);
        assertEquals(joppe.getX(), 2);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
=======
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
>>>>>>> 271c09424254a94913a0ab1321bfb3ae66a15532
