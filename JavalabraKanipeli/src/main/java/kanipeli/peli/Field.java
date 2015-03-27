
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.peli;

import java.util.List;
import java.util.Random;
import kanipeli.domain.Creature;
import kanipeli.domain.CreatureOnField;
import kanipeli.domain.PlayableCreature;
import kanipeli.ui.level.Level;


/**
 *
 * @author Sami
 */
public class Field {

    private int width;
    private int height;
    private PlayableCreature player;
    private List<Creature> randomEncounters;
    private List<CreatureOnField> creaturesOnField;
    private boolean[][] impassables;
    public Level level;

    public Field(int width, int height, PlayableCreature player, List<CreatureOnField> creaturesOnField, List<Creature> randomEncounters) {
        this.width = width;
        this.height = height;
        this.player = player;
        this.creaturesOnField = creaturesOnField;
        this.randomEncounters = randomEncounters;
        this.level = new Level(this);
        level.loadMap(0, 0, 0, 0);
        this.impassables = level.getImpassables();
        player.setImpassables(impassables);
    }

    public List<CreatureOnField> getCreaturesOnField() {
        return creaturesOnField;
    }

    public PlayableCreature getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean[][] getImpassables() {
        return impassables;
    }

    public CreatureOnField checkSpot() {
        for (CreatureOnField h : creaturesOnField) {
            if (player.getX() == h.getX() && player.getY() == h.getY()) {
                if (h.getCurrentHp() > 0) {
                    return h;
                }
            }
        }
        return null;
    }

    public boolean randomEncounter() {
        if (checkSpot() != null) {
            return false;
        } else {
            Random rm = new Random();
            if (rm.nextInt(15) == 1) {
                return true;
            }
        }
        return false;
    }

    public Creature createRandomEncounter() {
        Random rm = new Random();
        Creature re = randomEncounters.get(rm.nextInt(randomEncounters.size()));
        return new Creature(re.getName(), re.getMaxHp(), re.getDamage(), re.getExp());
    }
}