package main.gdx;

import main.constants.Resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Image {

    private int w, h;
    private int[] p;

    public Image(String name)
    {
        BufferedImage image = null;

        //image = ImageIO.read(Image.class.getResourceAsStream(path));
        image = Resource.getImage(name);

        setW(image.getWidth());
        setH(image.getHeight());
        setP(image.getRGB(0, 0, w, h, null, 0, w));
        image.flush();
    }


    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    public int[] getP() {
        return p;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setP(int[] p) {
        this.p = p;
    }

    public void setH(int h) {
        this.h = h;
    }
}
