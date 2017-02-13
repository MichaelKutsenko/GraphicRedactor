/*
 * 
 * 
 */
package ua.cn.al.teach.figures.engine;

import ua.cn.al.teach.figures.shapes.Shape;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author al
 */
public class Graphics {

    volatile static Graphics instance = null;
    HashMap<String, GraphicsEngine> configuredEngines = new HashMap<>();
    GraphicsEngine currentGE;
    final int threadNumber = 4;

    private Graphics() {
    }

    public static Graphics getInstance() {
        if (instance == null) {
            instance = new Graphics();
        }
        return instance;
    }

    public void addEngine(GraphicsEngine ge, String name) {
        currentGE = ge;
        configuredEngines.put(name, ge);
    }

    public GraphicsEngine getCurrentGE() {
        return currentGE;
    }

    public void clear(){
        currentGE.clear();
    }

    public void show(Shape s){
            s.show(currentGE);
    }

    public void showAsync(final Shape s) {
        ExecutorService executor = Executors.newFixedThreadPool(threadNumber);

        executor.submit(new Runnable() {
            @Override
            public void run() {
                synchronized(s){  
                    show(s);
                }
            }
        }
        );
        executor.shutdown();
    }
    int counter = 0;

    public void showAsync(final Shape s, boolean isFinish) {
        ExecutorService executor = Executors.newFixedThreadPool(threadNumber);

        executor.submit(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("start" + ++counter);
                                synchronized(s){
                                    show(s);
                                    System.out.println("draw" + counter);
                                }
                                System.out.println("finish" + counter);
                            }
                        }
        );
        System.out.println("total threads:" + counter);
//         executor.shutdown();
    }

}
