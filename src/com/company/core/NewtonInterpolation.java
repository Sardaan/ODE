package com.company.core;

import java.util.ArrayList;

public class NewtonInterpolation {

    public Function interpolate(final ArrayList<Dot> points) {
        int n = points.size();
        double[][] dividedDiff = new double[n][n];

        for (int i = 0; i < n; i++) {
            dividedDiff[0][i] = points.get(i).getY();
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                dividedDiff[i][j] = dividedDiff[i - 1][j + 1] - dividedDiff[i - 1][j];
            }
        }

        Dot d0 = points.get(0);
        double h = points.get(1).getX() - d0.getX();

        return new Function() {
            @Override
            public double getY(double x) {
                double t = (x-d0.getX())/h;
                double res = d0.getY();
                double product = 1d;
                for (int i = 1; i < n; i++) {
                    product *= t+1-i;
                    product /= i;
                    res += product*dividedDiff[i][0];
                }
                return res;
            }
        };
    }
}
