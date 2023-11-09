package com.cgvsu.rasterizationfxapp;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;

import com.cgvsu.rasterization.*;
import javafx.scene.paint.Color;

public class RasterizationController {

    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        Rasterization.drawLine(canvas.getGraphicsContext2D(), 0, 0, 100, 150, Color.BLACK, Color.YELLOW);
        Rasterization.drawLine(canvas.getGraphicsContext2D(), 300, 400, 300, 200, Color.ORANGE, Color.FUCHSIA);
        Rasterization.drawLine(canvas.getGraphicsContext2D(), 400, 550, 750, 550, Color.RED, Color.NAVY);
        Rasterization.drawLine(canvas.getGraphicsContext2D(), 700, 50, 500, 200, Color.BLUE, Color.PINK);
    }

}