package io.dmtri.areas;

import io.dmtri.beans.Point;

/**
 * This decorated uses the rotation matrix below to rotate 
 * any underlaying area around the origin.
 * <p>
 * x' = cos(delta)*x - sin(delta)*y
 * </p>
 * <p>
 * y' = sin(delta)*x + sin(delta)*y  
 * </p>
 */
public class RotatedArea extends AbstractArea {
    private final double delta;
    private final Area decoratedArea;

    /**
     * @param delta         The angle used for rotation in radians
     * @param decoratedArea The area being rotated
     */
    public RotatedArea(double delta, Area decoratedArea) {
        this.delta = delta;
        this.decoratedArea = decoratedArea;
    }

    @Override
    public boolean checkPoint(Point point) {
        // Rotate the point back
        double xPrime = Math.cos(-this.delta) * point.getX() - Math.sin(-this.delta) * point.getY();
        double yPrime = Math.sin(-this.delta) * point.getX() + Math.cos(-this.delta) * point.getY();

        return decoratedArea.checkPoint(new Point(xPrime, yPrime, point.getR()));
    }
}
