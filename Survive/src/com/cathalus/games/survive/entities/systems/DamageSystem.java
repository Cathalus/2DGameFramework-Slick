package com.cathalus.games.survive.entities.systems;

import com.cathalus.games.survive.entities.components.ProjectileComponent;
import com.cathalus.games.survive.entities.components.HealthComponent;
import com.cathalus.slick.framework.core.Scene;
import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.entities.systems.GameSystem;
import com.cathalus.slick.framework.core.resources.ResourceManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Iterator;// 4 Seconds TTL

/**
 * Created by cathalus on 23.09.14.
 */
public class DamageSystem extends GameSystem {

    public DamageSystem(Scene scene, String identifier, int priority) {
        super(scene, identifier,priority);
    }

    @Override
    public void update(GameContainer container,HashSet<Entity> range, float delta) {
        Iterator<Entity> it = range.iterator();

        while (it.hasNext()) {

            // Check if anything has been hit by a projectile
            Entity current = it.next();
            if(current.hasComponent(ProjectileComponent.NAME))
            {
                scene.removeEntity(current);
                ProjectileComponent projectileComponent = (ProjectileComponent) current.getComponent(ProjectileComponent.NAME);
                projectileComponent.update(container,delta);
                if(!projectileComponent.isHit()) {
                    handleCollisions(current, projectileComponent);
                    scene.addEntity(current);
                }
            }

            // Check if anything has died
            if(current.hasComponent(HealthComponent.NAME))
            {
                HealthComponent healthComponent = (HealthComponent) current.getComponent(HealthComponent.NAME);
                if(healthComponent.getCurrentHealth() <= 0)
                {
                    // TODO: CRAZY EXPLOSIONS
                    ResourceManager.getSound("blast").play();
                    scene.removeEntity(current);
                }
            }

        }
    }

    private void handleCollisions(Entity current, ProjectileComponent projectileComponent) {
        HashSet<Entity> entities = (HashSet<Entity>) scene.getTree().queryRange(current.getAABB(),new HashSet<Entity>());
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Entity other = it.next();

            if(!other.hasComponent(ProjectileComponent.NAME)) {
                if(other.hasComponent(HealthComponent.NAME))
                {
                    HealthComponent healthComponent = (HealthComponent) other.getComponent(HealthComponent.NAME);
                    healthComponent.changeHealth((int) projectileComponent.getDamage());
                }
                ResourceManager.getSound("impact").play();
                projectileComponent.setHit(true);
            }
        }
    }

    @Override
    public void render(Graphics graphics, HashSet<Entity> range) {

    }
}
