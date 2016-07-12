/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This JoystickLevelEnum.java is part of Robo4j and robo4j-joystick
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


import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Joystick event types that may happen on the canvas
 * Following events are considered in calculations
 *
 * @author Miro Kopecky (@miragemiko)
 * @author Choustoulakis Nikolaos (@eppnikos)
 *
 * @since 25/06/16
 */
public enum JoystickLevelEnum {

    //@formatter:off
    NONE    (0),
    LEVEL_1 (1),
    LEVEL_2 (2),
    LEVEL_3 (3),
    ;
    //@formatter:on
    private int level;

    private volatile static Map<Integer, JoystickLevelEnum> codeToJoystickLeveMapping;

    JoystickLevelEnum(int l) {
        this.level = l;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "JoystickLevelEnum{" +
                "level=" + level +
                '}';
    }

    public static JoystickLevelEnum getJoystickLevelByCode(int code){
        if(codeToJoystickLeveMapping == null){
            codeToJoystickLeveMapping = initMapping();
        }

        return codeToJoystickLeveMapping.get(code);
    }

    //Private Methods
    private static Map<Integer, JoystickLevelEnum> initMapping(){
        return Arrays.asList(values()).stream()
                .map(e -> new Map.Entry<Integer, JoystickLevelEnum>(){
                    @Override
                    public Integer getKey() {
                        return e.getLevel();
                    }

                    @Override
                    public JoystickLevelEnum getValue() {
                        return e;
                    }

                    @Override
                    public JoystickLevelEnum setValue(JoystickLevelEnum value) {
                        return null;
                    }
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
