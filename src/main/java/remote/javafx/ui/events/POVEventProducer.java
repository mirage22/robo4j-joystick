package remote.javafx.ui.events;

import java.util.Map;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import remote.javafx.ui.events.enums.QuadrantEnum;
import remote.javafx.ui.util.PovCalculator;

/**
 * Base Listener to determine when an event is POVEvent.
 * 
 * @author nikos
 *
 */
public final class POVEventProducer implements EventHandler<MouseEvent> {

    private final Node node;
    private PovCalculator calculator;
    private double beforeX = 0;
    private double beforeY = 0;

    private DoubleProperty povCenterXProperty;
    private DoubleProperty povCenterYProperty;
    private DoubleProperty povCenterXLayoutProperty;
    private DoubleProperty povCenterYLayoutProperty;
    private Map<Integer, IntegerProperty> levels;

    private int joystickLevelBefore = 0;
    private QuadrantEnum quadrantBefore = QuadrantEnum.NONE;

    public POVEventProducer(Node node, DoubleProperty povCenterXProperty, DoubleProperty povCenterYProperty, DoubleProperty povCenterXLayoutProperty,
            DoubleProperty povCenterYLayoutProperty,
            Map<Integer, IntegerProperty> levels) {
        this.node = node;
        this.levels = levels;
        calculator = new PovCalculator();
        this.povCenterXProperty = povCenterXProperty;
        this.povCenterYProperty = povCenterYProperty;
        this.povCenterXLayoutProperty = povCenterXLayoutProperty;
        this.povCenterYLayoutProperty = povCenterYLayoutProperty;
    }

    @Override
    public void handle(MouseEvent event) {
        produce(toMouseEventWithCartesianPoints(event));
    }

    private void produce(MouseEvent e) {
        quadrantBefore = whichQuadrant(beforeX, beforeY);
        double afterX = e.getX();
        double afterY = e.getY();
        QuadrantEnum quadrantAfter = whichQuadrant(afterX, afterY);
        int levelAfter = determinLevel(afterX, afterY);
        createEvent(levelAfter, quadrantAfter, e);
        resetToAfter(afterX, afterY, quadrantAfter, levelAfter);
    }

    private void createEvent(int level, QuadrantEnum quadrantAfter, MouseEvent e) {
        if (!(level == joystickLevelBefore)) {
            node.fireEvent(new POVEvent(e.getSource(), e.getTarget(), POVEvent.POV_LEVEL_CHANGED, e.getX(), e.getY(), quadrantAfter, level));
        }
        if (!quadrantAfter.equals(quadrantBefore)) {
            node.fireEvent(new POVEvent(e.getSource(), e.getTarget(), POVEvent.POV_CHANGE_QUADRANT, e.getX(), e.getY(), quadrantAfter, level));
        }
        node.fireEvent(new POVEvent(e.getSource(), e.getTarget(), POVEvent.ANY, e.getX(), e.getY(), quadrantAfter, level));
    }

    private int determinLevel(double x, double y) {
        if (y == x && y == 0) {
            return 0;
        }
        return levels.entrySet().stream().filter(e -> calculator.isInsideCircleArea(e.getValue().get(), x, y))
.max((l, r) -> r.getKey().compareTo(l.getKey()))
                .get().getKey();

    }

    private QuadrantEnum whichQuadrant(double x, double y) {
        if (y == x && y == 0) {
            return QuadrantEnum.NONE;
        }
        if (x * y >= 0) {
            if (x < 0) {
                return QuadrantEnum.QUADRANT_III;
            } else {
                return QuadrantEnum.QUADRANT_I;
            }
        } else {
            if (x > 0) {
                return QuadrantEnum.QUADRANT_IV;
            } else {
                return QuadrantEnum.QUADRANT_II;
            }
        }
    }

    private void resetToAfter(double afterX, double afterY, QuadrantEnum quadrantAfter, int joystickLevelAfter) {
        beforeX = afterX;
        beforeY = afterY;
        joystickLevelBefore = joystickLevelAfter;
        quadrantBefore = quadrantAfter;
    }

    private MouseEvent toMouseEventWithCartesianPoints(MouseEvent e) {
        //@formatter:off
        return new MouseEvent(e.getSource(), e.getTarget(), e.getEventType(), calculator.resetToCenterX(povCenterXProperty.get(), povCenterXLayoutProperty.get()), 
                calculator.resetToCenterY(povCenterYProperty.get(), povCenterYLayoutProperty.get()), e.getScreenX(), e.getScreenY(), e.getButton(), e.getClickCount(), 
                e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown(), e.isPrimaryButtonDown(), 
                e.isMiddleButtonDown(), e.isSecondaryButtonDown(), e.isSynthesized(), e.isPopupTrigger(), 
                e.isStillSincePress(), null);
        //@formatter:on
    }
}
