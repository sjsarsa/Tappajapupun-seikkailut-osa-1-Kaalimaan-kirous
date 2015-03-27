/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli;

import java.util.Scanner;
import java.util.ArrayList;
import kanipeli.domain.*;
import kanipeli.peli.Field;
import kanipeli.peli.Game;
import kanipeli.ui.UI;

/**
 *
 * @author Sami
 */
public class main {

    public static void main(String[] args) {
        PlayableCreature player = new PlayableCreature(null, 1, 1, "Hilipati", 200, 30, 0);
        ArrayList<CreatureOnField> fieldDwellers = new ArrayList();
        CreatureOnField boss = new CreatureOnField(null, 6, 6, "Kenkku", 3000, 500, 100);
        fieldDwellers.add(boss);
        ArrayList<Creature> randomEncounters = new ArrayList();
        Creature randomEncounter = new Creature("Pikkuhirviö", 75, 20, 2);
        Creature randomEncounter2 = new Creature("Pikkuhirviö", 100, 15, 3);
        randomEncounters.add(randomEncounter);
        randomEncounters.add(randomEncounter2);
        Field field = new Field(16, 16, player, fieldDwellers, randomEncounters);
        UI ui = new UI(field);
        ArrayList<Field> fields = new ArrayList<Field>();
        fields.add(field);
        Game game = new Game(fields);
        game.run();
    }
}
