/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrixpeckham.raytracerchallenge.core;

import static com.matrixpeckham.raytracerchallenge.utils.Utils.doubleEquals;

/**
 *
 * @author matri
 */
public class Color {

    public double r;

    public double g;

    public double b;

    public Color(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.r) ^ (Double.
                doubleToLongBits(this.r) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.g) ^ (Double.
                doubleToLongBits(this.g) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.b) ^ (Double.
                doubleToLongBits(this.b) >>> 32));
        return hash;
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
        final Color other = (Color) obj;
        if (!doubleEquals(r, other.r)) {
            return false;
        }
        if (!doubleEquals(g, other.g)) {
            return false;
        }
        if (!doubleEquals(b, other.b)) {
            return false;
        }
        return true;
    }

    public Color add(Color t) {
        return new Color(r + t.r, g + t.g, b + t.b);
    }

    public Color sub(Color t) {
        return new Color(r - t.r, g - t.g, b - t.b);
    }

    public Color negate() {
        return new Color(-r, -g, -b);
    }

    public Color mul(double d) {
        return new Color(r * d, g * d, b * d);
    }

    public static Color mul(double d, Color t) {
        return t.mul(d);
    }

    public Color div(double d) {
        return mul(1 / d);
    }

    public double magnitude() {
        return Math.sqrt(r * r + g * g + b * b);
    }

    public Color normalize() {
        double mag = magnitude();
        return new Color(r / mag, g / mag, b / mag);
    }

    public static Color normalize(Color v) {
        return v.normalize();
    }

    public static double dot(Color v, Color t) {
        return v.r * t.r + v.g * t.g + v.b * t.b;
    }

    public static Color mul(Color a, Color b) {
        return new Color(
                a.r * b.r,
                a.g * b.g,
                a.b * b.b
        );
    }

    @Override
    public String toString() {
        return "C( " + r + ", " + g + ", " + b + " )";
    }

}
