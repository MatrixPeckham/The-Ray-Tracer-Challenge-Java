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
public class Tuple {

    public double x;

    public double y;

    public double z;

    public double w;

    public Tuple(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public boolean isPoint() {
        return !isVector();
    }

    public boolean isVector() {
        return doubleEquals(w, 0.0);
    }

    public static Tuple Point(double x, double y, double z) {
        return new Tuple(x, y, z, 1);
    }

    public static Tuple Vector(double x, double y, double z) {
        return new Tuple(x, y, z, 0);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.
                doubleToLongBits(this.x) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.
                doubleToLongBits(this.y) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.
                doubleToLongBits(this.z) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.w) ^ (Double.
                doubleToLongBits(this.w) >>> 32));
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
        final Tuple other = (Tuple) obj;
        if (!doubleEquals(x, other.x)) {
            return false;
        }
        if (!doubleEquals(y, other.y)) {
            return false;
        }
        if (!doubleEquals(z, other.z)) {
            return false;
        }
        if (!doubleEquals(w, other.w)) {
            return false;
        }
        return true;
    }

    public Tuple add(Tuple t) {
        return new Tuple(x + t.x, y + t.y, z + t.z, w + t.w);
    }

    public Tuple sub(Tuple t) {
        return new Tuple(x - t.x, y - t.y, z - t.z, w - t.w);
    }

    public Tuple negate() {
        return new Tuple(-x, -y, -z, -w);
    }

    public Tuple mul(double d) {
        return new Tuple(x * d, y * d, z * d, w * d);
    }

    public static Tuple mul(double d, Tuple t) {
        return t.mul(d);
    }

    public Tuple div(double d) {
        return mul(1 / d);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public Tuple normalize() {
        double mag = magnitude();
        return new Tuple(x / mag, y / mag, z / mag, w / mag);
    }

    public static Tuple normalize(Tuple v) {
        return v.normalize();
    }

    public static double dot(Tuple v, Tuple t) {
        return v.x * t.x + v.y * t.y + v.z * t.z + v.w * t.w;
    }

    public static Tuple cross(Tuple a, Tuple b) {
        return Vector(
                a.y * b.z - a.z * b.y,
                a.z * b.x - a.x * b.z,
                a.x * b.y - a.y * b.x);
    }

    @Override
    public String toString() {
        return (isPoint() ? "P( " : "V( ") + x + ", " + y + ", " + z + " )";
    }

}
