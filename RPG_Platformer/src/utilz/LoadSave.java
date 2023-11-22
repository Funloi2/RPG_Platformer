package utilz;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    // Map data
    public static final String LEVEL_MAP = "/level/lvl_data.png";

    // Level Design asset
    public static final String BLOCK_SPRITE = "/level/outside_sprites.png";
    public static final String BG_LEVEL_1 = "/level/zone2.png";
    public static final String BG_LEVEL_2 = "/level/zone1.png";
    public static final String BG_LEVEL_3 = "/level/zone3.png";
    public static final String SPAWN_BG = "/level/spawn_bg_not_final.jpeg";
    public static final String CORRIDOR_BG = "/level/corridor.png";
    public static final String BOSS_ROOM_BG = "/level/bossRoom.png";

    // Objects
    public static final String CHEST = "/objects/chest/Chests.png";
    public static final String POTION_ATLAS = "/objects/potion/potions_sprites.png";
    public static final String ARMOR_ATLAS = "/objects/equipment/Armor/armor_4x.png";
    public static final String WEAPON_ATLAS = "/objects/equipment/Items/items_4x.png";

    public static final String ALTAR_SPRITE = "/objects/FlyingObelisk_no_lightnings_no_letter.png";
    // UI
    public static final String INVENTORY = "/ui/Inventory_sprite.png";
    public static final String PLAYER_SPLASH_ART = "/player/splash_art.png";
    public static final String ITEM_CARD = "/ui/item_card.png";

    public static final String ALTAR_UI = "/ui/altar_ui_bg.png";

    public static final String GAME_OVER_BG = "/ui/death_screen.png";

    // Buttons
    public static final String RESPAWN_BUTTON_ATLAS = "/ui/respawn_button.png";
    public static final String UPGRADE_BUTTON_ATLAS = "/ui/upgrade_button.png";
    public static final String UPGRADE_LABEL_ATLAS = "/ui/upgrade_label.png";
    public static final String RELOAD_BUTTON_ATLAS = "/ui/reload_button.png";

    //Boss Effects
    public static final String BOSS_EFFECTS1 = "/ennemies/DarkVFX/DarkVFX1.png";
    public static final String BOSS_EFFECTS2 = "/ennemies/DarkVFX/DarkVFX2.png";


    public static BufferedImage GetSpriteAtlas(String path) {
        BufferedImage image = null;

        InputStream is = LoadSave.class.getResourceAsStream(path);

        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return image;
    }

    public static int[][] GetLevelData() {

        BufferedImage image = GetSpriteAtlas(LEVEL_MAP);
        int[][] lvlData = new int[image.getHeight()][image.getWidth()];

        for (int j = 0; j < image.getHeight(); j++) {
            for (int i = 0; i < image.getWidth(); i++) {
                Color color = new Color(image.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48) {
                    value = 11;
                }
                lvlData[j][i] = value;
            }
        }
        return lvlData;
    }

}
