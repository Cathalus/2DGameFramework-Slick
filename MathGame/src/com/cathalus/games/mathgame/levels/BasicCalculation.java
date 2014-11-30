package com.cathalus.games.mathgame.levels;

import com.cathalus.games.mathgame.math.Calculation;

import java.util.Random;

/**
 * Created by Cathalus on 23.11.2014.
 */
public class BasicCalculation extends Level {

    public Calculation getCalculation()
    {
        int a = 0;
        int b = 0;
        Calculation.Type type = null;
        Calculation.Operator op = null;
        while(a-b<=0) {
            Random rand = new Random();
            type = Calculation.Type.values()[rand.nextInt(Calculation.Type.values().length - 1)];
            op = Calculation.Operator.values()[rand.nextInt(Calculation.Operator.values().length)];
            a = rand.nextInt(20);
            b = rand.nextInt(20 - a);
        }
        return new Calculation(op,type,a,b);
    }

}
