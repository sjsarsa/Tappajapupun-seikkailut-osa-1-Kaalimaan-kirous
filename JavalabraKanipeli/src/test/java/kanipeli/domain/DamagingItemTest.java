
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
 * @author Sami
 */
public class DamagingItemTest {
    
    /**
     *
     */
    public DamagingItemTest() {
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
    public void toStringToimii() {
        Item nuke = new DamagingItem("nuke", 1, 10000);
        assertEquals(nuke.toString(), "nuke: 1");
    }
    
    /**
     *
     */
    @Test
    public void getters() {
        Item nuke = new DamagingItem("nuke", 1, 10000);
        assertEquals(nuke.getName(), "nuke");
        assertEquals(nuke.getQuantity(), 1);
    }
    
    /**
     *
     */
    @Test
    public void use() {
        Item nuke = new DamagingItem("nuke", 1, 10000);
        Creature heikki = new Creature(5, "Heikki", 30, 1, 1);
        nuke.use(heikki);
        assertEquals(heikki.getCurrentHp(), 0);
        assertEquals(nuke.getQuantity(), 0);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
