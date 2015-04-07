/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.peli;

import java.util.ArrayList;
import java.util.List;
import kanipeli.domain.Creature;
import kanipeli.domain.CreatureOnField;
import kanipeli.domain.PlayableCreature;
import kanipeli.ui.level.Level;
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
    boolean[][] impassables;
    
    public FieldTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        foe = new Creature(5, "jeba", 0, 0, 0);
        player = new PlayableCreature(2, 4, impassables, 1, 1, "Seppo", 10, 3, 0);
        boss = new CreatureOnField(3, 5, impassables, 1, 1, "Vintiö", 10, 3, 2);
        this.creaturesOnField = new ArrayList<>();
        creaturesOnField.add(boss);
        this.randomEncounters = new ArrayList<>();
        randomEncounters.add(foe);
        Game game = new Game();
        field = new Field(game, null, 16, 16, player, creaturesOnField, randomEncounters);
        field.initField();
        Level level = new Level(field);
        level.loadMap(0, 0, 0, 0);
        impassables = level.getImpassables();
        field.getPlayer().setImpassables(impassables);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void getters() {
        assertEquals(field.getCreaturesOnField(), creaturesOnField);
        assertEquals(field.getHeight(), 16);
        assertArrayEquals(field.getImpassables(), impassables);
        assertEquals(field.getWidth(), 16);
        assertEquals(field.getPlayer(), player);
    }
    
    @Test
    public void checkSpot() {
        assertEquals(field.checkSpot(), boss);
        field.getPlayer().moveRight(9);
        assertEquals(field.checkSpot(), null);
        field.getPlayer().moveLeft();
        boss.takeDamage(1337);
        assertEquals(field.checkSpot(), null);
    }
    
    @Test
    public void createRandomEncounter() {
        Creature re = field.createRandomEncounter();
        assertEquals(re.getName(), foe.getName());
        assertEquals(re.getDamage(), foe.getDamage());
        assertEquals(re.getMaxHp(), foe.getMaxHp());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
