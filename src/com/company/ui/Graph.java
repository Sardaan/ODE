package com.company.ui;

import com.company.core.Function;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;


public class Graph {
    private XYChart<Double, Double> graph;

    public Graph(final XYChart<Double, Double> graph) {
        this.graph = graph;
    }

    public void clear(){
        graph.getData().clear();
    }

    public void drawDot(double x, double y, XYChart.Series<Double, Double> series){
        series.getData().add(new XYChart.Data<>(x,y));
        graph.getData().add(series);

    }
    public void drawLine(double x, double y,  XYChart.Series<Double, Double> series){
        series.getData().add(new XYChart.Data<>(x,y));
    }
    public void drawGraph(Function original, Function function){
        final XYChart.Series<Double, Double> series = new XYChart.Series<>();
        double step = 0.1;
        for (double i = original.getX0(); i < original.getXn(); i +=step) {
            drawLine(i, function.getY(i), series);
        }
        graph.getData().add(series);

        for(int i = 0; i<series.getData().size(); i++){
            Node lSymbol = series.getData().get(i).getNode().lookup(".chart-line-symbol");
            lSymbol.setStyle("-fx-background-radius: 0px;-fx-padding: 0px;");
        }
    }

}
