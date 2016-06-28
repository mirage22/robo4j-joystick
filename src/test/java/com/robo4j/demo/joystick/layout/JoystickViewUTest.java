package com.robo4j.demo.joystick.layout;

import org.junit.Assert;
import org.junit.Test;

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
