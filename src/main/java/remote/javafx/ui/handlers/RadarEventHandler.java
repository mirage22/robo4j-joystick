package remote.javafx.ui.handlers;

import java.util.Map;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import remote.javafx.ui.events.POVEvent;

public class RadarEventHandler {
    private double xOffset;
    private double yOffset;

    public void resetOffesetValues(MouseEvent e) {
        xOffset = e.getX();
        yOffset = e.getY();
    }

    public void onMouseDragged(MouseEvent e, Canvas canvas, Circle pov, DoubleProperty povCenteXProperty, DoubleProperty povCenteYProperty) {
        canvas.setLayoutX(e.getSceneX() - xOffset);
        canvas.setLayoutY(e.getSceneY() - yOffset);
        pov.setCenterX(povCenteXProperty.get());
        pov.setCenterY(povCenteYProperty.get());
    }

    public void setOnZoom(ZoomEvent e, DoubleProperty radiusProperty) {
        radiusProperty.set(radiusProperty.get() * e.getZoomFactor());
    }

    public void drawTargetLevel(POVEvent e, Canvas canvas, DoubleProperty radiusProperty, Map<Integer, IntegerProperty> levels) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawCircles(canvas, radiusProperty, levels, levels.size());
        //@formatter:off
        gc.setFill(Color.RED);
        gc.fillArc(
                radiusProperty.get() - levels.get(e.getLevel()).get(), 
                radiusProperty.get() - levels.get(e.getLevel()).get(), 
                levels.get(e.getLevel()).get() * 2,
                levels.get(e.getLevel()).get() * 2,
                e.getQuadrant().getStartAngle(), 
                e.getQuadrant().getAngleExtend(), ArcType.ROUND);
        //@formatter:on
        draw(canvas, radiusProperty, levels, e.getLevel());
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
