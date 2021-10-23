package main.Screens;

import main.Events.InputHandler;
import main.Events.MouseHandler;
import main.Events.Renderer;
import main.constants.Screen;
import main.GameContainer;

public class GameOverScreen implements Screen {

    protected GameContainer gc;

    GameOverScreen (GameContainer gc) {
        this.gc = gc;
    }
    @Override
    public void update() {

    }

    @Override
    public void draw(Renderer r) {

    }

    @Override
    public void handleInput(InputHandler input) {
        if (input.enter.isPressed()) {

        }
    }

    @Override
    public void handleMouse(MouseHandler mouse) {

    }
}
