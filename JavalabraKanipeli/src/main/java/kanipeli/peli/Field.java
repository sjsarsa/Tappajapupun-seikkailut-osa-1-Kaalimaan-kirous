
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
import kanipeli.ui.sprites.Sprite;

/**
 * The logic of prancing around on the field.
 *
 * @author Sami
 */
public class Field {

    private int width;
    private int height;
    private PlayableCreature player;
    private List<Creature> randomEncounters;
    private List<CreatureOnField> creaturesOnField;
    private Field[] connectedFields;
    private int[][] exits;
    private boolean[][] impassables;
    private Game game;

    /**
     *
     */
    public Level level;

    /**
     *
     */
    public Sprite sprite;

    /**
     *
     * @param game
     * @param sprite
     * @param player
     * @param creaturesOnField
     * @param randomEncounters
     */
    public Field(Game game, Sprite sprite, PlayableCreature player, List<CreatureOnField> creaturesOnField, List<Creature> randomEncounters) {
        this.width = sprite.w;
        this.height = sprite.h;
        this.player = player;
        this.creaturesOnField = creaturesOnField;
        this.randomEncounters = randomEncounters;
        this.game = game;
        this.connectedFields = new Field[4];
        this.exits = new int[4][2];
        this.sprite = sprite;
        this.level = new Level(this);
        level.loadMap(0, 0, 0, 0);
        this.impassables = level.getImpassables();
    }

    /**
     *
     */
    public void initField() {
        player.setImpassables(impassables);
    }

    /**
     *
     * @param edge
     * @param x
     * @param y
     */
    public void addExit(int edge, int x, int y) {
        exits[edge][0] = x;
        exits[edge][1] = y;
    }

    /**
     *
     * @param i
     * @param field
     */
    public void addConnectedField(int i, Field field) {
        connectedFields[i] = field;
    }

    /**
     *
     * @return
     */
    public int[][] getExits() {
        return exits;
    }

    /**
     *
     * @return
     */
    public Field[] getConnectedFields() {
        return connectedFields;
    }

    /**
     *
     * @return
     */
    public List<CreatureOnField> getCreaturesOnField() {
        return creaturesOnField;
    }

    /**
     *
     * @return
     */
    public PlayableCreature getPlayer() {
        return player;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @return
     */
    public boolean[][] getImpassables() {
        return impassables;
    }

    /**
     *
     */
    public void checkEdge() {
        if (player.getX() == 0) {
            if (connectedFields[0] != null) {
                changeField(0);
            }
        } else if (player.getY() == 0) {
            if (connectedFields[1] != null) {
                changeField(1);
            }
        } else if (player.getX() == width - 1) {
            if (connectedFields[2] != null) {
                changeField(2);
            }
        } else if (player.getY() == height - 1) {
            if (connectedFields[3] != null) {
                changeField(3);
            }
        }
    }

    private void changeField(int i) {
        game.setCurrentField(connectedFields[i]);
        if (i < 2) {
            player.setX(connectedFields[i].exits[i + 2][0]);
            player.setY(connectedFields[i].exits[i + 2][1]);
        } else {
            player.setX(connectedFields[i].exits[i - 2][0]);
            player.setY(connectedFields[i].exits[i - 2][1]);
        }
    }

    /**
     *
     * @return
     */
    public CreatureOnField checkSpot() {
        for (CreatureOnField h : creaturesOnField) {
            if (player.getX() == h.getX() && player.getY() == h.getY()) {
                if (h.getCurrentHp() > 0) {
                    h.setCurrentHp(h.getMaxHp());
                    return h;
                }
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
    public Creature createRandomEncounter() {
        Random rm = new Random();
        Creature re = randomEncounters.get(rm.nextInt(randomEncounters.size()));
        return new Creature(re.getBattleTile(), re.getName(), re.getMaxHp(), re.getDamage(), re.getExp());
    }
}
