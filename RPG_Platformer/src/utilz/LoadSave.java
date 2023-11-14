package utilz;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String LEVEL_MAP = "/level/lvl_data.png";
    public static final String BLOCK_SPRITE = "/level/outside_sprites.png";
    public static final String BG_LEVEL = "/level/test_fond2.jpg";
    public static final String SPAWN_BG = "/level/spawn_bg_not_final.jpeg";
    public static final String CHEST = "/objects/chest/Chests.png";
    public static final String POTION_ATLAS = "/objects/potion/potions_sprites.png";
    public static final String INVENTORY = "/ui/Inventory_sprite.png";


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
