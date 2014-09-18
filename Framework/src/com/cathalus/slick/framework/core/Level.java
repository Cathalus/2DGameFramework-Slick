package com.cathalus.slick.framework.core;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Created by cathalus on 18.09.14.
 */
public abstract class Level {

    protected TiledMap map;
    protected Vector2f dimensions;

    public Level(TiledMap map){
        this.map = map;
        this.dimensions = new Vector2f(map.getTileWidth()*map.getWidth(),map.getTileHeight()*map.getHeight());
    }


}
