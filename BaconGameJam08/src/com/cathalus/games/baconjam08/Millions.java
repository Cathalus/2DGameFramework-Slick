package com.cathalus.games.baconjam08;

import com.cathalus.games.baconjam08.levels.TestLevel;
import com.cathalus.games.baconjam08.states.GameplayState;
import com.cathalus.games.baconjam08.util.Globals;
import com.cathalus.games.baconjam08.util.State;
import com.cathalus.slick.framework.core.Level;
import com.cathalus.slick.framework.core.resources.ResourceManager;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Millions extends StateBasedGame {

    public Millions(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        // TODO: Add all states
        addState(new GameplayState());
        loadResources("./BaconGameJam08/res/resources.xml");

        // TODO: Set default level
        Level level = new TestLevel(ResourceManager.getTiledMap("level02"));
        Globals.CURRENT_LEVEL = level;

        // TODO: Enter Splashscreen state
        enterState(State.GAMEPLAY.getID());
    }

    private void loadResources(String path)
    {
        try {
            ResourceManager.loadResources(new BufferedInputStream(new FileInputStream(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        try {
            AppGameContainer container = new AppGameContainer(new Millions("Survive!"));
            container.setDisplayMode(1280,720,false);
            container.setAlwaysRender(true);
            container.setShowFPS(true);
            //container.setTargetFrameRate(60);
            container.setMinimumLogicUpdateInterval(1000 / 60);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

}
