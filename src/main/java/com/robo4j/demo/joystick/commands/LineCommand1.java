/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This LineCommand1.java is part of Robo4j and robo4j-joystick
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

package com.robo4j.demo.joystick.commands;

import com.robo4j.commons.annotation.BatchAnnotation;
import com.robo4j.core.bridge.command.BasicCommand;
import com.robo4j.core.bridge.command.cache.BatchCommand;

/**
 * @author Miro Kopecky (@miragemiko)
 *
 * @since 25/06/16
 */
@BatchAnnotation(name = "line1")
public class LineCommand1 implements BatchCommand, BasicCommand {

    private String name;
    private String batch;

    public LineCommand1() {
        this.name = "line1";
        this.batch = "move(30),back(30),move(30)";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBatch() {
        return batch;
    }

    @Override
    public void setBatch(String batch) {
        this.batch = batch;
    }

    @Override
    public String toString() {
        return "LineCommand1{" +
                "name='" + name + '\'' +
                ", batch='" + batch + '\'' +
                '}';
    }
}
