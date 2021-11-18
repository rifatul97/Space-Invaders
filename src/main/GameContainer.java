package main;

import main.Events.InputHandler;
import main.Events.Renderer;
import main.Screens.GameScreen;
import main.Screens.PauseScreen;
import main.Screens.ScoreboardScreen;
import main.Screens.StartScreen;
import main.constants.GameConstants;
import main.constants.GameState;
import main.constants.Screen;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameContainer implements Runnable{

    GameManager game;
    Renderer r;
    Thread thread;
    Window window;
    Renderer renderer;
    InputHandler input;
    //main.Events.InputHandler input;
    boolean running;
    int gameFps;
    public GameState state = GameState.START;
    Map<GameState, Screen> screens = new HashMap<>();


    public GameContainer (GameManager game) {
        this.game = game;
    }

    public void addScreen(GameState state, Screen screen)
    {
        screens.put(state, screen);
    }

    public synchronized void start () throws IOException {
        window = new Window(this);
        renderer = new Renderer(this);
        input = new InputHandler(this);
        screens.put(GameState.START, new StartScreen(this));
        screens.put(GameState.PAUSE, new PauseScreen(this));
        screens.put(GameState.SCOREBOARD, new ScoreboardScreen(this));
        screens.put(GameState.RUNNING, new GameScreen(this));
        thread = new Thread(this);
        running = true;
        thread.run();
    }

    public void run()
    {
        Screen screen = screens.get(state);
        boolean render = false;
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;


        screen.update();

        window.update();

        while (running) {
            screen = screens.get(state);
            render = false;

            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while (unprocessedTime >= GameConstants.MS_PER_UPDATE) {
                unprocessedTime -= GameConstants.MS_PER_UPDATE;
                render = true;

                //TODO: Update the Game
                //game.update(this, (float) Constants.MS_PER_UPDATE);
                screen.update();
                screen.handleInput(input);
                if (frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                    //System.out.println("FPS: " + fps);
                    gameFps = fps;
                }
            }

            if (render)
            {
                //TODO: Render the Game
                renderer.clear();
                screen.draw(renderer);
                window.update();
                //game.render(this, renderer);
                //window.update();
                //render();
                frames++;
            }

            else {
                try {
                    thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void stop () {
        running = false;
        System.exit(1);
    }


    public Window getWindow() {
        return window;
    }

    public Renderer getRenderer () { return renderer; }

    public void setGameState (GameState state) {
        this.state = state;
    }

    public void resetGameScreenState () {
        this.screens.remove(GameState.RUNNING);
        this.screens.put(GameState.RUNNING, new GameScreen(this));
    }

    public int getWidth() {
        return GameConstants.GAME_WIDTH;
    }

    public int getHeight() {
        return GameConstants.GAME_HEIGHT;
    }

    public float getScale() { return GameConstants.GAME_SCALE; }
}
