#Huom
Tämä on sama aihe kuin viime jaksossa. Silloin oli liikaa tekemistä muilla kursseilla/töissä eikä paja-ajatkaan olleet sopivia, joten päätin dropata kurssin silloin. En ehtinyt tekemään ohjelmaa paljoakaan.

#Aihe

Roolipeli, jossa pelataan kanilla... seikkaillaan kaalimaalla ja mätkitään hirviöitä ja kani kehittyy matkan
edetessä. Tavoitteena on voittaa pelin päävihollinen. 

 Kun liikkuu kaalimaalla, sieltä löytyy päihitettäviä vihollisia ja siirtymäpaikkoja uusille alueille. Jos vihollisen kohdalle liikkuu, tulee taistelu. Liikkuessa voi myös ilmestyä satunnaisia taisteluita vihollisten kanssa.
 
 Taistelut ovat vuoropohjaisia ja niissä voi käyttää tavaroita ja hyökkäysiskuja tai, jos pelottaa tai laiskottaa, voi myös juosta karkuun. Jos pelaaja voitaa taistelun hänen hahmonsa saa kokemuspisteitä ja kehittyy, kun saa niitä tarpeeksi. Jos vihu voittaa peli päättyy. Pelin tavoitteena on voittaa kartalla näkyvät viholliset.
#Käyttäjät
Pelaaja, tämä on yksinpeli.
#Toiminnot
Aloita uusi peli...

Kaalimaalla: liiku, avaa valikko.

Taistelussa: hyökkää, käytä tavara, pakene, avaa valikko.

Valikossa: uusi peli, jatka, lopeta peli.

#Rakennekuvaus

Ohjelma jakautuu neljään pakkaukseen: 
pääpakkaus, jossa on main-luokka ja AudioPlayer musiikin soittamiseen, 
domain, jossa on pelin yksinkertaiset palat kuten hahmot ja itemit(tavarat), 
logic, jossa on logiikkaa sisältävät palaset, kuten battle (taistelu) ja field (pelto) 
ja lopuksi ui, jossa on käyttöliittymä. Käyttöliittymässä on sen verran tavaraa, että se on jaettu omiin pakkauksiinsa: 
 ui.level, jossa on hahmojen ja peltojen spritet eli peliin tulevat kuvat hahmoille ja pellon osille,
 ui.sprite, jossa toteutetaan spritejen lataaminen kuvatiedostoista,
 ui.states, jossa on game state manager ja erilaiset game statet eli pelitilat. Pelitilat muokkaavat käyttöliittymää ja   toteuttavat napinpainalluksia.
 ui pakkauksessa itsessään on GamePanel, joka vain luo käyttöliittymän ja luokka Screen jota pelitilat käyttävät kälin   muokkaamiseen.

