package com.cathalus.games.baconjam08.systems;

import com.cathalus.games.baconjam08.components.AABBRenderComponent;
import com.cathalus.games.baconjam08.components.AIComponent;
import com.cathalus.games.baconjam08.components.MovementComponent;
import com.cathalus.games.baconjam08.components.SpawnComponent;
import com.cathalus.slick.framework.core.Scene;
import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.entities.systems.GameSystem;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Cathalus on 18.10.2014.
 */
public class SpawningSystem extends GameSystem {

    public static int enemies = 0;

    public SpawningSystem(Scene scene, String identifier, int priority) {
        super(scene, identifier, priority);
    }

    @Override
    public void update(GameContainer container, HashSet<Entity> range, float delta) {
        Iterator<Entity> it = range.iterator();
        while (it.hasNext()) {
            Entity current = it.next();

            if(current.hasComponent(SpawnComponent.NAME))
            {
                AABBRenderComponent render = null;
                SpawnComponent spawnComponent = (SpawnComponent) current.getComponent(SpawnComponent.NAME);
                spawnComponent.update(container,delta);
                if(!spawnComponent.onCooldown())
                {
                    for(int i = 0; i < 7; i++) {
                        // Spawn enemies
                        Random rand = new Random();
                        Entity enemy = new Entity(new Vector2f(current.getX(), -current.getY()), 5, 5);
                        float angle = (float) (rand.nextFloat() * (Math.PI * 2));
                        MovementComponent movementComponent = new MovementComponent(5, 100, true);
                        movementComponent.setDeltaMovement(new Vector2f((float) Math.cos(angle), (float) Math.sin(angle)));
                        AABBRenderComponent renderComponent = new AABBRenderComponent();
                        renderComponent.setColor(Color.green);
                        enemy.addComponent(movementComponent);
                        enemy.addComponent(renderComponent);
                        enemy.addComponent(new AIComponent());
                        scene.addEntity(enemy);
                        enemies++;
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics graphics, HashSet<Entity> range) {

    }
}
