package com.cathalus.slick.framework.core;

import com.cathalus.slick.framework.core.math.MathUtil;
import org.newdawn.slick.Graphics;
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

    public abstract void render(Graphics graphics, int posX, int posY);

    public Vector2f mouseToWorld(Vector2f mouse, float scale)
    {
        Vector2f world = new Vector2f();
        //map.getTileId((int) MathUtil.clamp(mouse.getX(),0,map.getTileWidth()),(int) MathUtil.clamp(mouse.getY(),0,map.getTileHeight()),0);
        world.set(mouse.getX()/scale,mouse.getY()/scale);
        return world;
    }

}
