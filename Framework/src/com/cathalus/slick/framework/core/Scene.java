package com.cathalus.slick.framework.core;

import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.graphics.Camera2D;
import com.cathalus.slick.framework.core.math.BoundingBox;
import com.cathalus.slick.framework.core.math.QuadTree;
import com.cathalus.slick.framework.core.states.SceneBasedState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Created by cathalus on 17.09.14.
 */
public abstract class Scene {

    protected QuadTree tree;
    protected SceneBasedState state;
    protected Camera2D camera;

    public Scene(SceneBasedState state, int windowWidth, int windowHeight, int childrenPerNode)
    {
        this.tree = new QuadTree(new BoundingBox(0,-windowHeight,windowWidth,0),childrenPerNode);
        this.state = state;
    }

    public Scene(SceneBasedState state, QuadTree tree, int windowWidth, int windowHeight)
    {
        this.tree = tree;
        this.state = state;
    }

    public void addEntity(Entity entity)
    {
        tree.add(entity);
    }
    public void removeEntity(Entity entity)
    {
        tree.remove(entity);
    }

    public abstract void init();
    public abstract void update(GameContainer container, float delta);
    public abstract void render(Graphics graphics);

    public abstract void loadResources();
    public abstract void unloadResources();

    public QuadTree getTree()
    {
        return tree;
    }
    public Camera2D getActiveCamera()
    {
        return camera;
    }
}