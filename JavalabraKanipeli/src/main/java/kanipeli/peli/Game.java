/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.peli;

import java.util.ArrayList;
import kanipeli.domain.Creature;
import kanipeli.domain.CreatureOnField;
import kanipeli.domain.DamagingItem;
import kanipeli.domain.HealingItem;
import kanipeli.domain.PlayableCreature;
import kanipeli.ui.sprites.SpriteSheet;

/**
 *Creates the fields and the creatures for the game.
 * Knows which is the current field the player is on.
 * @author Sami
 */
public class Game {
    private Field currentField;
    
    // 0 = left, 1 = up, 2 = right, 3 = down (for connecting fields)

    /**
     *Creates all the basic objects for the game i.e. fields and creatures.
     * Assigns the appropriate creatures for the right fields and connects
     * fields together.
     * Initialises a starting field.
     */
    
    public Game() {
        PlayableCreature player = new PlayableCreature(2, 4, null, 1, 1, "Tappajapupu", 200, 30, 0, null);
        HealingItem kerakaali = new HealingItem("pieni keräkaali", 2, 150, 3);
        player.addItem(kerakaali);
        
        ArrayList<CreatureOnField> fieldDwellers = new ArrayList();
        DamagingItem ydinrajahde = new DamagingItem("ydinräjähde", 3, 99999, 0);
        HealingItem mataKaali = new HealingItem("mätä kaali", 2, -100, 0);
        HealingItem isoKaali = new HealingItem("iso kaali", 2, 500, 0);
        CreatureOnField boss = new CreatureOnField(3, 5, null, 6, 6, "Kenkku", 10000, 500, 100000, ydinrajahde);
        CreatureOnField miniBoss1 = new CreatureOnField(3, 5, null, 5, 12, "Haisuli", 1000, 200, 200, mataKaali);
        CreatureOnField miniBoss2 = new CreatureOnField(3, 5, null, 12, 5, "Haisuli", 1000, 200, 200, isoKaali);
        fieldDwellers.add(boss);
        fieldDwellers.add(miniBoss1);
        fieldDwellers.add(miniBoss2);
        
        ArrayList<CreatureOnField> fieldDwellersUpOne = new ArrayList();
        HealingItem mKaali = new HealingItem("majesteettinen kaali", 1, 3000, 0);
        CreatureOnField blackBunny = new CreatureOnField(8, 9, null, 6, 6, "Höpö", 3000, 300, 300, mKaali);
        fieldDwellersUpOne.add(blackBunny);
        
        ArrayList<Creature> randomEncounters = new ArrayList();
        HealingItem kerakaali2 = new HealingItem("keräkaali", 1, 150, 4);
        DamagingItem papatti = new DamagingItem("papatti", 1, 100, 2);
        Creature re = new Creature(5, "Pikkuhirviö", 100, 20, 2, kerakaali);
        Creature re2 = new Creature(5, "Pikkuhirviö", 150, 15, 3, kerakaali2);
        Creature re3 = new Creature(5, "Pikkuhirviö", 175, 10, 3, papatti);
        randomEncounters.add(re);
        randomEncounters.add(re2);
        randomEncounters.add(re3);
         
        ArrayList<Creature> randomEncounters2 = new ArrayList();
        DamagingItem kranaatti = new DamagingItem("kranaatti", 1, 1000, 3);
        HealingItem kiinankaali = new HealingItem("kiinankaali", 1, 500, 4);
        Creature re21 = new Creature(5, "Isompi hirviö", 800, 75, 12, kiinankaali);
        Creature re22 = new Creature(5, "Isompi hirviö", 600, 125, 15, kranaatti);
        randomEncounters2.add(re21);
        randomEncounters2.add(re22);
        
        int w = 16;
        int h = 16;
        Field startingField = new Field(this, SpriteSheet.level[0][0], player, fieldDwellers, randomEncounters);
        Field fieldUpOne = new Field(this, SpriteSheet.levelUpOne[0][0], player, fieldDwellersUpOne, randomEncounters2);
        Field fieldDownOne = new Field(this, SpriteSheet.levelDownOne[0][0], player, fieldDwellersUpOne, randomEncounters2);
        Field fieldRightOne = new Field(this, SpriteSheet.levelRightOne[0][0], player, fieldDwellersUpOne, randomEncounters2);
        Field fieldLeftOne = new Field(this, SpriteSheet.levelLeftOne[0][0], player, fieldDwellersUpOne, randomEncounters2);
        
        connectFieldDown(startingField, fieldDownOne);
        connectFieldUp(startingField, fieldUpOne);
        connectFieldLeft(startingField, fieldLeftOne);
        connectFieldRight(startingField, fieldRightOne);

        currentField = startingField;
        currentField.initField();
    }

    /**
     *
     * @return
     */
    public Field getCurrentField() {
        return currentField;
    }

    /**
     *Initialises a new field which is then set as the new current field.
     * @param currentField
     */
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
