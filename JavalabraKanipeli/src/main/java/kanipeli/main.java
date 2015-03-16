/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli;

import java.util.Scanner;
import kanipeli.peli.Kartta;

/**
 *
 * @author Sami
 */
public class main {

    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        Kartta kartta = new Kartta(10, 10, lukija);
        kartta.run();
    }
}
