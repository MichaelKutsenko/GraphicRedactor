/*
 * 
 * 
 */
package ua.cn.al.teach.figures.engine;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import ua.cn.al.teach.figures.shapes.Point;
import ua.cn.al.teach.figures.shapes.RGBColor;

import java.util.List;

/**
 *
 * @author al
 */
public class FXEngine implements GraphicsEngine {

    GraphicsContext gc;

    public FXEngine() {
        this(null);
    }

    public FXEngine(GraphicsContext gc) {
        this.gc = gc;
    }

    public void setGC(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void clear() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    @Override
    public void strokeLine(double x1, double y1, double x2, double y2) {
        gc.strokeLine(x1, y1, x2, y2);
    }

    @Override
    public void strokeOval(double x, double y, double radiusX, double radiusY) {
        gc.strokeArc(x-radiusX, y-radiusY, radiusX*2, radiusY*2, 0, 360, ArcType.OPEN);
    }

    @Override
    public void paintPolygon(double x, double y, List<Point> points) {
        double[] xi = new double[points.size() - 1];
        double[] yi = new double[points.size() - 1];

        for (int i = 0; i < points.size() - 1; i++) {
            xi[i] = points.get(i).getX() + x;
            yi[i] = points.get(i).getY() + y;
        }

        gc.fillPolygon(xi, yi, points.size() - 1);
    }

    @Override
    public void paintOval (double x, double y, double radiusX, double radiusY) {
        gc.fillOval(x - radiusX, y - radiusY, radiusX*2, radiusY*2);
    }

    @Override
    public void setColor(RGBColor rgb) {
        Color c = new Color(rgb.getR(), rgb.getG(), rgb.getB(), rgb.getOpacity());
        gc.setStroke(c);
    }

    @Override
    public void setLineWidth(double w) {
        gc.setLineWidth(w);
    }

    @Override
    public void setDashLength(double dashLength) {
        gc.setLineDashes(dashLength);
    }

    @Override
    public void setFillColor(RGBColor rgb) {
        Color c = new Color(rgb.getR(), rgb.getG(), rgb.getB(), rgb.getOpacity());
        gc.setFill(c);        
    }
}
