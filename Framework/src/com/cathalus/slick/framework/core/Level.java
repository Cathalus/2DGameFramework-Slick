package com.cathalus.slick.framework.core;

import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.math.MathUtil;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;

/**
 * Created by cathalus on 18.09.14.
 */
public abstract class Level {

    protected TiledMap map;
    protected Vector2f dimensions;
    protected ArrayList<Entity> objects = new ArrayList<Entity>();

    public Level(TiledMap map){
        this.map = map;
        this.dimensions = new Vector2f(map.getTileWidth()*map.getWidth(),map.getTileHeight()*map.getHeight());
        init();
    }

    public abstract void render(Graphics graphics, int posX, int posY);
    public abstract void init();
    public abstract ArrayList<Entity> getObjects();

    public Vector2f mouseToWorld(Vector2f mouse, float scale)
    {
        Vector2f world = new Vector2f();
        //map.getTileId((int) MathUtil.clamp(mouse.getX(),0,map.getTileWidth()),(int) MathUtil.clamp(mouse.getY(),0,map.getTileHeight()),0);
        world.set(mouse.getX()/scale,mouse.getY()/scale);
        return world;
    }

}
