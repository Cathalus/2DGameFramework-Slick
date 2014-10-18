package com.cathalus.slick.framework.core.entities.systems;

import com.cathalus.slick.framework.core.Scene;
import com.cathalus.slick.framework.core.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.HashSet;

/**
 * Created by cathalus on 17.09.14.
 */
public abstract class GameSystem implements Comparable<GameSystem> {

    protected String identifier;
    protected boolean isActive = false;
    protected int priority = 0;
    protected Scene scene;
    protected int updatesPerSecond = 0;

    public GameSystem(Scene scene, String identifier, int priority){ this.identifier = identifier; this.scene = scene; this.priority = priority; }
    public abstract void update(GameContainer container, HashSet<Entity> range, float delta);
    public abstract void render(Graphics graphics, HashSet<Entity> range);

    public String getIdentifier() {
        return identifier;
    }
    public boolean isActive() {
        return isActive;
    }
    public int getPriority() {
        return priority;
    }
    public int compareTo(GameSystem o) {
        return o.getPriority() > priority ? 1 : o.getPriority() == priority ? 0 : -1;
    }

    public void setUpdatesPerSecond(int updatesPerSecond) {
        this.updatesPerSecond = updatesPerSecond;
    }

    public int getUpdatesPerSecond()
    {
        return updatesPerSecond;
    }

}
