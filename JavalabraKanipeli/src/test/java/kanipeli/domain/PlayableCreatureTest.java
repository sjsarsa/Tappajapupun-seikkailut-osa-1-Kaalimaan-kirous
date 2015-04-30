
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

    /**
     *
     */
    public PlayableCreatureTest() {
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
        vilperi = new PlayableCreature(2, 4, null, 0, 0, "vilperi", 100, 100, 0, null);
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
    public void addItem() {
        DamagingItem rottenCheese = new DamagingItem("Rotten cheese", 0, 999, 0);
        vilperi.addItem(rottenCheese, 1);
        assertEquals(vilperi.getItems().size(), 1);
        vilperi.addItem(rottenCheese, 1);
        assertEquals(vilperi.getItems().size(), 1);
        assertEquals(vilperi.getItems().get(0).getQuantity(), 2);
        HealingItem noodles = new HealingItem("Noodles", 0, 2000, 0);
        vilperi.addItem(noodles, 2);
        assertEquals(vilperi.getItems().size(), 2);
        assertEquals(noodles.getQuantity(), 2);
        vilperi.addItem(noodles, 3);
        assertEquals(noodles.getQuantity(), 5);
    }
    
    /**
     *
     */
    @Test
    public void addExp() {
        assertEquals(vilperi.addExp(5), true);
        assertEquals(vilperi.getExp(), 0);
        assertEquals(vilperi.getRequiredExp(), 7);
        assertEquals(vilperi.addExp(3), false);
        assertEquals(vilperi.getExp(), 3);
        assertEquals(vilperi.getRequiredExp(), 7);
        assertEquals(vilperi.addExp(30), true);
        assertEquals(vilperi.getRequiredExp(), 10);
    }
    
    /**
     *
     */
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
    
    /**
     *
     */
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
