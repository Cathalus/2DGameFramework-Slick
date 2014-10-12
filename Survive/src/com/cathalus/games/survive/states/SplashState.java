package com.cathalus.games.survive.states;

import com.cathalus.games.survive.util.State;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by cathalus on 22.09.14.
 */
public class SplashState extends BasicGameState {
    @Override
    public int getID() {
        return State.SPLASH.getID();
    }

    @Override
    public void init(GameContainer container, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawString("Splash",0,0);
    }

    @Override
    public void update(GameContainer container, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
}
