package com.cathalus.games.platformer.entities.components;

import com.cathalus.slick.framework.core.entities.EntityComponent;
import com.cathalus.slick.framework.core.math.MathUtil;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by cathalus on 15.09.14.
 */
public class MovementComponent extends PhysicsComponent {

    public static final String NAME = "MovementComponent";

    private int speed;

    private float jumpCounter = 0.0f;
    private float jumpHeight = 150;
    private float jumpSpeed = 3f;
    private float jumpStartY = 0.0f;
    private Vector2f deltaMovement = new Vector2f();

    public MovementComponent(Vector2f maxVelocity, int movementSpeed)
    {
        super(maxVelocity,Type.DYNAMIC);
        this.speed = movementSpeed;
        this.identifier = NAME;
    }

    @Override
    public void update(float delta, GameContainer container) {
        Input in = container.getInput();
        deltaMovement = new Vector2f(in.isKeyDown(Input.KEY_A) ? -1 : in.isKeyDown(Input.KEY_D) ? 1 : 0, in.isKeyDown(Input.KEY_W) ? 1 : in.isKeyDown(Input.KEY_S) ? -1 : 0);
        deltaMovement.normalise();
        deltaMovement.scale(speed * (delta));
    }

    @Override
    public void onAdd() {

    }

    public Vector2f getDeltaMovement()
    {
        return deltaMovement;
    }
}
