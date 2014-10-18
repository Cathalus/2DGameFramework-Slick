package com.cathalus.games.baconjam08.entities;

import com.cathalus.games.baconjam08.components.PhysicsComponent;
import com.cathalus.slick.framework.core.entities.Entity;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Cathalus on 18.10.2014.
 */
public class MapObject extends Entity {

    public MapObject(Vector2f pos, float width, float height, boolean collision) {
        super(pos, width, height);
        if(collision) {
            addComponent(new PhysicsComponent(PhysicsComponent.Type.STATIC));
        }
    }
}
