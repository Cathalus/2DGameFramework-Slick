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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Cathalus on 18.10.2014.
 */
public class AISystem extends GameSystem {

    private float acc = 0.0f;

    public AISystem(Scene scene, String identifier, int priority) {
        super(scene, identifier, priority);
    }

    @Override
    public void update(GameContainer container, HashSet<Entity> range, float delta) {
        acc += delta;
        if(acc > 1/updatesPerSecond) {
            ArrayList<Entity> aiEntities = new ArrayList<Entity>();

            Iterator<Entity> it = range.iterator();
            while (it.hasNext()) {
                Entity current = it.next();

                if (current.hasComponent(AIComponent.NAME)) {
                    AIComponent currentAIComponent = (AIComponent) current.getComponent(AIComponent.NAME);
                    if (current.hasComponent(MovementComponent.NAME) && currentAIComponent.getTimeAlive() > 0.5f) {
                        aiEntities.add(current);
                    }
                    currentAIComponent.update(container,delta);
                }
            }

            Vector2f cohesion, separation, alignment, targetDelta;
            for(Entity e : aiEntities) {
                MovementComponent currentComponent = (MovementComponent) e.getComponent(MovementComponent.NAME);

                cohesion = cohere(e,aiEntities);
                separation = separate(e,aiEntities);
                alignment = align(e,currentComponent,aiEntities);
                targetDelta = target(e, Globals.CURRENT_PLAYER.getAABB().getCenter());

                cohesion.scale(delta);
                separation.scale(delta);
                alignment.scale(delta);

                currentComponent.getDeltaMovement().add(cohesion);
                currentComponent.getDeltaMovement().add(separation);
                currentComponent.getDeltaMovement().add(alignment);
                Util.clampVector(currentComponent.getDeltaMovement(),-currentComponent.getMaxSpeed()*delta,currentComponent.getMaxSpeed()*delta);
            }

            acc = 0;
        }
    }

    private Vector2f target(Entity e, Vector2f center) {
        center.sub(e.getAABB().getCenter());
        center.scale(1/100.0f);
        return center;
    }

    private Vector2f cohere(Entity current, ArrayList<Entity> aiEntities) {
        if(aiEntities.size() > 1)
        {
            Vector2f flockCenter = Globals.CURRENT_PLAYER.getAABB().getCenter();
            /*
            for(Entity e : aiEntities)
            {
                if(!current.equals(e))
                {
                    flockCenter.add(e.getAABB().getCenter());
                }
            }
            flockCenter.scale(1.0f / (aiEntities.size() - 1));*/
            Vector2f result = new Vector2f(flockCenter.getX(),flockCenter.getY());
            result.sub(current.getAABB().getCenter());
            result.scale(5 / 100.0f);
            return result;
        }
        return new Vector2f();
    }

    private Vector2f separate(Entity current, ArrayList<Entity> aiEntities) {
        Vector2f distance = new Vector2f();
        for(Entity e : aiEntities)
        {
            if(!current.equals(e))
            {
                if(current.getAABB().getCenter().distance(e.getAABB().getCenter()) < 10)
                {
                    Vector2f temp = current.getAABB().getCenter().sub(e.getAABB().getCenter());
                    distance.add(temp);
                }
            }
        }
        return distance;
    }

    private Vector2f align(Entity current, MovementComponent currentComponent, ArrayList<Entity> aiEntities) {
        if(aiEntities.size() > 1) {
            Vector2f perceivedVelocity = new Vector2f();
            for (Entity e : aiEntities) {
                if (!current.equals(e)) {
                    MovementComponent component = (MovementComponent) e.getComponent(MovementComponent.NAME);
                    perceivedVelocity.add(component.getDeltaMovement());
                }
            }
            perceivedVelocity.set(perceivedVelocity.getX() / (aiEntities.size() - 1),
                    perceivedVelocity.getY() / (aiEntities.size() - 1));
            return new Vector2f((perceivedVelocity.getX() - currentComponent.getDeltaMovement().getX()) / 8.0f,
                    (perceivedVelocity.getY() - currentComponent.getDeltaMovement().getY()) / 8.0f);
        }
        return new Vector2f();
    }

    @Override
    public void render(Graphics graphics, HashSet<Entity> range) {

    }
}
