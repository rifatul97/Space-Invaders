package main.Screens;

import main.Events.InputHandler;
import main.Events.MouseHandler;
import main.Events.Renderer;
import main.constants.*;
import main.constants.Menu;
import main.gdx.Image;
import main.GameContainer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class StartScreen implements Screen {

    ArrayList<String> menuItems;
    GameContainer gc;
    Menu.MAIN selectedItem = Menu.MAIN.Start;
    BufferedImage titlelogo = Resource.getImage("Logo");

    public StartScreen(GameContainer gc) throws IOException {
        this.gc = gc;
        menuItems = new ArrayList<>();
        menuItems.add("Start Game");
        menuItems.add("LeaderBoards");
        menuItems.add("Exit");
    }

    @Override
    public void update() {}

    @Override
    public void draw(Renderer renderer) {
        int y = 250;
        for (String item : menuItems)
        {
            gc.getWindow().getG().setColor(Color.white);
            if ((item.startsWith(selectedItem.name()))) {
                gc.getWindow().getG().setColor(Color.green);
            }
            gc.getWindow().getG().drawString(item, 300, y);

            y+=20;
        }
        gc.getWindow().getG().drawImage(titlelogo, 242, 10, null);
    }

    @Override
    public void handleInput(InputHandler input) {
        if (input.enter.isPressed() && selectedItem == Menu.MAIN.Exit) {
            gc.stop();
        }

        else if (input.enter.isPressed() && selectedItem == Menu.MAIN.Start) {
            gc.setGameState(GameState.RUNNING);

        }

        if (input.up.isPressed()) {
            if (selectedItem == Menu.MAIN.Exit) {
                selectedItem = Menu.MAIN.Start;
            }
        }

        if (input.down.isPressed()) {
            if (selectedItem == Menu.MAIN.Start) {
                selectedItem = Menu.MAIN.Exit;
            }
        }
    }

    @Override
    public void handleMouse(MouseHandler mouse) {

    }

}
