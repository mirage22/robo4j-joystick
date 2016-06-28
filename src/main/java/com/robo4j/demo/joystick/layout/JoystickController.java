package com.robo4j.demo.joystick.layout;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.robo4j.demo.joystick.layout.events.JoystickEvent;
import com.robo4j.demo.joystick.layout.handlers.PovEventHandler;
import com.robo4j.demo.joystick.layout.handlers.RadarEventHandler;
import com.robo4j.demo.joystick.layout.util.JoystickEventProducer;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.MouseEvent;

public class JoystickController {

    protected Joystick joystick;

    protected Map<Integer, IntegerProperty> levels;
    protected DoubleProperty radiusProperty;
    protected DoubleProperty povCenteXProperty;
    protected DoubleProperty povCenteYProperty;

    protected JoystickEventProducer eventProducer;
    protected RadarEventHandler radarEventHandler;
    protected PovEventHandler povEventHandler;

    public JoystickController(final Joystick joystick) {
        this.joystick = joystick;
        initProperties(joystick.getRadius(), joystick.getLevels());
        bindingPropertie();
        addCanvasListener();
        initHandlers();
        addPovlistener();
        povEventHandling();

        radiusProperty.set(joystick.getRadius());
    }

    private void povEventHandling() {
        eventProducer = new JoystickEventProducer(joystick, joystick.getPov().centerXProperty(), joystick.getPov().centerYProperty(),
                povCenteXProperty, povCenteYProperty, levels);
        joystick.addEventHandler(MouseEvent.ANY, event -> eventProducer.handle(event));
    }

    private void initProperties(final double radius, final int levelNumber) {
        radiusProperty = new SimpleDoubleProperty();
        //@formatter:off
        levels = IntStream.range(0, levelNumber)
                .mapToObj(Integer::new)
                .collect(Collectors.toMap(
                        i -> i, 
                        i ->createProperty(radius, levelNumber, i+1)));
        //@formatter:on
        povCenteXProperty = new SimpleDoubleProperty();
        povCenteYProperty = new SimpleDoubleProperty();
    }

    private IntegerProperty createProperty(final double radius, int levels, Integer i) {
        SimpleIntegerProperty simpleIntegerProperty = new SimpleIntegerProperty((int) (radius / i));
        simpleIntegerProperty.bind(radiusProperty.divide(levels).multiply(i));
        return simpleIntegerProperty;
    }

    private void bindingPropertie() {

        joystick.getPov().radiusProperty().bind(radiusProperty.divide(levels.size()).divide(2));
        povCenteXProperty.bind(radiusProperty.add(joystick.getCanvas().layoutXProperty()));
        povCenteYProperty.bind(radiusProperty.add(joystick.getCanvas().layoutYProperty()));

        radiusProperty.addListener(e -> {
            radarEventHandler.drawCircles(joystick.getCanvas(), radiusProperty, levels, levels.size());
            povEventHandler.bindPovToCanvas(joystick.getPov(), povCenteXProperty, povCenteYProperty);
            joystick.getCanvas().setHeight(radiusProperty.get() * 2);
            joystick.getCanvas().setWidth(radiusProperty.get() * 2);
        });
    }

    private void initHandlers() {
        radarEventHandler = new RadarEventHandler();
        povEventHandler = new PovEventHandler();
    }

    private void addPovlistener() {
        joystick.getPov().addEventHandler(MouseEvent.MOUSE_DRAGGED,
                (e) -> povEventHandler.draggedPov(e, joystick.getPov(), radiusProperty, povCenteXProperty, povCenteYProperty));
        joystick.getPov().setOnMouseReleased(e -> povEventHandler.bindPovToCanvas(joystick.getPov(), povCenteXProperty, povCenteYProperty));
    }

    private void addCanvasListener() {
        joystick.getCanvas().addEventHandler(MouseEvent.MOUSE_DRAGGED, (e) -> {
            radarEventHandler.onMouseDragged(joystick.getPov(), povCenteXProperty, povCenteYProperty);
        });
        joystick.addEventHandler(JoystickEvent.QUADRANT_CHANGED, e -> {
            radarEventHandler.drawTargetLevel(e, joystick.getCanvas(), radiusProperty, levels);

        });
        joystick.addEventHandler(JoystickEvent.LEVEL_CHANGED, e -> {
            radarEventHandler.drawTargetLevel(e, joystick.getCanvas(), radiusProperty, levels);
        });
    }
}
