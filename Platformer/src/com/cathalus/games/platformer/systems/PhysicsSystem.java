package com.cathalus.games.platformer.systems;

import com.cathalus.games.platformer.entities.components.MovementComponent;
import com.cathalus.games.platformer.entities.components.PhysicsComponent;
import com.cathalus.slick.framework.core.Scene;
import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.entities.systems.GameSystem;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.Log;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by cathalus on 19.09.14.
 */
public class PhysicsSystem extends GameSystem {
    public PhysicsSystem(Scene scene, String identifier) {
        super(scene, identifier);
        this.priority = 5;
    }

    @Override
    public void update(float delta, GameContainer container) {
        // DO collision checking etc. here
        Set<Entity> entities = scene.getTree().queryRange(scene.getActiveCamera().getAABB(), new HashSet<Entity>());

        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Entity current = (Entity) it.next();

            scene.removeEntity(current);
            if(current.hasComponent("MovementComponent"))
            {
                MovementComponent movementComponent = (MovementComponent) current.getComponent("MovementComponent");
                movementComponent.update(delta,container);

                Vector2f deltaMovement = movementComponent.getDeltaMovement();
                checkForCollisions(current, deltaMovement);
            }

            scene.addEntity(current);
        }
    }

    private void checkForCollisions(Entity current, Vector2f deltaMovement) {

        float threshold = 7f;
        Vector2f deltaVec = new Vector2f(deltaMovement.getX(),deltaMovement.getY()).scale(threshold);

        int maxVelocity = Math.max((int) (deltaVec.getX()),(int) (deltaVec.getY()));
        Set<Entity> entities = scene.getTree().queryRange(current.getAABB().expand(Math.max(maxVelocity,threshold)), new HashSet<Entity>());
        Iterator<Entity> iterator = entities.iterator();

        boolean collision = false;
        // When Collision has occured
        while(iterator.hasNext())
        {
            Entity other = (Entity) iterator.next();
            if(other.hasComponent(PhysicsComponent.NAME)) {
                if(((PhysicsComponent)other.getComponent(PhysicsComponent.NAME)).getType() == PhysicsComponent.Type.STATIC) {
                    // Resolve y collision
                    entities = scene.getTree().queryRange(current.getAABB().expandY(deltaVec.getY()), new HashSet<Entity>());
                    Iterator it = entities.iterator();
                    if (it.hasNext()) {
                        // Remove velocity when touching wall and pushing against it
                        if(current.getAABB().getDistanceY(other.getAABB()) < 0 && deltaVec.getY() > 0)
                        {
                            deltaMovement.set(deltaMovement.getX(), 0);
                        }else if(current.getAABB().getDistanceY(other.getAABB()) > 0 && deltaMovement.getY() < 0)
                        {
                            deltaMovement.set(deltaMovement.getX(), 0);
                        }
                    }

                    // Resolve x collision
                    entities = scene.getTree().queryRange(current.getAABB().expandX(deltaMovement.getX()), new HashSet<Entity>());
                    it = entities.iterator();
                    // Collision on X
                    if (it.hasNext()) {
                        // Remove velocity when touching wall and pushing against it
                        if(current.getAABB().getDistanceX(other.getAABB()) < 0 && deltaMovement.getX() > 0)
                        {
                            deltaMovement.set(0, deltaMovement.getY());
                        }else if(current.getAABB().getDistanceX(other.getAABB()) > 0 && deltaMovement.getX() < 0)
                        {
                            deltaMovement.set(0, deltaMovement.getY());
                        }
                    }

                    //current.setDeltaX(deltaMovement.getX());
                    //current.setDeltaY(deltaMovement.getY());
                    //current.updateAABB();
                }
            }
        }

        current.setDeltaX(deltaMovement.getX());
        current.setDeltaY(deltaMovement.getY());
        current.updateAABB();
    }

    @Override
    public void render(Graphics graphics) {

    }
}
