/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.raytracerchallenge.core;

import static com.matrixpeckham.raytracerchallenge.utils.Utils.doubleEquals;

import java.util.Arrays;

/**
 *
 * @author matri
 */
public class Matrix {

    private double[] values;

    private int size;

    public Matrix(int size, double... values) {
        this.size = size;
        this.values = new double[size * size];
        if (values.length != this.values.length && values.length != 0) {
            throw new IllegalArgumentException(size + "x" + size
                    + " Matrix needs " + this.values.length
                    + " values and only got " + values.length + " or 0");
        }
        if (values.length != 0) {
            for (int i = 0; i < this.values.length; i++) {
                this.values[i] = values[i];
            }
        } else {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    if (row == col) {
                        set(row, col, 1);
                    } else {
                        set(row, col, 0);
                    }
                }
            }
        }
    }

    public static double determinant(Matrix m) {
        if (m.size == 2) {
            return m.get(0, 0) * m.get(1, 1) - m.get(0, 1) * m.get(1, 0);
        }

        double d = 0;

        for (int col = 0; col < m.size; col++) {
            d += m.get(0, col) * cofactor(m, 0, col);
        }

        return d;
    }

    public static Matrix submatrix(Matrix m, int r, int c) {
        Matrix ret = new Matrix(m.size - 1);
        for (int row = 0, mr = 0; row < ret.size; row++, mr++) {
            for (int col = 0, mc = 0; col < ret.size; col++, mc++) {
                if (mr == r) {
                    mr++;
                }
                if (mc == c) {
                    mc++;
                }
                ret.set(row, col, m.get(mr, mc));
            }
        }
        return ret;
    }

    public static double minor(Matrix m, int r, int c) {
        return Matrix.determinant(Matrix.submatrix(m, r, c));
    }

    public static double cofactor(Matrix m, int r, int c) {
        double d = Matrix.determinant(Matrix.submatrix(m, r, c));
        if ((r + c) % 2 == 0) {
            return d;
        } else {
            return -d;
        }
    }

    public static Matrix transpose(Matrix m) {
        Matrix t = new Matrix(m.size);
        for (int row = 0; row < m.size; row++) {
            for (int col = 0; col < m.size; col++) {
                t.set(row, col, m.get(col, row));
            }
        }
        return t;
    }

    public Matrix mult(Matrix m2) {
        if (this.size != m2.size) {
            throw new IllegalArgumentException(
                    "Only same size matrices are supported for multiplication");
        }
        Matrix result = new Matrix(4,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        );
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                result.set(row, col, getMult(m2, row, col));
            }
        }
        return result;
    }

    public Tuple mult(Tuple b) {
        if (size != 4) {
            throw new IllegalArgumentException(
                    "Must be 4x4 matrix to multiply with tuple");
        }
        Tuple t = new Tuple(0, 0, 0, 0);
        t.x = get(0, 0) * b.x + get(0, 1) * b.y + get(0, 2) * b.z + get(0, 3)
                * b.w;
        t.y = get(1, 0) * b.x + get(1, 1) * b.y + get(1, 2) * b.z + get(1, 3)
                * b.w;
        t.z = get(2, 0) * b.x + get(2, 1) * b.y + get(2, 2) * b.z + get(2, 3)
                * b.w;
        t.w = get(3, 0) * b.x + get(3, 1) * b.y + get(3, 2) * b.z + get(3, 3)
                * b.w;
        return t;
    }

    private double getMult(Matrix m2, int row, int col) {
        double r = 0;
        for (int i = 0; i < size; i++) {
            r += get(row, i) * m2.get(i, col);
        }
        return r;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Matrix other = (Matrix) obj;
        if (this.size != other.size) {
            return false;
        }
        for (int i = 0; i < values.length; i++) {
            if (!doubleEquals(values[i], other.values[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Arrays.hashCode(this.values);
        hash = 23 * hash + this.size;
        return hash;
    }

    public static Matrix M4x4(
            double d00, double d01, double d02, double d03,
            double d10, double d11, double d12, double d13,
            double d20, double d21, double d22, double d23,
            double d30, double d31, double d32, double d33
    ) {
        return new Matrix(4,
                d00, d01, d02, d03,
                d10, d11, d12, d13,
                d20, d21, d22, d23,
                d30, d31, d32, d33
        );
    }

    public static Matrix M3x3(
            double d00, double d01, double d02,
            double d10, double d11, double d12,
            double d20, double d21, double d22
    ) {
        return new Matrix(3,
                d00, d01, d02,
                d10, d11, d12,
                d20, d21, d22
        );
    }

    public static Matrix M2x2(
            double d00, double d01,
            double d10, double d11
    ) {
        return new Matrix(2, d00, d01, d10, d11);
    }

    public double get(int row, int col) {
        return values[index(row, col)];
    }

    public void set(int row, int col, double value) {
        values[index(row, col)] = value;
    }

    private int index(int row, int col) {
        if (row < 0 || row >= size) {
            throw new IllegalArgumentException("Row is out of Bounds index:"
                    + row + " size:" + size);
        }
        if (col < 0 || col >= size) {
            throw new IllegalArgumentException("Col is out of Bounds index:"
                    + col + " size:" + size);
        }
        return row * size + col;
    }

    public boolean isInvertible() {
        return determinant(this) != 0;
    }

    static Matrix invert(Matrix a) {
        double det = determinant(a);
        if (det == 0) {
            return new Matrix(0);
        }
        Matrix m = new Matrix(a.size);
        for (int row = 0; row < a.size; row++) {
            for (int col = 0; col < a.size; col++) {
                double c = cofactor(a, row, col);
                m.set(col, row, c / det);
            }
        }
        return m;
    }

}
