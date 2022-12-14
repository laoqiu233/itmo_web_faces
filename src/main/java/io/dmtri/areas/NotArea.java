package io.dmtri.areas;

import io.dmtri.beans.Point;

/**
 * Inverses the underlaying area
 */
public class NotArea extends AbstractArea {
    private final Area decoratedArea;

    public NotArea(Area decoratedArea) {
        this.decoratedArea = decoratedArea;
    }

    @Override
    public boolean checkPoint(Point point) {
        return !decoratedArea.checkPoint(point);
    }
}
