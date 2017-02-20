package ua.cn.al.teach.figures.shapes;

public class Sheet extends Composite{

    @Override
    public Sheet clone() throws CloneNotSupportedException {
        return (Sheet) super.clone();
    }

    @Override
    public void setPainted(boolean isPainted) {
        this.isPainted = true;
    }
}
