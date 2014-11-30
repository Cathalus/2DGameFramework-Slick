package com.cathalus.games.mathgame;

import com.cathalus.games.mathgame.states.AdditionSubtractionState;
import com.cathalus.games.mathgame.states.MainMenuState;
import com.cathalus.games.mathgame.util.Globals;
import com.cathalus.games.mathgame.util.States;
import com.cathalus.slick.framework.core.resources.ResourceManager;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class MathGame extends StateBasedGame {

    public MathGame(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        loadResources("./MathGame/res/resources.xml");

        this.addState(new AdditionSubtractionState());
        this.addState(new MainMenuState());
        this.enterState(States.MainMenu.getID());
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
            AppGameContainer container = new AppGameContainer(new MathGame(Globals.TITLE+" "+Globals.VERSION));
            container.setDisplayMode(1280,720,false);
            container.setAlwaysRender(true);
            container.setShowFPS(false);
            container.setTargetFrameRate(60);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}