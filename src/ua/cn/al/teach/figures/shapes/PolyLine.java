package ua.cn.al.teach.figures.shapes;

import ua.cn.al.teach.figures.engine.GraphicsEngine;

import java.util.Iterator;
import java.util.List;

public class PolyLine extends Shape {

    public void addPoint(Point p) {
        points.add(p);
    }

    public void PolyLine(List<Point> pl) {
        points.addAll(pl);
    }

    @Override
    public void draw(GraphicsEngine ge) {
                Iterator<Point> pi =points.iterator();
                Point p = pi.next();
                while (pi.hasNext()) {
                    Point p2 = pi.next();
                    ge.strokeLine(x + p.getX(), y + p.getY(), x + p2.getX(), y + p2.getY());
                    p = p2;
                }

                if (isPainted()){
                    ge.paintPolygon(getX(), getY(), points);
                }
    }

    @Override
    public boolean containedInField(Rectangle focusRect) {
        double x1, y1;
        for (int i = 0; i < points.size(); i++){
            x1 = points.get(i).getX() + x;
            y1 = points.get(i).getY() + y;

            if (x1 < focusRect.getBottomLeftPoint().getX() || x1 > focusRect.getTopRightPoint().getX() ||
                    y1 < focusRect.getBottomLeftPoint().getY() || y1 > focusRect.getTopRightPoint().getY()){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean containPoint(Point point) {
        final int POINT_AREA = 4;

        double x1 = point.getX() - POINT_AREA;
        double x2 = point.getX() + POINT_AREA;
        double y1 = point.getY() - POINT_AREA;
        double y2 = point.getY() + POINT_AREA;

        if (checkIntersection(new Line(new Point(x1, y1), new Point(x2, y2))) ||
                checkIntersection(new Line(new Point(x2, y1), new Point(x1, y2)))){
            return true;
        }
        return false;
    }

    private boolean checkIntersection(Line line){
        double ax1 = line.getPoints().get(0).getX();
        double ax2 = line.getPoints().get(1).getX();
        double ay1 = line.getPoints().get(0).getY();
        double ay2 = line.getPoints().get(1).getY();

        double cx1, cx2, cy1, cy2;
        double v1, v2, v3, v4;
        for (int i = 0; i< points.size() - 1; i++){
            cx1 = points.get(i).getX() + x;
            cx2 = points.get(i+1).getX() + x;
            cy1 = points.get(i).getY() + y;
            cy2 = points.get(i+1).getY() + y;

            v1 = (cx2-cx1)*(ay1-cy1) - (cy2-cy1)*(ax1-cx1);
            v2 = (cx2-cx1)*(ay2-cy1) - (cy2-cy1)*(ax2-cx1);
            v3 = (ax2-ax1)*(cy1-ay1) - (ay2-ay1)*(cx1-ax1);
            v4 = (ax2-ax1)*(cy2-ay1) - (ay2-ay1)*(cx2-ax1);

            if ((v1*v2<0) && (v3*v4<0)) return true;
        }

        return false;
    }

    @Override
    public boolean containInternalPoint(Point point) {
        if (points.get(0) != points.get(points.size()-1)){
            return false;
        }

        Double[] p = new Double[(points.size() - 1)*2];

        for (int i = 0, j = 0; i < getPoints().size() - 1; i++, j=j+2) {
            p[j] = getPoints().get(i).getX() + getX();
            p[j+1] = getPoints().get(i).getY() + getY();
        }

        javafx.scene.shape.Polygon polygon = new javafx.scene.shape.Polygon();
        polygon.getPoints().addAll(p);

        boolean isContain = polygon.contains(point.getX(), point.getY());

        return isContain;
    }
}
