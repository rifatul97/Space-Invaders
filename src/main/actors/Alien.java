package main.actors;

import main.Events.Renderer;
import main.Screens.GameScreen;
import main.constants.GameConstants;
import main.constants.GameObject;
import main.constants.Invader;
import main.gdx.ImageTile;
import main.GameContainer;

public class Alien extends GameObject {

    public Invader.Character species;
    public ImageTile alienImage;
    float temp = 0;

    public Alien (Invader.Character species, int posx, int posy)
    {
        this.species = species;
        switch (species) {
            case CRAB -> alienImage = new ImageTile("crab", 16, 14);
            case FISH -> alienImage = new ImageTile("fish", 16, 14);
            case OCTOPUS -> alienImage = new ImageTile("Octopus", 20, 14);
        }
        this.posX = posx;
        this.posY = posy;
        this.setHeight(alienImage.getH());
        this.setWidth(alienImage.getW()/2);
        temp = 0;
    }

    @Override
    public void update(GameScreen game, float dt) {
        String command = game.alienController.currentDirection.toString();
        switch (command) {
            case "STANDBY":
                return;
            case "LEFT":
                this.posX -= GameConstants.alien_X_move;
                break;
            case "RIGHT":
                this.posX += GameConstants.Alien_Y_move;
                break;
            case "DOWN":
                this.posY += GameConstants.alien_X_move;
                break;
            default:
                break;
        }

        temp += dt * 0.20;
        if ( temp > 2) temp = 0;
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawImageTile(alienImage, this.getPosX(), this.getPosY(), (int) temp, 0);
    }

}
