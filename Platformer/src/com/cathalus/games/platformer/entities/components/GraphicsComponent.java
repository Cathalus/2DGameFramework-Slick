package com.cathalus.games.platformer.entities.components;

import com.cathalus.slick.framework.core.entities.EntityComponent;
import com.cathalus.slick.framework.core.entities.components.Renderable;
import org.newdawn.slick.*;

/**
 * Created by cathalus on 19.09.14.
 */
public class GraphicsComponent extends EntityComponent implements Renderable {

    private static final String NAME = "GraphicsComponent";

    private Image sprite;
    private Animation animation;

    public GraphicsComponent(Image sprite)
    {
        this.identifier = NAME; this.sprite = sprite;
    }

    public GraphicsComponent(Animation animation)
    {
        this.identifier = NAME; this.animation = animation;
    }

    public GraphicsComponent() {
        this.identifier = NAME;
    }

    @Override
    public void update(float delta, GameContainer container) {

    }

    @Override
    public void onAdd() {

    }

    @Override
    public void render(Graphics graphics) {
        if(animation != null)
        {

        }else if(sprite != null)
        {

        }else{
            graphics.setColor(Color.red);
            float x = entity.getAABB().getMinX();
            float y = entity.getAABB().getMaxY();
            float w = entity.getAABB().getWidth();
            float h = entity.getAABB().getHeight();

            graphics.fillRect(x,-y,w,h);
        }
    }

    public void setAnimation(Animation animation)
    {
        this.animation = animation;
        this.sprite = null;
    }

    public void setSprite(Image sprite)
    {
        this.animation = null;
        this.sprite = sprite;
    }
}
