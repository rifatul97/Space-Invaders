package main.constants;

import main.audio.SoundClip;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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
    public static final String RES_SAVE_FILE_EXT = ".dat";

    public static final String RES_IMAGE_PATH = "/resources/image/";
    public static final String RES_SOUND_PATH = "/resources/sound/";
    public static final String RES_FONT_PATH = "/resources/font/";
    public static final String RES_LEVEL_PATH = "/resources/level/";
    public static final String RES_TITLE_ANIMATION_PATH = "/resources/title_animation/";
    public static final String RES_SAVE_FILE_PATH = "/";

    private static final Map<String, BufferedImage> IMAGES = new HashMap<>();
    private static final Map<String, byte[]> SOUNDS = new HashMap<>();
    private static final Map<String, SoundClip> MUSICS = new HashMap<>();
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

    public static SoundClip getSound(String name) {
        SoundClip clip = null;
        try {
            clip = MUSICS.get(name);
            if (clip == null) {
                String soundResource = RES_SOUND_PATH + name + RES_SOUND_FILE_EXT;
                clip = new SoundClip(getResource(soundResource));
                MUSICS.put(name, clip);
            }
        } catch (Exception ex) {
            Logger.getLogger(
                    Resource.class.getName()).log(Level.SEVERE, null, ex);

            System.exit(-1);
        }
        return clip;
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

    public static void saveData() {
        DataOutputStream output = null;
        try {
            output = new DataOutputStream(new FileOutputStream(
                    "data.dat"));

            for (int i = 0; i < 100; i++) {
                output.writeInt(i);
                System.out.println(i);
            }

            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readData() {
        try {
            DataInputStream input = new DataInputStream(new FileInputStream(
                    "data.dat"));

            while (input.available() > 0) {
                int x = input.readInt();
                System.out.println(x);
            }

            input.close();
        } catch (IOException e) {

        }

    }
}