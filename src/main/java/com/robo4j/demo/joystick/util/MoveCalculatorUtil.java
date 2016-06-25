/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This MoveCalculatorUtil.java is part of Robo4j and robo4j-joystick
 *
 *     robo4j-joystick is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     robo4j-joystick is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Robo4j and robo4j-joystick .  If not, see <http://www.gnu.org/licenses/>.
 */

package com.robo4j.demo.joystick.util;


/**
 * Movement calculation on the pane
 *
 * @author Choustoulakis Nikolaos (@eppnikos)
 * @author Miro Kopecky (@miragemiko)
 * @since 25/06/16
 */
/* (x-j)^2 + (y-k)^2 = r^2 */
public final class MoveCalculatorUtil {

    private MoveCalculatorUtil() {
    }
    
    public static double determinePointXOnCircleCircumference(double angle, double radius, double center) {
        return Math.sin(angle) * radius + center;
    }

    public static double determinePointYOnCircleCircumference(double angle, double radius, double center) {
        return Math.cos(angle) * radius + center;
    }

    public static double resetToCenterX(double x, double center) {
        return x - center;
    }

    public static double resetToCenterY(double y, double center) {
        return center - y;
    }

    public static double determineAngle(double x, double y) {
        double rad = Math.atan2(y, x);
        return rad + Math.PI / 2;
    }

    public static boolean isInsideCircleArea(double radius, double x, double y) {
        return Math.pow(Math.pow(x, 2) + Math.pow(y, 2), 0.5) < radius;
    }

    public static boolean isInsideCircleArea(double radius, double x, double y, double j, double k) {
        return Math.pow(Math.pow(x - j, 2) + Math.pow(y - k, 2), 0.5) > radius;
    }

    //Private Method
    @SuppressWarnings("unused")
    private static double determineRadius(double x, double y) {
        return Math.pow(Math.pow(x, 2) + Math.pow(y, 2), 0.5);
    }
}
