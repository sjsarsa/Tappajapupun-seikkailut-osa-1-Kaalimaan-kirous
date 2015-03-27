/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.peli;

import java.util.ArrayList;
import kanipeli.domain.Creature;
import kanipeli.domain.CreatureOnField;
import kanipeli.domain.PlayableCreature;
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
public class GameTest {
    private PlayableCreature player;
    private Field field;
    private Battle battle;
    private Game game;
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        player = new PlayableCreature(null, 1, 1, "Hilipati", 200, 30, 0);
        ArrayList<CreatureOnField> fieldDwellers = new ArrayList();
        CreatureOnField boss = new CreatureOnField(null, 1, 2, "Kenkku", 3000, 500, 100);
        fieldDwellers.add(boss);
        ArrayList<Creature> randomEncounters = new ArrayList();
        Creature randomEncounter = new Creature("Pikkuhirviö", 75, 20, 2);
        randomEncounters.add(randomEncounter);
        field = new Field(16, 16, player, fieldDwellers, randomEncounters);
        player.setImpassables(field.getImpassables());
        battle = new Battle(null, null, player, randomEncounter);
        ArrayList<Field> fields = new ArrayList<Field>();
        fields.add(field);
        game = new Game(fields);
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void checkRandomEncounter() {
        assertEquals(player.moved, false);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
