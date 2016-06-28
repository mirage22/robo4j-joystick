package com.robo4j.demo.joystick.layout.handlers;

import com.robo4j.demo.joystick.layout.util.MoveCalculatorUtil;

import javafx.beans.property.DoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class PovEventHandler {

    public void bindPovToCanvas(Circle pov, DoubleProperty povCenteXProperty, DoubleProperty povCenteYProperty) {
        pov.setCenterX(povCenteXProperty.get());
        pov.setCenterY(povCenteYProperty.get());
    }

    public void draggedPov(MouseEvent e, Circle pov, DoubleProperty radiusProperty, DoubleProperty povCenteXProperty,
            DoubleProperty povCenteYProperty) {
        double mouseX = e.getX();
        double mouseY = e.getY();
        DoubleProperty povRadius = pov.radiusProperty();
        if (MoveCalculatorUtil.isInsideCircleArea(radiusProperty.get() - povRadius.get(), mouseX, mouseY, povCenteXProperty.get(), povCenteYProperty.get())) {
            double x = MoveCalculatorUtil.resetToCenterX(mouseX, povCenteXProperty.get());
            double y = MoveCalculatorUtil.resetToCenterY(mouseY, povCenteYProperty.get());
            double angle = MoveCalculatorUtil.determineAngle(x, y);
            pov.setCenterX(MoveCalculatorUtil.determinePointXOnCircleCircumference(angle, radiusProperty.get() - povRadius.get(), povCenteXProperty.get()));
            pov.setCenterY(MoveCalculatorUtil.determinePointYOnCircleCircumference(angle, radiusProperty.get() - povRadius.get(), povCenteYProperty.get()));
            return;
        }
        pov.setCenterX(mouseX);
        pov.setCenterY(mouseY);
    }
}
