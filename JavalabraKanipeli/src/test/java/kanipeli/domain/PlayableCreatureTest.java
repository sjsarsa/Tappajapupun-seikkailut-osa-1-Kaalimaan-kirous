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
 * @author Sami
 */
public class PlayableCreatureTest {
    private PlayableCreature vilperi;
    public PlayableCreatureTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        vilperi = new PlayableCreature(null, 0, 0, "vilperi", 100, 100, 0);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void addItem() {
        DamagingItem rottenCheese = new DamagingItem("Rotten cheese", 1, 999);
        vilperi.addItem(rottenCheese);
        assertEquals(vilperi.getItems().size(), 1);
        vilperi.addItem(rottenCheese);
        assertEquals(vilperi.getItems().size(), 1);
        assertEquals(vilperi.getItems().get(0).getQuantity(), 2);
        HealingItem noodles = new HealingItem("Noodles", 6, 2000);
        vilperi.addItem(noodles);
        assertEquals(vilperi.getItems().size(), 2);
    }
    
    @Test
    public void addExp() {
        vilperi.addExp(5);
        assertEquals(vilperi.getRequiredExp(), 8);
        vilperi.addExp(3);
        assertEquals(vilperi.getRequiredExp(), 8);
        vilperi.addExp(30);
        assertEquals(vilperi.getRequiredExp(), 14);
    }
    
    public void levelUp() {
        vilperi.levelUp();
        assertEquals(vilperi.getDamage(), 120);
        assertEquals(vilperi.getMaxHp(), 120);
        assertEquals(vilperi.getLvl(), 2);
        vilperi.takeDamage(5);
        vilperi.levelUp();
        assertEquals(vilperi.getDamage(), 144);
        assertEquals(vilperi.getMaxHp(), 144);
        assertEquals(vilperi.getLvl(), 3);
    }
    
    @Test
    public void levelUp2() {
        vilperi.levelUpDamage();
        assertEquals(vilperi.getDamage(), 110);
        vilperi.levelUpHp();
        assertEquals(vilperi.getMaxHp(), 125);
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
 * @author Sami
 */
public class PlayableCreatureTest {
    
    public PlayableCreatureTest() {
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
    public void addItem() {
        DamagingItem rottenCheese = new DamagingItem("Rotten cheese", 1, 999);
        PlayableCreature vilperi = new PlayableCreature(0, 0, "vilperi", 1, 1, 0);
        vilperi.addItem(rottenCheese);
        assertEquals(vilperi.getItems().size(), 1);
        vilperi.addItem(rottenCheese);
        assertEquals(vilperi.getItems().size(), 1);
        assertEquals(vilperi.getItems().get(0).getQuantity(), 2);
        HealingItem noodles = new HealingItem("Noodles", 6, 2000);
        vilperi.addItem(noodles);
        assertEquals(vilperi.getItems().size(), 2);
    }
    
    @Test
    public void addExp() {
        PlayableCreature vilperi = new PlayableCreature(0, 0, "vilperi", 1, 1, 0);
        vilperi.addExp(5);
        assertEquals(vilperi.getRequiredExp(), 13);
    }
    
    public void levelUp() {
        PlayableCreature vilperi = new PlayableCreature(0, 0, "vilperi", 100, 100, 0);
        vilperi.levelUp();
        assertEquals(vilperi.getDamage(), 120);
        assertEquals(vilperi.getMaxHp(), 120);
        assertEquals(vilperi.getLvl(), 2);
        vilperi.takeDamage(5);
        vilperi.levelUp();
        assertEquals(vilperi.getDamage(), 144);
        assertEquals(vilperi.getMaxHp(), 144);
        assertEquals(vilperi.getLvl(), 3);
    }
    
    @Test
    public void levelUp2() {
        PlayableCreature vilperi = new PlayableCreature(0, 0, "vilperi", 100, 100, 0);
        vilperi.levelUpDamage();
        assertEquals(vilperi.getDamage(), 110);
        vilperi.levelUpHp();
        assertEquals(vilperi.getMaxHp(), 125);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
>>>>>>> 271c09424254a94913a0ab1321bfb3ae66a15532
