package com.cathalus.games.survive;

import com.cathalus.games.survive.entities.Player;
import com.cathalus.games.survive.levels.BasicLevel;
import com.cathalus.games.survive.states.GameplayState;
import com.cathalus.games.survive.states.MainMenuState;
import com.cathalus.games.survive.states.SplashState;
import com.cathalus.games.survive.util.State;
import com.cathalus.slick.framework.core.Level;
import com.cathalus.slick.framework.core.resources.ResourceManager;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by cathalus on 22.09.14.
 */
public class Game extends StateBasedGame {

    public static Player PLAYER;
    public static Level CURRENT_LEVEL;

    public Game(String name) {
        super(name);
        addState(new MainMenuState());
        addState(new GameplayState());
        addState(new SplashState());
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        getState(State.SPLASH.getID()).init(container,this);
        try {
            ResourceManager.loadResources(new BufferedInputStream(new FileInputStream("./Survive/res/resources.xml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Level level = new BasicLevel(ResourceManager.getTiledMap("level03"));
        CURRENT_LEVEL = level;
        this.enterState(State.GAMEPLAY.getID());
    }

    public static void main(String[] args)
    {
        try {
            AppGameContainer container = new AppGameContainer(new Game("Survive!"));
            container.setDisplayMode(1280,720,false);
            container.setAlwaysRender(true);
            container.setShowFPS(true);
            //container.setTargetFrameRate(60);
            //container.setMinimumLogicUpdateInterval(1000 / 60);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
