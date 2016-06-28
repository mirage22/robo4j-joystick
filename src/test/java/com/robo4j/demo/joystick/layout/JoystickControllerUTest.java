package com.robo4j.demo.joystick.layout;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JoystickControllerUTest {

    private JoystickController controller;

    @Before
    public void initController() {
        controller = new JoystickController(new Joystick(90, 3));
    }

    @Test
    public void whenRadius90And3LevelsThenPovRadius15() {
        Assert.assertEquals(controller.joystick.getPov().getRadius(), 15, 0);
        Assert.assertEquals(controller.levels.size(), 3, 0);
        Assert.assertEquals(controller.levels.size(), 3, 0);
    }
}
