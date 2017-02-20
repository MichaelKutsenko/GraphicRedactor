package ua.cn.al.teach.figures.shapes;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Rectangle extends PolyLine {
    private static final int BOTTOM_LEFT_POINT = 0;
    private static final int TOP_RIGHT_POINT = 2;
    private boolean isMarker;

    public Rectangle() {
    }

    public Rectangle(Point A, Point C) {
        points.add(A);
        points.add(new Point(A.x,C.y));
        points.add(C);
        points.add(new Point(C.x,A.y));
        points.add(A);
    }

    public Rectangle(Point A, Point C, boolean isMarker) {
        this(A, C);
        this.isMarker = isMarker;
    }

    @JsonIgnore
    public Point getBottomLeftPoint(){
        return points.get(BOTTOM_LEFT_POINT);
    }

    @JsonIgnore
    public Point getTopRightPoint(){
        return points.get(TOP_RIGHT_POINT);
    }

    @JsonIgnore
    public boolean isMarker() {
        return isMarker;
    }

    public void setMarker(boolean marker) {
        isMarker = marker;
        setDashLength(3);
    }

    @Override
    public Rectangle clone() throws CloneNotSupportedException {
        return (Rectangle) super.clone();
    }
}
