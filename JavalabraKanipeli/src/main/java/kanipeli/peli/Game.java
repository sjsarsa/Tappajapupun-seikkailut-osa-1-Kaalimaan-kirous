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

/**
 *
 * @author Sami
 */
public class Game {
    private Field currentField;
    
    // 0 = left, 1 = up, 2 = right, 3 = down

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
        
        Field field1 = new Field(this, Sprites.level[0][0], 16, 16, player, fieldDwellers, randomEncounters);
        Field field2 = new Field(this, Sprites.levelUpOne[0][0], 16, 16, player, fieldDwellers, randomEncounters);
        Field field3 = new Field(this, Sprites.levelDownOne[0][0], 16, 16, player, fieldDwellers, randomEncounters);
        Field field4 = new Field(this, Sprites.levelRightOne[0][0], 16, 16, player, fieldDwellers, randomEncounters);
        Field field5 = new Field(this, Sprites.levelLeftOne[0][0], 16, 16, player, fieldDwellers, randomEncounters);

        field1.addConnectedField(1, field2);
        field1.addConnectedField(3, field3);
        field1.addConnectedField(2, field4);
        field1.addConnectedField(0, field5);
        
        field2.addConnectedField(3, field1);
        field3.addConnectedField(1, field1);
        field4.addConnectedField(0, field1);
        field5.addConnectedField(2, field1);
        currentField = field1;
        currentField.initField();
    }

    public Field getCurrentField() {
        return currentField;
    }

    public void setCurrentField(Field currentField) {
        currentField.initField();
        this.currentField = currentField;
    }
      
}
