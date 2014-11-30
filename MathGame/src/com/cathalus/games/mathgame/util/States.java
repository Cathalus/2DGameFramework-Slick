package com.cathalus.games.mathgame.util;

/**
 * Created by Cathalus on 23.11.2014.
 */
public enum States {

   AdditionSubtraction(0), MainMenu(1);

    int id = -1;
    States(int id)
    {
        this.id = id;
    }

    public int getID() {
        return id;
    }
}
