package com.cathalus.games.survive.levels;

import com.cathalus.slick.framework.core.Level;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Created by cathalus on 22.09.14.
 */
public class BasicLevel extends Level {
    public BasicLevel(TiledMap map) {
        super(map);
    }

    @Override
    public void render(Graphics graphics, int posX, int posY) {
        map.render(posX,posY);
    }
}
