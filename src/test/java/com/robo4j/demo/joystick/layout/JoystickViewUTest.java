/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This JoystickViewUTest.java is part of Robo4j and robo4j-joystick
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
import org.junit.Test;

/**
 * @author Choustoulakis Nikolaos (@eppnikos)
 *
 * @since 28/06/16
 */

public class JoystickViewUTest {

    @Test
    public void testThatViewHasCanvasAndCircle() {
        double radius = 90;
        int levels = 3;
        double povRadius = radius / levels;
        povRadius = povRadius / 2;
        Joystick joystick = new Joystick(radius, levels);

        Assert.assertNotNull(joystick.getPov());
        Assert.assertNotNull(joystick.getCanvas());
        Assert.assertEquals(povRadius, joystick.getPov().getRadius(), 0);
        Assert.assertEquals(joystick.getCanvas().getWidth(), radius * 2, 0);
        Assert.assertEquals(joystick.getCanvas().getHeight(), radius * 2, 0);
    }
}
