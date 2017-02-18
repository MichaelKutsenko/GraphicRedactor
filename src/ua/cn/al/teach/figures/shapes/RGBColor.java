package ua.cn.al.teach.figures.shapes;

public class RGBColor {
    private final double R;
    private final double G;
    private final double B;
    private final double opacity;

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
}
