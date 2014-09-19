package com.cathalus.games.platformer.systems;

import com.cathalus.games.platformer.entities.components.GraphicsComponent;
import com.cathalus.slick.framework.core.Scene;
import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.entities.systems.GameSystem;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by cathalus on 19.09.14.
 */
public class RenderingSystem extends GameSystem {

    public RenderingSystem(Scene scene, String identifier) {
        super(scene, identifier);
    }

    @Override
    public void update(float delta, GameContainer container) {

    }

    @Override
    public void render(Graphics graphics) {
        Set<Entity> entities = scene.getTree().queryRange(scene.getActiveCamera().getAABB(), new HashSet<Entity>());

        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Entity current = (Entity) it.next();
            GraphicsComponent graphicsComponent = (GraphicsComponent) current.getComponent("GraphicsComponent");
            if(graphicsComponent != null)
            {
                graphicsComponent.render(graphics);
            }
        }
    }
}
