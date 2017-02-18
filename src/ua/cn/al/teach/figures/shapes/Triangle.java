package ua.cn.al.teach.figures.shapes;

public class Triangle extends PolyLine {

    public Triangle(Point A, Point B, Point C) {
        points.add(A);
        points.add(B);
        points.add(C);
        points.add(A);
    }

}
