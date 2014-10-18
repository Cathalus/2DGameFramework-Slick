package com.cathalus.games.baconjam08.components;

import com.cathalus.slick.framework.core.entities.EntityComponent;
import com.cathalus.slick.framework.core.input.XBOX360;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by cathalus on 23.09.14.
 */
public class InputComponent extends EntityComponent {

    public static final String NAME = "InputComponent";

    public static enum InputMethod{
        KEYBOARD, MOUSE, CONTROLLER, MOUSE_KEYBOARD;
    }

    private InputMethod method;
    private Controller controller;

    public InputComponent(Controller controller)
    {
        this.method = InputMethod.CONTROLLER;
        this.controller = controller;
        controller.setDeadZone(XBOX360.Axis.THUMB_LEFT_X.getID(),0.15f);
        controller.setDeadZone(XBOX360.Axis.THUMB_LEFT_Y.getID(),0.15f);
        this.identifier = NAME;
    }

    public InputComponent(InputMethod method)
    {
        this.method = method;

        this.identifier = NAME;
    }


    @Override
    public void update(GameContainer container, float delta) {

    }

    @Override
    public void onAdd() {

    }

    public InputMethod getInputMethod()
    {
        return method;
    }

    public boolean isButtonDown(int id)
    {
        switch(method)
        {
            case CONTROLLER:
                return controller.isButtonPressed(id);
            case MOUSE:
            case MOUSE_KEYBOARD:
                return Mouse.isButtonDown(id);
        }
        return false;
    }

    public boolean isKeyPressed(int id)
    {
        switch (method)
        {
            case CONTROLLER:
                return false;
            case MOUSE:
                return false;
            case KEYBOARD:
            case MOUSE_KEYBOARD:
                return Keyboard.isKeyDown(id);
        }
        return false;
    }

    public Vector2f getPosition()
    {
        switch(method)
        {
            case MOUSE:
            case MOUSE_KEYBOARD:
                return new Vector2f(Mouse.getX(),Mouse.getY());
            default:
                return new Vector2f();
        }
    }

    public float getValue(int axis)
    {
        switch (method)
        {
            case CONTROLLER:
                return controller.getAxisValue(axis);
            default:
                return 0.0f;
        }
    }

    public void rumble(float intensity)
    {
        controller.setRumblerStrength(0,intensity);
    }
}
