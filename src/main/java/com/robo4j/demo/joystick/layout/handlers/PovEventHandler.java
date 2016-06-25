package com.robo4j.demo.joystick.layout.handlers;

import javafx.beans.property.DoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import remote.javafx.ui.util.PovCalculator;

public class PovEventHandler {
    private PovCalculator calculator;

    public PovEventHandler() {
        calculator = new PovCalculator();
    }

    public void bindPovToCanvas(Circle pov, DoubleProperty povCenteXProperty, DoubleProperty povCenteYProperty) {
        pov.setCenterX(povCenteXProperty.get());
        pov.setCenterY(povCenteYProperty.get());
    }

    public void draggedPov(MouseEvent e, Circle pov, DoubleProperty radiusProperty, DoubleProperty povRadius, DoubleProperty povCenteXProperty,
            DoubleProperty povCenteYProperty) {
        double mouseX = e.getX();
        double mouseY = e.getY();

        if (calculator.isInsideCircleArea(radiusProperty.get() - povRadius.get(), mouseX, mouseY, povCenteXProperty.get(), povCenteYProperty.get())) {
            double x = calculator.resetToCenterX(mouseX, povCenteXProperty.get());
            double y = calculator.resetToCenterY(mouseY, povCenteYProperty.get());
            double angle = calculator.determinAngle(x, y);
            pov.setCenterX(calculator.determinePointXOnCircleCircumference(angle, radiusProperty.get() - povRadius.get(), povCenteXProperty.get()));
            pov.setCenterY(calculator.determinePointYOnCircleCircumference(angle, radiusProperty.get() - povRadius.get(), povCenteYProperty.get()));
            return;
        }
        pov.setCenterX(mouseX);
        pov.setCenterY(mouseY);
    }
}
