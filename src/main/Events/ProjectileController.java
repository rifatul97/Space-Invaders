package main.Events;

import main.Screens.GameScreen;
import main.actors.Projectile;
import main.constants.Direction;
import main.constants.Resource;
import main.gdx.Image;

import java.util.Iterator;

public class ProjectileController {

    public GameScreen game;
    public Image explode = new Image("explosion");

    public ProjectileController(GameScreen game) {
        this.game = game;
    }

    public void updateProjectiles() {

        for (int i = 0; i < game.bombs.size(); i++) {
            Projectile currProjectile = game.bombs.get(i);
            Direction bombCurrentDirection = currProjectile.currentDirection;

            if (game.bombs.get(i).collisionWithWall(bombCurrentDirection)) {
                game.explosions.add(new int[]{currProjectile.getPosX(), currProjectile.getPosY()});
                game.bombs.get(i).exist = false;
            } else {

                switch (bombCurrentDirection) {
                    case UP -> {
                        for (int j = 0; j < game.aliens.size(); j++) {
                            if (game.bombs.get(i).collisionWithOtherObject(game.aliens.get(j))) {
                                Resource.getSound("destroyed").play();
                                game.bombs.get(i).exist = false;
                                game.aliens.get(j).exist = false;
                                game.player.calculateScore(game.aliens.get(j));
                                game.currentTimeRemaining += 200;
                                game.aliens.remove(j);
                                game.explosions.add(new int[]{currProjectile.getPosX(), currProjectile.getPosY()});
                                break;
                            }
                        }
                    }
                    default -> {
                        if (currProjectile.collisionWithOtherObject(game.player)) {
                            game.player.shield -= currProjectile.power;
                            game.bombs.get(i).exist = false;
                        }
                    }
                }
            }

            if (game.bombs.get(i).exist) {
                game.bombs.get(i).update(game, currProjectile.speed);
            }

        }

        for (int i = 0; i < game.bombs.size(); i++) {
            if (!game.bombs.get(i).exist) {
                game.bombs.remove(i);
            }
        }

    }

    public void drawExplosions(Renderer r) {
        Iterator explodes = game.explosions.iterator();
        while (explodes.hasNext()) {
            int[] pt = (int[]) explodes.next();
            r.drawImage(explode, pt[0], pt[1]);
            explodes.remove();
        }
    }

    public void drawProjectiles(Renderer r) {
        for (Projectile bomb : game.bombs) {
            r.drawImage(bomb.image, bomb.getPosX(), bomb.getPosY());
        }
    }

}
