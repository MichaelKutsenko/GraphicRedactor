package ua.cn.al.teach.figures.shapes;

public class Point {
    protected double x;
    protected double y;

    public Point() {
        this(0,0);
    }
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void moveTo(Point from, Point dest) {
        x += dest.getX() - from.getX();
        y += dest.getY() - from.getY();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
