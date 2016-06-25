/*
 * Copyright (C) 2016. Miroslav Kopecky
 * This JoystickEventProducer.java is part of Robo4j and robo4j-joystick
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

package com.robo4j.demo.joystick;

import com.robo4j.demo.joystick.events.enums.JoystickEventEnum;
import com.robo4j.demo.joystick.events.enums.LevelEnum;
import com.robo4j.demo.joystick.events.enums.QuadrantEnum;
import com.robo4j.demo.joystick.util.EventUtil;
import com.robo4j.demo.joystick.util.MoveCalculatorUtil;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * Joystick logic
 *
 * @author Choustoulakis Nikolaos (@eppnikos)
 * @author Miro Kopecky (@miragemiko)
 *
 * @since 25/06/16
 */
public final class JoystickEventProducer implements EventHandler<MouseEvent> {

    private final Node node;
    private double beforeX = 0;
    private double beforeY = 0;

    private DoubleProperty levelIRadius;
    private DoubleProperty levelIIRadius;
    private DoubleProperty povCenterXProperty;
    private DoubleProperty povCenterYProperty;
    private DoubleProperty povCenterXLayoutProperty;
    private DoubleProperty povCenterYLayoutProperty;

    private LevelEnum joystickLevelBefore = LevelEnum.NONE;
    private QuadrantEnum quadrantBefore = QuadrantEnum.NONE;

    public JoystickEventProducer(Node node, DoubleProperty levelIRadius, DoubleProperty levelIIRadius, DoubleProperty povCenterXProperty,
                                 DoubleProperty povCenterYProperty, DoubleProperty povCenterXLayoutProperty,
                                 DoubleProperty povCenterYLayoutProperty) {

        this.node = node;
        this.levelIRadius = levelIRadius;
        this.levelIIRadius = levelIIRadius;
        this.povCenterXProperty = povCenterXProperty;
        this.povCenterYProperty = povCenterYProperty;
        this.povCenterXLayoutProperty = povCenterXLayoutProperty;
        this.povCenterYLayoutProperty = povCenterYLayoutProperty;
    }

    @Override
    public void handle(MouseEvent event) {
        produce(toMouseEventWithCartesianPoints(event));
    }


    //Private Methods
    private void produce(MouseEvent e) {
        quadrantBefore = EventUtil.whichQuadrant(beforeX, beforeY);
        double afterX = e.getX();
        double afterY = e.getY();
        QuadrantEnum quadrantAfter = EventUtil.whichQuadrant(afterX, afterY);
        LevelEnum levelAfter = EventUtil.determineLevel(levelIRadius.get(),levelIIRadius.get() , afterX, afterY);
        createEvent(levelAfter, quadrantAfter, e);
        resetToAfter(afterX, afterY, quadrantAfter, levelAfter);
    }

    private void createEvent(LevelEnum level, QuadrantEnum quadrantAfter, MouseEvent e) {
        if (!level.equals(joystickLevelBefore)) {
            node.fireEvent(EventUtil.createEvent(e, JoystickEventEnum.LEVEL_CHANGED, level, quadrantAfter));
        }
        if (!quadrantAfter.equals(quadrantBefore)) {
            node.fireEvent(EventUtil.createEvent(e, JoystickEventEnum.QUADRANT_CHANGED, level, quadrantAfter));
        }
        node.fireEvent(EventUtil.createEvent(e, JoystickEventEnum.UNDEFINED, level, quadrantAfter));
    }

    private void resetToAfter(double afterX, double afterY, QuadrantEnum quadrantAfter, LevelEnum joystickLevelAfter) {
        beforeX = afterX;
        beforeY = afterY;
        joystickLevelBefore = joystickLevelAfter;
        quadrantBefore = quadrantAfter;
    }

    private MouseEvent toMouseEventWithCartesianPoints(MouseEvent e) {
        //@formatter:off
        return new MouseEvent(e.getSource(), e.getTarget(), e.getEventType(),
                MoveCalculatorUtil.resetToCenterX(povCenterXProperty.get(), povCenterXLayoutProperty.get()),
                MoveCalculatorUtil.resetToCenterY(povCenterYProperty.get(), povCenterYLayoutProperty.get()),
                e.getScreenX(), e.getScreenY(), e.getButton(), e.getClickCount(),
                e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown(), e.isPrimaryButtonDown(), 
                e.isMiddleButtonDown(), e.isSecondaryButtonDown(), e.isSynthesized(), e.isPopupTrigger(), 
                e.isStillSincePress(), null);
        //@formatter:on
    }
}
