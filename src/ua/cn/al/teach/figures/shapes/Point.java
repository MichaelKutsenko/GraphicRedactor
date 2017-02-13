/*
 * 
 * 
 */
package ua.cn.al.teach.figures.shapes;

/**
 *
 * @author al
 */
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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Point)) return false;
//
//        Point point = (Point) o;
//
//        if (Double.compare(point.x, x) != 0) return false;
//        return Double.compare(point.y, y) == 0;
//    }

//    @Override
//    public int hashCode() {
//        int result;
//        long temp;
//        temp = Double.doubleToLongBits(x);
//        result = (int) (temp ^ (temp >>> 32));
//        temp = Double.doubleToLongBits(y);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        return result;
//    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
