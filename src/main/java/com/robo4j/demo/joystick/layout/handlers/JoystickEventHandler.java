/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This JoystickEventHandler.java is part of Robo4j and robo4j-joystick
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

package com.robo4j.demo.joystick.layout.handlers;

import com.robo4j.demo.joystick.layout.events.JoystickEvent;
import com.robo4j.demo.joystick.layout.events.enums.JoystickLevelEnum;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.ZoomEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


/**
 * @author Choustoulakis Nikolaos (@eppnikos)
 * @author Miro Kopecky (@miragemiko)
 *
 * @since 28.06.2016
 */

public class JoystickEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(JoystickEventHandler.class);
    private static final int ANGLE_360 = 360;
    private static final int MULTIPLIER = 2;

    public void onMouseDragged(Circle pov, DoubleProperty povCenteXProperty, DoubleProperty povCenteYProperty) {
        pov.setCenterX(povCenteXProperty.get());
        pov.setCenterY(povCenteYProperty.get());
    }

    public void setOnZoom(ZoomEvent e, DoubleProperty radiusProperty) {
        radiusProperty.set(radiusProperty.get() * e.getZoomFactor());
    }

    public void drawTargetLevel(JoystickEvent e, Canvas canvas, DoubleProperty radiusProperty, Map<Integer, IntegerProperty> levels) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawCircles(canvas, radiusProperty, levels, levels.size());
        //@formatter:off
        gc.setFill(Color.YELLOW);
        gc.fillArc(
                radiusProperty.get() - levels.get(getJoystickLevel(e.getJoystickLevel())).get(),
                radiusProperty.get() - levels.get(getJoystickLevel(e.getJoystickLevel())).get(),
                levels.get(getJoystickLevel(e.getJoystickLevel())).get() * MULTIPLIER,
                levels.get(getJoystickLevel(e.getJoystickLevel())).get() * MULTIPLIER,
                e.getQuadrant().getStartAngle(), 
                e.getQuadrant().getAngleExtend(), ArcType.ROUND);
        //@formatter:on
        draw(canvas, radiusProperty, levels, e.getJoystickLevel().getLevel());
    }

    public void drawCircles(Canvas canvas, DoubleProperty radiusProperty, Map<Integer, IntegerProperty> levels, int levelNumber) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        draw(canvas, radiusProperty, levels, levelNumber);
    }

    public void draw(Canvas canvas, DoubleProperty radiusProperty, Map<Integer, IntegerProperty> levels, int levelNumber) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        levels.entrySet().stream().filter(e -> e.getKey() < levelNumber).sorted((l, r) -> Integer.compare(r.getValue().get(), l.getValue().get()))
                .forEach(i -> {
                    switch (i.getKey()){
                        case 0:
                            gc.setFill(Color.YELLOWGREEN);
                            break;
                        case 1:
                            gc.setFill(Color.ROYALBLUE);
                            break;
                        case 2:
                            gc.setFill(Color.STEELBLUE);
                            break;
                        case 3:
                            gc.setFill(Color.SKYBLUE);
                            break;

                    }

                    gc.setStroke(Color.DARKBLUE);
                    double position = radiusProperty.get() - i.getValue().get();
                    int size = i.getValue().get() * MULTIPLIER;
                    gc.fillOval(position, position, size, size);
                    gc.strokeArc(position, position, size, size, 0, ANGLE_360, ArcType.CHORD);
                });
    }

    //Private Methods
    private int getJoystickLevel(JoystickLevelEnum levelEnum){
        return levelEnum.getLevel();
    }
}
