/*
 * 
 * 
 */
package ua.cn.al.teach.figures.shapes;

/**
 *
 * @author al
 */
public class Rectangle extends PolyLine {
    private static final int BOTTOM_LEFT_POINT = 0;
    private static final int TOP_RIGHT_POINT = 2;
    private boolean isMarker;

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

    public Point getBottomLeftPoint(){
        return points.get(BOTTOM_LEFT_POINT);
    }

    public Point getTopRightPoint(){
        return points.get(TOP_RIGHT_POINT);
    }

    public boolean isMarker() {
        return isMarker;
    }

    public void setMarker(boolean marker) {
        isMarker = marker;
        setDashLength(3);
    }
}
