/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This PovEventHandler.java is part of Robo4j and robo4j-joystick
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

import com.robo4j.demo.joystick.layout.util.MoveCalculatorUtil;

import javafx.beans.property.DoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

/**
 * @author Choustoulakis Nikolaos (@eppnikos)
 * @author Miro Kopecky (@miragemiko)
 *
 * @since 28.06.2016
 */

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
        if (MoveCalculatorUtil.isInsideCircleArea(radiusProperty.get() - povRadius.get(),
                mouseX, mouseY, povCenteXProperty.get(), povCenteYProperty.get())) {
            double x = MoveCalculatorUtil.resetToCenterX(mouseX, povCenteXProperty.get());
            double y = MoveCalculatorUtil.resetToCenterY(mouseY, povCenteYProperty.get());
            double angle = MoveCalculatorUtil.determineAngle(x, y);
            pov.setCenterX(MoveCalculatorUtil.determinePointXOnCircleCircumference(angle,
                    radiusProperty.get() - povRadius.get(), povCenteXProperty.get()));
            pov.setCenterY(MoveCalculatorUtil.determinePointYOnCircleCircumference(angle,
                    radiusProperty.get() - povRadius.get(), povCenteYProperty.get()));
            return;
        }
        pov.setCenterX(mouseX);
        pov.setCenterY(mouseY);
    }
}
