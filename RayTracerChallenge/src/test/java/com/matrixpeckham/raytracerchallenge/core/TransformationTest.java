/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.matrixpeckham.raytracerchallenge.core;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.*;

/**
 *
 * @author matri
 */
public class TransformationTest {

    public TransformationTest() {
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
    public void testTranslation() {
        Matrix t = Transformation.translate(5.0, -3.0, 2.0);
        Tuple p = Tuple.Point(-3.0, 4.0, 5.0);
        Tuple ex = Tuple.Point(2.0, 1.0, 7.0);
        Tuple re = t.mult(p);
        assertThat(re.equals(ex), is(true));
    }

    @Test
    public void testScalingPoint() {
        Matrix t = Transformation.scaling(2.0, 3.0, 4.0);
        Tuple p = Tuple.Point(-4.0, 6.0, 8.0);
        Tuple ex = Tuple.Point(-8.0, 18.0, 32.0);
        Tuple re = t.mult(p);
        assertThat(re.equals(ex), is(true));
    }

    @Test
    public void testScalingVector() {
        Matrix t = Transformation.scaling(2.0, 3.0, 4.0);
        Tuple p = Tuple.Vector(-4.0, 6.0, 8.0);
        Tuple ex = Tuple.Vector(-8.0, 18.0, 32.0);
        Tuple re = t.mult(p);
        assertThat(re.equals(ex), is(true));
    }

    @Test
    public void testScalingTuple() {
        Matrix t = Transformation.scaling(Tuple.Vector(2.0, 3.0, 4.0));
        Tuple p = Tuple.Vector(-4.0, 6.0, 8.0);
        Tuple ex = Tuple.Vector(-8.0, 18.0, 32.0);
        Tuple re = t.mult(p);
        assertThat(re.equals(ex), is(true));
    }

    @Test
    public void testTranslationDoesntEffectVector() {
        Matrix t = Transformation.translate(5.0, -3.0, 2.0);
        Tuple p = Tuple.Vector(-3.0, 4.0, 5.0);
        Tuple re = t.mult(p);
        assertThat(re.equals(p), is(true));
    }

    @Test
    public void testTranslationTuple() {
        Matrix t = Transformation.translate(Tuple.Vector(5.0, -3.0, 2.0));
        Tuple p = Tuple.Point(-3.0, 4.0, 5.0);
        Tuple ex = Tuple.Point(2.0, 1.0, 7.0);
        Tuple re = t.mult(p);
        assertThat(re.equals(ex), is(true));
    }

    @Test
    public void testTranslationTuplePoint() {
        assertThrows(IllegalArgumentException.class, () -> Transformation.
                translate(Tuple.Point(5.0, -3.0, 2.0)));
    }

    @Test
    public void testInverseTranslation() {
        Matrix t = Transformation.translate(5.0, -3.0, 2.0);
        Matrix inv = Matrix.invert(t);
        Tuple p = Tuple.Point(-3.0, 4.0, 5.0);
        Tuple ex = Tuple.Point(-8.0, 7.0, 3.0);
        Tuple re = inv.mult(p);
        assertThat(ex.equals(re), is(true));
    }

    @Test
    public void testRotateX() {
        Tuple p = Tuple.Point(0.0, 1.0, 0.0);
        Matrix halfQuarter = Transformation.rotateX(Math.PI / 4);
        Matrix fullQuarter = Transformation.rotateX(Math.PI / 2);
        Tuple exHalf = Tuple.Point(0.0, Math.sqrt(2) / 2, Math.sqrt(2) / 2);
        Tuple exFull = Tuple.Point(0.0, 0.0, 1.0);
        Tuple reHalf = halfQuarter.mult(p);
        Tuple reFull = fullQuarter.mult(p);
        assertThat(reHalf.equals(exHalf), is(true));
        assertThat(reFull.equals(exFull), is(true));
    }

    @Test
    public void testRotateXInverse() {
        Tuple p = Tuple.Point(0.0, 1.0, 0.0);
        Matrix halfQuarter = Matrix.invert(Transformation.rotateX(Math.PI / 4));
        Matrix fullQuarter = Matrix.invert(Transformation.rotateX(Math.PI / 2));
        Tuple exHalf = Tuple.Point(0.0, Math.sqrt(2) / 2, -Math.sqrt(2) / 2);
        Tuple exFull = Tuple.Point(0.0, 0.0, -1.0);
        Tuple reHalf = halfQuarter.mult(p);
        Tuple reFull = fullQuarter.mult(p);
        assertThat(reHalf.equals(exHalf), is(true));
        assertThat(reFull.equals(exFull), is(true));
    }

    @Test
    public void testRotateY() {
        Tuple p = Tuple.Point(0.0, 0.0, 1.0);
        Matrix halfQuarter = Transformation.rotateY(Math.PI / 4);
        Matrix fullQuarter = Transformation.rotateY(Math.PI / 2);
        Tuple exHalf = Tuple.Point(Math.sqrt(2) / 2, 0.0, Math.sqrt(2) / 2);
        Tuple exFull = Tuple.Point(1.0, 0.0, 0.0);
        Tuple reHalf = halfQuarter.mult(p);
        Tuple reFull = fullQuarter.mult(p);
        assertThat(reHalf.equals(exHalf), is(true));
        assertThat(reFull.equals(exFull), is(true));
    }

    @Test
    public void testRotateYInverse() {
        Tuple p = Tuple.Point(0.0, 0.0, 1.0);
        Matrix halfQuarter = Matrix.invert(Transformation.rotateY(Math.PI / 4));
        Matrix fullQuarter = Matrix.invert(Transformation.rotateY(Math.PI / 2));
        Tuple exHalf = Tuple.Point(-Math.sqrt(2) / 2, 0.0, Math.sqrt(2) / 2);
        Tuple exFull = Tuple.Point(-1.0, 0.0, 0.0);
        Tuple reHalf = halfQuarter.mult(p);
        Tuple reFull = fullQuarter.mult(p);
        assertThat(reHalf.equals(exHalf), is(true));
        assertThat(reFull.equals(exFull), is(true));
    }

    @Test
    public void testRotateZ() {
        Tuple p = Tuple.Point(0.0, 1.0, 0.0);
        Matrix halfQuarter = Transformation.rotateZ(Math.PI / 4);
        Matrix fullQuarter = Transformation.rotateZ(Math.PI / 2);
        Tuple exHalf = Tuple.Point(-Math.sqrt(2) / 2, Math.sqrt(2) / 2, 0.0);
        Tuple exFull = Tuple.Point(-1.0, 0.0, 0.0);
        Tuple reHalf = halfQuarter.mult(p);
        Tuple reFull = fullQuarter.mult(p);
        assertThat(reHalf.equals(exHalf), is(true));
        assertThat(reFull.equals(exFull), is(true));
    }

    @Test
    public void testRotateZInverse() {
        Tuple p = Tuple.Point(0.0, 1.0, 0.0);
        Matrix halfQuarter = Matrix.invert(Transformation.rotateZ(Math.PI / 4));
        Matrix fullQuarter = Matrix.invert(Transformation.rotateZ(Math.PI / 2));
        Tuple exHalf = Tuple.Point(Math.sqrt(2) / 2, Math.sqrt(2) / 2, 0.0);
        Tuple exFull = Tuple.Point(1.0, 0.0, 0.0);
        Tuple reHalf = halfQuarter.mult(p);
        Tuple reFull = fullQuarter.mult(p);
        assertThat(reHalf.equals(exHalf), is(true));
        assertThat(reFull.equals(exFull), is(true));
    }

    @Test
    public void testShearXY() {
        Matrix t = Transformation.shearing(1.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        Tuple p = Tuple.Point(2, 3, 4);
        Tuple re = t.mult(p);
        Tuple ex = Tuple.Point(5, 3, 4);
    }

    @Test
    public void testShearXZ() {
        Matrix t = Transformation.shearing(0.0, 1.0, 0.0, 0.0, 0.0, 0.0);
        Tuple p = Tuple.Point(2, 3, 4);
        Tuple re = t.mult(p);
        Tuple ex = Tuple.Point(6, 3, 4);
    }

    @Test
    public void testShearYX() {
        Matrix t = Transformation.shearing(0.0, 0.0, 1.0, 0.0, 0.0, 0.0);
        Tuple p = Tuple.Point(2, 3, 4);
        Tuple re = t.mult(p);
        Tuple ex = Tuple.Point(2, 5, 4);
    }

    @Test
    public void testShearYZ() {
        Matrix t = Transformation.shearing(0.0, 0.0, 0.0, 1.0, 0.0, 0.0);
        Tuple p = Tuple.Point(2, 3, 4);
        Tuple re = t.mult(p);
        Tuple ex = Tuple.Point(2, 7, 4);
    }

    @Test
    public void testShearZX() {
        Matrix t = Transformation.shearing(0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
        Tuple p = Tuple.Point(2, 3, 4);
        Tuple re = t.mult(p);
        Tuple ex = Tuple.Point(2, 3, 6);
    }

    @Test
    public void testShearZY() {
        Matrix t = Transformation.shearing(0.0, 0.0, 0.0, 0.0, 0.0, 1.0);
        Tuple p = Tuple.Point(2, 3, 4);
        Tuple re = t.mult(p);
        Tuple ex = Tuple.Point(2, 3, 7);
    }

    @Test
    public void testTransformationSequence() {
        Tuple p = Tuple.Point(1, 0, 1);
        Matrix a = Transformation.rotateX(Math.PI / 2);
        Matrix b = Transformation.scaling(5, 5, 5);
        Matrix c = Transformation.translate(10, 5, 7);
        Tuple p2 = a.mult(p);
        assertThat(p2.equals(Tuple.Point(1, -1, 0)), is(true));
        Tuple p3 = b.mult(p2);
        assertThat(p3.equals(Tuple.Point(5, -5, 0)), is(true));
        Tuple p4 = c.mult(p3);
        assertThat(p4.equals(Tuple.Point(15, 0, 7)), is(true));
    }

    @Test
    public void testTransformationChain() {
        Tuple p = Tuple.Point(1, 0, 1);
        Matrix a = Transformation.rotateX(Math.PI / 2);
        Matrix b = Transformation.scaling(5, 5, 5);
        Matrix c = Transformation.translate(10, 5, 7);
        Matrix t = c.mult(b.mult(a));
        Tuple p2 = t.mult(p);
        assertThat(p2.equals(Tuple.Point(15, 0, 7)), is(true));
    }

}
