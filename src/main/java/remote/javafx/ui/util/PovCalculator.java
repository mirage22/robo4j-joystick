package remote.javafx.ui.util;

/**
 * Class to do the math and determine cirlce position angle etc.
 * 
 * @author nikos
 *
 *         Base Function for a (x,y) point: (x-j)^2 + (y-k)^2 = r^2
 */
public class PovCalculator {

    public PovCalculator() {
    }

    public double determinePointXOnCircleCircumference(double angle, double radius, double center) {
        return Math.sin(angle) * radius + center;
    }

    public double determinePointYOnCircleCircumference(double angle, double radius, double center) {
        return Math.cos(angle) * radius + center;
    }

    public double resetToCenterX(double x, double center) {
        return x - center;
    }

    public double resetToCenterY(double y, double center) {
        return center - y;
    }

    public double determinAngle(double x, double y) {
        double rad = Math.atan2(y, x);
        return rad + Math.PI / 2;
    }

    public boolean isInsideCircleArea(double radius, double x, double y) {
        return Math.pow(Math.pow(x, 2) + Math.pow(y, 2), 0.5) < radius;
    }

    public boolean isInsideCircleArea(double radius, double x, double y, double j, double k) {
        return Math.pow(Math.pow(x - j, 2) + Math.pow(y - k, 2), 0.5) > radius;
    }

    @SuppressWarnings("unused")
    private double determineRadius(double x, double y) {
        return Math.pow(Math.pow(x, 2) + Math.pow(y, 2), 0.5);
    }
}
