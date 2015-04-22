
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
import kanipeli.domain.Item;
import kanipeli.domain.PlayableCreature;
import kanipeli.ui.level.Level;
import kanipeli.ui.sprites.Sprite;

/**
 * The logic for prancing around on the field.
 * A field has 1 to three exits and knows to which fields it is connected.
 *The player moved on the field where he currently is and can meet random
 * encounters of creatures on the field. He can also exit the field to another 
 * field.
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
     *The constructor of the Field class. Constructs all of its private
     * variables.
     * @param game needed to change the current field of the game.
     * @param sprite a sprite that tells the dimensions and details of the field.
     * @see Sprite
     * @param player the player which moves on the field.
     * @param creaturesOnField Creatures appearing on the map.
     * @param randomEncounters List of creatures that appear randomly while
     * traversing the field.
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
     *Sets the player's impassable tiles as the ones on this field.
     */
    public void initField() {
        player.setImpassables(impassables);
    }

    /**
     *Adds a new exit to the field.
     * @param edge The edge of the field.
     * @param x the x-coordinate for the exit
     * @param y the y-coordinate for the exit
     */
    public void addExit(int edge, int x, int y) {
        exits[edge][0] = x;
        exits[edge][1] = y;
    }

    /**
     *Adds a connection to another field for this field.
     * @param i the direction of the connection (0 = left, 1 = up, 2 = right,
     * 3 = down)
     * @param field the field to be connected
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
     *Checks if the player is on an edge meaning he is exiting the current
     * field. In such a case changes the field to according to which edge the
     * player has moved on.
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
     *Goes through creatures on the field and checks if their coordinates
     * match with those of the player's and the creature is still alive.
     * Sets the health of that creature to it's maximum health if it's not dead.
     * @return the creature on which the player has moved.
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
     *If the player hasn't moved on an enemy on the field has 1 in 15 chance of
     * providing a random encounter battle.
     * @return false if moved on an enemy tile or if good luck happened.
     * else true, which indicates that a random encounter battle should happen.
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
     *selects a new creature randomly from random encounters list and creates
     * a new creature with the selected creatures statistics.
     * @return freshly created creature for a battle.
     */
    public Creature createRandomEncounter() {
        Random rm = new Random();
        Creature re = randomEncounters.get(rm.nextInt(randomEncounters.size()));
        return new Creature(re.getBattleTile(), re.getName(), re.getMaxHp(), re.getDamage(), re.getExp(), re.getItem());
    }
}
