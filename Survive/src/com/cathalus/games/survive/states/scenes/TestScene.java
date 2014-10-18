package com.cathalus.games.survive.states.scenes;

import com.cathalus.games.survive.Game;
import com.cathalus.games.survive.entities.components.CameraComponent;
import com.cathalus.games.survive.entities.systems.*;
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

public class TestScene extends Scene {

    private QuadTree tree;
    private GameSystemManager manager;
    private MovementSystem movementSystem;
    private DamageSystem damageSystem;
    private WeaponSystem weaponSystem;
    private CameraSystem cameraSystem;
    private RenderingSystem renderingSystem;

    private int width, height;

    public TestScene(SceneBasedState state, QuadTree tree, int windowWidth, int windowHeight) {
        super(state, tree, windowWidth, windowHeight);
        this.tree = tree;
        this.width = windowWidth;
        this.height = windowHeight;
    }

    @Override
    public void init() {
        camera = new Camera2D(new BoundingBox(0,-height,width,0));
        camera.addComponent(new CameraComponent());

        movementSystem = new MovementSystem(this, "MovementSystem", 10);
        damageSystem = new DamageSystem(this, "DamageSystem",8);
        weaponSystem = new WeaponSystem(this, "WeaponSystem",9);
        cameraSystem = new CameraSystem(this, "CameraSystem",1);
        renderingSystem = new RenderingSystem(this, "RenderingSystem",0);

        manager = new GameSystemManager();
        manager.addGameSystem(movementSystem);
        manager.addGameSystem(damageSystem);
        manager.addGameSystem(weaponSystem);
        manager.addGameSystem(cameraSystem);
        manager.addGameSystem(renderingSystem);

        if(camera != null && Game.PLAYER != null)
        {
            cameraSystem.setChaseEntity(Game.PLAYER);
        }
    }

    @Override
    public void update(GameContainer container, float delta) {
        manager.update(container,(HashSet<Entity>) tree.getAll(new HashSet<Entity>()),delta);
    }

    @Override
    public void render(Graphics graphics) {
        if(Game.CURRENT_LEVEL != null)
        {
            Game.CURRENT_LEVEL.render(graphics,(int)-getActiveCamera().getAABB().getMinX(),(int) getActiveCamera().getAABB().getMaxY());
        }
        if(getActiveCamera() != null)
        {
            manager.render(graphics, (HashSet<Entity>) tree.queryRange(getActiveCamera().getAABB(), new HashSet<Entity>()));
        }else{
            manager.render(graphics,(HashSet<Entity>) tree.getAll(new HashSet<Entity>()));
        }
    }

    @Override
    public void loadResources() {

    }

    @Override
    public void unloadResources() {

    }
}
