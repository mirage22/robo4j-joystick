package remote.javafx.ui.events;

import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.input.InputEvent;
import remote.javafx.ui.events.enums.QuadrantEnum;

/**
 * Point of View Event
 * 
 * @author Choustoulakis Nikolaos
 * @since 11.06.16
 */
public class POVEvent extends InputEvent {

    private static final long serialVersionUID = 2231266847422883646L;

    public static final EventType<POVEvent> ANY = new EventType<>(InputEvent.ANY, "POV");
    public static final EventType<POVEvent> POV_CHANGE_QUADRANT = new EventType<>(InputEvent.ANY, "POV_CHANGE_QUADRANT");
    public static final EventType<POVEvent> POV_LEVEL_CHANGED = new EventType<>(InputEvent.ANY, "POV_LEVEL_CHANGED");

    private transient double x;
    private transient double y;
    private transient QuadrantEnum quadrant;
    private transient int level;

    public POVEvent(Object source, EventTarget target, EventType<? extends POVEvent> eventType, double x, double y, QuadrantEnum quadrant,
 int joystickLevel) {
        super(source, target, eventType);
        this.x = x;
        this.y = y;
        this.quadrant = quadrant;
        this.level = joystickLevel;
    }

    public final double getX() {
        return x;
    }

    public final double getY() {
        return y;
    }

    public final QuadrantEnum getQuadrant() {
        return quadrant;
    }

    public final int getLevel() {
        return level;
    }


}
