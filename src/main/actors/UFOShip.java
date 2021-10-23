package main.actors;

import main.Events.Renderer;
import main.Screens.GameScreen;
import main.constants.Direction;
import main.constants.GameConstants;
import main.constants.GameObject;
import main.gdx.Image;
import main.GameContainer;

public class UFOShip extends GameObject{

    public Projectile bomb;
    public Image ufoShipImage = new Image("ufoship");
    public Image bombImage = new Image("bomb");
    public int[] shot = {45, 126, 246, 180};
    public int shoot;

    public UFOShip(Direction direction) {
        if ( direction == Direction.RIGHT)
        {
            setCoordinate(-6, 35);
        }
        setImage(ufoShipImage);
        setWidth(ufoShipImage.getW());
        setHeight(ufoShipImage.getH());
        bomb = null;
        this.currentDirection = direction;
    }

    private void setCoordinate (int x, int y)
    {
        this.posX = x;
        this.posY = y;
    }

    @Override
    public void update(GameScreen gm, float dt) {
        String currDirection = currentDirection.toString();

        switch (currDirection) {
            case "LEFT":
                break;
            case "RIGHT":
                this.posX += dt;
                break;
            case "STANDBY":
                break;
            default:
                break;
        }

        if ( this.posX == shot[(int) (Math.random() + 3)] )
        {
            setShot();
        }
    }

    public void setShot ()
    {
        this.bomb = new Projectile(bombImage, Direction.BOTTOM_RIGHT, this.getPosX(), this.getPosY(), 4, 30);
        System.out.println("UFOShip shoots at " + this.getPosX());
    }

    public void removebomb ()
    {
        this.bomb = null;
    }

    public void render (GameContainer gc, Renderer r)
    {
        r.drawImage(image, this.getPosX(), this.getPosY());
    }


}
