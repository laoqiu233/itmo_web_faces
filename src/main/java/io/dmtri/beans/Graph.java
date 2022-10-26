package io.dmtri.beans;

import io.dmtri.areas.Area;
import io.dmtri.areas.CircleArea;
import io.dmtri.areas.OrArea;
import io.dmtri.areas.QuadrantArea;
import io.dmtri.areas.RectangleArea;
import io.dmtri.areas.RotatedArea;
import io.dmtri.areas.ScaledArea;
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
            new QuadrantArea(QuadrantArea.UPPER_LEFT, new ScaledArea(1, 0.5, new RotatedArea(Math.PI * 45d / 180d, new RectangleArea(Math.sqrt(2), Math.sqrt(2))))),
            new QuadrantArea(QuadrantArea.UPPER_RIGHT, new CircleArea(1)),
            new QuadrantArea(QuadrantArea.LOWER_RIGHT, new RectangleArea(1, 2))
    );
    private static final String bitmap = BitmapEncoder.encode(checker.generateBitmap(RESOLUTION), RESOLUTION);

    public Area getChecker() {
        return checker;
    }

    public String getEncodedGraph() {
        return bitmap;
    }
}
