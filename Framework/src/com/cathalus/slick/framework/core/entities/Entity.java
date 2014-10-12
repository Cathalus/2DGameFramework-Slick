package com.cathalus.slick.framework.core.entities;

import com.cathalus.slick.framework.core.entities.components.Renderable;
import com.cathalus.slick.framework.core.math.AABB;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.Log;

import java.util.ArrayList;
import java.util.Vector;

public class Entity {

    private ArrayList<EntityComponent> components;

    private float x, y;
    private float dX = 0.0f, dY = 0.0f;
    private AABB aabb;

    /**
     * Default constructor. Takes in an AABB and initializes variables
     * @param aabb AABB
     */
    public Entity(AABB aabb)
    {
        this.components = new ArrayList<EntityComponent>();
        this.aabb = aabb;
        this.x = aabb.getCenterX();
        this.y = aabb.getCenterY();
    }

    /**
     * Takes in a position, width and hide to initialize the entity
     * @param pos Position of the entity
     * @param width Width of the entity
     * @param height Height of the entity
     */
    public Entity(Vector2f pos, float width, float height)
    {
        this(new AABB(pos.getX(), -(pos.getY()+height), pos.getX()+width, -pos.getY()));
    }

    /**
     * Adds a component to the entity.
     * Calls the component's onAdd() method and sets the Component's entity.
     * Returns the current entity
     * @param component
     * @return Entity
     */
    public Entity addComponent(EntityComponent component)
    {
        component.setEntity(this);
        component.onAdd();
        components.add(component);
        return this;
    }

    /**
     * Adds the positional delta values to the AABB.
     */
    public void updateAABB()
    {
        float minX = aabb.getMinX() + dX;
        float maxX = aabb.getMaxX() + dX;
        float minY = aabb.getMinY() + dY;
        float maxY = aabb.getMaxY() + dY;

        aabb = new AABB(minX, minY, maxX, maxY);
    }

    /**
     * Looks for a given component by identifier and returns it.
     * Returns null if the component wasn't found
     * @param identifier Name of the component
     * @return Component
     */
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

    public boolean hasComponent(String identifier)
    {
        for(EntityComponent component : components)
        {
            if(component.getIdentifier().equals(identifier))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the entity and all of it's components
     * @param container GameContainer
     * @param delta float delta time
     */
    public void update(GameContainer container, float delta)
    {
        x = aabb.getCenterX();
        y = aabb.getCenterY();

        // TODO: let systems update components (later)
        for(EntityComponent component : components)
        {
            component.update(container,delta);
        }
    }

    /**
     * Render's all of the renderable components
     * @param graphics Graphics object
     */
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
    public void setDelta(Vector2f delta) {
        dX = delta.getX();
        dY = delta.getY();
    }

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
