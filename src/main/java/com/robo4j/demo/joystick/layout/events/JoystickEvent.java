/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This JoystickEvent.java is part of Robo4j and robo4j-joystick
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

package com.robo4j.demo.joystick.layout.events;

import com.robo4j.demo.joystick.layout.events.enums.JoystickEventEnum;
import com.robo4j.demo.joystick.layout.events.enums.QuadrantEnum;

import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.input.InputEvent;

/**
 * Event holder holds information about joystick position on the canvas
 * 
 * @author Choustoulakis Nikolaos
 * @author Miro Kopecky (@miragemiko)
 * @since 11.06.2016
 */
public class JoystickEvent extends InputEvent {

    private static final long serialVersionUID = 2231266847422883646L;

    private transient double x;
    private transient double y;
    private transient QuadrantEnum quadrant;
    private transient int joystickLevel;

    public JoystickEvent(Object source, EventTarget target,
                         JoystickEventEnum eventType,
                         double x, double y, QuadrantEnum quadrant,
                         int joystickLevel) {
        super(source, target, eventType.getEventType());
        this.x = x;
        this.y = y;
        this.quadrant = quadrant;
        this.joystickLevel = joystickLevel;
    }

    public final double getX() {
        return x;
    }

    public final double getY() {
        return y;
    }

    public final QuadrantEnum getQuadrant() {
        return quadrant;
    }

    public final int getJoystickLevel() {
        return joystickLevel;
    }


}
