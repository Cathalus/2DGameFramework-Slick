package com.cathalus.games.survive.entities.systems;

import com.cathalus.slick.framework.core.Scene;
import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.entities.systems.GameSystem;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by cathalus on 23.09.14.
 */
public class RenderingSystem extends GameSystem {

    public RenderingSystem(Scene scene, String identifier, int priority) {
        super(scene, identifier,priority);
    }

    @Override
    public void update(GameContainer container,HashSet<Entity> range, float delta) {

    }

    @Override
    public void render(Graphics graphics, HashSet<Entity> range) {

        Iterator<Entity> it = range.iterator();
        while (it.hasNext()) {
            Entity current = it.next();
            current.render(graphics);
        }
    }
}
