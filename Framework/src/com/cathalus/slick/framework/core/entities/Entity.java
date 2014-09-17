package com.cathalus.slick.framework.core.entities;

import com.cathalus.slick.framework.core.entities.components.Renderable;
import com.cathalus.slick.framework.core.math.AABB;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;

/**
 * Created by cathalus on 06.09.14.
 */
public class Entity {

    private ArrayList<EntityComponent> components;

    private float x, y;
    private float dX = 0.0f, dY = 0.0f;
    private AABB aabb;

    public Entity(AABB aabb)
    {
        System.out.println(aabb.toString());
        this.components = new ArrayList<EntityComponent>();
        this.aabb = aabb;
        this.x = aabb.getCenterX();
        this.y = aabb.getCenterY();
        System.out.println("Created "+aabb.toString());
    }

    public Entity(Vector2f pos, float width, float height)
    {
        this(new AABB(pos.getX(), -(pos.getY()-height), pos.getX()+width, -pos.getY()));
    }


    public Entity addComponent(EntityComponent component)
    {
        component.setEntity(this);
        component.onAdd();
        components.add(component);
        return this;
    }

    public void updateAABB()
    {
        float minX = aabb.getMinX() + dX;
        float maxX = aabb.getMaxX() + dX;
        float minY = aabb.getMinY() + dY;
        float maxY = aabb.getMaxY() + dY;

        aabb = new AABB(minX, minY, maxX, maxY);
    }

    public EntityComponent getComponent(String identifier)
    {
        for(EntityComponent component : components)
        {
            if(component.getIdentifier().equals(identifier))
            {
                return component;
            }
        }
        return null;
    }

    public void update(float delta, GameContainer container)
    {
        x = aabb.getCenterX();
        y = aabb.getCenterY();

        for(EntityComponent component : components)
        {
            component.update(delta,container);
        }
    }

    public void render(Graphics graphics)
    {
        for(EntityComponent component : components)
        {
            if(component instanceof Renderable)
                ((Renderable) component).render(graphics);
        }
    }

    public void setDeltaX(float value) { dX = value; }
    public void setDeltaY(float value) { dY = value; }

    public boolean hasMoved() { return dX != 0.0f || dY != 0.0f; }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public AABB getAABB() {
        return aabb;
    }
    public void setAABB(AABB value) { aabb = value; }

}
