package main.actors;

import main.Events.Renderer;
import main.Screens.GameScreen;
import main.constants.*;
import main.gdx.Image;
import main.GameContainer;

public class Player extends GameObject {
    public int shield;
    public int score;
    private long lastFire = System.currentTimeMillis();
    public double firingInterval = 0.33;

    public Player (int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
        image = new Image("player1");
        this.setHeight(image.getH());
        this.setWidth(image.getW());
        currentDirection = Direction.STANDBY;
        shield = 1;
        score = 0;
    }


    public boolean createShot() {
        if ((System.currentTimeMillis() - lastFire)/1000 < firingInterval) {
            return false;
        }
        lastFire = System.currentTimeMillis();
        return true;
    }

    public void calculateScore(Alien alien) {
        this.score += 100;
    }

    @Override
    public void update(GameScreen game, float dt) {
        String command = currentDirection.toString();
        switch (command) {
            case "STANDBY":
                return;
            case "LEFT":
                if(!collisionWithWall(Direction.LEFT))
                {
                    this.posX -= dt;
                }
                break;
            case "RIGHT":
                if(!collisionWithWall(Direction.RIGHT))
                {
                    this.posX += dt;
                }
                break;
            case "DEAD":
                System.out.println("Game Over..");
                break;
        }
        this.setCurrentDirection(Direction.STANDBY);
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawImage(this.getImage(), (int) this.getPosX(), (int) this.getPosY());
    }

}
