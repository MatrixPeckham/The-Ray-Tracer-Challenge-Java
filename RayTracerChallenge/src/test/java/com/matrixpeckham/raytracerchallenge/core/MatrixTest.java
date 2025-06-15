/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.matrixpeckham.raytracerchallenge.core;

import static com.matrixpeckham.raytracerchallenge.core.Matrix.M4x4;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.*;

/**
 *
 * @author matri
 */
public class MatrixTest {

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

    public MatrixTest() {
    }

    @Test
    public void test4x4Construction() {
        Matrix m = new Matrix(4,
                1, 2, 3, 4,
                5.5, 6.5, 7.5, 8.5,
                9, 10, 11, 12,
                13.5, 14.5, 15.5, 16.5
        );
        assertThat(m.get(0, 0), is(1.0));
        assertThat(m.get(0, 3), is(4.0));
        assertThat(m.get(1, 2), is(7.5));
        assertThat(m.get(2, 2), is(11.0));
        assertThat(m.get(3, 0), is(13.5));
        assertThat(m.get(3, 2), is(15.5));
    }

    @Test
    public void testStatic4x4Construction() {
        Matrix m = Matrix.M4x4(
                1, 2, 3, 4,
                5.5, 6.5, 7.5, 8.5,
                9, 10, 11, 12,
                13.5, 14.5, 15.5, 16.5
        );
        assertThat(m.get(0, 0), is(1.0));
        assertThat(m.get(0, 3), is(4.0));
        assertThat(m.get(1, 2), is(7.5));
        assertThat(m.get(2, 2), is(11.0));
        assertThat(m.get(3, 0), is(13.5));
        assertThat(m.get(3, 2), is(15.5));
    }

    @Test
    public void test2x2Construction() {
        Matrix m = new Matrix(2,
                -3, 5,
                1, -2
        );
        assertThat(m.get(0, 0), is(-3.0));
        assertThat(m.get(0, 1), is(5.0));
        assertThat(m.get(1, 0), is(1.0));
        assertThat(m.get(1, 1), is(-2.0));
    }

    @Test
    public void testStatic2x2Construction() {
        Matrix m = Matrix.M2x2(
                -3, 5,
                1, -2
        );
        assertThat(m.get(0, 0), is(-3.0));
        assertThat(m.get(0, 1), is(5.0));
        assertThat(m.get(1, 0), is(1.0));
        assertThat(m.get(1, 1), is(-2.0));
    }

    @Test
    public void test3x3Construction() {
        Matrix m = new Matrix(3,
                -3, 5, 0,
                1, -2, -7,
                0, 1, 1
        );
        assertThat(m.get(0, 0), is(-3.0));
        assertThat(m.get(1, 1), is(-2.0));
        assertThat(m.get(2, 2), is(1.0));
    }

    @Test
    public void test3x3StaticConstruction() {
        Matrix m = Matrix.M3x3(
                -3, 5, 0,
                1, -2, -7,
                0, 1, 1
        );
        assertThat(m.get(0, 0), is(-3.0));
        assertThat(m.get(1, 1), is(-2.0));
        assertThat(m.get(2, 2), is(1.0));
    }

    @Test
    public void testIndexOutOfBounds() {
        Matrix m3 = Matrix.M4x4(
                1, 2, 3, 4,
                5.5, 6.5, 7.5, 8.5,
                9, 10, 11, 12,
                13.5, 14.5, 15.5, 16.5
        );
        assertThrows(IllegalArgumentException.class, () -> m3.get(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> m3.get(1, -1));
        assertThrows(IllegalArgumentException.class, () -> m3.get(4, 0));
        assertThrows(IllegalArgumentException.class, () -> m3.get(1, 4));
        Matrix m = Matrix.M3x3(
                -3, 5, 0,
                1, -2, -7,
                0, 1, 1
        );
        assertThrows(IllegalArgumentException.class, () -> m.get(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> m.get(1, -1));
        assertThrows(IllegalArgumentException.class, () -> m.get(4, 0));
        assertThrows(IllegalArgumentException.class, () -> m.get(1, 4));
        Matrix m2 = Matrix.M2x2(
                -3, 5,
                1, -2
        );
        assertThrows(IllegalArgumentException.class, () -> m2.get(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> m2.get(1, -1));
        assertThrows(IllegalArgumentException.class, () -> m2.get(4, 0));
        assertThrows(IllegalArgumentException.class, () -> m2.get(1, 4));
    }

    @Test
    public void testEquality() {
        Matrix m = Matrix.M4x4(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 8, 7, 6,
                5, 4, 3, 2
        );
        Matrix m2 = Matrix.M4x4(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 8, 7, 6,
                5, 4, 3, 2
        );
        assertThat(m.equals(m2), is(true));
    }

    @Test
    public void testNotEquality() {
        Matrix m = Matrix.M4x4(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 8, 7, 6,
                5, 4, 3, 2
        );
        Matrix m2 = Matrix.M4x4(
                2, 3, 4, 5,
                6, 7, 8, 9,
                8, 7, 6, 5,
                4, 3, 2, 1
        );
        assertThat(m.equals(m2), is(false));
    }

    @Test
    public void testMatrixMultiplication() {
        Matrix m1 = Matrix.M4x4(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 8, 7, 6,
                5, 4, 3, 2
        );
        Matrix m2 = Matrix.M4x4(
                -2, 1, 2, 3,
                3, 2, 1, -1,
                4, 3, 6, 5,
                1, 2, 7, 8
        );
        Matrix ex = Matrix.M4x4(
                20, 22, 50, 48,
                44, 54, 114, 108,
                40, 58, 110, 102,
                16, 26, 46, 42
        );
        Matrix re = m1.mult(m2);
        assertThat(re.equals(ex), is(true));
    }

    @Test
    public void testMatrixTupleMult() {
        Matrix a = Matrix.M4x4(
                1, 2, 3, 4,
                2, 4, 4, 2,
                8, 6, 4, 1,
                0, 0, 0, 1
        );
        Tuple b = new Tuple(1, 2, 3, 1);
        Tuple ex = new Tuple(18, 24, 33, 1);
        Tuple re = a.mult(b);
        assertThat(re.equals(ex), is(true));
    }

    @Test
    public void testIdentityMatrix() {
        Matrix a = Matrix.M4x4(
                0, 1, 2, 4,
                1, 2, 4, 8,
                2, 4, 8, 16,
                4, 8, 16, 32
        );
        Matrix id = new Matrix(4);
        Tuple t = new Tuple(1, 2, 3, 4);
        Matrix mr = a.mult(id);
        Tuple tr = id.mult(t);
        assertThat(a.equals(mr), is(true));
        assertThat(t.equals(tr), is(true));
    }

    @Test
    public void testTranspose() {
        Matrix a = Matrix.M4x4(
                0, 9, 3, 0,
                9, 8, 0, 8,
                1, 8, 5, 3,
                0, 0, 5, 8
        );
        Matrix b = Matrix.M4x4(
                0, 9, 1, 0,
                9, 8, 8, 0,
                3, 0, 5, 5,
                0, 8, 3, 8
        );
        Matrix r = Matrix.transpose(a);

        assertThat(b.equals(r), is(true));
        Matrix id = new Matrix(4);
        assertThat(Matrix.transpose(id).equals(id), is(true));

    }

    @Test
    public void test2x2Determinant() {
        Matrix a = Matrix.M2x2(
                1, 5,
                -3, 2
        );
        assertThat(Matrix.determinant(a), is(17.0));
    }

    @Test
    public void testSubMatrix() {
        Matrix a1 = Matrix.M3x3(
                1, 5, 0,
                -3, 2, 7,
                0, 6, -3
        );
        Matrix ex1 = Matrix.M2x2(
                -3, 2,
                0, 6
        );
        Matrix re1 = Matrix.submatrix(a1, 0, 2);
        assertThat(ex1.equals(re1), is(true));
        Matrix a2 = Matrix.M4x4(
                -6, 1, 1, 6,
                -8, 5, 8, 6,
                -1, 0, 8, 2,
                -7, 1, -1, 1
        );
        Matrix ex2 = Matrix.M3x3(
                -6, 1, 6,
                -8, 8, 6,
                -7, -1, 1
        );
        Matrix rel2 = Matrix.submatrix(a2, 2, 1);
        assertThat(ex2.equals(rel2), is(true));
    }

    @Test
    public void testMinor() {
        Matrix a = Matrix.M3x3(
                3, 5, 0,
                2, -1, -7,
                6, -1, 5
        );
        Matrix b = Matrix.submatrix(a, 1, 0);
        assertThat(Matrix.determinant(b), is(25.0));
        assertThat(Matrix.minor(a, 1, 0), is(25.0));
    }

    @Test
    public void testCofactor() {
        Matrix m = Matrix.M3x3(
                3, 5, 0,
                2, -1, -7,
                6, -1, 5
        );
        assertThat(Matrix.minor(m, 0, 0), is(-12.0));
        assertThat(Matrix.cofactor(m, 0, 0), is(-12.0));
        assertThat(Matrix.minor(m, 1, 0), is(25.0));
        assertThat(Matrix.cofactor(m, 1, 0), is(-25.0));
    }

    @Test
    public void test3x3Determinant() {
        Matrix a = Matrix.M3x3(
                1, 2, 6,
                -5, 8, -4,
                2, 6, 4
        );
        assertThat(Matrix.cofactor(a, 0, 0), is(56.0));
        assertThat(Matrix.cofactor(a, 0, 1), is(12.0));
        assertThat(Matrix.cofactor(a, 0, 2), is(-46.0));
        assertThat(Matrix.determinant(a), is(-196.0));
    }

    @Test
    public void test4x4Determinant() {
        Matrix a = Matrix.M4x4(
                -2, -8, 3, 5,
                -3, 1, 7, 3,
                1, 2, -9, 6,
                -6, 7, 7, -9
        );
        assertThat(Matrix.cofactor(a, 0, 0), is(690.0));
        assertThat(Matrix.cofactor(a, 0, 1), is(447.0));
        assertThat(Matrix.cofactor(a, 0, 2), is(210.0));
        assertThat(Matrix.cofactor(a, 0, 3), is(51.0));
        assertThat(Matrix.determinant(a), is(-4071.0));
    }

    @Test
    public void testInvertible() {
        Matrix a = Matrix.M4x4(
                6, 4, 4, 4,
                5, 5, 7, 6,
                4, -9, 3, -7,
                9, 1, 7, -6
        );
        assertThat(Matrix.determinant(a), is(-2120.0));
        assertThat(a.isInvertible(), is(true));
    }

    @Test
    public void testNotInvertible() {
        Matrix a = Matrix.M4x4(
                -4, 2, -2, 3,
                9, 6, 2, 6,
                0, -5, 1, -5,
                0, 0, 0, 0
        );
        assertThat(Matrix.determinant(a), is(0.0));
        assertThat(a.isInvertible(), is(false));
    }

    @Test
    public void testInverse() {
        Matrix a = Matrix.M4x4(
                -5, 2, 6, -8,
                1, -5, 1, 8,
                7, 7, -6, -7,
                1, -3, 7, 4
        );
        Matrix b = Matrix.invert(a);
        Matrix ex = M4x4(
                0.21805, 0.45113, 0.24060, -0.04511,
                -0.80827, -1.45677, -0.44361, 0.52068,
                -0.07895, -0.22368, -0.05263, 0.19737,
                -0.52256, -0.81391, -0.30075, 0.30639
        );
        assertThat(Matrix.determinant(a), is(532.0));
        assertThat(Matrix.cofactor(a, 2, 3), is(-160.0));
        assertThat(b.get(3, 2), is(-160.0 / 532.0));
        assertThat(Matrix.cofactor(a, 3, 2), is(105.0));
        assertThat(b.get(2, 3), is(105.0 / 532.0));
        assertThat(b.equals(ex), is(true));
    }

    @Test
    public void testInverse2() {
        Matrix a = Matrix.M4x4(
                8, -5, 9, 2,
                7, 5, 6, 1,
                -6, 0, 9, 6,
                -3, 0, -9, -4
        );
        Matrix b = Matrix.invert(a);
        Matrix ex = M4x4(
                -0.15385, -0.15385, -0.28205, -0.53846,
                -0.07692, 0.12308, 0.02564, 0.03077,
                0.35897, 0.35897, 0.43590, 0.92308,
                -0.69231, -0.69231, -0.76923, -1.92308
        );
        assertThat(b.equals(ex), is(true));
    }

    @Test
    public void testInverse3() {
        Matrix a = Matrix.M4x4(
                9, 3, 0, 9,
                -5, -2, -6, -3,
                -4, 9, 6, 4,
                -7, 6, 6, 2
        );
        Matrix b = Matrix.invert(a);
        Matrix ex = M4x4(
                -0.04074, -0.07778, 0.14444, -0.22222,
                -0.07778, 0.03333, 0.36667, -0.33333,
                -0.02901, -0.14630, -0.10926, 0.12963,
                0.17778, 0.06667, -0.26667, 0.33333
        );
        assertThat(b.equals(ex), is(true));
    }

    @Test
    public void testInverseReversesMult() {
        Matrix a = M4x4(
                3, -9, 7, 3,
                3, -8, 2, -9,
                -4, 4, 4, 1,
                -6, 5, -1, 1
        );
        Matrix b = M4x4(
                8, 2, 2, 2,
                3, -1, 7, 0,
                7, 0, 5, 4,
                6, -2, 0, 5
        );
        Matrix c = a.mult(b);
        Matrix d = c.mult(Matrix.invert(b));
        assertThat(d.equals(a), is(true));
    }

}
