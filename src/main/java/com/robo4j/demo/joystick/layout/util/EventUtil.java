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

package com.robo4j.demo.joystick.layout.util;

import com.robo4j.demo.joystick.layout.events.JoystickEvent;
import com.robo4j.demo.joystick.layout.events.enums.JoystickEventEnum;
import com.robo4j.demo.joystick.layout.events.enums.QuadrantEnum;
import javafx.scene.input.MouseEvent;

/**
 * @author Miro Kopecky (@miragemiko)
 * @since 28.06.2016
 */
public class EventUtil {

    public static JoystickEvent createEvent(MouseEvent e, JoystickEventEnum joystickEvent, int level, QuadrantEnum quadrantAfter){
        return new JoystickEvent(e.getSource(),
                e.getTarget(), joystickEvent, e.getX(), e.getY(), quadrantAfter, level);
    }
}
