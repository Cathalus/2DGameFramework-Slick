package com.cathalus.games.survive.entities.weapons;

/**
 * Created by cathalus on 23.09.14.
 */
public abstract class Weapon {

    protected float damage;
    protected float cooldown;
    protected String sfx;

    public Weapon(float damage, float cooldown, String sfx)
    {
        this.damage = damage;
        this.cooldown = cooldown;
        this.sfx = sfx;
    }

    public float getDamage()
    {
        return damage;
    }
    public float getCooldown() { return cooldown; }
    public String getSfx() {
        return sfx;
    }
}
