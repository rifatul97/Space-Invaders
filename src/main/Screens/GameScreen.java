package main.Screens;

import main.Events.*;
import main.actors.Alien;
import main.actors.Player;
import main.actors.Projectile;
import main.actors.UFOShip;
import main.constants.*;
import main.GameContainer;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

    public Player player;
    public List<Alien> aliens;
    protected GameContainer gc;
    public List<Projectile> alienBombs;
    public AlienController alienController;
    public ProjectileController projectileController;
    public List<int[]> explosions = new ArrayList<>();
    public UFOShip ufoship;
    public int wave = 1;
    public int score, shield;
    public int numOfEnemies;


    public GameScreen(GameContainer gc) { // we are not drawing the objects! just initializing~
        this.gc = gc;
        player = new Player(25, 284);
        wave = 1;
        score = player.score;
        shield = player.shield;
        initNewLevel();
    }

    public void initNewLevel () {
        aliens = new ArrayList<Alien>();
        alienController = new AlienController(this);
        alienController.initAliens(aliens);
        projectileController = new ProjectileController(this);
        ufoship = null;
        numOfEnemies = 24;
    }

    @Override
    public void update() {
        if (gc.state == GameState.PAUSE) {
            return;
        }
        if (player.shield <= 0) {
            gc.getWindow().getG().drawString("GAME OVER", 100, 100);
            gc.resetGameScreenState();
            gc.setGameState(GameState.START);
        }
        if (numOfEnemies == 0) {
            wave++;
            initNewLevel();
        }
        alienController.update(this);
        if (numOfEnemies == 1) {
            alienController.update(this);
        }
        player.update(this, 2);
        player.setCurrentDirection(Direction.STANDBY);
        if (player.isShotVisible()) {
            projectileController.updatePlayerProjectile(player);
        }
        projectileController.updateAlienProjectiles(this, player);
        int playerhealth = (player.shield*player.totalshield)/100;
        gc.getWindow().getG().drawString("Shield " + playerhealth + "     Score " + player.score, 15, 20);
        gc.getWindow().getG().drawString("Time " + 0, 325, 20);
        gc.getWindow().getG().drawString("Wave " + this.wave, 605, 20);
    }

    @Override
    public void draw(Renderer r) {
        player.render(gc, r);
        if (player.isShotVisible()) {
            player.playerProjectile.render(gc, r);
        }
        alienController.render(gc, r);
        projectileController.drawAlienProjectiles(this, r);
        projectileController.drawExplosions(this, r);
        if ( this.ufoship != null ) ufoship.render(gc, r);
    }

    @Override
    public void handleInput(InputHandler input) {
        if (input.left.isPressed()) {
            player.setCurrentDirection(Direction.LEFT);
        }
        if (input.right.isPressed()) {
            player.setCurrentDirection(Direction.RIGHT);
        }
        if (input.space.isPressed()) {
            player.setShot();
        }
        if (input.p.isPressed()) {
            gc.state = GameState.PAUSE;
        }

    }

    public void setAlienBombs (List<Projectile> bombs) {
        this.alienBombs = bombs;
    }

    @Override
    public void handleMouse(MouseHandler mouse) {}
}
