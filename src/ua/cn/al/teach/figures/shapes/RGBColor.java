package ua.cn.al.teach.figures.shapes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RGBColor implements Cloneable{
    @JsonProperty("red")
    private double R;
    @JsonProperty("green")
    private double G;
    @JsonProperty("black")
    private double B;
    @JsonProperty("opacity")
    private double opacity;

    public RGBColor() {
        this(0, 0, 0, 1);
    }

    public RGBColor(double R, double G, double B, double opacity) {
        this.R = R;
        this.G = G;
        this.B = B;
        this.opacity = opacity;
    }

    public double getR() {
        return R;
    }

    public double getG() {
        return G;
    }

    public double getB() {
        return B;
    }

    public double getOpacity() {
        return opacity;
    }

    public void setR(double r) {
        R = r;
    }

    public void setG(double g) {
        G = g;
    }

    public void setB(double b) {
        B = b;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    @Override
    protected RGBColor clone() throws CloneNotSupportedException {
        return (RGBColor) super.clone();
    }
}
