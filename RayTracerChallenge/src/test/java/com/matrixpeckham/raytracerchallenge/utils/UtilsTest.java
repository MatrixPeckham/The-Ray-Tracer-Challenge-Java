/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrixpeckham.raytracerchallenge.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *
 * @author matri
 */
public class UtilsTest {

    public UtilsTest() {
    }

    /**
     * Test of doubleEquals method, of class Utils.
     */
    @Test
    public void testDoubleEqualsTrue() {
        double a = 0.0;
        double b = 0.0;
        boolean expResult = true;
        boolean result = Utils.doubleEquals(a, b);
        assertEquals(expResult, result);
    }

    @Test
    public void testDoubleEqualsFalse() {
        double a = 0.0;
        double b = 1.0;
        boolean expResult = false;
        boolean result = Utils.doubleEquals(a, b);
        assertEquals(expResult, result);
    }

    @Test
    public void testDoubleEqualsDiv() {
        System.out.println("doubleEquals");
        double a = 0.33333;
        double b = 1.0 / 3.0;
        boolean expResult = true;
        boolean result = Utils.doubleEquals(a, b);
        assertEquals(expResult, result);
    }

    @Test
    public void testDoubleEqualsDivFalse() {
        System.out.println("doubleEquals");
        double a = 0.33333;
        double b = 1.0 / 4.0;
        boolean expResult = false;
        boolean result = Utils.doubleEquals(a, b);
        assertEquals(expResult, result);
    }

    @Test
    public void testClampMid() {
        double a = 0.5;
        double min = 0;
        double max = 1;
        double result = Utils.clamp(a, min, max);
        assertEquals(a, result, Utils.EPSILON);
    }

    @Test
    public void testClampMax() {
        double a = 1.5;
        double min = 0;
        double max = 1;
        double result = Utils.clamp(a, min, max);
        assertEquals(max, result, Utils.EPSILON);
    }

    @Test
    public void testClampMin() {
        double a = -0.5;
        double min = 0;
        double max = 1;
        double result = Utils.clamp(a, min, max);
        assertEquals(min, result, Utils.EPSILON);
    }

}
