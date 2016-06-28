/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This QuadrantEnum.java is part of Robo4j and robo4j-joystick
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

package com.robo4j.demo.joystick.layout.events.enums;


/**
 * @author Choustoulakis Nikolaos (@eppnikos)
 *
 * @since 25/06/16
 */
public enum QuadrantEnum {
    // @formatter:off
    //name          id,     start angle,    end angle
    NONE            (0,     0,              0),
    QUADRANT_I      (1,     0,              90),
    QUADRANT_II     (2,     90,             90),
    QUADRANT_III    (3,     180,            90),
    QUADRANT_IV     (4,     270,            90), 
    ;
// @formatter:on

    private int quadrant;
    private int startAngle;
    private int angleExtend;

    private QuadrantEnum(int q, int startAngle, int angleExtend) {
        quadrant = q;
        this.startAngle = startAngle;
        this.angleExtend = angleExtend;
    }

    public int getQuadrant() {
        return quadrant;
    }

    public int getAngleExtend() {
        return angleExtend;
    }

    public int getStartAngle() {
        return startAngle;
    }
}
