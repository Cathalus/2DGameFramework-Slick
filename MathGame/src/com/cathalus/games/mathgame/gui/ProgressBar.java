package com.cathalus.games.mathgame.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class ProgressBar {

    private int current, max, height, width;
    private Vector2f position;

    public ProgressBar(int current, int max, int width, Vector2f position)
    {
        this.current = current;
        this.max = max;
        this.width = width;
        this.height = 25;
        this.position = position;
    }

    public void setCurrent(int current)
    {
        this.current = current;
    }

    public int getCurrent()
    {
        return current;
    }

    public int getMax() {
        return max;
    }

    public void increaseCurrent()
    {
        current++;
    }

    public void decreaseCurrent()
    {
        current--;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(Graphics g)
    {
        g.setColor(new Color(1.0f,1.0f,1.0f,0.5f));
        g.fillRect(position.x, position.y, width, height);
        if(current > 0) {
            g.setColor(new Color(0f,1f,00f,0.8f));
            g.fillRect(position.x,position.y,width*((float) current/max),height);
        }
        g.setColor(Color.black);
        g.drawString(current+"/"+max,width/2,5);
    }

}
