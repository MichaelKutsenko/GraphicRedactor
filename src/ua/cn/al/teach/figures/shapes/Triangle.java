package ua.cn.al.teach.figures.shapes;

public class Triangle extends PolyLine {

    public Triangle() {
    }

    public Triangle(Point A, Point B, Point C) {
        points.add(A);
        points.add(B);
        points.add(C);
        points.add(A);
    }

    @Override
    public Triangle clone() throws CloneNotSupportedException {
        return (Triangle) super.clone();
    }

}
