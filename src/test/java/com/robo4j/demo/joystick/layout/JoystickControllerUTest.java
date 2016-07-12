/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This JoystickControllerUTest.java is part of Robo4j and robo4j-joystick
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

package com.robo4j.demo.joystick.layout;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Choustoulakis Nikolaos (@eppnikos)
 *
 * @since 28/06/16
 */


public class JoystickControllerUTest {

    private JoystickController controller;

    @Before
    public void initController() {
        controller = new JoystickController(new Joystick(90, 3));
    }

    @Test
    public void whenRadius90And3LevelsThenPovRadius15() {
        Assert.assertEquals(controller.getJoystick().getPov().getRadius(), 15, 0);
        Assert.assertEquals(controller.getLevels().size(), 3, 0);
    }
}
