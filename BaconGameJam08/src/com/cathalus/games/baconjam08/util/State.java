package com.cathalus.games.baconjam08.util;

/**
 * Created by Cathalus on 18.10.2014.
 */
public enum State {
    SPLASH(0), MENU(1), GAMEPLAY(2);

    private int id;

    State(int id)
    {
        this.id = id;
    }

    public int getID()
    {
        return id;
    }

}