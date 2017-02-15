/*
 * 
 * 
 */
package ua.cn.al.teach.figures.shapes;

import ua.cn.al.teach.figures.engine.GraphicsEngine;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author al
 */
public abstract class Shape extends Point {
    protected List<Point> points = new ArrayList<>();
    protected boolean isFocused;
    protected boolean isPainted;

    protected RGBColor color = new RGBColor(0, 0, 0, 1);
    protected RGBColor fill = new RGBColor(0, 1.0, 0, 1);
    protected double lineWidth = 1;
    protected double dashLength = 0;

    protected abstract void draw(GraphicsEngine ge);
    protected abstract boolean containedInField(Rectangle focusRect);
    protected abstract boolean containPoint(Point point);
    protected abstract boolean containInternalPoint(Point point);

    public void show(GraphicsEngine ge) {
        if (!isFocused){
            ge.setLineWidth(lineWidth);
        } else {
            ge.setLineWidth(3);
        }
        ge.setColor(color);
        ge.setFillColor(fill);
        ge.setDashLength(dashLength);
        draw(ge);
    }

    public void setColor(RGBColor c) {
        color = c;
    }

    public void setFill(RGBColor c) {
        fill = c;
    }

    public void setLineWidth(double lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void setFocused(boolean focused) {
        isFocused = focused;
    }

    public void setDashLength(double dashLength) {
        this.dashLength = dashLength;
    }

    public boolean isFocused() {
        return isFocused;
    }

    public boolean isPainted() {
        return isPainted;
    }

    public void setPainted(boolean painted) {
        isPainted = painted;
    }

    public RGBColor getColor() {
        return color;
    }

    public RGBColor getFill() {
        return fill;
    }

    public double getLineWidth() {
        return lineWidth;
    }

    public double getDashLength() {
        return dashLength;
    }

    public List<Point> getPoints() {
        return points;
    }
}
