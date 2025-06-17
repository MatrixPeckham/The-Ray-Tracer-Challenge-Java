/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matrixpeckham.raytracerchallenge.core;

/**
 *
 * @author matri
 */
public class Transformation {

    public static Matrix translate(double x, double y, double z) {
        return Matrix.M4x4(
                1, 0, 0, x,
                0, 1, 0, y,
                0, 0, 1, z,
                0, 0, 0, 1
        );
    }

    public static Matrix translate(Tuple t) {
        if (t.isPoint()) {
            throw new IllegalArgumentException("Cannot translate by a point.");
        }
        return translate(t.x, t.y, t.z);
    }

    public static Matrix rotateX(double radians) {
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        return Matrix.M4x4(
                1, 0, 0, 0,
                0, cos, -sin, 0,
                0, sin, cos, 0,
                0, 0, 0, 1
        );
    }

    public static Matrix rotateY(double radians) {
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        return Matrix.M4x4(
                cos, 0, sin, 0,
                0, 1, 0, 0,
                -sin, 0, cos, 0,
                0, 0, 0, 1
        );
    }

    public static Matrix rotateZ(double radians) {
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        return Matrix.M4x4(
                cos, -sin, 0, 0,
                sin, cos, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );
    }

    public static Matrix scaling(Tuple s) {
        return scaling(s.x, s.y, s.z);
    }

    public static Matrix scaling(double s) {
        return scaling(s, s, s);
    }

    public static Matrix scaling(double x, double y, double z) {
        return Matrix.M4x4(
                x, 0, 0, 0,
                0, y, 0, 0,
                0, 0, z, 0,
                0, 0, 0, 1
        );
    }

    static Matrix shearing(double xy, double xz, double yx, double yz, double zx,
            double zy) {
        return Matrix.M4x4(
                1, xy, xz, 0,
                yx, 1, yz, 0,
                zx, zy, 1, 0,
                0, 0, 0, 1
        );
    }

}
