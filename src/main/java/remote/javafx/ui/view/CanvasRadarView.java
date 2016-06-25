package remote.javafx.ui.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CanvasRadarView extends Pane {

    private Canvas canvas;
    private Circle pov;

    public CanvasRadarView(final double radius, final int levelNumber) {
        pov = new Circle(0, Color.BLACK);
        canvas = new Canvas(radius * 2, radius * 2);
        getChildren().addAll(canvas, pov);
        setManaged(false);
    }

    public Circle getPov() {
        return pov;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
