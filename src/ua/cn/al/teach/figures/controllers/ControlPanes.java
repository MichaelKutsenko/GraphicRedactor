package ua.cn.al.teach.figures.controllers;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ControlPanes {
    private List<Pane>  panes = new ArrayList<>();
    private Pane selectedPane;

    public void addPane(Pane pane){
        panes.add(pane);
    }

    public Pane switchPanel(Pane pane) {
        if (pane != selectedPane) {
            if (selectedPane != null) {
                selectedPane.setStyle(null);
            }

            selectedPane = pane;
            selectedPane.setStyle("-fx-background-color: #1ddaff; -fx-border-color: black;");
        }
        return selectedPane;
    }
}
