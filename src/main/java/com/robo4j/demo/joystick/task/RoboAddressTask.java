/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This RoboAddressTask.java is part of Robo4j and robo4j-joystick
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

package com.robo4j.demo.joystick.task;

import javafx.concurrent.Task;

/**
 * JavaFx task checking IPAddress
 *
 * @author Miro Kopecky (@miragemiko)
 */
public class RoboAddressTask extends Task<String> {

    private String controlPad;

    public RoboAddressTask(String controlPad) {
        this.controlPad = controlPad;
    }

    @Override
    protected String call() throws Exception {
        final String result = controlPad;
        updateMessage(result);
        return result;
    }

}
