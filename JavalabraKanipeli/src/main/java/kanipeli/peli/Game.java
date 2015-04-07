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

/**
 *
 * @author Sami
 */
public class Game {
    private Field currentField;

    public Game() {
        PlayableCreature player = new PlayableCreature(2, 4, null, 1, 1, "Hilipati", 200, 30, 0);
        
        ArrayList<CreatureOnField> fieldDwellers = new ArrayList();
        CreatureOnField boss = new CreatureOnField(3, 5, null, 6, 6, "Kenkku", 3000, 500, 100);
        fieldDwellers.add(boss);
        
        ArrayList<Creature> randomEncounters = new ArrayList();
        Creature randomEncounter = new Creature(5, "Pikkuhirviö", 75, 20, 2);
        Creature randomEncounter2 = new Creature(5, "Pikkuhirviö", 100, 15, 3);
        randomEncounters.add(randomEncounter);
        randomEncounters.add(randomEncounter2);
        
        ArrayList<Field> fields = new ArrayList<Field>();
        Field field = new Field(16, 16, player, fieldDwellers, randomEncounters);
        fields.add(field);
        
        currentField = fields.get(0);
    }

    public Field getCurrentField() {
        return currentField;
    }
    
    
}
