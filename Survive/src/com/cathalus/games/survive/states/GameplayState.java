package com.cathalus.games.survive.states;

import com.cathalus.games.survive.Game;
import com.cathalus.games.survive.entities.Player;
import com.cathalus.games.survive.entities.components.AABBRenderComponent;
import com.cathalus.games.survive.entities.components.HealthComponent;
import com.cathalus.games.survive.entities.components.InputComponent;
import com.cathalus.games.survive.entities.components.PhysicsComponent;
import com.cathalus.games.survive.states.scenes.TestScene;
import com.cathalus.games.survive.util.State;
import com.cathalus.slick.framework.core.Level;
import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.math.AABB;
import com.cathalus.slick.framework.core.math.QuadTree;
import com.cathalus.slick.framework.core.states.SceneBasedState;
import org.lwjgl.input.Controllers;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import java.security.InvalidParameterException;

/**
 * Created by cathalus on 22.09.14.
 */
public class GameplayState extends SceneBasedState {

    private Level currentLevel;
    private QuadTree tree;
    private Player player;

    //float counter = 0;

    @Override
    public int getID() {
        return State.GAMEPLAY.getID();
    }

    @Override
    public void init(GameContainer container, StateBasedGame stateBasedGame) throws SlickException {
        player = new Player(new Vector2f(640,360),50,50,new InputComponent(Controllers.getController(0)));
        tree = new QuadTree(new AABB(0,-container.getHeight(),container.getHeight(),0),5);
        tree.add(player);
        tree.add(new Entity(new Vector2f(200,200),200,200).addComponent(new PhysicsComponent(PhysicsComponent.Type.STATIC)).addComponent(new AABBRenderComponent()).addComponent(new HealthComponent(200)));
        tree.add(new Entity(new Vector2f(400,400),50,50).addComponent(new PhysicsComponent(PhysicsComponent.Type.STATIC)).addComponent(new AABBRenderComponent()).addComponent(new HealthComponent(50)));

        Game.PLAYER = player;

        scenes.put("TestScene", new TestScene(this, tree, container.getWidth(), container.getHeight()));
        transitionTo("TestScene");
    }

    @Override
    public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        current.render(graphics);
    }

    @Override
    public void update(GameContainer container, StateBasedGame stateBasedGame, int i) throws SlickException {

        if(current != null) {
            float delta = i;
            current.update(container, delta/1000);
        }
    }
}
