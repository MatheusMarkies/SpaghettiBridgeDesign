/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.spaghettibridge.utilities;

/**
 *
 * @author Matheus Markies
 */
public class Matrix {

    public static double[][] identity(double[][] A) {
        double[][] I = new double[A.length][A[0].length];
        for (int i = 0; i < I.length; i++) {
            for (int j = 0; j < I[0].length; j++) {
                if (j == i) {
                    I[i][j] = 1;
                } else {
                    I[i][j] = 0;
                }
            }
        }
        return I;
    }

    public static double[][] zero(double[][] A) {
        double[][] I = new double[A.length][A[0].length];
        for (int i = 0; i < I.length; i++)
            for (int j = 0; j < I[0].length; j++)
                I[i][j] = 0;

        return I;
    }

    public static double[][] one(double[][] A) {
        double[][] I = new double[A.length][A[0].length];
        for (int i = 0; i < I.length; i++)
            for (int j = 0; j < I[0].length; j++)
                I[i][j] = 1;

        return I;
    }

    public static double[][] multiply(double[][] A, double[][] B) {
        double[][] newMatrix = new double[A.length][B[0].length];

        for (int i = 0; i < newMatrix[0].length; i++) { // Linha i
            for (int j = 0; j < newMatrix.length; j++) { // Coluna j

                double a = 0;
                for (int k = 0; k < B.length; k++) {
                    a += A[j][k] * B[k][i];
                }

                newMatrix[j][i] = a;
            }
        }

        return newMatrix;
    }

    public static String[][] multiply(double[][] A, String[][] B) {

        String[][] newMatrix = new String[A.length][B[0].length];

        for (int i = 0; i < B[0].length; i++) { // Linha i
            for (int j = 0; j < A.length; j++) { // Coluna j

                String a = "";
                for (int k = 0; k < B.length; k++) {
                    if (a.length() > 0) {
                        a += " + " + A[j][k] + " * " + B[k][i];
                    } else {
                        a += A[j][k] + " * " + B[k][i];
                    }
                }

                newMatrix[j][i] = a;
            }
        }

        return newMatrix;
    }

    public static String toString(double[][] A) {
        String matrix = "";
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                matrix += (A[i][j] + " ");
            }
            matrix += ("\n");
        }
        return matrix;
    }

    public static double[][] invert(double a[][]) {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i = 0; i < n; ++i) {
            b[i][i] = 1;
        }

        gaussian(a, index);

        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    b[index[j]][k]
                            -= a[index[j]][i] * b[index[i]][k];
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

    public static void gaussian(double a[][], int index[]) {
        int n = index.length;
        double c[] = new double[n];

        // Initialize the index
        for (int i = 0; i < n; ++i) {
            index[i] = i;
        }

        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) {
                    c1 = c0;
                }
            }
            c[i] = c1;
        }

        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];

                a[index[i]][j] = pj;

                for (int l = j + 1; l < n; ++l) {
                    a[index[i]][l] -= pj * a[index[j]][l];
                }
            }
        }
    }

}
