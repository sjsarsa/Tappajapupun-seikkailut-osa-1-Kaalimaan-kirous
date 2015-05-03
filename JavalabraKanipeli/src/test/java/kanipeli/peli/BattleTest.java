
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.peli;

import kanipeli.logic.Battle;
import kanipeli.domain.Creature;
import kanipeli.domain.DamagingItem;
import kanipeli.domain.HealingItem;
import kanipeli.domain.Item;
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
       
       player = new PlayableCreature(2, 4, null, 0, 0, "Seppo", 10, 3, 0, null);
       foe = new Creature(5, "Vintiö", 10, 3, 2, new DamagingItem("pommi", 1, 1, 0));
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
    public void gettersSetters() {
        assertEquals(battle.getEscaped(), false);
        battle.setEscaped(true);
        assertEquals(battle.getEscaped(), true );
        assertEquals(battle.getFoe(), foe);
        assertEquals(battle.getLost(), false);
        assertEquals(battle.getPlayer(), player);
        //getUsedItem tested in test usedItem()
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
       assertEquals(battle.getLost(), false);
       for(int i = 0; i < 5; i++) {
           damage = battle.attack(foe, player);
       }
       assertEquals(battle.getLost(), true);
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
    
    @Test
    public void takeDamage() {
        player = new PlayableCreature(2, 4, null, 0, 0, "Seppo", 120, 100, 0, null);
        battle = new Battle(player, foe);
        player.changeState();
        boolean wut = false;
        for (int i = 0; i < 1000; i++) {
            battle.attack(player, player);
            if (player.getCurrentHp() > 0) wut = true;
            player.setCurrentHp(120);
        }
        assertEquals(wut, false);
    }
    
    @Test public void LevelUp() {
        player = new PlayableCreature(2, 4, null, 0, 0, "Seppo", 100, 100, 0, null);
        battle = new Battle(player, foe);
        assertEquals(player.getMaxHp(), 100);
        assertEquals(player.getDamage(), 100);
        battle.levelUp();
        assertEquals(player.getMaxHp(), 144);
        assertEquals(player.getDamage(), 144);
        
        player = new PlayableCreature(2, 4, null, 0, 0, "Seppo", 100, 100, 0, null);
        battle = new Battle(player, foe);
        player.changeState();
        battle.levelUp();
        assertEquals(player.getMaxHp(), 120);
        assertEquals(player.getDamage(), 135);
        
        player = new PlayableCreature(2, 4, null, 0, 0, "Seppo", 100, 100, 0, null);
        battle = new Battle(player, foe);
        player.changeState();
        player.changeState();
        battle.levelUp();
        assertEquals(player.getMaxHp(), 180);
        assertEquals(player.getDamage(), 120);
    }

    @Test
    public void checkLevelUp() { 
        battle.victory();
        assertEquals(player.getLvl(), 1);
        battle.victory();
        battle.victory();
        battle.victory();
        assertEquals(player.getLvl(), 2);
        battle = new Battle(player, new Creature(5, "Napero", 0, 0, 30, new DamagingItem("pommi", 1, 1, 0)));
        battle.victory();
        assertEquals(player.getLvl(), 4);
    }  
    
    @Test
    public void dropItem() {
        assertEquals(player.getItems().size(), 0);
        Item item = new DamagingItem("pommi", 1, 1, 0);
        battle = new Battle(player, new Creature(5, "Napero", 0, 0, 30, item));
        assertEquals(battle.getDroppedItem(), null);
        assertEquals(player.getItems().size(), 0);
        assertEquals(battle.victory(), 1);
        assertEquals(battle.getPlayer().getItems().size(), 1);
        assertEquals(battle.getDroppedItem(), item);

        item = new DamagingItem("turska", 1, 1, 2);
        battle = new Battle(player, new Creature(5, "Napero", 0, 0, 30, item)); 
        boolean itemAdded = false;
        for (int i = 0; i < 10000; i++) {
            battle.victory();
        }
        if (player.getItems().size() > 1) itemAdded = true;
        assertEquals(battle.getDroppedItem(), item);
        assertEquals(itemAdded, true);
        
        boolean itemReturned = true;
        for (int i = 0; i < 10000; i++) {
            if (battle.victory() == 0) itemReturned = false;
        }
        assertEquals(itemReturned, false);
    }
    @Test
    public void useItem() {
        DamagingItem di = new DamagingItem("pum", 0, 100, 0);
        player.addItem(di, 1);
        int damage = battle.useItem(0);
        assertEquals(player.getItems().size(), 0);
        assertEquals(battle.getUsedItem(), di);
        assertEquals(damage, 100);
        assertEquals(battle.getFoe().getCurrentHp(), 0);
        
        HealingItem kaali = new HealingItem("kaali", 0, 1200, 0);
        player.addItem(di, 1);
        player.addItem(kaali, 2);
        player.takeDamage(100);
        damage = battle.useItem(1);
        assertEquals(player.getItems().size(), 2);
        assertEquals(battle.getUsedItem(), kaali);
        assertEquals(damage, -1200);
        assertEquals(player.getCurrentHp(), 10);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
