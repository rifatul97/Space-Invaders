package main.actors;

import main.Events.Renderer;
import main.Screens.GameScreen;
import main.constants.Direction;
import main.constants.GameConstants;
import main.constants.GameObject;
import main.gdx.Image;
import main.GameContainer;

public class Projectile extends GameObject {

    public int speed;
    public int power;
    public Direction currentDirection;
    public Image image;

    //player's projectile.
    public Projectile (int x, int y) {
        this.posX = x;
        this.posY = y;
        this.image = new Image("bomb");
        this.power = 25;
        this.speed = 3;
        this.setHeight(image.getH());
        this.setWidth(image.getW());
        this.exist = true;
        this.currentDirection = Direction.UP;
    }


    public Projectile (Image p, Direction direction, int x, int y, int speed, int power)
    {
        this.posX = x;
        this.posY = y;
        this.image = p;
        this.setHeight(image.getH());
        this.setWidth(image.getW());
        this.speed = speed;
        this.power = power;
        this.currentDirection = direction;
    }


    @Override
    public void update(GameScreen game, float dt) {
        String direction = currentDirection.toString();
        switch (direction) {
            case "UP":
                this.posY -= this.speed;
                break;
            case "DOWN":
                this.posY += this.speed;
                break;
            case "BOTTOM_LEFT":
                this.posY += this.speed;
                this.posX -= 1;
                break;
            case "BOTTOM_RIGHT":
                this.posX += 1;
                this.posY += this.speed;
                break;
            default:
                break;
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawImage(image, this.getPosX(), this.getPosY());
    }

}

