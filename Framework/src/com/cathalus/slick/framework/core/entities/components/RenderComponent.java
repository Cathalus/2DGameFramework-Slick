package com.cathalus.slick.framework.core.entities.components;

import com.cathalus.slick.framework.core.entities.EntityComponent;
import com.cathalus.slick.framework.core.math.AABB;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.awt.*;

/**
 * Created by cathalus on 10.09.14.
 */
public class RenderComponent extends EntityComponent implements Renderable {

    public static final String NAME = "RenderComponent";

    private Image sprite;

    public RenderComponent(Image sprite) {
        this.identifier = NAME;
        this.sprite = sprite;
    }

    @Override
    public void update(float delta, GameContainer container) {

    }

    @Override
    public void onAdd() {
        if(sprite != null) {

            float minX = entity.getX()-sprite.getWidth()/2;
            float minY = entity.getY()-sprite.getHeight()/2;
            float maxX = minX+sprite.getWidth();
            float maxY = minY+sprite.getHeight();

            entity.setAABB(new AABB(minX,minY,maxX,maxY));
        }
    }

    @Override
    public void render(Graphics graphics) {
        if(sprite != null) {
            float x = entity.getAABB().getMinX();
            float y = entity.getAABB().getMaxY();
            float w = entity.getAABB().getWidth();
            float h = entity.getAABB().getHeight();

            sprite.draw(x,-y,w,h);
        }else{
            graphics.setColor(Color.red);
            float x = entity.getAABB().getMinX();
            float y = entity.getAABB().getMaxY();
            float w = entity.getAABB().getWidth();
            float h = entity.getAABB().getHeight();

            graphics.fillRect(x,-y,w,h);
        }
    }
}
