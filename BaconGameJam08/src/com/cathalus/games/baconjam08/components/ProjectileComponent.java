package com.cathalus.games.baconjam08.components;

import com.cathalus.games.baconjam08.util.Globals;
import com.cathalus.games.baconjam08.weapons.Weapon;
import com.cathalus.slick.framework.core.entities.EntityComponent;
import org.newdawn.slick.GameContainer;

/**
 * Created by cathalus on 23.09.14.
 */
public class ProjectileComponent extends EntityComponent {

    public static final String NAME = "ProjectileComponent";

    private float damage;
    private boolean hit = false;
    private float ttl = Globals.PROJECTILE_TTL;

    public ProjectileComponent(Weapon w)
    {
        damage = w.getDamage();
        this.identifier = NAME;
    }

    @Override
    public void update(GameContainer container, float delta) {
        ttl -= delta;
        if(ttl <= 0)
        {
            hit = true;
        }
    }

    @Override
    public void onAdd() {

    }


    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public float getDamage()
    {
        return damage;
    }

}
