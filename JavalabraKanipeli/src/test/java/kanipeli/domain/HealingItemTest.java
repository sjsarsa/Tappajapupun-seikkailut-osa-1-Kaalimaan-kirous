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
public class HealingItemTest {
    
    public HealingItemTest() {
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
    public void toStringToimii() {
        Item potion = new HealingItem("potion", 1, 10);
        assertEquals(potion.toString(), "potion: 1");
    }
    
    @Test
    public void getNameToimii() {
        Item potion = new HealingItem("potion", 1, 10);
        assertEquals(potion.getName(), "potion");
    }
    
    @Test
    public void getQuantityToimii() {
        Item potion = new HealingItem("potion", 1, 10);
        assertEquals(potion.getQuantity(), 1);
    }
    
    @Test
    public void use() {
        Item potion = new HealingItem("potion", 1, 10);
        Creature heikki = new Creature("Heikki", 30, 1, 1);
        heikki.takeDamage(13);
        potion.use(heikki);
        assertEquals(heikki.getCurrentHp(), 27);
        assertEquals(potion.getQuantity(), 0);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
