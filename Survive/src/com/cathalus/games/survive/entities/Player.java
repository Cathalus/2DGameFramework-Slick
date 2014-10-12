package com.cathalus.games.survive.entities;

import com.cathalus.games.survive.entities.components.*;
import com.cathalus.games.survive.entities.weapons.BasicWeapon;
import com.cathalus.slick.framework.core.entities.Entity;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by cathalus on 23.09.14.
 */
public class Player extends Entity {

    public Player(Vector2f pos, float width, float height, InputComponent input) {
        super(pos, width, height);

        addComponent(new MovementComponent(true,200,300));
        addComponent(input);
        addComponent(new AABBRenderComponent());

        WeaponComponent weaponComponent = new WeaponComponent();
        weaponComponent.addWeapon(new BasicWeapon(2,0.1f));

        addComponent(weaponComponent);
    }
}
