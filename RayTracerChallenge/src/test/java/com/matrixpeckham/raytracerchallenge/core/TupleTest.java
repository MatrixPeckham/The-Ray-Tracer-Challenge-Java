/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrixpeckham.raytracerchallenge.core;

import static com.matrixpeckham.raytracerchallenge.core.Tuple.Point;
import static com.matrixpeckham.raytracerchallenge.core.Tuple.Vector;
import static com.matrixpeckham.raytracerchallenge.utils.Utils.doubleEquals;
import static org.hamcrest.CoreMatchers.is;

import org.junit.*;

/**
 *
 * @author matri
 */
public class TupleTest {

    public TupleTest() {
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
    public void tupleW1IsPoint() {
        Tuple t = new Tuple(4.3, -4.2, 3.1, 1.0);
        Assert.assertThat(t.x, is(4.3));
        Assert.assertThat(t.y, is(-4.2));
        Assert.assertThat(t.z, is(3.1));
        Assert.assertThat(t.w, is(1.0));
        Assert.assertThat(t.isPoint(), is(true));
        Assert.assertThat(t.isVector(), is(false));
    }

    @Test
    public void tupleW0IsVector() {
        Tuple t = new Tuple(4.3, -4.2, 3.1, 0.0);
        Assert.assertThat(t.x, is(4.3));
        Assert.assertThat(t.y, is(-4.2));
        Assert.assertThat(t.z, is(3.1));
        Assert.assertThat(t.w, is(0.0));
        Assert.assertThat(t.isPoint(), is(false));
        Assert.assertThat(t.isVector(), is(true));
    }

    @Test
    public void pointMakesPoint() {
        Tuple t = Point(4, -4, 3);
        Assert.assertThat(t.x, is(4.0));
        Assert.assertThat(t.y, is(-4.0));
        Assert.assertThat(t.z, is(3.0));
        Assert.assertThat(t.w, is(1.0));
        Assert.assertThat(t.isPoint(), is(true));
        Assert.assertThat(t.isVector(), is(false));
    }

    @Test
    public void vectorMakesVector() {
        Tuple t = Vector(4.0, -4.0, 3.0);
        Assert.assertThat(t.x, is(4.0));
        Assert.assertThat(t.y, is(-4.0));
        Assert.assertThat(t.z, is(3.0));
        Assert.assertThat(t.w, is(0.0));
        Assert.assertThat(t.isPoint(), is(false));
        Assert.assertThat(t.isVector(), is(true));
    }

    @Test
    public void tupleEquals() {
        Tuple t = Point(1, 1, 1);
        Tuple t2 = Vector(1, 1, 1);
        Tuple t3 = new Tuple(1, 1, 1, 1);
        Assert.assertThat(t.equals(t3), is(true));
        Assert.assertThat(t.equals(t2), is(false));
        Assert.assertThat(t3.equals(t2), is(false));
    }

    @Test
    public void addingTuples() {
        Tuple t = new Tuple(3, -2, 5, 1);
        Tuple t2 = new Tuple(-2, 3, 1, 0);
        Tuple t3 = t.add(t2);
        Tuple t4 = new Tuple(1, 1, 6, 1);
        Assert.assertThat(t4.equals(t3), is(true));
        Assert.assertThat(t4.isPoint(), is(true));
        t = new Tuple(6, -1, 4, 0);
        t2 = new Tuple(-2, 3, 1, 0);
        t3 = t.add(t2);
        t4 = new Tuple(4, 2, 5, 0);
        Assert.assertThat(t4.equals(t3), is(true));
        Assert.assertThat(t4.isVector(), is(true));
    }

    @Test
    public void subtractingPoints() {
        Tuple t = Point(3, 2, 1);
        Tuple t2 = Point(5, 6, 7);
        Tuple t3 = t.sub(t2);
        Tuple t4 = Vector(-2, -4, -6);
        Assert.assertThat(t4.equals(t3), is(true));
        Assert.assertThat(t3.isVector(), is(true));
    }

    @Test
    public void subtractingPV() {
        Tuple t = Point(3, 2, 1);
        Tuple t2 = Vector(5, 6, 7);
        Tuple t3 = t.sub(t2);
        Tuple t4 = Point(-2, -4, -6);
        Assert.assertThat(t4.equals(t3), is(true));
        Assert.assertThat(t3.isPoint(), is(true));
    }

    @Test
    public void subtractingVV() {
        Tuple t = Vector(6, 7, 8);
        Tuple t2 = Vector(5, 6, 7);
        Tuple t3 = t.sub(t2);
        Tuple t4 = Vector(1, 1, 1);
        Assert.assertThat(t4.equals(t3), is(true));
        Assert.assertThat(t3.isVector(), is(true));
    }

    @Test
    public void subZero() {
        Tuple t = Vector(6, 7, 8);
        Tuple t2 = Vector(0, 0, 0);
        Tuple t3 = t2.sub(t);
        Tuple t4 = Vector(-6, -7, -8);
        Assert.assertThat(t4.equals(t3), is(true));
    }

    @Test
    public void negation() {
        Tuple t = new Tuple(5, 4, 3, -2);
        Tuple t2 = t.negate();
        Tuple t3 = new Tuple(-5, -4, -3, 2);
        Assert.assertThat(t3.equals(t2), is(true));
    }

    @Test
    public void multiply() {
        Tuple t = new Tuple(1, 2, 3, 0);
        double d = 2;
        Tuple t2 = t.mul(d);
        Tuple t3 = new Tuple(2, 4, 6, 0);
        Assert.assertThat(t3.equals(t2), is(true));
        d = 0.5;
        t2 = Tuple.mul(d, t);
        t3 = new Tuple(0.5, 1, 1.5, 0);
        Assert.assertThat(t3.equals(t2), is(true));
    }

    @Test
    public void divude() {
        Tuple t = new Tuple(1, 2, 3, 0);
        double d = 2;
        Tuple t2 = t.div(d);
        Tuple t3 = new Tuple(0.5, 1, 1.5, 0);
        Assert.assertThat(t3.equals(t2), is(true));
        d = 0.5;
        t2 = t.div(d);
        t3 = new Tuple(2, 4, 6, 0);
        Assert.assertThat(t3.equals(t2), is(true));
    }

    @Test
    public void magnitudes() {
        Tuple t = Vector(1, 0, 0);
        Assert.assertThat(doubleEquals(t.magnitude(), 1), is(true));
        t = Vector(0, 1, 0);
        Assert.assertThat(doubleEquals(t.magnitude(), 1), is(true));
        t = Vector(0, 0, 1);
        Assert.assertThat(doubleEquals(t.magnitude(), 1), is(true));
        t = Vector(1, 2, 3);
        Assert.assertThat(doubleEquals(t.magnitude(), Math.sqrt(14)), is(true));
        t = Vector(-1, -2, -3);
        Assert.assertThat(doubleEquals(t.magnitude(), Math.sqrt(14)), is(true));
    }

    @Test
    public void normalizes() {
        Tuple t = Vector(4, 0, 0);
        Tuple r = t.normalize();
        Assert.assertThat(r.equals(Vector(1, 0, 0)), is(true));
        t = Vector(1, 2, 3);
        r = t.normalize();
        double root14 = Math.sqrt(14.0);
        Tuple e = Vector(1 / root14, 2 / root14, 3 / root14);
        Assert.assertThat(r.equals(e), is(true));
        Assert.assertThat(doubleEquals(r.magnitude(), 1.0), is(true));
        Assert.assertThat(t.normalize().equals(Tuple.normalize(t)), is(true));
    }

    @Test
    public void dot() {
        Tuple t = Vector(1, 2, 3);
        Tuple t2 = Vector(2, 3, 4);
        double dot = Tuple.dot(t, t2);
        Assert.assertThat(doubleEquals(dot, 20.0), is(true));
    }

    @Test
    public void cross() {
        Tuple a = Vector(1, 2, 3);
        Tuple b = Vector(2, 3, 4);
        Tuple ab = Vector(-1, 2, -1);
        Tuple ba = Vector(1, -2, 1);
        Assert.assertThat(Tuple.cross(a, b).equals(ab), is(true));
        Assert.assertThat(Tuple.cross(b, a).equals(ba), is(true));
    }

}
