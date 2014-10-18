package com.cathalus.slick.framework.core.entities.systems;

import com.cathalus.slick.framework.core.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by cathalus on 28.09.14.
 */
public class GameSystemManager {

    private ArrayList<GameSystem> systems = new ArrayList<GameSystem>();

    public void addGameSystem(GameSystem system)
    {
        this.systems.add(system);
        Collections.sort(systems);
    }

    public void removeGameSystem(GameSystem system)
    {
        this.systems.add(system);
    }

    public void removeGameSystem(String gameSystem)
    {
        for(GameSystem system : systems)
        {
            if(system.getIdentifier().equals(gameSystem))
            {
                removeGameSystem(system);
            }
        }
    }

    public GameSystem getGameSystem(String gameSystem){
        for(GameSystem system : systems)
        {
            if(system.getIdentifier().equals(gameSystem))
            {
                return system;
            }
        }
        return null;
    }

    public void update(GameContainer container, HashSet<Entity> range, float delta)
    {
        for(GameSystem system : systems)
        {
            system.update(container,range,delta);
        }
    }

    public void render(Graphics graphics, HashSet<Entity> range)
    {
        for(GameSystem system : systems)
        {
            system.render(graphics,range);
        }
    }
}
