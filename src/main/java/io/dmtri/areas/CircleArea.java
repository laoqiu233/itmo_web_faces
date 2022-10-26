package io.dmtri.areas;

import io.dmtri.beans.Point;

/**
 * Describes a circular area with radius R centered in the origin.
 */
public class CircleArea extends AbstractArea {
    private final double r;

    public CircleArea(double r) {
        this.r = r;
    }

    @Override
    public boolean checkPoint(Point point) {
        return point.getX() * point.getX() + point.getY() * point.getY() <= point.getR() * point.getR() * r * r;
    }
}
