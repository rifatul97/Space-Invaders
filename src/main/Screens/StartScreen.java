package main.Screens;

import main.Events.InputHandler;
import main.Events.MouseHandler;
import main.Events.Renderer;
import main.GameContainer;
import main.constants.MenuItem;
import main.constants.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class StartScreen implements Screen {

    private final ArrayList<String> menuItems = new MenuItems().getMenuItemAsList();
    private final GameContainer gc;
    private final BufferedImage gameLogo = Resource.getImage("Logo");
    private boolean DownPressed;
    private boolean UpPressed;
    private MenuItem currentSelected;

    public StartScreen(GameContainer gc) throws IOException {
        this.gc = gc;
        currentSelected = new MenuItems().getMenuItems();
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Renderer r) {
        ///gc.getWindow().getG().setFont();
        int y = 250;
        for (String item : menuItems) {
            gc.getWindow().getG().setColor(Color.white);
            if ((item.startsWith(currentSelected.getName()))) {
                gc.getWindow().getG().setColor(Color.green);
            }
            gc.getWindow().getG().drawString(item, 300, y);

            y += 30;
        }
        gc.getWindow().getG().drawImage(gameLogo, 242, 10, null);
        gc.getWindow().getG().setColor(Color.LIGHT_GRAY);
        gc.getWindow().getG().drawString("Programmed By Rifatul Karim", 250, 575);
        gc.getWindow().getG().setColor(Color.WHITE);
    }


    @Override
    public void handleInput(InputHandler input) {

        if (input.down.isPressed()) {
            if (!DownPressed) {
                DownPressed = true;
                Resource.getSound("navsound").play();
                currentSelected = currentSelected.getNext();
            }
        } else {
            DownPressed = false;
        }

        if (input.up.isPressed()) {
            if (!UpPressed) {
                UpPressed = true;
                Resource.getSound("navsound").play();
                currentSelected = currentSelected.getPrev();
            }
        } else {
            UpPressed = false;
        }

        if (input.enter.isPressed()) {
            switch (currentSelected.getName()) {
                case "Start Game" -> {
                    gc.setGameState(GameState.RUNNING);
                }
                case "Scoreboard" -> {
                    gc.setGameState(GameState.SCOREBOARD);
                }
                case "Exit Game" -> {
                    gc.stop();
                }
            }

        }
    }

    @Override
    public void handleMouse(MouseHandler mouse) {

    }

}
