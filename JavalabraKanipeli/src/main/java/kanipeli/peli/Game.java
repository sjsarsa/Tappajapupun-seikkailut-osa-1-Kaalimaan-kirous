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
        CreatureOnField boss = new CreatureOnField(3, 5, null, 6, 6, "Kenkku", 10000, 500, 100000);
        fieldDwellers.add(boss);
        
        ArrayList<CreatureOnField> fieldDwellers2 = new ArrayList();
        CreatureOnField blackBunny = new CreatureOnField(8, 9, null, 6, 6, "Höpö", 3000, 300, 100);
        fieldDwellers2.add(blackBunny);
        
        ArrayList<Creature> randomEncounters = new ArrayList();
        Creature re = new Creature(5, "Pikkuhirviö", 75, 20, 200);
        Creature re2 = new Creature(5, "Pikkuhirviö", 100, 15, 300);
        randomEncounters.add(re);
        randomEncounters.add(re2);
        
        ArrayList<Creature> randomEncounters2 = new ArrayList();
        Creature re21 = new Creature(5, "Isompi hirviö", 450, 75, 8);
        Creature re22 = new Creature(5, "Isompi hirviö", 300, 150, 10);
        randomEncounters2.add(re21);
        randomEncounters2.add(re22);
        
        int w = 16;
        int h = 16;
        Field startingField = new Field(this, Sprites.level[0][0], player, fieldDwellers, randomEncounters);
        Field fieldUpOne = new Field(this, Sprites.levelUpOne[0][0], player, fieldDwellers2, randomEncounters2);
        Field fieldDownOne = new Field(this, Sprites.levelDownOne[0][0], player, fieldDwellers2, randomEncounters2);
        Field fieldRightOne = new Field(this, Sprites.levelRightOne[0][0], player, fieldDwellers2, randomEncounters2);
        Field fieldLeftOne = new Field(this, Sprites.levelLeftOne[0][0], player, fieldDwellers2, randomEncounters2);
        
        connectFieldDown(startingField, fieldDownOne);
        connectFieldUp(startingField, fieldUpOne);
        connectFieldLeft(startingField, fieldLeftOne);
        connectFieldRight(startingField, fieldRightOne);

        currentField = startingField;
        currentField.initField();
    }

    public Field getCurrentField() {
        return currentField;
    }

    public void setCurrentField(Field currentField) {
        currentField.initField();
        this.currentField = currentField;
    }
    
    private void connectFieldUp(Field field1, Field field2) {
        field1.addConnectedField(1, field2);
        field2.addConnectedField(3, field1);
    }
    
    private void connectFieldDown(Field field1, Field field2) {
        field1.addConnectedField(3, field2);
        field2.addConnectedField(1, field1);
    }
    
    private void connectFieldRight(Field field1, Field field2) {
        field1.addConnectedField(2, field2);
        field2.addConnectedField(0, field1);
    }
    
    private void connectFieldLeft(Field field1, Field field2) {
        field1.addConnectedField(0, field2);
        field2.addConnectedField(2, field1);
    }
    
      
}
