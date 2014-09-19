package com.cathalus.slick.framework.core.graphics;

import com.cathalus.slick.framework.core.math.AABB;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by cathalus on 19.09.14.
 */
public class Camera2D {

    private AABB camera;

    public Camera2D(AABB camera)
    {
        this.camera = camera;
    }

    public AABB getAABB()
    {
        return camera;
    }

}
