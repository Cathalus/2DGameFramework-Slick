package com.cathalus.slick.framework.core.input;

/**
 * Created by cathalus on 23.09.14.
 */
public class XBOX360 {

    public static enum Buttons
    {
        A(0), B(1), X(2), Y(3), LB(4), RB(5), SELECT(6), MODE(7), LEFT_STICK(8), RIGHT_STICK(9);

        private int id;

        Buttons(int id)
        {
            this.id = id;
        }

        public int getID() {
            return id;
        }
    }

    public static enum Axis
    {
        THUMB_LEFT_X(1), THUMB_LEFT_Y(2), THUMB_RIGHT_X(4), THUMB_RIGHT_Y(5);

        private int id;

        Axis(int id)
        {
            this.id = id;
        }

        public int getID()
        {
            return id;
        }
    }



}
