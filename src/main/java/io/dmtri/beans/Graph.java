package io.dmtri.beans;

import io.dmtri.areas.Area;
import io.dmtri.areas.CircleArea;
import io.dmtri.areas.OrArea;
import io.dmtri.areas.QuadrantArea;
import io.dmtri.areas.RectangleArea;
import io.dmtri.areas.RotatedArea;
import io.dmtri.areas.TranslatedArea;
import io.dmtri.utils.BitmapEncoder;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import java.io.Serializable;

@Singleton
@Named
public class Graph implements Serializable {
    public static final int RESOLUTION = 300;
    private static final Area checker = new OrArea(
            new TranslatedArea(0.5, 0.5, new RectangleArea(1, 1)),
            new QuadrantArea(QuadrantArea.LOWER_LEFT, new CircleArea(0.5)),
            new QuadrantArea(QuadrantArea.LOWER_RIGHT, new RotatedArea(Math.PI / 4, new RectangleArea(Math.sqrt(2), Math.sqrt(2))))
    );

    public Area getChecker() {
        return checker;
    }

    public String getEncodedGraph() {
        return BitmapEncoder.encode(checker.generateBitmap(RESOLUTION), RESOLUTION);
    }
}
