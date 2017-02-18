package ua.cn.al.teach.figures.shapes;

public class Circle extends Ellipse {

    public Circle(Point center, double radius) {
        super(center, radius, radius);
    }

    public double getRadius(){
        return super.getRadiusX();
    }
}
