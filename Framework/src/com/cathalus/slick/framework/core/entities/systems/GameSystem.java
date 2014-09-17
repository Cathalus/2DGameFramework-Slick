package com.cathalus.slick.framework.core.entities.systems;

import org.newdawn.slick.GameContainer;

/**
 * Created by cathalus on 17.09.14.
 */
public abstract class GameSystem implements Comparable<GameSystem> {

    protected String identifier;
    protected boolean isActive = false;
    protected int priority = 0;

    public GameSystem(String identifier){ this.identifier = identifier; }
    public abstract void update(float delta,GameContainer container);

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
