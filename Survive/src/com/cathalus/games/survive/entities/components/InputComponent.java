package com.cathalus.games.survive.entities.components;

import com.cathalus.slick.framework.core.entities.EntityComponent;
import com.cathalus.slick.framework.core.input.XBOX360;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;

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
    private Keyboard keyboard;
    private Mouse mouse;

    public InputComponent(Controller controller)
    {
        this.method = InputMethod.CONTROLLER;
        this.controller = controller;
        controller.setDeadZone(XBOX360.Axis.THUMB_LEFT_X.getID(),0.15f);
        controller.setDeadZone(XBOX360.Axis.THUMB_LEFT_Y.getID(),0.15f);
        this.identifier = NAME;
    }

    public InputComponent(Mouse mouse)
    {
        this.method = InputMethod.MOUSE;
        this.mouse = mouse;
        this.identifier = NAME;
    }

    public InputComponent(Keyboard keyboard)
    {
        this.method = InputMethod.KEYBOARD;
        this.keyboard = keyboard;
        this.identifier = NAME;
    }

    public InputComponent(Mouse mouse, Keyboard keyboard)
    {
        this.method = InputMethod.MOUSE_KEYBOARD;
        this.mouse = mouse;
        this.keyboard = keyboard;
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
                return mouse.isButtonDown(id);
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
                return keyboard.isKeyDown(id);
        }
        return false;
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
