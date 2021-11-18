package main.constants;

import main.Events.Renderer;
import main.GameContainer;
import main.Screens.GameScreen;
import main.actors.Alien;
import main.gdx.Image;
import main.gdx.ImageTile;

import java.awt.*;

public abstract class GameObject {
    public int posX;
    public int posY;
    public int width;
    public int height;
    public boolean exist = true;
    public Image image;
    public ImageTile imageTile;
    protected Direction currentDirection;
    public float moveSpeed;

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public abstract void update(GameScreen game, float dt);

    public abstract void render(GameContainer gc, Renderer r);

    public boolean collisionWithWall(Direction d) {
        String direction = d.toString();
        switch (direction) {
            // check if the object is colliding with left wall
            case "LEFT":
                return this.getPosX() <= 1;
            case "RIGHT":
                return this.getPosX() >= 382;
            case "UP":
                return this.getPosY() <= 5;
            case "DOWN":
                return this.posY >= 288;
        }
        return false;
    }

    public boolean collisionWithOtherObject(GameObject other) {
        if (this.getPosX() >= (other.getPosX())
                && this.getPosX() <= (other.getPosX() + other.getWidth())
                && this.getPosY() >= (other.getPosY())
                && this.getPosY() <= (other.getPosY() + other.getHeight())) {
            System.out.println("shotX = " + this.posX + " alienX= " + other.posX + "\nshotY= " + this.posY + " alienY= " + other.posY);
            return true;
        }
        return false;
    }

    public Rectangle getRectangle() {
        return new Rectangle(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    }

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