package remote.javafx.ui.events.enums;

public enum QuadrantEnum {

    // @formatter:off
        QUADRANT_I      (1,     0,      90),
        QUADRANT_II     (2,     90,     90),
        QUADRANT_III    (3,     180,    90),
        QUADRANT_IV     (4,     270,    90), 
        NONE            (0,     0,      0),
        ;
    // @formatter:on

    private int quadrant;
    private int startAngle;
    private int angleExtend;

    private QuadrantEnum(int q, int startAngle, int angleExtend) {
        quadrant = q;
        this.startAngle = startAngle;
        this.angleExtend = angleExtend;
    }

    public int getQuadrant() {
        return quadrant;
    }

    public int getAngleExtend() {
        return angleExtend;
    }

    public int getStartAngle() {
        return startAngle;
    }
}
