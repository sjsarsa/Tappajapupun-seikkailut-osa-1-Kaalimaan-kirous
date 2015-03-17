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
public class CreatureTest {
    
    private Creature hahmo;
    
    public CreatureTest() {
        hahmo = new Creature("Einari", 10, 1, 1);
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
    public void getNameToimii() {
        assertEquals(hahmo.getName(), "Einari");
    }
    
    @Test
    public void getMaxHpToimii() {
        assertEquals(hahmo.getMaxHp(), 10);
    }
    
    @Test
    public void getDamageToimii() {
        assertEquals(hahmo.getDamage(), 1);
    }
    
    @Test
    public void getExpToimii() {
        assertEquals(hahmo.getExp(), 1);
    }
    
    @Test
    public void setDamageToimii() {
        hahmo.setDamage(3);
        assertEquals(hahmo.getDamage(), 3);
    }
    
    @Test
    public void setCurrentHpToimii() {
        hahmo.setCurrentHp(4);
        assertEquals(hahmo.getCurrentHp(), 4);
        hahmo.setCurrentHp(1337);
        assertEquals(hahmo.getCurrentHp(), 10);
    }
    
    @Test
    public void setMaxHpToimii() {
        hahmo.setMaxHp(20);
        assertEquals(hahmo.getMaxHp(), 20);
    }
    
    @Test
    public void ottaaDamagea() {
        hahmo.setMaxHp(10);
        hahmo.takeDamage(3);  
        assertEquals(hahmo.getCurrentHp(), 7);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
