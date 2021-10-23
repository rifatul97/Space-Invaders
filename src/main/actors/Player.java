package main.actors;

import main.Events.Renderer;
import main.Screens.GameScreen;
import main.constants.Direction;
import main.constants.GameConstants;
import main.constants.GameObject;
import main.gdx.Image;
import main.GameContainer;

public class Player extends GameObject {
    public Projectile playerProjectile;
    public int shield;
    public int score;
    public int totalshield;

    public Player (int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
        image = new Image("player1");
        this.setHeight(image.getH());
        this.setWidth(image.getW());
        currentDirection = Direction.STANDBY;
        playerProjectile = null;
        totalshield = 100;
        shield = 100;
        score = 0;
    }

    public boolean isShotVisible () {
        return this.playerProjectile != null;
    }

    public void setShot () {
        if ( playerProjectile == null )
        {
            System.out.println("shot set!");
            playerProjectile = new Projectile(this.getPosX()+7, this.getPosY());
            playerProjectile.setCurrentDirection(Direction.UP);
        }
    }

    public void destroyShot ()
    {
        System.out.println("shot destroyed at " + playerProjectile.getPosY());
        playerProjectile = null;
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
