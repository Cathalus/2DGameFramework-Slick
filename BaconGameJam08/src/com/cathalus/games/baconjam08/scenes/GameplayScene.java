package com.cathalus.games.baconjam08.scenes;

import com.cathalus.games.baconjam08.systems.AISystem;
import com.cathalus.games.baconjam08.systems.MovementSystem;
import com.cathalus.games.baconjam08.systems.RenderingSystem;
import com.cathalus.games.baconjam08.systems.SpawningSystem;
import com.cathalus.games.baconjam08.util.Globals;
import com.cathalus.slick.framework.core.Scene;
import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.entities.systems.GameSystemManager;
import com.cathalus.slick.framework.core.math.QuadTree;
import com.cathalus.slick.framework.core.states.SceneBasedState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.HashSet;

/**
 * Created by Cathalus on 18.10.2014.
 */
public class GameplayScene extends Scene {

    GameSystemManager systemManager;
    MovementSystem movementSystem;
    RenderingSystem renderingSystem;
    SpawningSystem spawningSystem;
    AISystem aiSystem;

    public GameplayScene(SceneBasedState state, QuadTree tree, int windowWidth, int windowHeight) {
        super(state, tree, windowWidth, windowHeight);
    }

    @Override
    public void init() {
        systemManager = new GameSystemManager();

        // Initialize Systems
        spawningSystem = new SpawningSystem(this, "SpawningSystem", 5);
        aiSystem = new AISystem(this, "AISystem", 11);
        movementSystem = new MovementSystem(this,"MovementSystem",10);
        renderingSystem = new RenderingSystem(this,"RenderingSystem",0);

        systemManager.addGameSystem(aiSystem);
        systemManager.addGameSystem(spawningSystem);
        systemManager.addGameSystem(movementSystem);
        systemManager.addGameSystem(renderingSystem);
    }

    @Override
    public void update(GameContainer container, float delta) {
        HashSet<Entity> all = (HashSet<Entity>) tree.getAll(new HashSet<Entity>());
        systemManager.update(container, all, delta);
    }

    @Override
    public void render(Graphics graphics) {
        if(Globals.CURRENT_LEVEL != null)
        {
            Globals.CURRENT_LEVEL.render(graphics,0,0);
        }
        HashSet<Entity> entities = (HashSet<Entity>) tree.getAll(new HashSet<Entity>());
        systemManager.render(graphics,entities);
        graphics.drawString("Number of entities: "+entities.size(),0,100);
    }

    @Override
    public void loadResources() {

    }

    @Override
    public void unloadResources() {

    }
}
