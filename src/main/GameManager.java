package main;

import java.io.IOException;

public class GameManager {

    public static void main(String[] args) throws IOException {
        GameManager game = new GameManager();
        GameContainer gc = new GameContainer(game);
        gc.start();
    }

}