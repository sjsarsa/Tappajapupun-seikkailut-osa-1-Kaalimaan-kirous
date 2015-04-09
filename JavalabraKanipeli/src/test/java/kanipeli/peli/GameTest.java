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
import kanipeli.ui.sprites.Sprites;
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
public class GameTest {
    
    private Game game = new Game();
    private Field f;
    
    /**
     *
     */
    public GameTest() {
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
        PlayableCreature player = new PlayableCreature(2, 4, null, 1, 1, "Hilipati", 200, 30, 0);
        
        ArrayList<CreatureOnField> fieldDwellers = new ArrayList();
        CreatureOnField boss = new CreatureOnField(3, 5, null, 6, 6, "Kenkku", 10000, 500, 100000);
        fieldDwellers.add(boss);
        
        ArrayList<Creature> randomEncounters = new ArrayList();
        randomEncounters.add(boss);
        
        f = new Field(game, Sprites.level[0][0], player, fieldDwellers,randomEncounters);
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
    public void gettersAndSetters() {
        assertNotNull(game.getCurrentField());
        
        game.setCurrentField(f);
        assertEquals(game.getCurrentField(), f);
    }
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
