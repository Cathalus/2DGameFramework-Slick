package com.cathalus.games.platformer.entities;

import com.cathalus.games.platformer.entities.components.MovementComponent;
import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.math.AABB;
import org.newdawn.slick.geom.Vector2f;

public class Player extends Entity {

    public Player(AABB aabb) {
        super(aabb);
    }

    public Player(Vector2f pos, float width, float height) {
        super(pos, width, height);
    }

    public Player initialize()
    {
        // TODO: initialization of components
        addComponent(new MovementComponent(new Vector2f(200,200),200));

        return this;
    }

}
