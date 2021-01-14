/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrixpeckham.raytracerchallenge.utils;

/**
 *
 * @author matri
 */
public class Utils {

    public static final double EPSILON = 0.0001;

    public static boolean doubleEquals(double a, double b) {
        return (Math.abs(a - b)) < EPSILON;
    }

}
