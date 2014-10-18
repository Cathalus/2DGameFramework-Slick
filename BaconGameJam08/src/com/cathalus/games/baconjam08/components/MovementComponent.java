package com.cathalus.games.baconjam08.components;

import com.cathalus.slick.framework.core.entities.EntityComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Cathalus on 18.10.2014.
 */
public class MovementComponent extends EntityComponent {

    public static final String NAME = "MovementComponent";

    private float movementSpeed = 0.0f;
    private float speedFactor = 1.0f;
    private Vector2f deltaMovement;
    private boolean handlesCollision;
    private int maxSpeed;

    public MovementComponent(float movementSpeed, int maxSpeed, boolean handlesCollision)
    {
        this.movementSpeed = movementSpeed;
        this.identifier = NAME;
        this.handlesCollision = handlesCollision;
        this.deltaMovement = new Vector2f();
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void update(GameContainer container, float delta) {
        deltaMovement.normalise();
        deltaMovement.scale(movementSpeed*delta*speedFactor);
        speedFactor = 1.0f;
    }

    @Override
    public void onAdd() {

    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public Vector2f getDeltaMovement() {
        return deltaMovement;
    }

    public void setDeltaMovement(Vector2f deltaMovement) {
        this.deltaMovement = deltaMovement;
    }

    public boolean handlesCollision()
    {
        return handlesCollision;
    }

    public void addSpeedFactor(float factor)
    {
        this.speedFactor *= factor;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }
}
