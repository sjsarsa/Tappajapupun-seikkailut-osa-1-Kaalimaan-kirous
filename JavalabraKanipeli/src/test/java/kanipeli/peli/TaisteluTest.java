/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.peli;

import java.util.Scanner;
import kanipeli.domain.Hahmo;
import kanipeli.domain.KehittyvaHahmo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sami
 */
public class TaisteluTest {
    private Hahmo hahmo;
    private Taistelu taistelu;
    
    public TaisteluTest() {
        Scanner lukija = new Scanner(System.in);
        this.hahmo = new Hahmo("Pikkuhirviö", 15, 3, 0);
        KehittyvaHahmo sankari = new KehittyvaHahmo(0, 0, "Hilipati", 20, 5,  0);
        this.taistelu = new Taistelu(sankari, hahmo, lukija);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
       
    }
    
    @After
    public void tearDown() {
    }
    

    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
