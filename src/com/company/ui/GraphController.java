package com.company.ui;

import com.company.core.*;
import com.company.core.ODESolver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GraphController implements Initializable {

    @FXML
    private LineChart<Double, Double> chart;
    private Graph graph;
    @FXML
    private ChoiceBox<FunctionType> function;
    @FXML
    private TextField x;
    @FXML
    private TextField y;
    @FXML
    private TextField bound;
    @FXML
    private TextField accuracy;
    @FXML
    private Label error;

    public void clear(){
        graph.clear();
        setErrorLine("");
    }

    public double getX(){
        try {
            return Double.parseDouble(x.getText());
        }catch (NumberFormatException e){
            setErrorLine("неверное значение для X0");
            throw new NumberFormatException();
        }
    }
    public double getY(){
        try {
            return Double.parseDouble(y.getText());
        }catch (NumberFormatException e){
            setErrorLine("неверное значение для Y0");
            throw new NumberFormatException();
        }
    }
    public double getBound(){
        try {
            return Double.parseDouble(bound.getText());
        }catch (NumberFormatException e){
            setErrorLine("неверное значение для Bound");
            throw new NumberFormatException();
        }
    }
    public double getAccuracy(){
        try {
            return Double.parseDouble(accuracy.getText());
        }catch (NumberFormatException e){
            setErrorLine("неверное значение для Accuracy");
            throw new NumberFormatException();
        }
    }

    public FunctionType getFunctionType() {
        return function.getValue();
    }

    public void calculate() {
        clear();
        Function function = new Function(getX(), getY(), getBound(), getAccuracy());
        function.setType(getFunctionType());
        graph.drawGraph(function, new ODESolver().solveODE(function));

    }

    private void setErrorLine(String str){
        error.setText(str);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<FunctionType> typeList
                = FXCollections.observableArrayList(
                FunctionType.FIRST,
                FunctionType.SECOND,
                FunctionType.THIRD
        );
        function.setItems(typeList);
        function.setValue(FunctionType.FIRST);

        graph = new Graph(chart);
    }

}
