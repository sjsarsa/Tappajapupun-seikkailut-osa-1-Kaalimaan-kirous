
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.peli;

import java.util.Scanner;
import kanipeli.domain.Creature;
import kanipeli.domain.PlayableCreature;
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
public class BattleTest {
    private PlayableCreature player;
    private Creature foe;
    private Battle battle;
    
    /**
     *
     */
    public BattleTest() {
        
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
       
       player = new PlayableCreature(2, 4, null, 0, 0, "Seppo", 10, 3, 0);
       foe = new Creature(5, "Vintiö", 10, 3, 2);
       battle = new Battle(player, foe);
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
    public void getters() {
        assertEquals(battle.getEscaped(), false);
        assertEquals(battle.getFoe(), foe);
        assertEquals(battle.getLost(), false);
        assertEquals(battle.getPlayer(), player);
    }

    /**
     *
     */
    @Test
    public void attack() {
       int damage = battle.attack(player, foe);
       assertEquals(foe.getCurrentHp(), 10 - damage);
       assertEquals(player.getCurrentHp(), 10);
       damage = battle.attack(foe, player);
       assertEquals(player.getCurrentHp(), 10 - damage);
    }
    
    /**
     *
     */
    @Test
    public void alive() {
        assertEquals(battle.alive(foe), true);
        foe.takeDamage(10);
        assertEquals(battle.alive(foe), false);
    }
    
    /**
     *
     */
    @Test
    public void checkLevelUp() {
        battle.checkLevelUp();
        assertEquals(player.getLvl(), 1);
        battle.checkLevelUp();
        battle.checkLevelUp();
        assertEquals(player.getLvl(), 2);
        battle = new Battle(player, new Creature(5, "Napero", 0, 0, 30));
        battle.checkLevelUp();
        assertEquals(player.getLvl(), 4);
    }  
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
