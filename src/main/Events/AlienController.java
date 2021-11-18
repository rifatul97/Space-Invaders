package main.Events;

import main.Screens.GameScreen;
import main.actors.Alien;
import main.actors.Projectile;
import main.actors.UFOShip;
import main.constants.Direction;
import main.constants.GameConstants;
import main.constants.Invader;
import main.gdx.Image;
import main.GameContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlienController {

    public GameContainer gc;
    public GameScreen game;
    public Direction currentDirection;
    public Direction previousDirection;
    public boolean changeDirection;
    private final Invader.Character[] invaders = new Invader.Character[]{Invader.Character.CRAB, Invader.Character.CRAB, Invader.Character.FISH, Invader.Character.OCTOPUS};

    public AlienController (GameScreen game)
    {
        this.game = game;
        resetAlienPositions();
    }

    public void resetAlienPositions() {
        changeDirection = false;
        currentDirection = Direction.LEFT;
        previousDirection = Direction.RIGHT;

        int y = 50;
        int index = 0;
        for(int i=0; i<4; i++)
        {
            int x = GameConstants.playerXposition;
            for(int j = 0; j < 6; j ++)
            {
                Alien alien = new Alien(invaders[index], x, y);
                x += 25;
                game.aliens.add(alien);
                game.numOfEnemies++;
            }
            y += 20;
            index++;
        }

    }


    public void initUFOShip (GameScreen game)
    {
        Random rand = new Random();
        if ( game.ufoship == null)
        {
            int chance = rand.nextInt(3000);
            if ( chance <= 5)
            {
                game.ufoship = new UFOShip(Direction.RIGHT);
            }
        }
        else
        {
            if ( game.ufoship.getPosX() > 393 ) //&& currentDirection == Direction.RIGHT)
            {
                game.ufoship = null;
            }
            else
            {
                moveUFOShip(game);
            }

        }
    }

    public void update (GameScreen game)
    {
        checkIfAliensCollideWalls(game);
        moveAliens(game);
        initUFOShip(game);
    }

    private void moveUFOShip(GameScreen game)
    {
        game.ufoship.update(game, 3);
    }

    private void checkIfAliensCollideWalls(GameScreen game) {

        if(changeDirection) {
            if (previousDirection == Direction.LEFT) currentDirection = Direction.RIGHT;
            else currentDirection = Direction.LEFT;
            changeDirection = false;
        }

        if(currentDirection == Direction.LEFT)
        {
            for(Alien alien : game.aliens)
            {
                if(alien.exist)
                {
                    if (alien.collisionWithWall(Direction.LEFT))
                    {
                        changeDirection = true;
                        break;
                    }
                }

            }
        }
        else
        {
            for(Alien alien : game.aliens)
            {
                if(alien.exist && alien.collisionWithWall(Direction.RIGHT))
                {
                    changeDirection = true;
                    break;
                }
            }
        }

        if (changeDirection) {
            previousDirection = currentDirection;
            currentDirection = Direction.DOWN;
        }
    }

    private void moveAliens(GameScreen game) {
        var generator = new Random();
        boolean shot = false;


        for (Alien alien : game.aliens)
        {
            if (alien.exist)
            {
                int shoot = (generator.nextInt(2000) + generator.nextInt(2000))/2;
                //System.out.println(shoot);
                alien.update(game, 1);

                    if (shoot <= game.wave - 1) {
                        Direction shotdirection = shoot%2 == 0 ? Direction.BOTTOM_LEFT : Direction.BOTTOM_RIGHT;
                        Projectile alienBomb = new Projectile(new Image("thunder"), shotdirection, alien.getPosX(), alien.getPosY(), 2, 20);
                        alienBomb.setCurrentDirection(shotdirection);
                        game.bombs.add(alienBomb);
                    } else if (shoot <= game.wave + 20) {
                        Projectile alienBomb = new Projectile(new Image("bomb"), Direction.DOWN, alien.getPosX(), alien.getPosY(), 1, 30);
                        game.bombs.add(alienBomb);
                    } else if (shoot <= game.wave + 50){
                        Projectile alienBomb = new Projectile(new Image("shot"), Direction.DOWN, alien.getPosX(), alien.getPosY(), 2, 15);
                        game.bombs.add(alienBomb);
                    }
                }
            }
        }

    public void render(GameContainer gc, Renderer r) {
        for (Alien alien : game.aliens)
        {
            if (alien.exist) {
                alien.render(gc, r);
            }
        }
    }

}
