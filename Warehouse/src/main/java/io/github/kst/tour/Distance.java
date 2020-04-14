package io.github.kst.tour;

import java.awt.geom.Point2D;

public class Distance {

    public double calculateDistanceBetweenPoints(
            double x1,
            double y1,
            double x2,
            double y2) {

        return Point2D.distance(x1, y1, x2, y2);
    }
}
