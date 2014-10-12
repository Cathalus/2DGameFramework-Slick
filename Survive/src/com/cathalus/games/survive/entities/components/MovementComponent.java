package com.cathalus.games.survive.entities.components;

import com.cathalus.slick.framework.core.entities.EntityComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by cathalus on 23.09.14.
 */
public class MovementComponent extends EntityComponent {

    public static final String NAME = "MovementComponent";

    private float maxVelocity;
    private float velocity;
    private Vector2f deltaMovement = new Vector2f();
    private boolean collision = false;

    public MovementComponent(boolean collision, float velocity, float maxVelocity)
    {
        this.collision = collision;
        this.velocity = velocity;
        this.maxVelocity = maxVelocity;
        this.identifier = NAME;
    }

    public MovementComponent(boolean collision, Vector2f direction, float velocity, float maxVelocity)
    {
        this(collision,velocity,maxVelocity);
        this.deltaMovement = direction;
    }

    public void setDeltaMovement(Vector2f deltaMovement)
    {
        this.deltaMovement = deltaMovement;
    }

    public void addDeltaMovement(Vector2f deltaMovement) { this.deltaMovement.add(deltaMovement);}

    public Vector2f getDeltaMovement()
    {
        return this.deltaMovement;
    }

    @Override
    public void update(GameContainer container,float delta) {
        this.deltaMovement.normalise();
        this.deltaMovement.scale(delta*velocity);
    }

    @Override
    public void onAdd() {

    }

    public boolean hasCollision()
    {
        return collision;
    }
}
