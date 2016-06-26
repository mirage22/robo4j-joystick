/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This LegoBrickSetupProperties.java is part of Robo4j and robo4j-joystick
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

package com.robo4j.demo.joystick.system;

import com.robo4j.commons.annotation.SystemProperties;
import com.robo4j.commons.control.RoboSystemConfig;
import com.robo4j.core.lego.LegoBrickProperties;
import com.robo4j.core.lego.LegoBrickPropertiesHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * Default Robot configuration
 *
 * note: Commands and sensor options are not required for the demo
 * @author Miro Kopecky (@miragemiko)
 * @since 25/04/16.
 */

@SystemProperties
public class LegoBrickSetupProperties implements LegoBrickProperties, RoboSystemConfig {

    private Map<String, String> bricks;
    private String corePackage;
    private String enginePackage;

    public LegoBrickSetupProperties() {
        final Map<String, String> bricks = new HashMap<>();
        bricks.put(LegoBrickPropertiesHolder.BRICK_IP_1, "<ROBOT_IP>");
        this.bricks = bricks;
        this.corePackage = "com.robo4j.demo.joystick";
        this.enginePackage = "com.robo4j.demo.joystick.engine";
    }

    @Override
    public Map<String, String> getBricks() {
        return bricks;
    }

    @Override
    public String getCorePackage() {
        return corePackage;
    }

    @Override
    public String getCommandPackage() {
        return null;
    }

    @Override
    public String getEnginePackage() {
        return enginePackage;
    }

    @Override
    public String getSensorPackage() {
        return null;
    }

    @Override
    public String toString() {
        return "LegoBrickSetupProperties{" +
                "bricks=" + bricks +
                ", corePackage='" + corePackage + '\'' +
                ", enginePackage='" + enginePackage + '\'' +
                '}';
    }
}
