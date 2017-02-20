package ua.cn.al.teach.figures.shapes;

public class Line extends PolyLine {

    public Line() {
    }

    public Line(Point A, Point B) {
        points.add(A);
        points.add(B);
    }

    @Override
    public Line clone() throws CloneNotSupportedException {
        return (Line) super.clone();
    }



}
