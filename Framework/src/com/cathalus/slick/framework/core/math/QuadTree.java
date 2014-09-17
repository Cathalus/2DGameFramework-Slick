package com.cathalus.slick.framework.core.math;


import com.cathalus.slick.framework.core.entities.Entity;

import java.util.Set;

/**
 * Created by cathalus on 07.09.14.
 */
public class QuadTree {
    private QuadTree nodes[];
    private Entity entities[];
    private int numEntities;
    private AABB aabb;

    public QuadTree(AABB aabb, int numChildrenPerNode) {
        this.nodes = new QuadTree[4];
        this.entities = new Entity[numChildrenPerNode];
        this.numEntities = 0;
        this.aabb = aabb;
        //System.out.println("game AABB"+aabb.toString());
    }

    private QuadTree(QuadTree other) {
        this.nodes = other.nodes;
        this.entities = other.entities;
        this.numEntities = other.numEntities;
        this.aabb = other.aabb;
    }

    public void add(Entity entity) {
        if (entity.getAABB().intersectsAABB(this.aabb)) {
            if (numEntities < entities.length) {
                entities[numEntities] = entity;
                numEntities++;
            } else {
                addToChild(entity);
            }
        } else {
            QuadTree thisAsNode = new QuadTree(this);

            float dirX = entity.getX() - this.aabb.getCenter().getX();
            float dirY = entity.getY() - this.aabb.getCenter().getY();

            float minX = this.aabb.getMinX();
            float minY = this.aabb.getMinY();
            float maxX = this.aabb.getMaxX();
            float maxY = this.aabb.getMaxY();

            float expanseX = maxX - minX;
            float expanseY = maxY - minY;

            nodes = new QuadTree[4];
            numEntities = 0;
            entities = new Entity[entities.length];

            if (dirX <= 0 && dirY <= 0) {
                nodes[1] = thisAsNode;
                this.aabb = new AABB(minX - expanseX,
                        minY - expanseY, maxX, maxY);
            } else if (dirX <= 0 && dirY > 0) {
                nodes[3] = thisAsNode;
                this.aabb = new AABB(minX - expanseX, minY, maxX,
                        maxY + expanseY);

            } else if (dirX > 0 && dirY > 0) {
                nodes[2] = thisAsNode;
                this.aabb = new AABB(minX, minY, maxX + expanseX,
                        maxY + expanseY);

            } else if (dirX > 0 && dirY <= 0) {
                nodes[0] = thisAsNode;
                this.aabb = new AABB(minX, minY - expanseY, maxX
                        + expanseX, maxY);
            } else {
                throw new AssertionError(
                        "Error: QuadTree direction is invalid (?): "
                                + dirX + " (dirX) " + dirY
                                + " (dirY)");
            }

            add(entity);
        }
    }

    public boolean remove(Entity entity) {
        if (!entity.getAABB().intersectsAABB(aabb)) {
            return false;
        }

        for (int i = 0; i < numEntities; i++) {
            if (entities[i] == entity) {
                removeEntityFromList(i);
            }
        }

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null && nodes[i].remove(entity)) {
                nodes[i] = null;
            }
        }

        return isThisNodeEmpty();
    }

    private boolean isThisNodeEmpty() {
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                return false;
            }
        }

        return numEntities == 0;
    }

    private void removeEntityFromList(int index) {
        for (int i = index + 1; i < numEntities; i++) {
            entities[i - 1] = entities[i];
        }
        entities[numEntities - 1] = null;
        numEntities--;
    }

    public Set<Entity> getAll(Set<Entity> result) {
        return queryRange(aabb, result);
    }

    public Set<Entity> queryRange(AABB aabb, Set<Entity> result) {
        if (!aabb.intersectsAABB(this.aabb)) {
            return result;
        }

        for (int i = 0; i < numEntities; i++) {
            if (entities[i].getAABB().intersectsAABB(aabb)) {
                result.add(entities[i]);
            }
        }

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                nodes[i].queryRange(aabb, result);
            }
        }

        return result;
    }

    private void tryToAddToChildNode(Entity entity, float minX, float minY, float maxX, float maxY, int nodeIndex) {
        if (entity.getAABB().intersectsRectangle(minX, minY, maxX, maxY)) {
            if (nodes[nodeIndex] == null) {
                nodes[nodeIndex] = new QuadTree(new AABB(minX,
                        minY, maxX, maxY), entities.length);
            }

            nodes[nodeIndex].add(entity);
        }
    }

    private void addToChild(Entity entity) {
        float minX = aabb.getMinX();
        float minY = aabb.getMinY();
        float maxX = aabb.getMaxX();
        float maxY = aabb.getMaxY();

        float halfXLength = (maxX - minX) / 2.0f;
        float halfYLength = (maxY - minY) / 2.0f;

        minY += halfYLength;
        maxX -= halfXLength;

        tryToAddToChildNode(entity, minX, minY, maxX, maxY, 0);

        minX += halfXLength;
        maxX += halfXLength;

        tryToAddToChildNode(entity, minX, minY, maxX, maxY, 1);

        minY -= halfYLength;
        maxY -= halfYLength;

        tryToAddToChildNode(entity, minX, minY, maxX, maxY, 3);

        minX -= halfXLength;
        maxX -= halfXLength;

        tryToAddToChildNode(entity, minX, minY, maxX, maxY, 2);
    }
}
