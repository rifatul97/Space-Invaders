package main.Events;

import main.GameContainer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    private GameContainer gc;

    public InputHandler(GameContainer gc) {
        this.gc = gc;
        gc.getWindow().getCanvas().addKeyListener(this);
    }

    public class Key {
        private boolean pressed = false;

        public boolean isPressed () {
            return pressed;
        }

        public void toggle (boolean isPressed) {
            pressed = isPressed;
        }
    }

    public Key left = new Key ();
    public Key right = new Key ();
    public Key space = new Key ();
    public Key up = new Key ();
    public Key enter = new Key();
    public Key down = new Key ();
    public Key p = new Key();

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        toggleKey(keyEvent.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        toggleKey(keyEvent.getKeyCode(), false);
    }

    public void toggleKey(int keyCode, boolean isPressed) {
        if(keyCode == KeyEvent.VK_UP) {
            up.toggle(isPressed);
        }
        if(keyCode == KeyEvent.VK_DOWN) {
            down.toggle(isPressed);
        }
        if(keyCode == KeyEvent.VK_LEFT) {
            left.toggle(isPressed);
        }
        if(keyCode == KeyEvent.VK_RIGHT) {
            right.toggle(isPressed);
        }
        if(keyCode == KeyEvent.VK_SPACE) {
            space.toggle(isPressed);
        }
        if(keyCode == KeyEvent.VK_ENTER) {
            enter.toggle(isPressed);
        }
        if(keyCode == KeyEvent.VK_P) {
            p.toggle(isPressed);
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

}