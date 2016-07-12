package com.robo4j.demo.joystick.layout.events.enums;

/**
 * @author Choustoulakis Nikolaos (@eppnikos)
 * @author Miro Kopecky (@miragemiko)
 *
 * @since 25.06.2016
 */
public enum QuadrantEnum {

    // @formatter:off
    //name          id,     start angle,    end angle
    NONE            (0,     0,              0),
    QUADRANT_I      (1,     315,            90),
    QUADRANT_II     (2,     45,             90),
    QUADRANT_III    (3,     135,            90),
    QUADRANT_IV     (4,     225,            90),
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

    @Override
    public String toString() {
        return "QuadrantEnum{" +
                "quadrant=" + quadrant +
                ", startAngle=" + startAngle +
                ", angleExtend=" + angleExtend +
                '}';
    }
}
