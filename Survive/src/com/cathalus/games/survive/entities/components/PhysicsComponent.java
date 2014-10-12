package com.cathalus.games.survive.entities.components;

import com.cathalus.slick.framework.core.entities.EntityComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by cathalus on 19.09.14.
 */
public class PhysicsComponent extends EntityComponent {

    public static final String NAME = "PhysicsComponent";

    public static enum Type { STATIC, DYNAMIC }

    protected Type type;

    public PhysicsComponent(Type type)
    {
        this.type = type;
        this.identifier = NAME;
    }

    @Override
    public void update(GameContainer container, float delta) {

    }

    @Override
    public void onAdd() {

    }

    public Type getType()
    {
        return type;
    }
}
