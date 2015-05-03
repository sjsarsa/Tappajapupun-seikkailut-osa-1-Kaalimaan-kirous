##Pelissä on kolme pelitilaa: 
##Valikko
Peli aloitetaan valikossa. Valikon vaihtoehtoja selataan nuolinäppäimillä ja painamalla enteriä valitaan valittu(maalattu)
vaihtoehto. Jos peli on käynnissä valikossa näkyy pelaajan tiedot.
Vaihtoehdot Continue ja Change state eivät toimi ennen kuin peli on aloitettu.
####New Game 
aloittaa uuden pelin. 
####Continue
palaa aikaisempaan tilaan (vaihtoehtoisesti voi painaa escapea ja ei,
pelissä ei ole mahdollista tallentaa peliä :'( ),
####Change state
vaihtaa pelaajan tilaa, joita on kolme: 
#####normaali
ei tee mitään erityistä.
#####frenzied 
lisää pelaajantuhovoimaa, mutta pelaaja ottaa enemmän vahinkoa.
#####calm
pelaaja kestää enemmän vahinkoa ja ottaa sitä vähemmän, mutta tuhovoima kärsii.

Pelaajan tila vaikuttaa myös kehittymiseen riippuen missä tilassa pelaaja on, kun pelaaja kehittyy (saa levelin).
Frenzied = enemmän tuhovoimaa,
Calm = enemmän hp:ta (kestävyyttä)

####Exit
Sulkee ohjelman.

##Pelto
Peli alkaa aloituspellolla vasemmasta ylälaidasta, siellä on valkoinen kani, jota voi liikuttaa vapaasti käyttäen 
nuolinäppäimiä, paitsi kivien päälle ei pääse. Pellolla liikkuessa tulee vastaan
satunnaisia vihollisia ja silloin tulee taistelu. Liikkumiseksi lasketaan myös kiviä kohti liikkuminen, vaikka pelaaja ei varsinaisesti liiku. Pellolla näkyy myös vihollisia, joiden kohdalle kun kävelee tulee myös
taistelu. Kun pellolla olleen vihollisen voittaa, kyseinen vihollinen katoaa pelistä lopullisesti. Pelin tavoitteena on
voittaa kaikki pelloilla olevat viholliset. Satunnaisia vihollisia tulee rajattomasti. Peltojen laidalla on ovia. Ovista
siirtyy toiselle pellolle.

Painamalla escape-näppäintä pääsee takaisin valikkoon.

##Taistelut
Taistelussa on kolme vaihtoehtoa etenemiseen, joita selataan niin ikään nuolinäppäimillä.
Pelaajan valittua jonkin toiminnon enterillä seuraa vastustajan vuoro, joka osaa vain attack-toiminnon.
Myös karkuun juostessa vastustaja ehtii lyödä kerran.
Kuten pellolla, taistelussakin voi avata valikon painamalla escapea.

####Attack
Hyökkää vastustajan kimppuun. Aiheutettu vahinko on 1-2 kertaa pelaajan tuhovoima.

####Use item
Avaa tavaravalikon jota selataan nuolinäppäimillä. Pelaaja voi käyttää tavaran valitsemalla haluamansa tai peruuttaa
toiminnon painamalla cancel-näppäintä. Tavaroita on parantavia ja vahinkoa aiheuttavia. Parantavat tavarat kohdistuvat
pelaajaan ja vahingolliset vastustajaan. Tavarat ovat tehokkuusjärjestyksessä.

####Flee
Juokse karkuun

Pelaajan valittua jonkin toiminnon enterillä seuraa vastustajan vuoro, joka osaa vain attack-toiminnon.
Myös karkuun juostessa vastustaja ehtii lyödä kerran.

###Voitto
Pelaaja voittaa, kun vastustajan hp on nollassa.
Mikäli pelaaja voittaa taistelun hän saa vastustajalta exp eli kokemuspisteitä, joita tarpeeksi saadessaan pelaaja kehittyy.
Vihollinen saattaa myös pudottaa tavaroita, joita pelaaja voi käyttää tulevissa taisteluissaan.

###Tappio
Jos pelaaja häviää eli hänen hp:t menevät nollaan peli päättyy.
Uuden pelin voi aloittaa menemällä valikkoon painmalla escapea.

##Lisäinfoa
Jos ensimmäiseltä lukemalta jäi ohi, pelin tavoite on päihittää kaikki pelloilla näkyvät hahmot sinua itseäsi lukuunottamatta.
(Keltainen on pääpahis, ei kannata yrittää voittaa ensimmäisenä).
Ympäröiviä peltoja aloituspellon lisäksi on neljä.
Vaikeusjärjestys helpoimmasta vaikeimpaan: Vasen, Oikea, Alas, Ylös.
