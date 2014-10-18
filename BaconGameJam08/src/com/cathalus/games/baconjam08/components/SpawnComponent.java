package com.cathalus.games.baconjam08.components;

import com.cathalus.slick.framework.core.entities.EntityComponent;
import org.newdawn.slick.GameContainer;

/**
 * Created by Cathalus on 18.10.2014.
 */
public class SpawnComponent extends EntityComponent {

    public static final String NAME = "SpawnComponent";

    private int quantity = 0;
    private float cooldown = 0.0f;
    private float elapsedTime = 0.0f;
    private float spawnsPerInterval = 1;


    public SpawnComponent(int quantity, float cooldown, float spawnsPerInterval)
    {
        this.quantity = quantity;
        this.cooldown = cooldown;
        this.spawnsPerInterval = spawnsPerInterval;
        this.identifier = NAME;
    }

    @Override
    public void update(GameContainer container, float delta) {
        elapsedTime += delta;
    }

    @Override
    public void onAdd() {

    }

    public boolean onCooldown()
    {
        if(elapsedTime >= cooldown)
        {
            elapsedTime = 0.0f;
            return false;
        }
        return true;
    }

    public void changeQuantity(int delta)
    {
        quantity += delta;
    }
}
