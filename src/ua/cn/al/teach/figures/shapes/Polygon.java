package ua.cn.al.teach.figures.shapes;

import java.util.List;

public class Polygon extends PolyLine {

    public Polygon(List<Point> points) {
        this.points.addAll(points);
    }
}
