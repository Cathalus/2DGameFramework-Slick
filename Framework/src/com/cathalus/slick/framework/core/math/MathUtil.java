package com.cathalus.slick.framework.core.math;

/**
 * Created by cathalus on 17.09.14.
 */
public class MathUtil {

    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

}
