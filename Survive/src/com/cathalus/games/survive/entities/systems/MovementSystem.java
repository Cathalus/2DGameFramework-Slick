package com.cathalus.games.survive.entities.systems;

import com.cathalus.games.survive.entities.components.InputComponent;
import com.cathalus.games.survive.entities.components.MovementComponent;
import com.cathalus.games.survive.entities.components.PhysicsComponent;
import com.cathalus.slick.framework.core.Scene;
import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.entities.systems.GameSystem;
import com.cathalus.slick.framework.core.input.XBOX360;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.Log;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This game system operatos on the Input and MovementComponent
 */
public class MovementSystem extends GameSystem {


    public MovementSystem(Scene scene, String identifier, int priority) {
        super(scene, identifier,priority);
    }

    @Override
    public void update(GameContainer container, HashSet<Entity> range, float delta) {
        Iterator<Entity> it = range.iterator();
        while (it.hasNext()) {
            Entity current = it.next();

            if(current.hasComponent(MovementComponent.NAME))
            {
                scene.removeEntity(current);
                MovementComponent movementComponent = (MovementComponent) current.getComponent(MovementComponent.NAME);

                if(current.hasComponent(InputComponent.NAME))
                {
                    InputComponent inputComponent = (InputComponent) current.getComponent(InputComponent.NAME);
                    // Input Handling
                    if(inputComponent.getInputMethod().equals(InputComponent.InputMethod.CONTROLLER))
                    {
                        // Controller Code
                        movementComponent.setDeltaMovement(new Vector2f(inputComponent.getValue(XBOX360.Axis.THUMB_LEFT_X.getID()),
                                                                        -inputComponent.getValue(XBOX360.Axis.THUMB_LEFT_Y.getID())));
                    }
                }
                movementComponent.update(container,delta);
                Vector2f deltaMovement = movementComponent.getDeltaMovement();

                if(movementComponent.hasCollision())
                    handleWorldCollisions(current, deltaMovement);

                current.setDeltaX(deltaMovement.getX());
                current.setDeltaY(deltaMovement.getY());
                current.updateAABB();

                scene.addEntity(current);
            }
        }
    }

    private void handleWorldCollisions(Entity current, Vector2f deltaMovement) {
        float threshold = 1f;
        Vector2f deltaVec = new Vector2f(deltaMovement.getX(),deltaMovement.getY()).scale(threshold);

        int maxVelocity = Math.max((int) (deltaVec.getX()), (int) (deltaVec.getY()));
        Set<Entity> entities;

        // Resolve y collision
        entities = scene.getTree().queryRange(current.getAABB().expandY(deltaMovement.getY()), new HashSet<Entity>());
        Iterator it = entities.iterator();
        if (it.hasNext()) {
            Entity other = (Entity) it.next();
            if (other.hasComponent(PhysicsComponent.NAME)) {
                if (((PhysicsComponent) other.getComponent(PhysicsComponent.NAME)).getType() == PhysicsComponent.Type.STATIC) {
                    // Remove velocity when touching wall and pushing against it
                    if (current.getAABB().getDistanceY(other.getAABB()) < 0 && deltaVec.getY() > 0) {
                        deltaMovement.set(deltaMovement.getX(), 0);
                    } else if (current.getAABB().getDistanceY(other.getAABB()) > 0 && deltaMovement.getY() < 0) {
                        deltaMovement.set(deltaMovement.getX(), 0);
                    }
                }
            }
        }

        // Resolve x collision
        entities = scene.getTree().queryRange(current.getAABB().expandX(deltaMovement.getX()), new HashSet<Entity>());
        it = entities.iterator();
        // Collision on X
        if (it.hasNext()) {
            Entity other = (Entity) it.next();
            if (other.hasComponent(PhysicsComponent.NAME)) {
                if (((PhysicsComponent) other.getComponent(PhysicsComponent.NAME)).getType() == PhysicsComponent.Type.STATIC) {
                    // Remove velocity when touching wall and pushing against it
                    if (current.getAABB().getDistanceX(other.getAABB()) < 0 && deltaMovement.getX() > 0) {
                        deltaMovement.set(0, deltaMovement.getY());
                    } else if (current.getAABB().getDistanceX(other.getAABB()) > 0 && deltaMovement.getX() < 0) {
                        deltaMovement.set(0, deltaMovement.getY());
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics graphics, HashSet<Entity> range) {

    }
}