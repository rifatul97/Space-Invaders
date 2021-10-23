package main.Events;

import main.Screens.GameScreen;
import main.actors.Alien;
import main.actors.Player;
import main.actors.Projectile;
import main.constants.GameConstants;

import main.gdx.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjectileController {

    public GameScreen game;
    public Image explode;

    public ProjectileController (GameScreen game) {
        this.game = game;
        explode = new Image("explosion");
    }

    public void updatePlayerProjectile(Player player) {
        if (player.playerProjectile.getPosY() <= 5) {
            player.destroyShot();
            return;
        }

        if (player.isShotVisible())
        {
            List<Alien> aliens = game.aliens;
            Projectile playerProjectile = player.playerProjectile;

            if (game.ufoship != null)
            {
                if (playerProjectile.collisionWithOtherObject(game.ufoship))
                {
                    game.ufoship = null;
                    player.score += 300;
                    player.destroyShot();
                    int[] pt = new int[2];
                    pt[0] = playerProjectile.getPosX();
                    pt[1] = playerProjectile.getPosY();
                    game.explosions.add(pt);
                    return;
                }
            }

            for (Alien alien : aliens)
            {
                if (alien.exist) {
                    // Assuming there is an intersect method, otherwise just handcompare the values
                    if (playerProjectile.collisionWithOtherObject(alien))
                    {
                        //System.out.println("a alien destroyed!");
                        alien.exist = false;
                        player.score += 100;
                        game.numOfEnemies--;
                        int[] pt = new int[2];
                        pt[0] = alien.getPosX();
                        pt[1] = alien.getPosY();
                        game.explosions.add(pt);
                        player.destroyShot();
                        break; //
                    }
                    else
                    {
                        //    System.out.println("nope its not ");
                    }
                }
            }

            if (player.isShotVisible())
            {
                player.playerProjectile.update(game, playerProjectile.speed);
            }
        }
    }

    public void drawExplosions (GameScreen game, Renderer r)
    {
        Iterator explodes = game.explosions.iterator();
        while(explodes.hasNext())
        {
            int[] pt = (int[]) explodes.next();
            r.drawImage(explode, pt[0], pt[1]);
            explodes.remove();
        }
    }

    public void drawAlienProjectiles (GameScreen game, Renderer r)
    {
        for (Projectile bomb : game.alienBombs)
        {
            if ( bomb.exist )
            {
                r.drawImage(bomb.image, bomb.getPosX(), bomb.getPosY());
            }
        }

    }

    public void updateAlienProjectiles (GameScreen game, Player player)
    {
        //System.out.println("Currently there are " + game.alienBombs.size() + " bombs.");
        List<Projectile> tempAlienBombs = new ArrayList<>();
        //System.out.println("so far " + game.alienBombs.size() + " bombs.");

        if ( game.ufoship != null && game.ufoship.bomb != null ) {
            game.alienBombs.add(game.ufoship.bomb);
            game.ufoship.removebomb();
        }

        for (Projectile bomb : game.alienBombs)
        {
            if ( bomb.exist )
            {
                if (bomb.collisionWithOtherObject(player))
                {
                    int[] explodePt = new int[2];
                    explodePt[0] = player.getPosX();
                    explodePt[1] = player.getPosY();
                    game.explosions.add(explodePt);
                    player.shield -= bomb.power;
                    bomb.exist = false;
                }
                else if (!bomb.collideWithGround() && !bomb.collisionWithWall(bomb.currentDirection))
                {
                    bomb.update(game, bomb.speed);
                    tempAlienBombs.add(bomb);
                }
                else
                {
                    int[] explodePt = new int[2];
                    explodePt[0] = bomb.getPosX();
                    explodePt[1] = bomb.getPosY();
                    game.explosions.add(explodePt);
                    bomb.exist = false;
                }
            }
        }

        game.setAlienBombs(tempAlienBombs);
    }
}
