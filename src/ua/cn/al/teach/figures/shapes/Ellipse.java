package ua.cn.al.teach.figures.shapes;

import ua.cn.al.teach.figures.engine.GraphicsEngine;

public class Ellipse extends Shape {
    private Point center;
    private  double radiusX;
    private  double radiusY;

    public Ellipse() {
        this(new Point(0, 0), 0, 0);
    }

    public Ellipse(Point center, double radiusX, double radiusY) {
        this.center = center;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    @Override
    protected void draw(GraphicsEngine ge) {
        ge.strokeOval(x + center.getX(), y + center.getY(), radiusX, radiusY);
    }

    @Override
    protected boolean containedInField(Rectangle focusRect) {
        if (center.getX()-radiusX+x  >= focusRect.getBottomLeftPoint().getX() &&
                center.getX()+radiusX+x <= focusRect.getTopRightPoint().getX() &&
                center.getY()-radiusY+y >= focusRect.getBottomLeftPoint().getY() &&
                center.getY()+radiusY+y <= focusRect.getTopRightPoint().getY()){
//            this.setFocused(true);
            return true;
        } else {
//            this.setFocused(false);
            return false;
        }
    }

    @Override
    protected boolean containPoint(Point point) {
        double x0 = center.getX() + x;
        double y0 = center.getY() + y;

        double angle =(Math.PI-Math.atan2(point.getY() - y0, -(point.getX() - x0)));

        double r = (radiusX*radiusY) /
                Math.sqrt(radiusY*radiusY*Math.cos(angle)*Math.cos(angle) + radiusX*radiusX*Math.sin(angle)*Math.sin(angle));

        double length = Math.sqrt((point.getX()-x0)*(point.getX()-x0) + (point.getY()-y0)*(point.getY()-y0));

        if (r >= length - 7 && r <= length + 7) {
//            isFocused = true;
            return true;
        }
//        isFocused = false;
        return false;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadiusX() {
        return radiusX;
    }

    public void setRadiusX(double radiusX) {
        this.radiusX = radiusX;
    }

    public double getRadiusY() {
        return radiusY;
    }

    public void setRadiusY(double radiusY) {
        this.radiusY = radiusY;
    }
}
