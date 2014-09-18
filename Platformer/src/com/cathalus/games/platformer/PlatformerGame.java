package com.cathalus.games.platformer;

import com.cathalus.games.platformer.states.GameplayState;
import com.cathalus.games.platformer.util.States;
import com.cathalus.slick.framework.core.resources.ResourceManager;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by cathalus on 17.09.14.
 */
public class PlatformerGame extends StateBasedGame {

    public PlatformerGame(String name) {
        super(name);
        // Adding States to the Game
        addState(new GameplayState());
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        getState(States.GAMEPLAY).init(container,this);

        try {
            ResourceManager.loadResources(new BufferedInputStream(new FileInputStream("./Platformer/res/resources.xml")));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args)
    {
        try{
            AppGameContainer container = new AppGameContainer(new PlatformerGame("Platformer 3000"));
            container.setDisplayMode(1280,720,false);
            container.setAlwaysRender(true);
            container.setShowFPS(true);
//            container.setTargetFrameRate(60);
            container.setMinimumLogicUpdateInterval(1000 / 60);
            container.start();
        }catch(SlickException ex)
        {

        }
    }
}
