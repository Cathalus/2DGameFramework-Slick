package com.cathalus.games.platformer.scenes;

import com.cathalus.games.platformer.entities.components.GraphicsComponent;
import com.cathalus.games.platformer.entities.components.MovementComponent;
import com.cathalus.games.platformer.entities.components.PhysicsComponent;
import com.cathalus.games.platformer.systems.PhysicsSystem;
import com.cathalus.games.platformer.systems.RenderingSystem;
import com.cathalus.slick.framework.core.Scene;
import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.entities.systems.GameSystem;
import com.cathalus.slick.framework.core.graphics.Camera2D;
import com.cathalus.slick.framework.core.math.AABB;
import com.cathalus.slick.framework.core.resources.ResourceManager;
import com.cathalus.slick.framework.core.states.SceneBasedState;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.GameState;

/**
 * Created by cathalus on 17.09.14.
 */
public class GameplayScene extends Scene {

    private SpriteSheet mm;
    private SpriteSheet gb;
    private Animation anim;

    public GameplayScene(SceneBasedState state, int windowWidth, int windowHeight, int childrenPerNode) {
        super(state, windowWidth, windowHeight, childrenPerNode);
        gb = ResourceManager.getSpriteSheet("gb_walk");
        mm = ResourceManager.getSpriteSheet("mm_jump");
        anim = ResourceManager.getAnimation("gb_walk");
        camera = new Camera2D(new AABB(0,-720,1280,0));

        tree.add(new Entity(new Vector2f(0,0),75,75).addComponent(new GraphicsComponent()).addComponent(new MovementComponent(new Vector2f(200,200),200)));
        tree.add(new Entity(new Vector2f(400,360),200,200).addComponent(new GraphicsComponent()).addComponent(new PhysicsComponent(null, PhysicsComponent.Type.STATIC)));
        tree.add(new Entity(new Vector2f(220,560),200,200).addComponent(new GraphicsComponent()).addComponent(new PhysicsComponent(null, PhysicsComponent.Type.STATIC)));
        addSystem(new PhysicsSystem(this, "PhysicsSystem"));
        addSystem(new RenderingSystem(this,"RenderingSystem"));
    }

    @Override
    public void update(GameContainer container, float delta) {
        long d = (long) delta*1000;
        anim.update(d);
        for(GameSystem s : gameSystems)
        {
            s.update(delta,container);
        }
    }

    int i = 1;
    @Override
    public void render(Graphics graphics) {
        graphics.drawString("Gameplay",0,300);
        gb.getSprite(1,0).draw(0, 0);
        anim.draw(400,400);

        for(GameSystem s : gameSystems)
        {
            s.render(graphics);
        }
    }
}
