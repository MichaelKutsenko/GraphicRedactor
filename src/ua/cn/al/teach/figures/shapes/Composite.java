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
public class Composite extends Shape {
    private List<Shape> shapes;

    public Composite() {
        shapes = new ArrayList<>();
    }
    
    @Override
    public void draw(GraphicsEngine ge) {
       for(Shape s: shapes){
           s.show(ge);
       }
    }

    public void selectFigures(Rectangle focusRect, boolean groupFigures, Composite composite) {
        if (!groupFigures) {
            composite.clear();
        }

        for (Shape s : shapes) {
            if (groupFigures && s.isFocused()) {
                continue;
            }

            if (s.containedInField(focusRect)) {
                s.setFocused(true);
                composite.add(s);
            } else {
                s.setFocused(false);
                composite.remove(s);
            }
        }
    }

    @Override
    public boolean containedInField(Rectangle focusRect){
        for (Shape shape : shapes){
            if (!shape.containedInField(focusRect)){
                return false;
            }
        }
        return true;
    }

    public void attachSelectedFigure(Point point, boolean groupFigures, Composite composite) {
        if (!groupFigures) {
            composite.clear();
        }

        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (groupFigures && shapes.get(i).isFocused()) continue;

            if (shapes.get(i).containPoint(point)) {
                shapes.get(i).setFocused(true);
                composite.add(shapes.get(i));

                if (groupFigures) {
                    return;
                } else {
                    for (int j = 0; j < i; j++) {
                        shapes.get(j).setFocused(false);
                        composite.remove(shapes.get(j));
                    }
                    return;
                }
            } else {
                shapes.get(i).setFocused(false);
                composite.remove(shapes.get(i));
            }
        }
    }

    public void separateFigure(Point point, boolean startFromLast, Composite composite){
        if (startFromLast){
            for (int i = shapes.size(); i > 0; i--){

                if (shapes.get(i-1).isFocused() && shapes.get(i-1).containPoint(point)) {
                    shapes.get(i-1).setFocused(false);
                    composite.remove(shapes.get(i-1));
                    return;
                }
            }
        }
        else {
            for (int i = 0; i <  shapes.size(); i++){

                if (shapes.get(i).isFocused() && shapes.get(i).containPoint(point)) {
                    shapes.get(i).setFocused(false);
                    composite.remove(shapes.get(i));

                    return;
                }
            }
        }
    }

    @Override
    public boolean containPoint(Point point) {
        for (Shape s : shapes){
            if (s.containPoint(point)) {
                return true;
            }
        }
        return false;
    }

    public void deselect() {
        for(Shape s: shapes){
            s.setFocused(false);
        }
    }

    @Override
    public void moveTo(Point from, Point dest) {
        for(Shape s: shapes){
            if (s.isFocused) {
                s.moveTo(from, dest);
            }
        }
    }

    @Override
    protected boolean containInternalPoint(Point point) {
        for (Shape s : shapes){
            if (s.containInternalPoint(point)) {
                return true;
            }
        }

        return false;
    }

    public void paint(Point point, RGBColor color){
        for (int i = shapes.size() - 1; i >= 0; i--){
            if (shapes.get(i).containInternalPoint(point)){
                shapes.get(i).setFill(color);
                shapes.get(i).setPainted(true);
                return;
            }
        }
    }

    public void add(Shape s){
        shapes.add(s);
    }

    public boolean remove(Shape s){
        return shapes.remove(s);
    }

    public boolean contains(Shape s){
        return shapes.contains(s);
    }

    public void clear(){
        shapes.clear();
    }

    public List sublist(int fromIndex, int toIndex){
        return shapes.subList(fromIndex, toIndex);
    }

    public void set(int index, Shape element ){
        shapes.set(index, element);
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public void setFocused(boolean isFocused){
        this.isFocused = isFocused;

        for (Shape s : shapes){
            s.setFocused(isFocused);
        }
    }

    @Override
    public void setColor(RGBColor color){
        for (Shape s : shapes){
            s.setColor(color);
        }
    }

    @Override
    public void setLineWidth(double lineWidth) {
        for (Shape s : shapes){
            s.setLineWidth(lineWidth);
        }
    }

    @Override
    public void setFill(RGBColor color) {
        for (Shape s : shapes){
            s.setFill(color);
        }
    }

    @Override
    public void setPainted(boolean isPainted) {
        for (Shape s : shapes){
            s.setPainted(isPainted);
        }
    }
}
