package com.cathalus.games.baconjam08.weapons;

/**
 * Created by cathalus on 23.09.14.
 */
public abstract class Weapon {

    protected float damage;
    protected float cooldown;
    protected float velocity;
    protected String sfx;

    public Weapon(float damage, float cooldown, float velocity, String sfx)
    {
        this.damage = damage;
        this.cooldown = cooldown;
        this.sfx = sfx;
        this.velocity = velocity;
    }

    public float getDamage()
    {
        return damage;
    }
    public float getCooldown() { return cooldown; }
    public String getSfx() {
        return sfx;
    }
    public float getVelocity() {
        return velocity;
    }
}
