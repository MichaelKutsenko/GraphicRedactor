/*
 * 
 * 
 */
package ua.cn.al.teach.figures.engine;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import ua.cn.al.teach.figures.shapes.RGBColor;

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
//        gc.strokeOval(x - radiusX, y - radiusY, radiusX*2, radiusY*2);
        gc.strokeArc(x-radiusX, y-radiusY, radiusX*2, radiusY*2, 0, 360, ArcType.OPEN);
    }

    public void strokeCurve(double x1, double y1, double x2, double y2) {
        gc.strokeArc(x2, y2, y2, y2, y2, x2, ArcType.CHORD);
    }

    @Override
    public void setColor(RGBColor rgb) {
        Color c = new Color(rgb.R, rgb.G, rgb.B, rgb.opacity);
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
        Color c = new Color(rgb.R, rgb.G, rgb.B, rgb.opacity);
        gc.setFill(c);        
    }
}
