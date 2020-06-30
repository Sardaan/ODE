package com.company.core;

import java.util.ArrayList;

public class ODESolver {

    public Function solveODE(Function function) {
        double length = function.getXn() - function.getX0();
        int division = Math.max(10, (int) (length / (Math.pow(function.getAccuracy(), 1.0d / 5)) + (1.0d - 1e-9d))/2);
        double h = length/division;
        ArrayList<Dot> dots = adamsMethod(function, new Dot(function.getX0(), function.getY0()), h, division, function.getAccuracy());
        return new NewtonInterpolation().interpolate(dots);
    }

    private static ArrayList<Dot> rungeKuttaMethod(Function function, Dot dot0, double h, int count){
        ArrayList<Dot> dots = new ArrayList<>();
        dots.add(dot0);

        double x = dot0.getX();
        double y = dot0.getY();

        for (int i = 1; i < count; i++) {

            double k0 = h * function.getYDerivative(x, y);
            double k1 = h * function.getYDerivative(x + h / 2, y + k0 / 2);
            double k2 = h * function.getYDerivative(x + h / 2, y + k1 / 2);
            double k3 = h * function.getYDerivative(x + h, y + k2);

            y = y + (k0 + 2*k1 + 2*k2 + k3)/6;
            x += h;

            dots.add(new Dot(x, y));
        }
        return dots;
    }

    private static ArrayList<Dot> adamsMethod(Function function, Dot dot0, double h, int count, double accuracy){
        ArrayList<Dot> dots = new ArrayList<>(rungeKuttaMethod(function, dot0, h, 4));
        double[] yDerivative = new double[count+1];

        for (int i = 0; i < 4; i++) {
            yDerivative[i] = function.getYDerivative(dots.get(i).getX(), dots.get(i).getY());
        }

        double x = dots.get(3).getX();
        double y = dots.get(3).getY();

        for (int i = 4; i < count+1; i++) {
            double f1 = yDerivative[i-1]-yDerivative[i-2];
            double f2 = yDerivative[i-1]-2*yDerivative[i-2]+yDerivative[i-3];
            double f3 = yDerivative[i-1]-3*yDerivative[i-2]+3*yDerivative[i-3]-yDerivative[i-4];
            y = y + h*yDerivative[i-1] + Math.pow(h,2)*f1/2 + Math.pow(h,3)*f2*5/12 +Math.pow(h,4)*f3*3/8;
            x += h;
            yDerivative[i] = function.getYDerivative(x, y);
            dots.add(new Dot(x, y));

        }
        return dots;
    }

}
