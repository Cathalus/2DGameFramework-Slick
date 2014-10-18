package com.cathalus.games.baconjam08.scenes;

import com.cathalus.games.baconjam08.components.CameraComponent;
import com.cathalus.games.baconjam08.systems.*;
import com.cathalus.games.baconjam08.util.Globals;
import com.cathalus.slick.framework.core.Scene;
import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.entities.systems.GameSystemManager;
import com.cathalus.slick.framework.core.graphics.Camera2D;
import com.cathalus.slick.framework.core.math.BoundingBox;
import com.cathalus.slick.framework.core.math.QuadTree;
import com.cathalus.slick.framework.core.states.SceneBasedState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.HashSet;

/**
 * Created by Cathalus on 18.10.2014.
 */
public class GameplayScene extends Scene {

    private GameSystemManager systemManager;
    private MovementSystem movementSystem;
    private RenderingSystem renderingSystem;
    private SpawningSystem spawningSystem;
    private AISystem aiSystem;
    private CameraSystem cameraSystem;
    private int width, height;

    public GameplayScene(SceneBasedState state, QuadTree tree, int windowWidth, int windowHeight) {
        super(state, tree, windowWidth, windowHeight);
        this.width = windowWidth;
        this.height = windowHeight;
    }

    @Override
    public void init() {
        camera = new Camera2D(new BoundingBox(0,-height,width,0));
        System.out.println(camera.getAABB());
        camera.addComponent(new CameraComponent());

        systemManager = new GameSystemManager();

        // Initialize Systems
        spawningSystem = new SpawningSystem(this, "SpawningSystem", 5);
        aiSystem = new AISystem(this, "AISystem", 11);
        movementSystem = new MovementSystem(this,"MovementSystem",10);
        cameraSystem = new CameraSystem(this, "CameraSystem",1);
        renderingSystem = new RenderingSystem(this,"RenderingSystem",0);

        aiSystem.setUpdatesPerSecond(10);
        cameraSystem.setChaseEntity(Globals.CURRENT_PLAYER);

        systemManager.addGameSystem(aiSystem);
        systemManager.addGameSystem(spawningSystem);
        systemManager.addGameSystem(movementSystem);
        systemManager.addGameSystem(renderingSystem);
        systemManager.addGameSystem(cameraSystem);
    }

    @Override
    public void update(GameContainer container, float delta) {
        HashSet<Entity> all = (HashSet<Entity>) tree.getAll(new HashSet<Entity>());
        systemManager.update(container, all, delta);
    }

    @Override
    public void render(Graphics graphics) {
        if(getActiveCamera() != null)
        {
            if(Globals.CURRENT_LEVEL != null)
            {
                Globals.CURRENT_LEVEL.render(graphics,(int) -getActiveCamera().getAABB().getMinX(),(int) getActiveCamera().getAABB().getMaxY());
            }
            systemManager.render(graphics, (HashSet<Entity>) tree.queryRange(getActiveCamera().getAABB(), new HashSet<Entity>()));
        }else{
            systemManager.render(graphics,(HashSet<Entity>) tree.getAll(new HashSet<Entity>()));
        }
    }

    @Override
    public void loadResources() {

    }

    @Override
    public void unloadResources() {

    }
}
