package objects;

import gameStates.Playing;
import level.Level;
import objects.equipment.Equipment;
import objects.equipment.Helmet;
import utilz.LoadSave;

import static utilz.Constants.ObjectConstants.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class ObjectManager {
    /// ------------------------------- ATTRIBUTE ------------------------------- ///
    private Playing playing;

    // Spites
    private BufferedImage[][] chestImages;
    private BufferedImage[][] potionImages;

    // List of items
    private ArrayList<Chest> chests = new ArrayList<>();
    private ArrayList<Potion> potions = new ArrayList<>();
    private ArrayList<Equipment> equipments = new ArrayList<>();


    /// ------------------------------- CONSTRUCTOR ------------------------------- ///
    public ObjectManager(Playing playing) {
        this.playing = playing;
        loadImages();
    }

    /// ------------------------------- METHOD ------------------------------- ///
//    public void checkObjectTouched(Rectangle2D.Float hitbox) {
//        for (Potion p : potions) {
//            if (p.isActive()) {
//                if (hitbox.intersects(p.getHitbox())) {
//                    p.setActive(false);
//                    applyEffectToPlayer(p);
//                }
//            }
//        }
//    }

//    public void applyEffectToPlayer(Potion p) {
//        if (p.getObjType() == RED_POTION) {
//            playing.getPlayer().changeHealth(RED_POTION_VALUE);
//        } else {
//            playing.getPlayer().changePower(BLUE_POTION_VALUE);
//        }
//    }

    public void checkPickUpItem(Rectangle2D.Float hitBox) {
        for (Equipment equipment : equipments) {
            if (equipment.isActive()) {
                if (hitBox.intersects(equipment.hitbox)) {
                    if (!pickUpItem(equipment)) {
                        playing.setInventoryFull(true);
                        playing.setInventoryFullClock(0);
                    } else {
                        equipment.setActive(false);
                    }
                    break;
                }
            }
        }
    }

    private void inventoryFull() {

    }

    private boolean pickUpItem(Equipment equipment) {
        return playing.getInventoryOverlay().pickUpItem(equipment);
    }

    public void checkObjectHit(Rectangle2D.Float attackbox) {
        for (Chest ch : chests) {
            if (ch.isActive() && !ch.doAnimation) {
                if (ch.getHitbox().intersects(attackbox)) {
                    ch.objState = 1;
                    for (int i = 0; i < 20; i++)
                        equipments.add(new Helmet(ch.x, (int) (ch.getHitbox().y + ch.getHitbox().height - ARMOR_HEIGHT), 0, ch.x / 10));

//                    potions.add(new Potion((int) (ch.getHitbox().x + ch.getHitbox().width / 2), type == 0 ? (int) (ch.getHitbox().y - ch.getHitbox().height / 2) : (int) (ch.getHitbox().y), type));
                    return;
                }
            }
        }
    }

    public void dropItem(Equipment equipment) {
        equipments.add(equipment);
    }

    public void loadObjects(Level level) {
        potions = new ArrayList<>(level.getPotions());
        chests = new ArrayList<>(level.getChests());
    }


    private void loadImages() {
        BufferedImage potionSprite = LoadSave.GetSpriteAtlas(LoadSave.POTION_ATLAS);
        potionImages = new BufferedImage[2][7];

        for (int j = 0; j < potionImages.length; j++) {
            for (int i = 0; i < potionImages[j].length; i++) {
                potionImages[j][i] = potionSprite.getSubimage(i * POTION_WIDTH_DEFAULT, j * POTION_HEIGHT_DEFAULT, POTION_WIDTH_DEFAULT, POTION_HEIGHT_DEFAULT);
            }
        }

        BufferedImage chestSprite = LoadSave.GetSpriteAtlas(LoadSave.CHEST);
        chestImages = new BufferedImage[8][5];

        for (int j = 0; j < chestImages.length; j++) {
            for (int i = 0; i < chestImages[j].length; i++) {
                chestImages[j][i] = chestSprite.getSubimage(i * CHEST_DEFAULT_WIDTH, j * CHEST_DEFAULT_HEIGHT, CHEST_DEFAULT_WIDTH, CHEST_DEFAULT_HEIGHT);
            }
        }

    }

    public void update() {
        for (Potion p : potions) {
            if (p.isActive()) {
                p.update();
            }
        }
        for (Chest ch : chests) {
            if (ch.isActive()) {
                ch.update();
            }
        }
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        drawPotions(g, xLvlOffset, yLvlOffset);
        drawChests(g, xLvlOffset, yLvlOffset);
        drawEquipment(g, xLvlOffset, yLvlOffset);
    }

    private void drawEquipment(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Equipment equipment : equipments) {
            if (equipment.isActive())
                equipment.draw(g, xLvlOffset, yLvlOffset);
        }
    }

    private void drawChests(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Chest c : chests) {
            if (c.isActive()) {
                g.drawImage(chestImages[c.objState][c.getAniIndex()],
                        (int) (c.getHitbox().x - c.getxDrawOffset() - xLvlOffset),
                        (int) (c.getHitbox().y - c.getyDrawOffset() - yLvlOffset),
                        CHEST_WIDTH,
                        CHEST_HEIGHT,
                        null);
//                c.drawHitBox(g, xLvlOffset, yLvlOffset);
            }
        }


    }

    private void drawPotions(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Potion p : potions) {
            if (p.isActive()) {
                int type = 0;
                if (p.getObjType() == LIFE_POTION) {
                    type = 1;
                }

                g.drawImage(potionImages[type][p.getAniIndex()],
                        (int) (p.getHitbox().x - p.getxDrawOffset() - xLvlOffset),
                        (int) (p.getHitbox().y - p.getyDrawOffset() - yLvlOffset),
                        POTION_WIDTH,
                        POTION_HEIGHT,
                        null);
                p.drawHitBox(g, xLvlOffset, yLvlOffset);
            }
        }
    }


    public void resetObjects() {
        loadObjects(playing.getLevelManager().getCurrentLevel());

        for (Potion p : potions) {
            p.reset();
        }
        for (Chest c : chests) {
            c.reset();
        }
    }


    /// ------------------------------- GETTER AND SETTER ------------------------------- ///

}
