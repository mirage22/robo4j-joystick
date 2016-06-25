package com.robo4j.demo.joystick.layout.handlers;

import java.util.Map;

import com.robo4j.demo.joystick.events.JoystickEvent;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;

public class RadarEventHandler {

    public void onMouseDragged(MouseEvent e, Canvas canvas, Circle pov, DoubleProperty povCenteXProperty, DoubleProperty povCenteYProperty) {
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
        gc.setFill(Color.RED);
        gc.fillArc(
                radiusProperty.get() - levels.get(e.getJoystickLevel()).get(), 
                radiusProperty.get() - levels.get(e.getJoystickLevel()).get(), 
                levels.get(e.getJoystickLevel()).get() * 2,
                levels.get(e.getJoystickLevel()).get() * 2,
                e.getQuadrant().getStartAngle(), 
                e.getQuadrant().getAngleExtend(), ArcType.ROUND);
        //@formatter:on
        draw(canvas, radiusProperty, levels, e.getJoystickLevel());
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
                    gc.setFill(Color.GRAY);
                    gc.setStroke(Color.BLACK);
                    double position = radiusProperty.get() - i.getValue().get();
                    int size = i.getValue().get() * 2;
                    gc.fillOval(position, position, size, size);
                    gc.strokeArc(position, position, size, size, 0, 360, ArcType.CHORD);
                });
    }
}
