package com.cathalus.games.baconjam08.levels;

import com.cathalus.games.baconjam08.components.TriggerComponent;
import com.cathalus.games.baconjam08.entities.MapObject;
import com.cathalus.games.baconjam08.levels.BasicLevel;
import com.cathalus.games.baconjam08.util.Globals;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.Log;

/**
 * Created by Cathalus on 18.10.2014.
 */
public class TestLevel extends BasicLevel {

    private static final int COLLISION_OBJECT_GROUP = 0;
    private static final int SPAWN_OBJECT_GROUP = 1;
    private static final int ENEMY_SPAWN_OBJECT_GROUP = 2;


    public TestLevel(TiledMap map) {
        super(map);

    }

    @Override
    protected void addObject(int groupID, int objectID)
    {
        MapObject object;
        int x = map.getObjectX(groupID, objectID);
        int y = map.getObjectY(groupID, objectID);
        int width = map.getObjectWidth(groupID, objectID);
        int height = map.getObjectHeight(groupID, objectID);
        Log.debug("\t\t #" + objectID + ": [" + x + "|" + y + "] [" + width + "|" + height + "] ");
        switch (groupID)
        {
            case COLLISION_OBJECT_GROUP:
                object = new MapObject(new Vector2f(x,y),width,height,true);
                break;
            case SPAWN_OBJECT_GROUP:
                object = new MapObject(new Vector2f(x,y),1,1,false);
                object.addComponent(new TriggerComponent(Globals.Triggers.PLAYER_SPAWN));
                break;
            case ENEMY_SPAWN_OBJECT_GROUP:
                object = new MapObject(new Vector2f(x,y),10,10,false);
                object.addComponent(new TriggerComponent(Globals.Triggers.ENEMY_SPAWN));
                break;
            default:
                object = new MapObject(new Vector2f(x,y),width,height,false);
                break;
        }
        objects.add(object);
    }
}
