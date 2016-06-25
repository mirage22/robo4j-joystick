package remote.javafx.ui.controller;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.MouseEvent;
import remote.javafx.ui.events.POVEvent;
import remote.javafx.ui.events.POVEventProducer;
import remote.javafx.ui.handlers.PovEventHandler;
import remote.javafx.ui.handlers.RadarEventHandler;
import remote.javafx.ui.view.CanvasRadarView;

public class CanvasRadarController {

    private CanvasRadarView canvasRadarView;

    private Map<Integer, IntegerProperty> levels;
    private DoubleProperty radiusProperty;
    private DoubleProperty povRadius;
    private DoubleProperty povCenteXProperty;
    private DoubleProperty povCenteYProperty;

    private POVEventProducer eventProducer;
    private RadarEventHandler radarEventHandler;
    private PovEventHandler povEventHandler;

    public CanvasRadarController(final double radius, final int levelNumber) {
        canvasRadarView = new CanvasRadarView(radius, levelNumber);
        initProperties(radius, levelNumber);
        bindingPropertie();
        addCanvasListener();
        initHandlers();
        addPovlistener();
        povEventHandling();

        radiusProperty.set(radius);
    }

    private void povEventHandling() {
        eventProducer = new POVEventProducer(canvasRadarView, canvasRadarView.getPov().centerXProperty(), canvasRadarView.getPov().centerYProperty(),
                povCenteXProperty, povCenteYProperty, levels);
        canvasRadarView.addEventHandler(MouseEvent.ANY, event -> eventProducer.handle(event));
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
        povRadius = new SimpleDoubleProperty();
        povCenteXProperty = new SimpleDoubleProperty();
        povCenteYProperty = new SimpleDoubleProperty();
    }

    private IntegerProperty createProperty(final double radius, int levels, Integer i) {
        SimpleIntegerProperty simpleIntegerProperty = new SimpleIntegerProperty((int) (radius / i));
        simpleIntegerProperty.bind(radiusProperty.divide(levels).multiply(i));
        return simpleIntegerProperty;
    }

    private void bindingPropertie() {

        povRadius.bind(radiusProperty.divide(levels.size()));
        canvasRadarView.getPov().radiusProperty().bindBidirectional(povRadius);
        povCenteXProperty.bind(radiusProperty.add(canvasRadarView.getCanvas().layoutXProperty()));
        povCenteYProperty.bind(radiusProperty.add(canvasRadarView.getCanvas().layoutYProperty()));

        radiusProperty.addListener(e -> {
            radarEventHandler.drawCircles(canvasRadarView.getCanvas(), radiusProperty, levels, levels.size());
            povEventHandler.bindPovToCanvas(canvasRadarView.getPov(), povCenteXProperty, povCenteYProperty);
            canvasRadarView.getCanvas().setHeight(radiusProperty.get() * 2);
            canvasRadarView.getCanvas().setWidth(radiusProperty.get() * 2);
        });
    }

    private void initHandlers() {
        radarEventHandler = new RadarEventHandler();
        povEventHandler = new PovEventHandler();
    }

    private void addPovlistener() {
        canvasRadarView.getPov().addEventHandler(MouseEvent.MOUSE_DRAGGED, (e) -> {
            povEventHandler.draggedPov(e, canvasRadarView.getPov(), radiusProperty, povRadius, povCenteXProperty, povCenteYProperty);
        });
        canvasRadarView.getPov().setOnMouseReleased(e -> povEventHandler.bindPovToCanvas(canvasRadarView.getPov(), povCenteXProperty, povCenteYProperty));
    }

    private void addCanvasListener() {
        canvasRadarView.getCanvas().addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
            radarEventHandler.resetOffesetValues(e);
        });
        canvasRadarView.getCanvas().addEventHandler(MouseEvent.MOUSE_DRAGGED, (e) -> {
            radarEventHandler.onMouseDragged(e, canvasRadarView.getCanvas(), canvasRadarView.getPov(), povCenteXProperty, povCenteYProperty);
        });
        canvasRadarView.getCanvas().setOnZoom(e -> {
            radarEventHandler.setOnZoom(e, radiusProperty);
        });
        canvasRadarView.addEventHandler(POVEvent.POV_CHANGE_QUADRANT, e -> {
            radarEventHandler.drawTargetLevel(e, canvasRadarView.getCanvas(), radiusProperty, levels);

        });
        canvasRadarView.addEventHandler(POVEvent.POV_LEVEL_CHANGED, e -> {
            radarEventHandler.drawTargetLevel(e, canvasRadarView.getCanvas(), radiusProperty, levels);
        });
    }

    public CanvasRadarView getView() {
        return canvasRadarView;
    }
}
