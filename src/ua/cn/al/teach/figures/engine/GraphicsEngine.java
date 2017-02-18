package ua.cn.al.teach.figures.engine;

import ua.cn.al.teach.figures.shapes.Point;
import ua.cn.al.teach.figures.shapes.RGBColor;

import java.util.List;

public interface GraphicsEngine {
    public void clear();
    public void strokeLine(double x1, double y1, double x2, double y2);
    public void strokeOval(double centerX, double centerY, double radiusX, double radiusY);
    public void setColor(RGBColor c);
    public void setFillColor(RGBColor c);
    public void setLineWidth(double w);
    public void setDashLength(double dashLength);

    public void paintPolygon(double x, double y, List<Point> points);
    public void paintOval(double x, double y, double radiusX, double radiusY);
}
