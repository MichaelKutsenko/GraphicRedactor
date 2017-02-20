package ua.cn.al.teach.figures.shapes;

public class Curve extends PolyLine {
    protected Point apex;

    protected double a;
    protected double b;
    protected double c;

    public Point getApex() {
        return apex;
    }

    public void setApex(Point apex) {
        this.apex = apex;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    @Override
    public Curve clone() throws CloneNotSupportedException {
        Curve clone = (Curve) super.clone();
        clone.apex = apex.clone();

        return clone;
    }
}
