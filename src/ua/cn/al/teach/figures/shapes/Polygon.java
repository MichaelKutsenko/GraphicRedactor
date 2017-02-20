package ua.cn.al.teach.figures.shapes;

import java.util.List;

public class Polygon extends PolyLine {

    public Polygon() {
    }

    public Polygon(List<Point> points) {
        this.points.addAll(points);
    }

    @Override
    public Polygon clone() throws CloneNotSupportedException {
        return (Polygon) super.clone();
    }
}
