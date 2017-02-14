package ua.cn.al.teach.figures.shapes;


import ua.cn.al.teach.figures.enums.ShapeType;

import java.util.ArrayList;
import java.util.List;

public class ShapeFactory {
    private static volatile ShapeFactory instance;

    private ShapeFactory() {
    }

    public static ShapeFactory getInstance(){
        if (instance == null) {
            synchronized (ShapeFactory.class){
                if (instance == null) instance = new ShapeFactory();
            }
        }
        return instance;
    }

    public Shape getShape(List<Point> points, ShapeType shape, double lineWidth, RGBColor color){
        try {
            if (ShapeType.Line == shape){
                Line line = new Line(points.get(0), points.get(1));
                setProperies(line, lineWidth, color);

                return line;
            }
            else if (ShapeType.Rectangle == shape){
                return createRectangle(points, lineWidth, color);
            }
            else if (ShapeType.Square == shape){
                Point a = points.get(0);
                Point c = points.get(1);

                double xDiff = c.x - a.x;
                double yDiff = c.y - a.y;

                if (Math.abs(xDiff) != Math.abs(yDiff)) {
                    boolean isXBigger = Math.abs(xDiff) > Math.abs(yDiff);

                    if (isXBigger && (yDiff >= 0)){
                        c = new Point(c.x, a.y + Math.abs(xDiff));
                    } else if (isXBigger && (yDiff < 0)){
                        c = new Point(c.x, a.y - Math.abs(xDiff));
                    } else if (!isXBigger && (xDiff >= 0)){
                        c = new Point(a.x + Math.abs(yDiff), c.y);
                    } else {
                        c = new Point(a.x - Math.abs(yDiff), c.y);
                    }
                }
                points.set(1, c);

                return createRectangle(points, lineWidth, color);
            }
            else if (ShapeType.Polyline == shape ){
                PolyLine polyline = new PolyLine();
                polyline.PolyLine(points);

                setProperies(polyline, lineWidth, color);

                return polyline;
            }
            else if (ShapeType.Triangle == shape){
                if (points.size() == 2){
                    Point apex1 = points.get(0);
                    Point apex2 = new Point(points.get(1).getX(), points.get(0).getY());
                    Point apex3 = new Point(points.get(1).getX() - (points.get(1).getX() - points.get(0).getX()) /2, points.get(1).getY());

                    Triangle triangle = new Triangle(apex1, apex2, apex3);
                    setProperies(triangle, lineWidth, color);

                    return  triangle;
                }
                else {
                    Triangle triangle = new Triangle(points.get(0), points.get(1), points.get(2));
                    setProperies(triangle, lineWidth, color);

                    return  triangle;
                }
            }
            else if (ShapeType.Polygon == shape ){
                Polygon polygon = new Polygon(points);
                setProperies(polygon, lineWidth, color);

                return  polygon;
            }
            else if (ShapeType.Curve == shape) {
                double x1 = points.get(0).getX();
                double y1 = points.get(0).getY();

                double x3 = points.get(1).getX();
                double y3 = points.get(1).getY();

                double x2;
                double y2;
                if (points.size() == 2) {
                    x2 = x3 - (x3 - x1) / 2;
                    y2 = y3 - (y3 - y1) / 2;

                } else {
                    x2 = points.get(2).getX();
                    y2 = points.get(2).getY();
                }

                double a = (y3 - (x3 * (y2-y1) + x2*y1 - x1*y2) /(x2-x1)) / (x3 * (x3-x1-x2) + x1*x2);
                double b = ((y2-y1) /(x2-x1)) - (a * (x1+x2));
                double c = ((x2*y1-x1*y2) / (x2-x1)) + a*x1*x2;
                Point apex = new Point((-b) / (2 * a), (4 * a * c - b * b) / (4 * a));

                List<Point> curvePoints = new ArrayList<>();
                curvePoints.add(points.get(0));
                if (x3 - x1 > 0) {
                    for (double i = x1 + 1; i < x3; i += 1) {
                        curvePoints.add(new Point(i, a * i * i + b * i + c));
                    }
                } else {
                    for (double i = x1 - 1; i > x3; i -= 1) {
                        curvePoints.add(new Point(i, a * i * i + b * i + c));
                    }
                }
                curvePoints.add(points.get(1));

                Curve curve = new Curve();
                curve.PolyLine(curvePoints);
                curve.setA(a);
                curve.setB(b);
                curve.setC(c);
                curve.setApex(apex);
                setProperies(curve, lineWidth, color);

                return curve;
            }
            else if (ShapeType.RectMarker == shape){
                Rectangle rectangle = (Rectangle)createRectangle(points, lineWidth, color);
                rectangle.setLineWidth(1);
                rectangle.setColor(new RGBColor(0, 0, 0, 1));
                rectangle.setMarker(true);

                return rectangle;
            }
            else if (ShapeType.Circle == shape){
                Point center = points.get(0);
                double radius = Math.sqrt((center.getX()-points.get(1).getX())*(center.getX()-points.get(1).getX()) +
                        (center.getY()-points.get(1).getY())*(center.getY()-points.get(1).getY()));

                Circle circle = new Circle(center, radius);
                setProperies(circle, lineWidth, color);

                return circle;
            }
            else if (ShapeType.Ellipse == shape){
                Point center = points.get(0);
                double radiusX = Math.abs(center.getX() - points.get(1).getX());
                double radiusY = Math.abs(center.getY() - points.get(1).getY());

                Ellipse ellipse = new Ellipse(center, radiusX, radiusY);
                setProperies(ellipse, lineWidth, color);

                return ellipse;
            }
            else return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Shape createRectangle(List<Point> points, double lineWidth, RGBColor color) {
        Point a = points.get(0);
        Point c = points.get(1);

        double startX;
        double startY;
        double endX;
        double endY;

        if (a.x <= c.x){
            startX = a.x;
            endX = c.x;
        }else {
            startX = c.x;
            endX = a.x;
        }

        if (a.y <= c.y){
            startY = a.y;
            endY = c.y;
        }else {
            startY = c.y;
            endY = a.y;
        }

        a = new Point(startX, startY);
        c = new Point(endX, endY);

        Rectangle rectangle = new Rectangle(a, c);
        setProperies(rectangle, lineWidth, color);

        return rectangle;
    }

    private void setProperies(Shape shape, double lineWidth, RGBColor color){
        shape.setLineWidth(lineWidth);
        shape.setColor(color);
    }
}
