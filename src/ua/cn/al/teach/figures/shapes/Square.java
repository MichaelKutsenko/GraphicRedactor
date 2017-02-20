package ua.cn.al.teach.figures.shapes;

public class Square extends Rectangle {

    public Square(Point A, Point C) {
        super(A, C);
    }

    @Override
    public Square clone() throws CloneNotSupportedException {
        return (Square) super.clone();
    }
}
