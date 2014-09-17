package com.cathalus.slick.framework.core;

import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.entities.systems.GameSystem;
import com.cathalus.slick.framework.core.math.AABB;
import com.cathalus.slick.framework.core.math.QuadTree;
import com.cathalus.slick.framework.core.states.SceneBasedState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.GameState;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by cathalus on 17.09.14.
 */
public abstract class Scene {

    protected QuadTree tree;
    protected ArrayList<GameSystem> gameSystems = new ArrayList<GameSystem>();
    protected SceneBasedState state;

    public Scene(SceneBasedState state, int windowWidth, int windowHeight, int childrenPerNode)
    {
        this.tree = new QuadTree(new AABB(0,-windowHeight,windowWidth,0),childrenPerNode);
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

    public void addSystem(GameSystem gameSystem){ gameSystems.add(gameSystem); }
    public void removeSystem(GameSystem gameSystem) { gameSystems.remove(gameSystem); }

    public abstract void update(GameContainer container, float delta);
    public abstract void render(Graphics graphics);
}