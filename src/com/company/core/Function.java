package com.company.core;

public class Function {
    private double x0;
    private double y0;
    private double xn;
    private double accuracy;
    private FunctionType type;

    public Function(){}

    public Function(double x0, double y0, double xn, double accuracy){
        this.x0 = x0;
        this.y0 = y0;
        this.xn = xn;
        this.accuracy = accuracy;
    }

    public double getX0() {
        return x0;
    }

    public double getY0() {
        return y0;
    }

    public double getXn() {
        return xn;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public FunctionType getType() {
        return type;
    }
    public void setType(FunctionType type) {
        this.type = type;
    }

    public double getYDerivative(double x, double y){
        switch (type){
            case FIRST:
                return 4*Math.sin(x)-y;
            case SECOND:
                return x-y;
            default:
                return Math.cos(x)-y;
        }
    }
    public double getY(double x){
        switch (type){
            case FIRST:
                return ((getY0()-2*Math.sin(getX0())+2*Math.cos(getX0()))/Math.pow(Math.E, -getX0()))*Math.pow(Math.E, -x)+2*Math.sin(x)-2*Math.cos(x);
            case SECOND:
                return (getY0() + 1 - getX0())*Math.pow(Math.E, -x)/Math.pow(Math.E, -getX0())+x-1;
            default:
                return ((getY0()-Math.sin(getX0())/2+Math.cos(getX0())/2)/Math.pow(Math.E, -getX0()))*Math.pow(Math.E, -x)+Math.sin(x)/2+Math.cos(x)/2;
        }
    }
}
