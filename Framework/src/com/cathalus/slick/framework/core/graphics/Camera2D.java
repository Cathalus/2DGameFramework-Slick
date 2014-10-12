package com.cathalus.slick.framework.core.graphics;

import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.math.AABB;
import javafx.scene.Camera;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by cathalus on 19.09.14.
 */

public class Camera2D extends Entity {

    public enum CameraMode{CHASE,FREE};

    private CameraMode mode = CameraMode.FREE;

    public Camera2D(AABB camera)
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
