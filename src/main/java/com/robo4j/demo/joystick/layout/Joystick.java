/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This Joystick.java is part of Robo4j and robo4j-joystick
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
