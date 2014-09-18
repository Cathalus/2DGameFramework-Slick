package com.cathalus.games.platformer.scenes;

import com.cathalus.slick.framework.core.Scene;
import com.cathalus.slick.framework.core.resources.ResourceManager;
import com.cathalus.slick.framework.core.states.SceneBasedState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.GameState;

/**
 * Created by cathalus on 17.09.14.
 */
public class GameplayScene extends Scene {
    public GameplayScene(SceneBasedState state, int windowWidth, int windowHeight, int childrenPerNode) {
        super(state, windowWidth, windowHeight, childrenPerNode);
    }

    @Override
    public void update(GameContainer container, float delta) {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawString("Gameplay",0,300);
        Image mage = ResourceManager.getImage("black_mage");
        mage.draw(0,0);
    }
}
