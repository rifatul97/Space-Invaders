package main.constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Resource {

    public static final String RES_IMAGE_FILE_EXT = ".png";
    public static final String RES_SOUND_FILE_EXT = ".wav";
    public static final String RES_FONT_FILE_EXT = ".ttf";
    public static final String RES_LEVEL_FILE_EXT = ".txt";
    public static final String RES_TITLE_ANIMATION_FILE_EXT = ".txt";

    public static final String RES_IMAGE_PATH = "/resources/image/";
    public static final String RES_SOUND_PATH = "/resources/sound/";
    public static final String RES_FONT_PATH = "/resources/font/";
    public static final String RES_LEVEL_PATH = "/resources/level/";
    public static final String RES_TITLE_ANIMATION_PATH = "/resources/title_animation/";

    private static final Map<String, BufferedImage> IMAGES = new HashMap<>();
    private static final Map<String, byte[]> SOUNDS = new HashMap<>();
    private static final Map<String, Font> FONTS = new HashMap<>();
    //private static final Map<String, TitleAnimation> TITLE_ANIMATIONS = new HashMap<>();

    private Resource() {
    }

    public static InputStream getResource(String path) {
        URL url = Resource.class.getResource(path);
        try {
            URLConnection c = url.openConnection();
            return c.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getImage(String name) {
        BufferedImage image = null;
        try {
            String imageResource = RES_IMAGE_PATH + name + RES_IMAGE_FILE_EXT;
            //System.out.println(imageResource);
            InputStream is = getResource(imageResource);
            image = IMAGES.get(name);
            if (image == null) {
                image = ImageIO.read(is);
                IMAGES.put(name, image);
            }
        } catch (Exception ex) {
            Logger.getLogger(
                    Resource.class.getName()).log(Level.SEVERE, null, ex);

            System.exit(-1);
        }
        return image;
    }

    public static Font getFont(String name) {
        Font font = FONTS.get(name);

        if (font == null) {
            String fontResource
                    = RES_FONT_PATH + name + RES_FONT_FILE_EXT;
            System.out.println(fontResource);
            try {
                font = Font.createFont(Font.TRUETYPE_FONT
                        , new BufferedInputStream(getResource(fontResource)));

                FONTS.put(name, font);
            } catch (Exception ex) {
                Logger.getLogger(
                        Resource.class.getName()).log(Level.SEVERE, null, ex);

                System.exit(-1);
            }
        }
        return font;
    }
}