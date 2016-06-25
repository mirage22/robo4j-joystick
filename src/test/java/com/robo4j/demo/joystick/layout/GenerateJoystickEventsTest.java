package com.robo4j.demo.joystick.layout;

import org.junit.Test;

import com.robo4j.demo.joystick.events.JoystickEvent;

public class GenerateJoystickEventsTest extends AbstractApplicationFXTest {


    @Test
    public void test() {
        JOYSTICK.getPov().setCenterX(10);
        JOYSTICK.getPov().setCenterY(10);

        JOYSTICK.getPov().setCenterX(70);
        JOYSTICK.getPov().setCenterY(70);

        JOYSTICK.addEventHandler(JoystickEvent.ANY, e -> System.out.println("any"));
    }

}
