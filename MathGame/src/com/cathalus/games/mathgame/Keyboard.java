package com.cathalus.games.mathgame;

import org.newdawn.slick.Input;

/**
 * Created by Cathalus on 23.11.2014.
 */
public class Keyboard {

    public static int getNumber(Input in)
    {
        if (in.isKeyPressed(Input.KEY_0))
        {
            return 0;
        }else if(in.isKeyPressed(Input.KEY_1))
        {
            return 1;
        }else if(in.isKeyPressed(Input.KEY_2))
        {
            return 2;
        }else if(in.isKeyPressed(Input.KEY_3))
        {
            return 3;
        }else if(in.isKeyPressed(Input.KEY_4))
        {
            return 4;
        }else if(in.isKeyPressed(Input.KEY_5))
        {
            return 5;
        }else if(in.isKeyPressed(Input.KEY_6))
        {
            return 6;
        }else if(in.isKeyPressed(Input.KEY_7))
        {
            return 7;
        }else if(in.isKeyPressed(Input.KEY_8))
        {
            return 8;
        }else if(in.isKeyPressed(Input.KEY_9))
        {
            return 9;
        }else if(in.isKeyDown(Input.KEY_BACK))
        {
            return -1;
        }
        return 42;
    }

}
