package com.cathalus.games.platformer.states;

import com.cathalus.games.platformer.scenes.GameplayIntroductionScene;
import com.cathalus.games.platformer.scenes.GameplayScene;
import com.cathalus.games.platformer.util.States;
import com.cathalus.slick.framework.core.resources.ResourceManager;
import com.cathalus.slick.framework.core.states.SceneBasedState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by cathalus on 17.09.14.
 */
public class GameplayState extends SceneBasedState {

    @Override
    public int getID() {
        return States.GAMEPLAY;
    }

    @Override
    public void init(GameContainer container, StateBasedGame stateBasedGame) throws SlickException {
        // Go to default scene
        scenes.put("GameplayIntroduction", new GameplayIntroductionScene(this, container.getWidth(), container.getHeight(), 1));
        scenes.put("Gameplay",new GameplayScene(this,container.getWidth(),container.getHeight(),10));
        current = scenes.get("GameplayIntroduction");
    }

    @Override
    public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        if(current != null)
        {
            current.render(graphics);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(current != null)
        {
            current.update(container,i);
        }
    }
}
