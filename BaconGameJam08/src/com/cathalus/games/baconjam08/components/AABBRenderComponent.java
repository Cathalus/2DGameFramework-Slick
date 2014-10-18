package com.cathalus.games.baconjam08.components;

import com.cathalus.slick.framework.core.entities.EntityComponent;
import com.cathalus.slick.framework.core.entities.components.Renderable;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Created by cathalus on 23.09.14.
 */
public class AABBRenderComponent extends EntityComponent implements Renderable {

    public static final String NAME = "AABBRenderComponent";

    private Color color = Color.blue;

    public AABBRenderComponent()
    {
        this.identifier = NAME;
    }

    @Override
    public void update(GameContainer container, float delta) {

    }

    @Override
    public void onAdd() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        float x = entity.getAABB().getMinX();
        float y = entity.getAABB().getMaxY();
        float w = entity.getAABB().getWidth();
        float h = entity.getAABB().getHeight();

        graphics.fillRect(x, -y, w, h);

        /*
        if(entity.hasComponent(HealthComponent.NAME))
        {
            HealthComponent healthComponent = (HealthComponent) entity.getComponent(HealthComponent.NAME);
            graphics.setColor(Color.red);
            graphics.drawString(healthComponent.getCurrentHealth()+"/"+healthComponent.getMaxHealth(),entity.getAABB().getCenterX()-20,-entity.getAABB().getMinY());
        }*/
    }

    public void setColor(Color c)
    {
        this.color = c;
    }
}
