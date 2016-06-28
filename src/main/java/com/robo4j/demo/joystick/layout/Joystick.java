package com.robo4j.demo.joystick.layout;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Joystick extends Group {

    private Canvas canvas;
    private Circle pov;

    public Joystick(final double radius, final int levelNumber) {
        pov = new Circle(0, Color.BLACK);
        canvas = new Canvas(radius * 2, radius * 2);
        getChildren().addAll(canvas, pov);
        new JoystickController(radius, levelNumber, this);
    }

    public Circle getPov() {
        return pov;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
