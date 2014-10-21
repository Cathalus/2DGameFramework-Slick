package com.cathalus.games.baconjam08.components;

import com.cathalus.games.baconjam08.weapons.Weapon;
import com.cathalus.slick.framework.core.entities.EntityComponent;
import org.newdawn.slick.GameContainer;

import java.util.ArrayList;

/**
 * Created by cathalus on 23.09.14.
 */
public class WeaponComponent extends EntityComponent {

    public static final String NAME = "WeaponComponent";

    private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    private float cooldown = 0;

    public WeaponComponent()
    {
        this.identifier = NAME;
    }

    @Override
    public void update(GameContainer container, float delta) {
        if(cooldown > 0)
            cooldown -= delta;
    }

    @Override
    public void onAdd() {

    }

    public void fire()
    {
        if(cooldown <= 0)
        {
            cooldown = getCurrentWeapon().getCooldown();
        }
    }

    public Weapon getCurrentWeapon()
    {
        // TODO: real weapon changing
        return weapons.get(0);
    }

    public boolean isOnCooldown() {
        return (cooldown > 0);
    }

    public void addWeapon(Weapon weapon)
    {
        if(!weapons.contains(weapon))
            weapons.add(weapon);
    }
}
