/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.peli;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import kanipeli.domain.Creature;
import kanipeli.domain.CreatureOnField;
import kanipeli.domain.PlayableCreature;

/**
 *
 * @author Sami
 */
public class Field {

    private int leveys;
    private int korkeus;
    private PlayableCreature pelaaja;
    private CreatureOnField paavihollinen;

    private List<CreatureOnField> kartallaOlevat;
    private Scanner lukija;

    public Field(int leveys, int korkeus, Scanner lukija) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.lukija = lukija;
        this.pelaaja = new PlayableCreature(0, 0, "Hilipati", 20, 5, 0);
        this.kartallaOlevat = new ArrayList();
        this.paavihollinen = new CreatureOnField(leveys / 2, korkeus / 2, "Pedobear", 1000, 30, 0);
        this.kartallaOlevat.add(paavihollinen);
    }

    public void run() {

        while (true) {
            System.out.println(luoJaTulostaKartta());
            System.out.println("");
            kysyJaLueSyote();
        }
    }

    public boolean taistele(Creature vihollinen) {
        System.out.println(vihollinen.getName() + "kävi kimppuusi!");
        System.out.println("");
        Battle taistelu = new Battle(pelaaja, vihollinen, lukija);
        taistelu.run();
        if (taistelu.getLoss()) {
            System.out.println("Hävisit pelin.");
        } else if (taistelu.getEscaped()) {
            System.out.println("Lähdit pakoon senkin nössö.");
            
        } else {
            System.out.println("Voitit!");
            
        }
        if (taistelu.getLoss()) {
            return false;
        }
        if (!taistelu.getEscaped()) {
            if (pelaaja.addExp(vihollinen.getExp())) {
                levelUp();
            }
        }
        return true;
    }

    private String luoJaTulostaKartta() {
        StringBuilder sb = new StringBuilder();
        char[][] kartta = new char[leveys][korkeus];

        for (int x = 0; x < leveys; x++) {
            for (int y = 0; y < korkeus; y++) {
                kartta[x][y] = '.';
            }
        }
        kartta[pelaaja.getX()][pelaaja.getY()] = '@';
        for (CreatureOnField h : kartallaOlevat) {
            kartta[h.getX()][h.getY()] = 'B';
        }
        kartta[paavihollinen.getX()][paavihollinen.getY()] = 'B';
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                sb.append(kartta[x][y]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void kysyJaLueSyote() {
        System.out.println("");
        String syote = lukija.nextLine();

        for (int i = 0; i < syote.length(); i++) {
            if (syote.charAt(i) == 's') {
                if (pelaaja.getY() < korkeus - 1) {
                    pelaaja.moveDown();
                }
                onLiikuttu();
            }
            if (syote.charAt(i) == 'a') {
                if (pelaaja.getX() > 0) {
                    pelaaja.moveLeft();
                }
                onLiikuttu();
            }
            if (syote.charAt(i) == 'w') {
                if (pelaaja.getY() > 0) {
                    pelaaja.moveUp();
                }
                onLiikuttu();
            }
            if (syote.charAt(i) == 'd') {
                if (pelaaja.getX() < leveys - 1) {
                    pelaaja.moveRight();
                }
                onLiikuttu();
            }

        }
    }

    public void onkoSamaRuutu(List<CreatureOnField> kartallaOlevat) {
        for (CreatureOnField h : kartallaOlevat) {
            if (pelaaja.getX() == h.getX() && pelaaja.getY() == h.getY()) {
                taistele(h);
            }
        }
    }

    private void onLiikuttu() {
        onkoSamaRuutu(kartallaOlevat);
        Random rm = new Random();
        Creature perusvihollinen = new Creature("Pikkuhirviö", 15, 3, 1);
        if (rm.nextInt(3) == 1) {
            taistele(perusvihollinen);
        }

    }

    private void levelUp() {
        System.out.println("Level up!");
        System.out.println("Haluatko kehittää kestävyyttä (= 1) vai tuhovoimaa (= 2)?");
        System.out.println("");
        String komento = lukija.nextLine();
        if (komento.equals("1")) {
            pelaaja.levelUpHp();
        } else if (komento.equals("2")) {
            pelaaja.levelUpDamage();
        }
    }
}
