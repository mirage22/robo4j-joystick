package com.robo4j.demo.joystick.layout;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Choustoulakis Nikolaos (@eppnikos)
 * @author Miro Kopecky (@miragemiko)
 *
 * @since 28/06/16
 */

public class Joystick extends Group {

    private Canvas canvas;
    private Circle pov;
    private IntegerProperty levelsProperty;
    private DoubleProperty radiusProperty;

    public Joystick(final double radius, final int levelNumber) {
        levelsProperty = new SimpleIntegerProperty(levelNumber);
        radiusProperty = new SimpleDoubleProperty(radius);
        pov = new Circle(0, Color.BLACK);
        canvas = new Canvas(radius * 2, radius * 2);
        getChildren().addAll(canvas, pov);
        new JoystickController(this);
    }

    public Circle getPov() {
        return pov;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public int getLevels() {
        return levelsProperty.get();
    }

    public double getRadius() {
        return radiusProperty.get();
    }

    public DoubleProperty radiusProperty() {
        return radiusProperty;
    }

    public IntegerProperty levelsProperty() {
        return levelsProperty;
    }
}
