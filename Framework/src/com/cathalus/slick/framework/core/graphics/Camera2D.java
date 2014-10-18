package com.cathalus.slick.framework.core.graphics;

import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.math.BoundingBox;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Created by cathalus on 19.09.14.
 */

public class Camera2D extends Entity {

    public enum CameraMode{CHASE,FREE};

    private CameraMode mode = CameraMode.FREE;

    public Camera2D(BoundingBox camera)
    {
        super(camera);
    }

    public void update(GameContainer container, float delta)
    {

    }

    public void render(Graphics graphics) {

    }

    public CameraMode getMode()
    {
        return mode;
    }

    public void setMode(CameraMode value)
    {
        mode = value;
    }

}
