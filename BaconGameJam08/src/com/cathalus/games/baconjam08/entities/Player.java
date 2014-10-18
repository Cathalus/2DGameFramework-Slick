package com.cathalus.games.baconjam08.entities;

import com.cathalus.games.baconjam08.components.AABBRenderComponent;
import com.cathalus.games.baconjam08.components.InputComponent;
import com.cathalus.games.baconjam08.components.MovementComponent;
import com.cathalus.slick.framework.core.entities.Entity;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Cathalus on 18.10.2014.
 */
public class Player extends Entity {

    public Player(Vector2f pos, float width, float height, InputComponent inputComponent) {
        super(pos, width, height);
        addComponent(new MovementComponent(100, 400, true));
        addComponent(inputComponent);
        addComponent(new AABBRenderComponent());
    }

}
