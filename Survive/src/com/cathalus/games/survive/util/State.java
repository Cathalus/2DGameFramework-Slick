package com.cathalus.games.survive.util;

/**
 * Created by cathalus on 22.09.14.
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
