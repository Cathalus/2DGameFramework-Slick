package com.cathalus.games.baconjam08.levels;

import com.cathalus.slick.framework.core.Level;
import com.cathalus.slick.framework.core.entities.Entity;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.Log;

import java.util.ArrayList;

/**
 * Created by Cathalus on 18.10.2014.
 */
public class BasicLevel extends Level {

    public BasicLevel(TiledMap map) {
        super(map);
    }

    @Override
    public void render(Graphics graphics, int posX, int posY) {
        map.render(posX,posY);
    }

    @Override
    public void init() {
        int objectGroups = map.getObjectGroupCount();
        int layerCount = map.getLayerCount();
        Log.debug("Map Information:");
        Log.debug("~~~~~~~~~~~~~~~~");
        Log.debug("Number of Objectgroups: "+objectGroups);
        Log.debug("Number of Layers: "+layerCount);
        for(int i = 0; i < objectGroups; i++)
        {
            Log.debug("Objectgroup ["+i+"]: ");
            Log.debug("\tNumber of objects: "+map.getObjectCount(i));
            for(int j = 0; j < map.getObjectCount(i); j++)
            {
                addObject(i,j);
            }
        }
    }

    protected void addObject(int groupID, int objectID)
    {

    }

    @Override
    public ArrayList<Entity> getObjects() {
        return objects;
    }
}
