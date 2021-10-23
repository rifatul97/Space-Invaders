package main.Screens;

import main.Events.InputHandler;
import main.Events.MouseHandler;
import main.Events.Renderer;
import main.constants.GameState;
import main.constants.Menu;
import main.constants.Screen;
import main.GameContainer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PauseScreen implements Screen {

    GameContainer gc;
    Menu.PAUSE selectedPauseItem = Menu.PAUSE.Null;
    List<String> menuItems;

    public PauseScreen(GameContainer gc) {
        this.gc = gc;
        menuItems = new ArrayList<>();
        menuItems.add("Resume Game");
        menuItems.add("Exit Game");
    }

    @Override
    public void update() {}

    @Override
    public void draw(Renderer r) {
        int y = 250;
        for (String item : menuItems)
        {
            gc.getWindow().getG().setColor(Color.white);
            if ((item.startsWith(selectedPauseItem.name()))) {
                gc.getWindow().getG().setColor(Color.green);
            }
            gc.getWindow().getG().drawString(item, 300, y);

            y+=20;
        }
    }

    @Override
    public void handleInput(InputHandler input) {

        if(gc.state == GameState.PAUSE) {
            if (selectedPauseItem == Menu.PAUSE.Resume && input.enter.isPressed()) {
                gc.setGameState(GameState.RUNNING);
            } else if (selectedPauseItem == Menu.PAUSE.Exit && input.enter.isPressed()) {
                gc.resetGameScreenState();
                gc.setGameState(GameState.START);
            } else if (selectedPauseItem == Menu.PAUSE.Null) {
                if (input.up.isPressed()) {
                    selectedPauseItem = Menu.PAUSE.Resume;
                } else {
                    selectedPauseItem = Menu.PAUSE.Exit;
                }
            } else if (selectedPauseItem == Menu.PAUSE.Resume) {
                if (input.down.isPressed()) {
                    selectedPauseItem = Menu.PAUSE.Exit;
                }
            } else if (selectedPauseItem == Menu.PAUSE.Exit) {
                if (input.up.isPressed()) {
                    selectedPauseItem = Menu.PAUSE.Resume;
                }
            }
        }
    }

    @Override
    public void handleMouse(MouseHandler mouse) {

    }
}
