package main.constants;

import main.Events.MouseHandler;
import main.Events.Renderer;
import main.Events.InputHandler;

public interface Screen
{
    void update();
    void draw(Renderer r);
    void handleInput(InputHandler input);
    void handleMouse(MouseHandler mouse);
}