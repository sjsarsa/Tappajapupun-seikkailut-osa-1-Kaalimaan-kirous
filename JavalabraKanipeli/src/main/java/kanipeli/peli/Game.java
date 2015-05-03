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
        PlayableCreature player = new PlayableCreature(4, 12, null, 1, 1, "Tappajapupu", 200, 30, 0, null);
        HealingItem kerakaali = new HealingItem("keräkaali", 0, 150, 3);
        player.addItem(kerakaali, 2);
        
        //Starting field
        ArrayList<CreatureOnField> fieldDwellers = new ArrayList();
        DamagingItem ydinrajahde = new DamagingItem("ydinräjähde", 1, 99999, 0);
        HealingItem mataKaali = new HealingItem("mätä kaali", 1, -100, 0);
        DamagingItem mataTomaatti = new DamagingItem("mätä tomaatti", 3, 3000, 0);
        CreatureOnField boss = new CreatureOnField(5, 19, null, 6, 6, "Kenkku", 30000, 1500, 10000, ydinrajahde);
        CreatureOnField miniBoss1 = new CreatureOnField(3, 14, null, 5, 12, "Haisuli", 5000, 200, 100, mataKaali);
        CreatureOnField miniBoss2 = new CreatureOnField(3, 14, null, 12, 5, "Haisuli", 5000, 200, 100, mataTomaatti);
        fieldDwellers.add(boss);
        fieldDwellers.add(miniBoss1);
        fieldDwellers.add(miniBoss2);
        
        ArrayList<Creature> randomEncounters = new ArrayList();
        HealingItem kerakaali2 = new HealingItem("keräkaali", 0, 150, 4);
        DamagingItem papatti = new DamagingItem("papatti", 0, 100, 2);
        Creature re = new Creature(13, "Pikkuhirviö", 100, 20, 2, kerakaali);
        Creature re2 = new Creature(13, "Pikkuhirviö", 150, 15, 3, kerakaali2);
        Creature re3 = new Creature(13, "Pikkuhirviö", 175, 10, 3, papatti);
        randomEncounters.add(re);
        randomEncounters.add(re2);
        randomEncounters.add(re3);
        
        //upward field
        ArrayList<CreatureOnField> fieldDwellersUpOne = new ArrayList();
        HealingItem mKaali = new HealingItem("majesteettinen kaali", 3, 10000, 0);
        CreatureOnField blackBunny = new CreatureOnField(6, 15, null, 6, 6, "Höpö", 10000, 1000, 500, mKaali);
        fieldDwellersUpOne.add(blackBunny);
    
        ArrayList<Creature> randomEncountersUpOne = new ArrayList();
        DamagingItem kranaatti = new DamagingItem("kranaatti", 0, 1500, 4);
        HealingItem isoKiinankaali = new HealingItem("iso kiinankaali", 0, 1000, 6);
        Creature re21 = new Creature(13, "Tosi iso hirviö", 2500, 350, 30, isoKiinankaali);
        Creature re22 = new Creature(13, " Tosi iso hirviö", 1800, 400, 20, kranaatti);
        randomEncountersUpOne.add(re21);
        randomEncountersUpOne.add(re22);
        
        //field to the left
        ArrayList<CreatureOnField> fieldDwellersLeftOne = new ArrayList();
        DamagingItem heittoveitsi = new DamagingItem("heittoveitsi", 3, 1000, 0);
        CreatureOnField redBunny = new CreatureOnField(7, 16, null, 2, 6, "Höpö", 2000, 200, 40, heittoveitsi);
        fieldDwellersLeftOne.add(redBunny);
    
        ArrayList<Creature> randomEncountersLeftOne = new ArrayList();
        DamagingItem  munaKranaatti = new DamagingItem("munakranaatti", 0, 200, 4);
        HealingItem kiinankaali = new HealingItem("kiinankaali", 0, 250, 5);
        Creature re31 = new Creature(13, "Isompi hirviö", 400, 25, 5, kiinankaali);
        Creature re32 = new Creature(13, "Isompi hirviö", 350, 30, 4, munaKranaatti);
        randomEncountersLeftOne.add(re31);
        randomEncountersLeftOne.add(re32);
        
        //field to the right
        ArrayList<CreatureOnField> fieldDwellersRightOne = new ArrayList();
        DamagingItem heittokirves = new DamagingItem("heittokirves", 3, 2000, 0);
        CreatureOnField greenBunny = new CreatureOnField(8, 17, null, 13, 1, "Höpö", 4000, 400, 80, heittokirves);
        fieldDwellersRightOne.add(greenBunny);
    
        ArrayList<Creature> randomEncountersRightOne = new ArrayList();
        DamagingItem pieniKranaatti = new DamagingItem("pieni kranaatti", 0, 500, 4);
        HealingItem kaalikaaryle = new HealingItem("kaalikääryle", 0, 400, 5);
        Creature re41 = new Creature(13, "Vielä Isompi hirviö", 900, 100, 10, pieniKranaatti);
        Creature re42 = new Creature(13, "Vielä Isompi hirviö", 700, 150, 12, kaalikaaryle);
        randomEncountersRightOne.add(re41);
        randomEncountersRightOne.add(re42);
        
        //field downwards
        ArrayList<CreatureOnField> fieldDwellersDownOne = new ArrayList();
        DamagingItem keihas = new DamagingItem("keihäs", 1, 5000, 0);
        CreatureOnField purpleBunny = new CreatureOnField(9, 18, null, 6, 9, "Höpö", 6000, 600, 120, keihas);
        fieldDwellersDownOne.add(purpleBunny);
    
        ArrayList<Creature> randomEncountersDownOne = new ArrayList();
        DamagingItem tomaatti = new DamagingItem("tomaatti", 0, 1000, 4);
        HealingItem hervotonKaali = new HealingItem("hervoton kaali", 0, 1000, 5);
        Creature re51 = new Creature(13, "Paha hirviö", 1300, 200, 15, hervotonKaali);
        Creature re52 = new Creature(13, "Paha hirviö", 1100, 300, 18, tomaatti);
        randomEncountersDownOne.add(re51);
        randomEncountersDownOne.add(re52);
        
        
        int w = 16;
        int h = 16;
        Field startingField = new Field(this, SpriteSheet.level[0][0], player, fieldDwellers, randomEncounters);
        Field fieldUpOne = new Field(this, SpriteSheet.levelUpOne[0][0], player, fieldDwellersUpOne, randomEncountersUpOne);
        Field fieldDownOne = new Field(this, SpriteSheet.levelDownOne[0][0], player, fieldDwellersDownOne, randomEncountersDownOne);
        Field fieldRightOne = new Field(this, SpriteSheet.levelRightOne[0][0], player, fieldDwellersRightOne, randomEncountersRightOne);
        Field fieldLeftOne = new Field(this, SpriteSheet.levelLeftOne[0][0], player, fieldDwellersLeftOne, randomEncountersLeftOne);
        
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
