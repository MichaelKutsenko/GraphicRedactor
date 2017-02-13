package ua.cn.al.teach.figures.controllers;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import ua.cn.al.teach.figures.engine.FXEngine;
import ua.cn.al.teach.figures.engine.Graphics;
import ua.cn.al.teach.figures.enums.ShapeType;
import ua.cn.al.teach.figures.shapes.*;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    private boolean isCtrlDownPressed;
    private Sheet sheet;
    private Composite selectedShapes;
    private Shape shape;
    private List<Point> points;
    private ShapeType type;
    private ShapeFactory factory;

    private ControlPanes panes;
    private Pane selectedPane;

    private Graphics graph;

    @FXML
    private Canvas cnvs;
    @FXML
    private Pane pnLine;
    @FXML
    private Pane pnCurve;
    @FXML
    private Pane pnCircle;
    @FXML
    private Pane pnEllipse;
    @FXML
    private Pane pnSquare;
    @FXML
    private Pane pnRectangle;
    @FXML
    private Pane pnTriangle;
    @FXML
    private Pane pnPolygon;
    @FXML
    private Pane pnSelect;
    @FXML
    private Pane pnMove;
    @FXML
    private Pane pnJoin;
    @FXML
    private Pane pnOnTop;
    @FXML
    private javafx.scene.shape.Rectangle rctlSelect;
    @FXML
    private Label lblY;
    @FXML
    private Label lblX;

    @FXML
    public void initialize() {
        sheet = new Sheet();
        selectedShapes = new Composite();
        factory = ShapeFactory.getInstance();

        graph = Graphics.getInstance();
        graph.addEngine(new FXEngine(cnvs.getGraphicsContext2D()), "FXEngine");

        createControlPanes();
        drawPaneImg();
    }

    @FXML
    void chooseShape(MouseEvent event) {
        if (type != null && points != null) {
            sheet.add(shape);
            points = null;
        }

        Pane pane = (Pane) event.getSource();
        selectedPane = panes.switchPanel(pane);

        specifyShapeType(pane);

        if (pane == pnJoin){
            if (selectedShapes.getShapes().size() > 1){
                joinFigures();
            }
            else if (selectedShapes.getShapes().size() == 1 && selectedShapes.getShapes().get(0) instanceof Composite){
                divorceFugures();
            }
        }

        if (pane == pnOnTop && selectedShapes.getShapes().size() > 0){

            for (Shape s : selectedShapes.getShapes()){
                sheet.remove(s);
                sheet.add(s);
            }
            repaint(event);
        }
    }

    private void specifyShapeType(Pane pane) {
        if (pane == pnLine) type = ShapeType.Line;
        else if (pane == pnCircle) type = ShapeType.Circle;
        else if (pane == pnEllipse) type = ShapeType.Ellipse;
        else if (pane == pnSquare) type = ShapeType.Square;
        else if (pane == pnRectangle) type = ShapeType.Rectangle;
        else if (pane == pnTriangle) type = ShapeType.Triangle;
        else if (pane == pnPolygon) type = ShapeType.Polygon;
        else if (pane == pnCurve) type = ShapeType.Curve;
        else if (pane == pnSelect) type = ShapeType.RectMarker;
        else type = null;

        if (type != null){
            sheet.deselect();
            selectedShapes.clear();
            graph.clear();
            graph.showAsync(sheet);
        }
    }

    private void joinFigures() {
        Composite composite = new Composite();
        for (Shape s : selectedShapes.getShapes()){
            composite.add(s);
            sheet.remove(s);
        }

        selectedShapes.clear();
        selectedShapes.add(composite);
        sheet.add(composite);

        composite.setFocused(true);

        graph.clear();
        graph.showAsync(sheet);
    }

    private void divorceFugures() {
        int num = sheet.getShapes().indexOf(selectedShapes.getShapes().get(0));

        List<Shape> head = (List<Shape>) sheet.sublist(0, num);
        List<Shape> tail = sheet.sublist(num + 1, sheet.getShapes().size());

        List<Shape> shapeList = new ArrayList<>();
        shapeList.addAll(head);
        for (Shape s : ((Composite) selectedShapes.getShapes().get(0)).getShapes()){
            shapeList.add(s);
        }
        if (tail.size() > 0){
            shapeList.addAll(tail);
        }

        sheet.setShapes(shapeList);

        selectedShapes.clear();
    }

    @FXML
    public void startDraw(MouseEvent event) {
        if (selectedPane == null) {
            return;
        }

        if (points != null) {
            points.add(new Point(event.getX(), event.getY()));
            shape = factory.getShape(points, type);

            repaint(event);
            return;
        }

        points = new ArrayList<>();
        points.add(new Point(event.getX(), event.getY()));
    }

    @FXML
    public void drawShape(MouseEvent event) {
        showCoordinates(event);

        if (selectedPane == null) {
            return;
        }

        if (points.size() == 1) points.add(new Point(event.getX(), event.getY()));
        else points.set(points.size()-1, new Point(event.getX(), event.getY()));

        if (selectedPane == pnMove){
            sheet.moveTo(points.get(points.size() - 2), points.get(points.size() - 1));
            points.set(points.size() - 2, points.get(points.size() - 1));
        }
        else if (selectedPane == pnSelect){
            sheet.selectFigures((Rectangle)factory.getShape(points, type), isCtrlDownPressed, selectedShapes);
            System.out.println(selectedShapes.getShapes().size());
        }

        shape = factory.getShape(points, type);
        repaint(event);
    }

    @FXML
    void finishDraw(MouseEvent event) {
        if (selectedPane == null || selectedPane == pnMove || (points.size()==1 && selectedPane!=pnSelect)){
            points = null;
            return;
        }

        if (selectedPane == pnSelect) {
            if (points.size() == 1) {

                if ( event.getButton() == MouseButton.PRIMARY ){
                    sheet.attachSelectedFigure(new Point(event.getX(), event.getY()), isCtrlDownPressed, selectedShapes);
                }
                else {
                    sheet.separateFigure(new Point(event.getX(), event.getY()), isCtrlDownPressed, selectedShapes);
                }


                shape = null;
                repaint(event);
            }
            points = null;
            return;
        }

        if ((type == ShapeType.Polygon  && !(isaCompletePolygon(event))) ||
                (type == ShapeType.Curve && points.size() < 3)){
            return;
        }

        if (shape != null){
            sheet.add(shape);
        }
        points = null;

        repaint(event);
    }

    private void repaint(MouseEvent event) {
        Composite c = new Composite();
        c.add(sheet);

        if (type == ShapeType.Polygon && points != null) {
            if (isaCompletePolygon(event) && points.size() > 2) {
                points.set(points.size()-1, points.get(0));
                shape = factory.getShape(points, type);
                c.add(new Circle(points.get(0), 10));
            } else {
                shape = factory.getShape(points, ShapeType.Polyline);
            }
        }
        c.add(shape);

        graph.clear();
        graph.showAsync(c);
    }

    private boolean isaCompletePolygon(MouseEvent event) {
        return Math.abs(event.getX() - points.get(0).getX()) <= 5 && Math.abs(event.getY() - points.get(0).getY()) <= 5;
    }

    @FXML
    void collectFigures(KeyEvent event) {
        if (!event.isControlDown()) {
            return;
        }
        isCtrlDownPressed = true;
    }

    @FXML
    void onKeyReleased(KeyEvent event) {
        if ("Delete".equals(event.getCode().getName())) {
            if (selectedShapes.getShapes().size() > 0){
                for (Shape s : selectedShapes.getShapes()){
                    sheet.remove(s);
                }
                selectedShapes.clear();
                graph.clear();
                graph.showAsync(sheet);
            }
        }

        if ("Ctrl".equals(event.getCode().getName())) {
            isCtrlDownPressed = false;
        }
    }


    @FXML
    public void showCoordinates(MouseEvent event) {
        lblX.setText("X: " + event.getX());
        lblY.setText("Y: " + event.getY());
    }

    @FXML
    public void deleteCoordinates(MouseEvent event) {
        lblX.setText("X: ");
        lblY.setText("Y: ");
    }

    private void createControlPanes() {
        panes = new ControlPanes();
        panes.addPane(pnLine);
        panes.addPane(pnCurve);
        panes.addPane(pnCircle);
        panes.addPane(pnEllipse);
        panes.addPane(pnSquare);
        panes.addPane(pnRectangle);
        panes.addPane(pnTriangle);
        panes.addPane(pnPolygon);
        panes.addPane(pnSelect);
        panes.addPane(pnMove);
        panes.addPane(pnJoin);
        panes.addPane(pnOnTop);
    }

    private void drawPaneImg() {
        setCurveShape();
        rctlSelect.getStrokeDashArray().add(3d);
    }

    private void setCurveShape() {
        Path path = new Path();

        MoveTo moveTo = new MoveTo();
        moveTo.setX(10);
        moveTo.setY(10);

        QuadCurveTo quadTo = new QuadCurveTo();
        quadTo.setControlX(60);
        quadTo.setControlY(90);
        quadTo.setX(180);
        quadTo.setY(50);

        path.getElements().add(moveTo);
        path.getElements().add(quadTo);

        pnCurve.getChildren().add(path);
    }
}
