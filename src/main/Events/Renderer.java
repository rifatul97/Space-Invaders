package main.Events;

import main.gdx.Image;
import main.gdx.ImageTile;
import main.GameContainer;

import java.awt.image.DataBufferInt;

public class Renderer {

    private int pW, pH;
    private int[] p;
    //Font font = Font.STANDARD;

    public Renderer(GameContainer gc)
    {
        pW = gc.getWidth();
        pH = gc.getHeight();
        p = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
        System.out.println(p.length);
    }

    public void drawImageTile (ImageTile image, int offX, int offY, int tileX, int tileY)
    {
        int newX = 0;
        int newY = 0;
        int newWidth = image.getTileW();
        int newHeight = image.getTileH();

        for(int y = newY; y < newHeight; y++)
        {
            for(int x = newX; x < newWidth; x++)
            {
                setPixel(x + offX, y + offY, image.getP()[(x + tileX * image.getTileW()) + (y + tileY * image.getTileH()) * image.getW()]);
            }
        }
    }

    public void drawImage (Image image, int offX, int offY)
    {
        int newX = 0;
        int newY = 0;
        int newWidth = image.getW();
        int newHeight = image.getH();

        //System.out.println(newWidth + " " + newHeight);

        for(int y = newY; y < newHeight; y++)
        {
            for(int x = newX; x < newWidth; x++)
            {
                setPixel(x + offX, y + offY, image.getP()[x + y * image.getW()]);
            }
        }

        //System.out.println("image drawn!");
    }

    public void setPixel(int x, int y, int value) {

        if( (x < 0 || x >= pW || y < 0 || y >= pH ) )//|| value == 0xffff00ff) )
        {
            //System.out.println("returned");
            return;
        }

        p[x + y * pW] = value;
    }

    public void clear()
    {
        for(int i = 0; i < p.length; i++)
        {
            p[i] = 0;
        }
    }
}




