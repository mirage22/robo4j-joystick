/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This JoystickEventEnum.java is part of Robo4j and robo4j-joystick
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

package com.robo4j.demo.joystick.events.enums;

import com.robo4j.demo.joystick.events.JoystickEvent;

import javafx.event.EventType;
import javafx.scene.input.InputEvent;

/**
 * Joystick event types that may happen on the canvas
 * Following events are considered in calculations
 *
 * @author Miro Kopecky (@miragemiko)
 * @author Choustoulakis Nikolaos (@eppnikos)
 *
 * @since 25/06/16
 */
@Deprecated
public enum JoystickEventEnum {

    //@formatter:off
    UNDEFINED           (1, new EventType<>(InputEvent.ANY, "POV")),
    QUADRANT_CHANGED    (2, new EventType<>(InputEvent.ANY, "POV_CHANGE_QUADRANT")),
    LEVEL_CHANGED       (3, new EventType<>(InputEvent.ANY, "POV_LEVEL_CHANGED")),
    ;
    //@formatter:on
    private final int id;
    private final EventType<JoystickEvent>  eventType;

    JoystickEventEnum(int id, EventType<JoystickEvent> eventType){
        this.id = id;
        this.eventType = eventType;
    }

    public int getId() {
        return id;
    }

    public EventType<JoystickEvent> getEventType() {
        return eventType;
    }

    //    public static final EventType<JoystickEventHolder> ANY = new EventType<>(InputEvent.ANY, "POV");
//    public static final EventType<JoystickEventHolder> POV_CHANGE_QUADRANT = new EventType<>(InputEvent.ANY, "POV_CHANGE_QUADRANT");
//    public static final EventType<JoystickEventHolder> POV_LEVEL_CHANGED = new EventType<>(InputEvent.ANY, "POV_LEVEL_CHANGED");



}
