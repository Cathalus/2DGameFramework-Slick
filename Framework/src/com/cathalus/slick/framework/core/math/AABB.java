package com.cathalus.slick.framework.core.math;

import org.newdawn.slick.geom.Vector2f;


/**
 * Created by cathalus on 17.09.14.
 */
public class AABB {

    private float minX, minY, maxX, maxY;

    public AABB(float minX, float minY, float maxX, float maxY)
    {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public boolean intersectsAABB(AABB other)
    {
        return intersectsRectangle(other.getMinX(),other.getMinY(),other.getMaxX(),other.getMaxY());
    }

    public boolean intersectsRectangle(float minX, float minY, float maxX, float maxY)
    {
        return this.minX < maxX && this.maxX > minX && this.minY < maxY && this.maxY > minY;
    }

    public float getMinX() {
        return minX;
    }

    public float getMinY() {
        return minY;
    }

    public float getMaxX() {
        return maxX;
    }

    public float getMaxY() {
        return maxY;
    }

    public float getCenterX()
    {
        return (maxX+minX)/2;
    }

    public float getCenterY()
    {
        return (minY+maxY)/2;
    }

    public Vector2f getCenter()
    {
        return new Vector2f(getCenterX(),getCenterY());
    }

    @Override
    public String toString() { return "[AABB ("+minX+"|"+minY+") ("+maxX+"|"+maxY+")]"; }

    public float getWidth() {
        return Math.abs(maxX-minX);
    }

    public float getHeight() {
        return Math.abs(maxY-minY);
    }
}
