package main.constants;

import main.Events.Renderer;
import main.Screens.GameScreen;
import main.gdx.Image;
import main.gdx.ImageTile;
import main.GameContainer;

import java.awt.*;

public abstract class GameObject {
    public int posX;
    public int posY;
    public int width;
    public int height;
    public boolean exist = true;
    public Image image;
    public ImageTile imageTile;

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    protected Direction currentDirection;

    public abstract void update(GameScreen game, float dt);
    public abstract void render(GameContainer gc, Renderer r);

    public boolean collisionWithWall(Direction d) {
        String direction = d.toString();
        switch (direction)
        {
            // check if the object is colliding with left wall
            case "LEFT":
                if (this.getPosX() <= 1) {
                    return true;
                }
                return false;
            case "RIGHT":
                if (this.getPosX() >= 382) {
                    return true;
                }
                return false;
        }
        return false;
    }

    public boolean collideWithGround () { return this.posY >= 288; }


    public boolean collisionWithOtherObject (GameObject other)
    {
        //System.out.println("shotX = " + this.posX + " alienX= " + other.posX + "\nshotY= " + this.posY + " alienY= " + other.posY);
        if (this.getPosX() >= (other.getPosX())
                && this.getPosX() <= (other.getPosX() + other.getWidth())
                && this.getPosY() >= (other.getPosY())
                && this.getPosY() <= (other.getPosY() + other.getHeight())){
            return true;
        }
        else {
            return false;
        }
    }
    public Rectangle getRectangle() { return new Rectangle(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight()); }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
