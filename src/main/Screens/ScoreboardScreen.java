package main.Screens;

import main.Events.InputHandler;
import main.Events.MouseHandler;
import main.Events.Renderer;
import main.GameContainer;
import main.constants.GameState;
import main.constants.Screen;
import main.gdx.Image;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreboardScreen implements Screen {

    GameContainer gc;
    Image backgroundImage;
    public Long[] highscores = new Long[5];
    public ScoreboardScreen(GameContainer gc) {
        this.gc = gc;
        backgroundImage = new Image("wallpaper");
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Renderer r) {
        r.drawImage(backgroundImage, 0, 0);
        gc.getWindow().getG().setColor(Color.red);
        gc.getWindow().getG().drawString("RANK", 100, 170);
        gc.getWindow().getG().drawString("SCORE", 350, 170);
        gc.getWindow().getG().drawString("WAVE", 600, 170);
        for (int i = 0, y = 240; i < highscores.length; i++, y+= 30) {
            gc.getWindow().getG().setColor(Color.RED);
            gc.getWindow().getG().drawString(i+1 + "", 100, y);
            gc.getWindow().getG().setColor(Color.CYAN);
            gc.getWindow().getG().drawString(highscores[i] + "", 355, y);
            gc.getWindow().getG().drawString(10 + "", 615, y);
        }
        gc.getWindow().getG().setColor(Color.WHITE);
    }

    @Override
    public void handleInput(InputHandler input) {
        if(input.space.isPressed()) {
            gc.setGameState(GameState.RUNNING);
        }

    }

    @Override
    public void handleMouse(MouseHandler mouse) {

    }
}
