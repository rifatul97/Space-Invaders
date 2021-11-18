package main.constants;

import java.util.ArrayList;

public interface GameConstants {

    String title = "Space Invaders 2020";
    int GAME_WIDTH = 400, GAME_HEIGHT = 300;
    float GAME_SCALE = 2f;
    double FPS = 1000000000D/60D;
    double MS_PER_UPDATE = 1.0/60.0;


    int playerXposition = (int) (Math.random() * 302);
    int playerYposition = 225;
    int alien_X_move = 2;
    int Alien_Y_move = 2;
    int alien_X_position = 0;
    int alien_Y_position = 0;

}
