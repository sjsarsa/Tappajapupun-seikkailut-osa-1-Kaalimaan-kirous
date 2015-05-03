/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.peli;

import kanipeli.logic.Field;
import kanipeli.logic.Game;
import java.util.ArrayList;
import java.util.List;
import kanipeli.domain.Creature;
import kanipeli.domain.CreatureOnField;
import kanipeli.domain.PlayableCreature;
import kanipeli.ui.level.Level;
import kanipeli.ui.sprite.SpriteSheet;
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
public class FieldTest {
    
    private Field field;
    private PlayableCreature player;
    private CreatureOnField boss;
    private Creature foe;
    private List<Creature> randomEncounters;
    private List<CreatureOnField> creaturesOnField;
    private Game game;
    boolean[][] impassables;
    
    /**
     *
     */
    public FieldTest() {
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
        foe = new Creature(5, "jeba", 0, 0, 0, null);
        player = new PlayableCreature(2, 4, impassables, 1, 1, "Seppo", 10, 3, 0, null);
        boss = new CreatureOnField(3, 5, impassables, 1, 1, "Vintiö", 10, 3, 2, null);
        this.creaturesOnField = new ArrayList<>();
        creaturesOnField.add(boss);
        this.randomEncounters = new ArrayList<>();
        randomEncounters.add(foe);
        game = new Game();
        field = new Field(game, SpriteSheet.level[0][0], player, creaturesOnField, randomEncounters);
        field.initField();
        Level level = new Level(field);
        level.loadMap(0, 0, 0, 0);
        impassables = level.getImpassables();
        field.getPlayer().setImpassables(impassables);
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
        assertEquals(field.getCreaturesOnField(), creaturesOnField);
        assertEquals(field.getHeight(), 16);
        assertArrayEquals(field.getImpassables(), impassables);
        assertEquals(field.getWidth(), 16);
        assertEquals(field.getPlayer(), player);
    }
    
    /**
     *
     */
    @Test
    public void checkSpot() {
        assertEquals(field.checkSpot(), boss);
        field.getPlayer().moveRight(9);
        assertEquals(field.checkSpot(), null);
        field.getPlayer().moveLeft();
        
        //boss gets maxhp back if not defeated
        boss.takeDamage(5);       
        field.checkSpot();
        assertEquals(field.checkSpot(), boss);
        assertEquals(boss.getCurrentHp(), boss.getMaxHp());
        
        boss.takeDamage(1337);
        assertEquals(field.checkSpot(), null);
    }
    
    @Test
    public void randomEncounter() {
        player.setX(0);
        player.setY(0);
        boss.setX(0);
        boss.setY(0);
        assertEquals(field.randomEncounter(), false);
        
        boolean re = false;
        player.setX(4);
        for (int i = 0; i < 10000; i++) {
            if (field.randomEncounter()) re = true;
        }
        assertEquals(re, true);

        for (int i = 0; i < 10000; i++) {
            if (!field.randomEncounter()) re = false;
        }
        assertEquals(re, false);
    }
    
    /**
     *
     */
    @Test
    public void createRandomEncounter() {
        Creature re = field.createRandomEncounter();
        assertEquals(re.getName(), foe.getName());
        assertEquals(re.getDamage(), foe.getDamage());
        assertEquals(re.getMaxHp(), foe.getMaxHp());
    }
    
    /**
     *
     */
    @Test
    public void addConnectedField() {
        field.addConnectedField(0, game.getCurrentField());
        assertEquals(field.getConnectedFields()[0], game.getCurrentField());
    }
    
    /**
     *
     */
    @Test
    public void checkLeftEdge() {
        field = game.getCurrentField();
        player = field.getPlayer();
        field.checkEdge();
        assertEquals(game.getCurrentField(), field);
        player.setX(0);
        field.checkEdge();
        assertEquals(player.getX(), field.getConnectedFields()[0].getExits()[2][0]);
        assertEquals(player.getY(), field.getConnectedFields()[0].getExits()[2][1]);
        
        field.addConnectedField(0, null);
        player.setX(0);
        field.checkEdge();
        assertEquals(player.getX(), 0);
    }
    
    /**
     *
     */
    @Test
    public void checkRightEdge() {
        field = game.getCurrentField();
        player = field.getPlayer();
        player.setX(15);
        field.checkEdge();
        assertEquals(player.getX(), field.getConnectedFields()[2].getExits()[0][0]);
        assertEquals(player.getY(), field.getConnectedFields()[2].getExits()[0][1]);
        
        field.addConnectedField(2, null);
        player.setX(15);
        field.checkEdge();
        assertEquals(player.getX(), 15);
    }
    
    /**
     *
     */
    @Test
    public void checkUpperEdge() {
        field = game.getCurrentField();
        player = field.getPlayer();
        player.setY(0);
        field.checkEdge();
        assertEquals(player.getX(), field.getConnectedFields()[1].getExits()[3][0]);
        assertEquals(player.getY(), field.getConnectedFields()[1].getExits()[3][1]);
        
        field.addConnectedField(1, null);
        player.setY(0);
        field.checkEdge();
        assertEquals(player.getY(), 0);
    }
    
    /**
     *
     */
    @Test
    public void checkLowerEdge() {
        field = game.getCurrentField();
        player = field.getPlayer();
        player.setY(15);
        field.checkEdge();
        assertEquals(player.getX(), field.getConnectedFields()[3].getExits()[1][0]);
        assertEquals(player.getY(), field.getConnectedFields()[3].getExits()[1][1]);
        
        field.addConnectedField(3, null);
        player.setY(15);
        field.checkEdge();
        assertEquals(player.getY(), 15);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
