package com.cathalus.slick.framework.core.entities.systems;

import com.cathalus.slick.framework.core.Scene;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Created by cathalus on 17.09.14.
 */
public abstract class GameSystem implements Comparable<GameSystem> {

    protected String identifier;
    protected boolean isActive = false;
    protected int priority = 0;
    protected Scene scene;

    public GameSystem(Scene scene, String identifier){ this.identifier = identifier; this.scene = scene; }
    public abstract void update(float delta,GameContainer container);
    public abstract void render(Graphics graphics);

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

}
