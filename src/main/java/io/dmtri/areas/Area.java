package io.dmtri.areas;

import io.dmtri.beans.Point;

public interface Area {
    boolean checkPoint(Point point);
    boolean[][] generateBitmap(int resolution);
}