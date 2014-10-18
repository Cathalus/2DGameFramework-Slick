package com.cathalus.games.baconjam08.util;

import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Cathalus on 18.10.2014.
 */
public class Util {

    public static void clampVector(Vector2f vec, float min, float max)
    {
        float x = vec.getX();
        float y = vec.getY();

        if(x < min) {
            x = min;
        }else if(x > max)
        {
            x = max;
        }

        if(y < min)
        {
            y = min;
        }else if(y > max)
        {
            y = max;
        }

        vec.set(x,y);
    }

}
