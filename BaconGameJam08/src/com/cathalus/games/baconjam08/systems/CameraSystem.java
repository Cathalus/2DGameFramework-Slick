package com.cathalus.games.baconjam08.systems;

import com.cathalus.slick.framework.core.Scene;
import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.entities.systems.GameSystem;
import com.cathalus.slick.framework.core.graphics.Camera2D;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

import java.util.HashSet;

/**
 * Created by cathalus on 28.09.14.
 */
public class CameraSystem extends GameSystem {

    private Entity chaseEntity;

    public CameraSystem(Scene scene, String identifier, int priority) {
        super(scene, identifier, priority);
    }

    @Override
    public void update(GameContainer container, HashSet<Entity> range, float delta) {
        // Camera Movement
        Camera2D current = scene.getActiveCamera();
        if(current != null) {
            Vector2f deltaMovement;
            switch(current.getMode())
            {
                case CHASE:
                    if(chaseEntity == null)
                    {
                        current.setMode(Camera2D.CameraMode.FREE);
                    }else {
                        float dX = current.getAABB().getCenterX() - chaseEntity.getAABB().getCenterX();
                        float dY = current.getAABB().getCenterY() - chaseEntity.getAABB().getCenterY();
                        deltaMovement = new Vector2f(-dX, -dY);
                        current.setDelta(deltaMovement);
                        current.updateAABB();
                    }
                    break;
                case FREE:
                    Input in = container.getInput();
                    deltaMovement = new Vector2f(in.isKeyDown(Input.KEY_LEFT) ? -1 : in.isKeyDown(Input.KEY_RIGHT) ? 1 : 0, in.isKeyDown(Input.KEY_UP) ? 1 : in.isKeyDown(Input.KEY_DOWN) ? -1 : 0);
                    deltaMovement.normalise();
                    deltaMovement.scale(50 * (delta));
                    current.setDeltaX(deltaMovement.getX());
                    current.setDeltaY(deltaMovement.getY());
                    current.updateAABB();
                    break;
            }
            Input in = container.getInput();
            if(in.isKeyDown(Input.KEY_F9))
            {
                current.setMode(Camera2D.CameraMode.FREE);
            }else if(in.isKeyDown(Input.KEY_F10))
            {
                current.setMode(Camera2D.CameraMode.CHASE);
            }
        }

    }

    @Override
    public void render(Graphics graphics, HashSet<Entity> range) {
        if(scene.getActiveCamera() != null)
            graphics.translate(-scene.getActiveCamera().getAABB().getMinX(),scene.getActiveCamera().getAABB().getMaxY());
    }

    public void setCameraMode(Camera2D.CameraMode mode)
    {
        Camera2D camera = scene.getActiveCamera();
        if(camera != null)
        {
            camera.setMode(mode);
        }
    }

    public void setChaseEntity(Entity entity)
    {
        chaseEntity = entity;
    }
}
