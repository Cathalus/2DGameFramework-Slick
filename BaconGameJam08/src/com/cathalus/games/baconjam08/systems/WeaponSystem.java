package com.cathalus.games.baconjam08.systems;

import com.cathalus.games.baconjam08.components.*;
import com.cathalus.slick.framework.core.Scene;
import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.entities.systems.GameSystem;
import com.cathalus.slick.framework.core.input.XBOX360;
import com.cathalus.slick.framework.core.resources.ResourceManager;
import net.java.games.input.Component;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by cathalus on 23.09.14.
 */
public class WeaponSystem extends GameSystem {

    public WeaponSystem(Scene scene, String identifier, int priority) {
        super(scene, identifier,priority);
    }

    boolean shooting = false;
    private Vector2f mouseDelta = new Vector2f();

    @Override
    public void update(GameContainer container, HashSet<Entity> range, float delta) {
        Iterator<Entity> it = range.iterator();
        while (it.hasNext()) {
            Entity current = it.next();
            if(current.hasComponent(WeaponComponent.NAME))
            {
                WeaponComponent weaponComponent = (WeaponComponent) current.getComponent(WeaponComponent.NAME);
                // is Player
                if(current.hasComponent(InputComponent.NAME))
                {
                    InputComponent inputComponent = (InputComponent) current.getComponent(InputComponent.NAME);
                    mouseDelta = new Vector2f(current.getAABB().getCenterX()-Mouse.getX(),current.getAABB().getCenterY()-Mouse.getY());
                    if(inputComponent.isButtonDown(0))
                    {
                        // TODO: get direction
                        fire(current, mouseDelta, weaponComponent);
                    }
                }

                weaponComponent.update(container,delta);
            }
        }
    }

    private void fire(Entity current, Vector2f direction, WeaponComponent weaponComponent) {
        direction.normalise();
        Vector2f position = new Vector2f(current.getAABB().getCenterX(), -current.getAABB().getCenterY());
        position.add(direction);
        Entity bullet = new Entity(position,3,3).addComponent(new AABBRenderComponent()).addComponent(new ProjectileComponent(weaponComponent.getCurrentWeapon()));
        MovementComponent movementComponent = new MovementComponent(weaponComponent.getCurrentWeapon().getVelocity(), (int) weaponComponent.getCurrentWeapon().getVelocity(), false);

        System.out.println(direction);
        movementComponent.setDeltaMovement(direction);
        bullet.addComponent(movementComponent);
        scene.addEntity(bullet);
    }


    @Override
    public void render(Graphics graphics, HashSet<Entity> range) {

    }
}
