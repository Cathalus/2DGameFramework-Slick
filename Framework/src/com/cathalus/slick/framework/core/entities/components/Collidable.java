package com.cathalus.slick.framework.core.entities.components;

import com.cathalus.slick.framework.core.entities.Entity;

/**
 * Created by cathalus on 17.09.14.
 */
public interface Collidable {

    public void onCollision(Entity collider);

}
