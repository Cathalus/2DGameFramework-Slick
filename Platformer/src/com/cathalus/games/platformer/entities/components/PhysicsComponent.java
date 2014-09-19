package com.cathalus.games.platformer.entities.components;

import com.cathalus.slick.framework.core.entities.EntityComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

import java.util.Vector;

/**
 * Created by cathalus on 19.09.14.
 */
public class PhysicsComponent extends EntityComponent {

    public static final String NAME = "PhysicsComponent";

    protected Vector2f maxVelocity;
    public static enum Type { STATIC, DYNAMIC }

    protected Type type;

    public PhysicsComponent(Vector2f maxVelocity, Type type)
    {
        this.type = type;
        this.identifier = NAME;
    }

    @Override
    public void update(float delta, GameContainer container) {

    }

    @Override
    public void onAdd() {

    }

    public Type getType()
    {
        return type;
    }
}
