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

/**
 *
 * @author Sami
 */
public class main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PlayableCreature player = new PlayableCreature(0, 0, "Hilipati", 200, 30, 0);
        ArrayList<CreatureOnField> fieldDwellers = new ArrayList();
        CreatureOnField boss = new CreatureOnField(6, 6, "Pedobear", 3000, 500, 100);
        fieldDwellers.add(boss);
        ArrayList<Creature> randoms = new ArrayList();
        Creature randomEncounter = new Creature("Pikkuhirivio", 50, 20, 2);
        randoms.add(randomEncounter);
        Field field = new Field(10, 10, player, fieldDwellers, randoms, sc);
        field.run();
    }
}
