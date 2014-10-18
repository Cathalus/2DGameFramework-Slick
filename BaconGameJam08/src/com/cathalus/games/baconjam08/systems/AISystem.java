package com.cathalus.games.baconjam08.systems;

import com.cathalus.games.baconjam08.components.AIComponent;
import com.cathalus.games.baconjam08.components.MovementComponent;
import com.cathalus.games.baconjam08.util.Globals;
import com.cathalus.games.baconjam08.util.Util;
import com.cathalus.slick.framework.core.Scene;
import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.entities.systems.GameSystem;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Cathalus on 18.10.2014.
 */
public class AISystem extends GameSystem {

    private static float desiredSeparation = 15.0f;
    private static float desiredNeighbourDistance = 1000;
    private static float maxForce = 1000f;

    public AISystem(Scene scene, String identifier, int priority) {
        super(scene, identifier, priority);
    }

    @Override
    public void update(GameContainer container, HashSet<Entity> range, float delta) {
        Iterator<Entity> it = range.iterator();
        while (it.hasNext()) {
            Entity current = it.next();

            if(current.hasComponent(AIComponent.NAME))
            {
                if(current.hasComponent(MovementComponent.NAME))
                {
                    AIComponent aiComponent = (AIComponent) current.getComponent(AIComponent.NAME);
                    MovementComponent movementComponent = (MovementComponent) current.getComponent(MovementComponent.NAME);

                    //HashSet<Entity> currentRange = (HashSet<Entity>) scene.getTree().queryRange(current.getAABB().expand(100), new HashSet<Entity>());

                    flock(range,movementComponent, current);
                    // TODO: Update

                }
            }

        }
    }

    private void flock(HashSet<Entity> range, MovementComponent movement, Entity current) {
        Entity[] entityRange = new Entity[range.size()];
        range.toArray(entityRange);
        Vector2f separation = separate(entityRange,movement, current);
        Vector2f alignment  = align(entityRange,movement, current);
        Vector2f cohesion   = cohere(entityRange,movement, current);

        movement.getDeltaMovement().add(separation);
        movement.getDeltaMovement().add(alignment);
        movement.getDeltaMovement().add(cohesion);
    }

    private Vector2f cohere(Entity[] range, MovementComponent movement, Entity current) {
        Vector2f cohesion = new Vector2f();
        int count = 0;
        for(int i = 0; i < range.length; i++)
        {
            Entity other = range[i];
            if(current.equals(other))
                continue;
            if (other.hasComponent(AIComponent.NAME)) {
                float dist = current.getAABB().getCenter().distance(other.getAABB().getCenter());
                if(dist < desiredNeighbourDistance)
                {
                    // seek for other center
                    //cohesion.add(other.getAABB().getCenter());
                    cohesion.add(Globals.CURRENT_PLAYER.getAABB().getCenter());
                    count++;
                }
            }
        }
        if(count > 0)
        {
            cohesion.set(cohesion.getX()/count,cohesion.getY()/count);
            return seek(current.getAABB().getCenter(), cohesion, movement);
        }
        return new Vector2f();
    }

    private Vector2f seek(Vector2f current, Vector2f target, MovementComponent movementComponent) {
        Vector2f desired = target.sub(current);
        desired.normalise();
        desired.scale(movementComponent.getMaxSpeed());

        Vector2f steering = desired.sub(movementComponent.getDeltaMovement());
        System.out.println("Steering = "+steering);
        //Util.clampVector(steering,-maxForce,maxForce);
        return steering;
    }

    private Vector2f align(Entity[] range, MovementComponent movement, Entity current) {
        Vector2f alignment = new Vector2f();
        int count = 0;
        for(int i = 0; i < range.length; i++)
        {
            Entity other = range[i];
            if(current.equals(other))
                continue;
            if (other.hasComponent(AIComponent.NAME)) {
                float dist = current.getAABB().getCenter().distance(other.getAABB().getCenter());
                if (dist < desiredNeighbourDistance) {
                    MovementComponent component = (MovementComponent) other.getComponent(MovementComponent.NAME);
                    alignment.add(component.getDeltaMovement());
                    count++;
                }
            }
        }
        if (count > 0) {
            alignment.set(alignment.getX()/count, alignment.getY()/count);
            alignment.normalise();
            alignment.scale(movement.getMaxSpeed());
            Vector2f steering = alignment.sub(movement.getDeltaMovement());
            return steering;
        }
        return alignment;
    }

    // Checks for nearby entities and steers away
    private Vector2f separate(Entity[] range, MovementComponent movement, Entity current) {
        Vector2f separation = new Vector2f();

        // Number of boids that were too close
        int count = 0;

        for(int i = 0; i < range.length; i++)
        {
            Entity other = range[i];
            if(current.equals(other))
                continue;

            if (other.hasComponent(AIComponent.NAME)) {

                // Distance between current entity and other
                float dist = current.getAABB().getCenter().distance(other.getAABB().getCenter());
                if (dist < desiredSeparation) {
                    Vector2f diff = current.getAABB().getCenter().sub(other.getAABB().getCenter());
                    diff.normalise();
                    diff.set(diff.getX() / dist, diff.getY() / dist);
                    separation.add(diff);
                    count++;
                }
            }

        }
        if (count > 0)
            separation.set(separation.getX() / count, separation.getY() / count);

        if (separation.length() > 0) {
            // TODO: fix bug here
            separation.normalise();
            separation.scale(movement.getMaxSpeed());
            separation.sub(movement.getDeltaMovement());
        }

        return separation;
    }


    @Override
    public void render(Graphics graphics, HashSet<Entity> range) {

    }
}
