/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.peli;

import java.util.Scanner;
import kanipeli.domain.*;

/**
 *
 * @author Sami
 */
public class Taistelu {

    private KehittyvaHahmo pelaaja;
    private Hahmo vihollinen;
    private Scanner lukija;
    private boolean pakeni;
    private boolean tappio;

    public Taistelu(KehittyvaHahmo pelaaja, Hahmo vihollinen, Scanner lukija) {
        this.pelaaja = pelaaja;
        this.vihollinen = vihollinen;
        this.lukija = lukija;
        this.pakeni = false;
        this.tappio = false;
    }

    public void run() {
        while (pakeni != true) {
            tulostaTilanne();

            pelaajanVuoro();
            if (vihollinen.getCurrentHp() <= 0) {
                System.out.println(vihollinen.getNimi());
                System.out.println("Hp: 0");
                break;
            }
            koneenVuoro();
            if (pelaaja.getCurrentHp() <= 0) {
                System.out.println(pelaaja.getNimi());
                System.out.println("Hp: 0");
                tappio = true;
                break;
            }
        }
    }

    public void tulostaTilanne() {
        System.out.println(pelaaja.getNimi());
        System.out.println("Hp: " + pelaaja.getCurrentHp());
        System.out.println(vihollinen.getNimi());
        System.out.println("Hp: " + vihollinen.getCurrentHp());
        System.out.println("");
    }

    private String kysyKomento() {
        System.out.println("Anna komento: (1 = hyökkää, 2 = käytä tavara,  3 = pakene)");
        return lukija.nextLine();
    }

    public void pelaajanVuoro() {
        while (true) {
            String komento = kysyKomento();
            if (komento.equals("1")) {
                int vahinko = pelaaja.attack();
                vihollinen.takeDamage(vahinko);
                System.out.println("Pow! " + vahinko + "!");
                System.out.println("");
                break;
            } else if (komento.equals("2")) {
                System.out.println("Tavarat: ");
                for (Item i : pelaaja.getItems()) {
                    System.out.println(i);
                }
                System.out.println("Mitä haluaisit käyttää?");
                komento  = lukija.nextLine();
                for (Item i : pelaaja.getItems()) {
                    if (komento.equals(i.getName())) {
                        System.out.println("Kehen? (Minä/Vihu)");
                        komento = lukija.nextLine();
                        if (komento.equals("Minä")) {
                            i.use(pelaaja);
                            break;
                        } else {
                            i.use(vihollinen);
                            break;
                        }
                    }
                }
            } else if (komento.equals("3")) {
                pakeni = true;
                break;
            } else {
                System.out.println("Näppäilit väärin, yritä uudestaan.");
                System.out.println("");
            }
        }
    }

    public void koneenVuoro() {
            System.out.println(vihollinen.getNimi() + " hyökkää.");
            int vahinko = vihollinen.attack();
            pelaaja.takeDamage(vahinko);
            System.out.println("Pow! " + vahinko + "!");
            System.out.println(""); 
    }

    public boolean isPakeni() {
        return pakeni;
    }

    public boolean isTappio() {
        return tappio;
    }
}
