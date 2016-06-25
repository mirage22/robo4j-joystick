/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This JoystickPane.java is part of Robo4j and robo4j-joystick
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

import com.robo4j.demo.joystick.JoystickEventProducer;
import com.robo4j.demo.joystick.util.MoveCalculatorUtil;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 *
 * @author Choustoulakis Nikolaos (@eppnikos)
 *
 * @since 25/06/16
 */
@Deprecated
public class JoystickPane extends Pane {

    private DoubleProperty radiusProperty;
    private DoubleProperty level2Radius;
    private DoubleProperty level3Radius;
    private DoubleProperty povRadius;

    private DoubleProperty povCenterXProperty;
    private DoubleProperty povCenterYProperty;

    private Canvas canvas;
    private Circle pov;

    private JoystickEventProducer eventProducer;

    private double xOnClick;
    private double yOnClick;

    public JoystickPane(final double radius) {
        this.radiusProperty = new SimpleDoubleProperty(radius);
        this.level2Radius = new SimpleDoubleProperty();
        this.level3Radius = new SimpleDoubleProperty();
        this.povRadius = new SimpleDoubleProperty();
        this.povCenterXProperty = new SimpleDoubleProperty();
        this.povCenterYProperty = new SimpleDoubleProperty();
        this.pov = new Circle(0, Color.RED);
        this.canvas = new Canvas(300, 300);

        eventProducer = new JoystickEventProducer(this, level2Radius, level3Radius, pov.centerXProperty(), pov.centerYProperty(),
                povCenterXProperty, povCenterYProperty);

        this.level2Radius.bind(this.radiusProperty.divide(3));
        this.level3Radius.bind(this.radiusProperty.divide(1.5));
        this.povRadius.bind(this.radiusProperty.divide(4.5));
        this.pov.radiusProperty().bindBidirectional(povRadius);
        this.povCenterXProperty.bind(this.radiusProperty.add(canvas.layoutXProperty()));
        this.povCenterYProperty.bind(this.radiusProperty.add(canvas.layoutYProperty()));
        drawCircles();
        bindPointerToCanvas();
        addCanvasListener();
        addPointerListener();
        addEventHandler(MouseEvent.ANY, event -> eventProducer.handle(event));
        getChildren().addAll(canvas, pov);
    }

    private void addPointerListener() {
        pov.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::draggedPov);
        pov.setOnMouseReleased(e -> bindPointerToCanvas());
    }

    //Private Methods
    private void addCanvasListener() {
        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
            xOnClick = e.getX();
            yOnClick = e.getY();
        });
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, (e) -> {
            canvas.setLayoutX(e.getSceneX() - xOnClick);
            canvas.setLayoutY(e.getSceneY() - yOnClick);
            pov.setCenterX(povCenterXProperty.get());
            pov.setCenterY(povCenterYProperty.get());
        });
    }

    private void bindPointerToCanvas() {
        pov.setCenterX(povCenterXProperty.get());
        pov.setCenterY(povCenterYProperty.get());
    }

    private void drawCircles() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GRAY);
        gc.fillOval(0, 0, radiusProperty.get() * 2, radiusProperty.get() * 2);
        gc.setFill(Color.AQUA);
        gc.fillOval(0 + radiusProperty.get() - level3Radius.get(), 0 + radiusProperty.get() - level3Radius.get(), level3Radius.get() * 2,
                level3Radius.get() * 2);
        gc.setFill(Color.BISQUE);
        gc.fillOval(0 + radiusProperty.get() - level2Radius.get(), 0 + radiusProperty.get() - level2Radius.get(), level2Radius.get() * 2,
                level2Radius.get() * 2);
    }

    private void draggedPov(MouseEvent e) {
        double mouseX = e.getX();
        double mouseY = e.getY();

        if (MoveCalculatorUtil.isInsideCircleArea(radiusProperty.get() - povRadius.get(), mouseX, mouseY, povCenterXProperty.get(), povCenterYProperty.get())) {
            double x = MoveCalculatorUtil.resetToCenterX(mouseX, povCenterXProperty.get());
            double y = MoveCalculatorUtil.resetToCenterY(mouseY, povCenterYProperty.get());
            double angle = MoveCalculatorUtil.determineAngle(x, y);
            pov.setCenterX(MoveCalculatorUtil.determinePointXOnCircleCircumference(angle, radiusProperty.get() - povRadius.get(), povCenterXProperty.get()));
            pov.setCenterY(MoveCalculatorUtil.determinePointYOnCircleCircumference(angle, radiusProperty.get() - povRadius.get(), povCenterYProperty.get()));
            return;
        }
        pov.setCenterX(mouseX);
        pov.setCenterY(mouseY);
    }
}
