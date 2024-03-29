
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

    /**
     *
     */
    public CreatureOnFieldTest() {

    }

    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     *
     */
    @Before
    public void setUp() {
        impassables = new boolean[16][16];
        joppe = new CreatureOnField(3, 5, impassables, 1, 1, "Joppe", 30, 10, 0, null);
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     *
     */
    @Test
    public void gettersSetters() {
        assertEquals(joppe.getFieldTile(), 3);
        assertEquals(joppe.getX(), 1);
        assertEquals(joppe.getY(), 1);
        joppe.setX(5);
        joppe.setY(6);
        assertEquals(joppe.getX(), 5);
        assertEquals(joppe.getY(), 6);
    }

    /**
     *
     */
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

    /**
     *
     */
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

    /**
     *
     */
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

    /**
     *
     */
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
