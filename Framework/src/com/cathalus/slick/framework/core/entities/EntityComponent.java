package com.cathalus.slick.framework.core.entities;

import org.newdawn.slick.GameContainer;

/**
 * Created by cathalus on 06.09.14.
 */
public abstract class EntityComponent {

    protected Entity entity;
    protected String identifier = "";

    public EntityComponent()
    { }

    public abstract void update(GameContainer container,float delta);
    public abstract  void onAdd();
    public void setEntity(Entity entity)
    {
        this.entity = entity;
    }
    public String getIdentifier()
    {
        return identifier;
    }
}
