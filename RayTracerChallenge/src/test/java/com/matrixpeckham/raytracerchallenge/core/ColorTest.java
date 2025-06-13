/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrixpeckham.raytracerchallenge.core;

import static com.matrixpeckham.raytracerchallenge.utils.Utils.doubleEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.*;

/**
 *
 * @author matri
 */
public class ColorTest {

    public ColorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void colorEquals() {
        Color t = new Color(1, 1, 1);
        Color t2 = new Color(1, 2, 1);
        Color t3 = new Color(1, 1, 1);
        assertThat(t.equals(t3), is(true));
        assertThat(t.equals(t2), is(false));
        assertThat(t3.equals(t2), is(false));
    }

    @Test
    public void addingColors() {
        Color t = new Color(3, -2, 5);
        Color t2 = new Color(-2, 3, 1);
        Color t3 = t.add(t2);
        Color t4 = new Color(1, 1, 6);
        assertThat(t4.equals(t3), is(true));
        t = new Color(6, -1, 4);
        t2 = new Color(-2, 3, 1);
        t3 = t.add(t2);
        t4 = new Color(4, 2, 5);
        assertThat(t4.equals(t3), is(true));
    }

    @Test
    public void subtractingColors() {
        Color t = new Color(3, 2, 1);
        Color t2 = new Color(5, 6, 7);
        Color t3 = t.sub(t2);
        Color t4 = new Color(-2, -4, -6);
        assertThat(t4.equals(t3), is(true));
    }

    @Test
    public void subtractingPV() {
        Color t = new Color(3, 2, 1);
        Color t2 = new Color(5, 6, 7);
        Color t3 = t.sub(t2);
        Color t4 = new Color(-2, -4, -6);
        assertThat(t4.equals(t3), is(true));
    }

    @Test
    public void subtractingVV() {
        Color t = new Color(6, 7, 8);
        Color t2 = new Color(5, 6, 7);
        Color t3 = t.sub(t2);
        Color t4 = new Color(1, 1, 1);
        assertThat(t4.equals(t3), is(true));
    }

    @Test
    public void subZero() {
        Color t = new Color(6, 7, 8);
        Color t2 = new Color(0, 0, 0);
        Color t3 = t2.sub(t);
        Color t4 = new Color(-6, -7, -8);
        assertThat(t4.equals(t3), is(true));
    }

    @Test
    public void negation() {
        Color t = new Color(5, 4, 3);
        Color t2 = t.negate();
        Color t3 = new Color(-5, -4, -3);
        assertThat(t3.equals(t2), is(true));
    }

    @Test
    public void multiply() {
        Color t = new Color(1, 2, 3);
        double d = 2;
        Color t2 = t.mul(d);
        Color t3 = new Color(2, 4, 6);
        assertThat(t3.equals(t2), is(true));
        d = 0.5;
        t2 = Color.mul(d, t);
        t3 = new Color(0.5, 1, 1.5);
        assertThat(t3.equals(t2), is(true));
    }

    @Test
    public void divide() {
        Color t = new Color(1, 2, 3);
        double d = 2;
        Color t2 = t.div(d);
        Color t3 = new Color(0.5, 1, 1.5);
        assertThat(t3.equals(t2), is(true));
        d = 0.5;
        t2 = t.div(d);
        t3 = new Color(2, 4, 6);
        assertThat(t3.equals(t2), is(true));
    }

    @Test
    public void magnitudes() {
        Color t = new Color(1, 0, 0);
        assertThat(doubleEquals(t.magnitude(), 1), is(true));
        t = new Color(0, 1, 0);
        assertThat(doubleEquals(t.magnitude(), 1), is(true));
        t = new Color(0, 0, 1);
        assertThat(doubleEquals(t.magnitude(), 1), is(true));
        t = new Color(1, 2, 3);
        assertThat(doubleEquals(t.magnitude(), Math.sqrt(14)), is(true));
        t = new Color(-1, -2, -3);
        assertThat(doubleEquals(t.magnitude(), Math.sqrt(14)), is(true));
    }

    @Test
    public void normalizes() {
        Color t = new Color(4, 0, 0);
        Color r = t.normalize();
        assertThat(r.equals(new Color(1, 0, 0)), is(true));
        t = new Color(1, 2, 3);
        r = t.normalize();
        double root14 = Math.sqrt(14.0);
        Color e = new Color(1 / root14, 2 / root14, 3 / root14);
        assertThat(r.equals(e), is(true));
        assertThat(doubleEquals(r.magnitude(), 1.0), is(true));
        assertThat(t.normalize().equals(Color.normalize(t)), is(true));
    }

    @Test
    public void dot() {
        Color t = new Color(1, 2, 3);
        Color t2 = new Color(2, 3, 4);
        double dot = Color.dot(t, t2);
        assertThat(doubleEquals(dot, 20.0), is(true));
    }

    @Test
    public void cross() {
        Color a = new Color(1, 2, 3);
        Color b = new Color(2, 3, 4);
        Color ab = new Color(2, 6, 12);
        assertThat(Color.mul(a, b).equals(ab), is(true));
    }

}
