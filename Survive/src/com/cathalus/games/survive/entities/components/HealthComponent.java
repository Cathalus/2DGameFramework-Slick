package com.cathalus.games.survive.entities.components;

import com.cathalus.slick.framework.core.entities.EntityComponent;
import org.newdawn.slick.GameContainer;

/**
 * Created by cathalus on 23.09.14.
 */
public class HealthComponent extends EntityComponent {

    public static final String NAME = "HealthComponent";

    private int maxHealth = 0;
    private int currentHealth = 0;

    public HealthComponent(int maxHealth)
    {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.identifier = NAME;
    }

    public void changeHealth(int delta)
    {
        currentHealth -= delta;
    }


    @Override
    public void update(GameContainer container, float delta) {

    }

    @Override
    public void onAdd() {

    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
}
