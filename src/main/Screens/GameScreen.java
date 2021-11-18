package main.Screens;

import main.Events.*;
import main.actors.Alien;
import main.actors.Player;
import main.actors.Projectile;
import main.actors.UFOShip;
import main.constants.*;
import main.GameContainer;
import main.gdx.Image;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

    public Player player;
    public List<Alien> aliens;
    protected GameContainer gc;
    public List<Projectile> bombs;
    public List<Projectile> playerShots;
    public AlienController alienController;
    public ProjectileController projectileController;
    public List<int[]> explosions = new ArrayList<>();
    public UFOShip ufoship;
    public int wave = 0;
    public double currentTimeRemaining;
    public int score, shield;
    public int numOfEnemies;
    public boolean drawn= false;
    public Image backgroundImage;
    public boolean gameOver = false;

    public GameScreen(GameContainer gc) { // we are not drawing the objects! just initializing~
        this.gc = gc;
        player = new Player(25, 284);
        score = player.score;
        shield = player.shield;
        currentTimeRemaining = 30000;
        initNewLevel();
        Resource.getSound("soundtrack").play();
        gameOver = false;
    }

    public void initNewLevel () {
        backgroundImage = new Image("background");
        aliens = new ArrayList<Alien>();
        bombs = new ArrayList<Projectile>();
        alienController = new AlienController(this);
        projectileController = new ProjectileController(this);
        ufoship = null;
        wave++;
    }

    @Override
    public void update() {
        currentTimeRemaining -= 10;
        if (player.shield <= 0 || currentTimeRemaining == 0) {
            gameOver = true;
            Resource.getSound("soundtrack").stop();
            gc.getWindow().getG().setColor(Color.RED);
            gc.getWindow().getG().drawString("GAME OVER", 350, 300);
            gc.getWindow().getG().setColor(Color.WHITE);
            return;
        }
        if (gc.state == GameState.PAUSE) {
            return;
        }

        if (aliens.size() == 0) {
            wave++;
            player.shield += (int)(currentTimeRemaining/100);
            initNewLevel();
        }
        alienController.update(this);

        if (numOfEnemies == 1) {
            alienController.update(this);
        }

        player.update(this, 2);
        player.setCurrentDirection(Direction.STANDBY);

        projectileController.updateProjectiles();

        drawHUD();
    }

    private void drawHUD() {
        gc.getWindow().getG().drawString("Shield " + player.shield + "     Score " + player.score, 15, 20);
        gc.getWindow().getG().drawString("Time " + (int) currentTimeRemaining/1000 + " " + (int) currentTimeRemaining % 100, 325, 20);
        gc.getWindow().getG().drawString("Wave " + this.wave, 605, 20);
    }

    @Override
    public void draw(Renderer r) {
        r.drawImage(backgroundImage, 0, 0);
        player.render(gc, r);
        alienController.render(gc, r);
        projectileController.drawProjectiles(r);
        projectileController.drawExplosions(r);
        if ( this.ufoship != null ) ufoship.render(gc, r);
    }

    @Override
    public void handleInput(InputHandler input) {
        if(gameOver && input.space.isPressed()) {
            gc.resetGameScreenState();
            gc.setGameState(GameState.START);
        }
        if (input.left.isPressed()) {
            player.setCurrentDirection(Direction.LEFT);
        }
        if (input.right.isPressed()) {
            player.setCurrentDirection(Direction.RIGHT);
        }
        if (input.space.isPressed()) {
            if (player.createShot()) {
                Resource.getSound("playershot").play();
                this.bombs.add(new Projectile(player.getPosX()+7, player.posY));
            }
        }
        if (input.p.isPressed()) {
            gc.state = GameState.PAUSE;
        }


    }

    @Override
    public void handleMouse(MouseHandler mouse) {}
}
