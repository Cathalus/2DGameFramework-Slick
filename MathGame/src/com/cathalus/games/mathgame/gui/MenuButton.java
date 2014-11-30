package com.cathalus.games.mathgame.gui;

import com.cathalus.slick.framework.core.math.BoundingBox;
import com.cathalus.slick.framework.core.resources.ResourceManager;
import jdk.internal.util.xml.impl.*;
import org.newdawn.slick.*;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Cathalus on 30.11.2014.
 */
public class MenuButton {

    private String text, font;
    private Color color;
    private int posX, posY;
    private boolean cVert = false;
    private boolean cHori = false;

    public MenuButton(String text, String font)
    {
        this.text = text;
        this.font = font;
    }

    public void draw(Graphics g)
    {
        g.setFont(ResourceManager.getFont(font));
        int width = ResourceManager.getFont(font).getWidth(text);
        int height = ResourceManager.getFont(font).getHeight(text);
        int x = (posX-width)/2;
        int y = (posY-height)/2;
        g.drawString(text,x,y);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public BoundingBox getBoundingBox()
    {
        int width = ResourceManager.getFont(font).getWidth(text);
        int height = ResourceManager.getFont(font).getHeight(text);
        int x = (posX-width)/2;
        int y = (posY-height)/2;
        return new BoundingBox(x,(y-height)/2,x+width,(y+height)/2);
    }
}
