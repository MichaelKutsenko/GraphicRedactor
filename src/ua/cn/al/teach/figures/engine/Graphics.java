/*
 * 
 * 
 */
package ua.cn.al.teach.figures.engine;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import ua.cn.al.teach.figures.shapes.Shape;

import java.util.HashMap;

/**
 *
 * @author al
 */
public class Graphics extends Service {
    private volatile static Graphics instance = null;
    private HashMap<String, GraphicsEngine> configuredEngines = new HashMap<>();
    private GraphicsEngine currentGE;

    private Shape shape;

    private Graphics() {
    }

    public static Graphics getInstance() {
        if (instance == null) {
            instance = new Graphics();
        }
        return instance;
    }

    @Override
    protected Task createTask() {
        Shape shape = getShape();

        return new Task() {
            @Override
            protected Object call() {
                show(shape);
                return null;
            }
        };
    }

    public void clear(){
        currentGE.clear();
    }

    public void show(Shape s){
        s.show(currentGE);
    }

    public void addEngine(GraphicsEngine ge, String name) {
        currentGE = ge;
        configuredEngines.put(name, ge);
    }

    public GraphicsEngine getCurrentGE() {
        return currentGE;
    }


    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
