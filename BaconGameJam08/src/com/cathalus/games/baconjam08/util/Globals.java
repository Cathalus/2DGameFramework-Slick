package com.cathalus.games.baconjam08.util;

import com.cathalus.games.baconjam08.entities.Player;
import com.cathalus.slick.framework.core.Level;

/**
 * Created by Cathalus on 18.10.2014.
 */
public class Globals {

    public enum Triggers{
        PLAYER_SPAWN, ENEMY_SPAWN;
    }

    public enum Difficulty{
        EASY(0.25f), NORMAL(0.50f), HARD(1);

        private float percentage;

        Difficulty(float percentage)
        {
            this.percentage = percentage;
        }

        public float getPercentage()
        {
            return percentage;
        }
    }

    public static Level CURRENT_LEVEL = null;
    public static Player CURRENT_PLAYER = null;
    public static Difficulty CURRENT_DIFFICULTY = Difficulty.HARD;
}
