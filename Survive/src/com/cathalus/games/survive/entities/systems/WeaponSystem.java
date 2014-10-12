package com.cathalus.games.survive.entities.systems;

import com.cathalus.games.survive.entities.components.*;
import com.cathalus.slick.framework.core.Scene;
import com.cathalus.slick.framework.core.entities.Entity;
import com.cathalus.slick.framework.core.entities.systems.GameSystem;
import com.cathalus.slick.framework.core.input.XBOX360;
import com.cathalus.slick.framework.core.resources.ResourceManager;
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
                    shooting = false;
                    InputComponent inputComponent = (InputComponent) current.getComponent(InputComponent.NAME);
                    if(inputComponent.isButtonDown(XBOX360.Buttons.LB.getID()))
                    {
                        if(!weaponComponent.isOnCooldown())
                        {
                            shooting = true;
                            Vector2f direction = new Vector2f(inputComponent.getValue(XBOX360.Axis.THUMB_RIGHT_X.getID()),
                                                              -inputComponent.getValue(XBOX360.Axis.THUMB_RIGHT_Y.getID()));
                            fire(current, direction, weaponComponent);

                            // Effects
                            inputComponent.rumble(5f);
                            ResourceManager.getSound(weaponComponent.getCurrentWeapon().getSfx()).play();
                            if(current.hasComponent(MovementComponent.NAME))
                            {
                                MovementComponent component = (MovementComponent) current.getComponent(MovementComponent.NAME);
                                component.addDeltaMovement(new Vector2f(inputComponent.getValue(XBOX360.Axis.THUMB_RIGHT_X.getID()),
                                        -inputComponent.getValue(XBOX360.Axis.THUMB_RIGHT_Y.getID())));
                            }
                        }
                    }

                    if(!shooting)
                    {
                        inputComponent.rumble(0f);
                    }
                }

                weaponComponent.update(container,delta);
            }
        }
    }

    private void fire(Entity current, Vector2f direction, WeaponComponent weaponComponent) {
        weaponComponent.fire();
        if(direction.equals(new Vector2f()))
        {
            direction = new Vector2f(-1, 1);
        }
        direction.normalise();
        Vector2f position = new Vector2f(direction.getX(),direction.getY());
        position.scale(Math.max(current.getAABB().getWidth(), current.getAABB().getHeight()));
        position.add(current.getAABB().getCenter());
        position.set(position.getX(), (position.getY() * -1));
        Entity bullet = new Entity(position,10,10).addComponent(new AABBRenderComponent()).addComponent(new ProjectileComponent(weaponComponent.getCurrentWeapon())).addComponent(new MovementComponent(false,direction,500,100));
        scene.addEntity(bullet);
    }


    @Override
    public void render(Graphics graphics, HashSet<Entity> range) {

    }
}
