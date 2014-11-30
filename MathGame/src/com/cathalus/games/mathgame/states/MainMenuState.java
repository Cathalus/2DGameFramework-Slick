package com.cathalus.games.mathgame.states;

import com.cathalus.games.mathgame.gui.MenuButton;
import com.cathalus.games.mathgame.util.States;
import com.cathalus.slick.framework.core.resources.ResourceManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Cathalus on 30.11.2014.
 */
public class MainMenuState extends BasicGameState
{

    MenuButton newGame = new MenuButton("Spielen","eraser");
    MenuButton options = new MenuButton("Optionen","eraser");
    MenuButton back = new MenuButton("Zurück","eraser");

    @Override
    public int getID() {
        return States.MainMenu.getID();
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        newGame.setPosX(1000);
        newGame.setPosY(300);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(ResourceManager.getImage("board"),0,0);
        newGame.draw(graphics);
        options.draw(graphics);
        back.draw(graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        newGame.getBoundingBox().contains(input.getMouseX(), input.getMouseY());
        //System.out.println(newGame.getBoundingBox().contains(input.getMouseX(), input.getMouseY()));

    }
}
