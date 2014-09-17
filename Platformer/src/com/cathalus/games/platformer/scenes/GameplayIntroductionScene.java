package com.cathalus.games.platformer.scenes;

import com.cathalus.slick.framework.core.Scene;
import com.cathalus.slick.framework.core.states.SceneBasedState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.GameState;

/**
 * Created by cathalus on 17.09.14.
 */
public class GameplayIntroductionScene extends Scene {

    public GameplayIntroductionScene(SceneBasedState state, int windowWidth, int windowHeight, int childrenPerNode) {
        super(state, windowWidth, windowHeight, childrenPerNode);
    }

    @Override
    public void update(GameContainer container, float delta) {
        Input in = container.getInput();
        if(in.isKeyDown(Input.KEY_ENTER))
        {
            state.transitionTo("Gameplay");
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawString("Keys:\nA,D - Move Left/Right\nSpace - Jump\n\nTo start the game press Enter!",0,300);
    }
}
