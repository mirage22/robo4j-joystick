/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This EventUtil.java is part of Robo4j and robo4j-joystick
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

import com.robo4j.demo.joystick.events.JoystickEventHolder;
import com.robo4j.demo.joystick.events.enums.JoystickEventEnum;
import com.robo4j.demo.joystick.events.enums.LevelEnum;
import com.robo4j.demo.joystick.events.enums.QuadrantEnum;
import javafx.scene.input.MouseEvent;

/**
 *
 * Event Related utils methods
 *
 * @author Miro Kopecky (@miragemiko)
 * @author Choustoulakis Nikolaos (@eppnikos)
 *
 * @since 25/06/16
 */
public final class EventUtil {

    public static JoystickEventHolder createEvent(MouseEvent e, JoystickEventEnum joystickEvent, LevelEnum level, QuadrantEnum quadrant ){
            return new JoystickEventHolder(e.getSource(), e.getTarget(), joystickEvent.getEventType(), e.getX(), e.getY(), quadrant, level);

    }

    public static LevelEnum determineLevel(double radius1, double radius2, double x, double y) {
        if (y == x && y == 0) {
            return LevelEnum.NONE;
        }
        if (MoveCalculatorUtil.isInsideCircleArea(radius1, x, y)) {
            return LevelEnum.LEVEL_1;
        } else if (MoveCalculatorUtil.isInsideCircleArea(radius2, x, y)) {
            return LevelEnum.LEVEL_2;
        }
        return LevelEnum.LEVEL_3;
    }

    public static QuadrantEnum whichQuadrant(double x, double y) {
        if (y == x && y == 0) {
            return QuadrantEnum.NONE;
        }
        if (x * y >= 0) {
            if (x < 0) {
                return QuadrantEnum.QUADRANT_III;
            } else {
                return QuadrantEnum.QUADRANT_I;
            }
        } else {
            if (x > 0) {
                return QuadrantEnum.QUADRANT_IV;
            } else {
                return QuadrantEnum.QUADRANT_II;
            }
        }
    }
}
