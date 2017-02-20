package ua.cn.al.teach.figures.shapes;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Circle extends Ellipse {

    public Circle() {
    }

    public Circle(Point center, double radius) {
        super(center, radius, radius);
    }

    @JsonIgnore
    public double getRadius(){
        return super.getRadiusX();
    }

    @Override
    public Circle clone() throws CloneNotSupportedException {
        return  (Circle) super.clone();
    }
}
