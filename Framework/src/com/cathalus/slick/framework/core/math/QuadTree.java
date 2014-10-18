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
    private BoundingBox boundingBox;

    public QuadTree(BoundingBox boundingBox, int numChildrenPerNode) {
        this.nodes = new QuadTree[4];
        this.entities = new Entity[numChildrenPerNode];
        this.numEntities = 0;
        this.boundingBox = boundingBox;
        //System.out.println("game BoundingBox"+boundingBox.toString());
    }

    private QuadTree(QuadTree other) {
        this.nodes = other.nodes;
        this.entities = other.entities;
        this.numEntities = other.numEntities;
        this.boundingBox = other.boundingBox;
    }

    public void add(Entity entity) {
        if (entity.getAABB().intersectsBB(this.boundingBox)) {
            if (numEntities < entities.length) {
                entities[numEntities] = entity;
                numEntities++;
            } else {
                addToChild(entity);
            }
        } else {
            QuadTree thisAsNode = new QuadTree(this);

            float dirX = entity.getX() - this.boundingBox.getCenter().getX();
            float dirY = entity.getY() - this.boundingBox.getCenter().getY();

            float minX = this.boundingBox.getMinX();
            float minY = this.boundingBox.getMinY();
            float maxX = this.boundingBox.getMaxX();
            float maxY = this.boundingBox.getMaxY();

            float expanseX = maxX - minX;
            float expanseY = maxY - minY;

            nodes = new QuadTree[4];
            numEntities = 0;
            entities = new Entity[entities.length];

            if (dirX <= 0 && dirY <= 0) {
                nodes[1] = thisAsNode;
                this.boundingBox = new BoundingBox(minX - expanseX,
                        minY - expanseY, maxX, maxY);
            } else if (dirX <= 0 && dirY > 0) {
                nodes[3] = thisAsNode;
                this.boundingBox = new BoundingBox(minX - expanseX, minY, maxX,
                        maxY + expanseY);

            } else if (dirX > 0 && dirY > 0) {
                nodes[2] = thisAsNode;
                this.boundingBox = new BoundingBox(minX, minY, maxX + expanseX,
                        maxY + expanseY);

            } else if (dirX > 0 && dirY <= 0) {
                nodes[0] = thisAsNode;
                this.boundingBox = new BoundingBox(minX, minY - expanseY, maxX
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
        if (!entity.getAABB().intersectsBB(boundingBox)) {
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
        return queryRange(boundingBox, result);
    }

    public Set<Entity> queryRange(BoundingBox boundingBox, Set<Entity> result) {
        if (!boundingBox.intersectsBB(this.boundingBox)) {
            return result;
        }

        for (int i = 0; i < numEntities; i++) {
            if (entities[i].getAABB().intersectsBB(boundingBox)) {
                result.add(entities[i]);
            }
        }

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                nodes[i].queryRange(boundingBox, result);
            }
        }

        return result;
    }

    private void tryToAddToChildNode(Entity entity, float minX, float minY, float maxX, float maxY, int nodeIndex) {
        if (entity.getAABB().intersectsRectangle(minX, minY, maxX, maxY)) {
            if (nodes[nodeIndex] == null) {
                nodes[nodeIndex] = new QuadTree(new BoundingBox(minX,
                        minY, maxX, maxY), entities.length);
            }

            nodes[nodeIndex].add(entity);
        }
    }

    private void addToChild(Entity entity) {
        float minX = boundingBox.getMinX();
        float minY = boundingBox.getMinY();
        float maxX = boundingBox.getMaxX();
        float maxY = boundingBox.getMaxY();

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
