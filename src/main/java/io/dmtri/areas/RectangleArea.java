package io.dmtri.areas;

import io.dmtri.beans.Point;

/**
 * Describes a reactangular area centered in the origin 
 * with specified width and height.
 */
public class RectangleArea extends AbstractArea {
    private final double width;
    private final double height;

    public RectangleArea(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean checkPoint(Point point) {
        return Math.abs(point.getX()) < width * point.getR() / 2 && Math.abs(point.getY()) < height * point.getR() / 2;
    }
}
