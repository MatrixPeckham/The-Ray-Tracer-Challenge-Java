/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrixpeckham.raytracerchallenge.core;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.StringReader;
import java.util.Scanner;
import org.junit.Test;

/**
 *
 * @author matri
 */
public class CanvasTest {

    public CanvasTest() {
    }

    @Test
    public void testCanvasInitialization() {
        Canvas c = new Canvas(10, 20);
        Color black = new Color(0, 0, 0);
        assertThat(c.getWidth(), is(10));
        assertThat(c.getHeight(), is(20));
        for (int x = 0; x < c.getWidth(); x++) {
            for (int y = 0; y < c.getHeight(); y++) {
                assertThat(black.equals(c.pixelAt(x, y)), is(true));
            }
        }
    }

    @Test
    public void testEquals() {
        Canvas c = new Canvas(10, 10);
        Canvas s = new Canvas(10, 10);
        Canvas t = new Canvas(5, 5);
        assertThat(c.equals(s), is(true));
        assertThat(c.equals(t), is(false));
        c.writePixel(1, 1, new Color(1, 0, 1));
        assertThat(c.equals(s), is(false));
    }

    @Test
    public void testOutOfBoundsIgnored() {
        Canvas c = new Canvas(10, 10);
        Canvas s = new Canvas(10, 10);
        Color c1 = new Color(1, 0, 1);
        c.writePixel(-1, 0, c1);
        assertThat(c.equals(s), is(true));
        c.writePixel(10, 0, c1);
        assertThat(c.equals(s), is(true));
        c.writePixel(0, -1, c1);
        assertThat(c.equals(s), is(true));
        c.writePixel(0, 10, c1);
        assertThat(c.equals(s), is(true));
    }

    @Test
    public void testWritePixel() {
        Canvas c = new Canvas(10, 20);
        Color red = new Color(1, 0, 0);
        c.writePixel(2, 5, red);
        assertThat(red.equals(c.pixelAt(2, 5)), is(true));
    }

    @Test
    public void testPPMHeader() {
        Canvas c = new Canvas(5, 3);
        String PPM = c.toPPM();
        String header1 = "P3";
        String header2 = "5 3";
        String header3 = "255";
        Scanner s = new Scanner(new StringReader(PPM));
        assertThat(header1.equals(s.nextLine()), is(true));
        assertThat(header2.equals(s.nextLine()), is(true));
        assertThat(header3.equals(s.nextLine()), is(true));
    }

    @Test
    public void testPPMWhole() {
        Canvas c = new Canvas(5, 3);
        Color c1 = new Color(1.5, 0, 0);
        Color c2 = new Color(0, 0.5, 0);
        Color c3 = new Color(-0.5, 0, 1);
        c.writePixel(0, 0, c1);
        c.writePixel(2, 1, c2);
        c.writePixel(4, 2, c3);
        String PPM = c.toPPM();
        String line4 = "255 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
        String line5 = "0 0 0 0 0 0 0 128 0 0 0 0 0 0 0";
        String line6 = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 255";
        Scanner s = new Scanner(new StringReader(PPM));
        String discard = s.nextLine();
        discard = s.nextLine();
        discard = s.nextLine();
        assertThat(line4.equals(s.nextLine()), is(true));
        assertThat(line5.equals(s.nextLine()), is(true));
        assertThat(line6.equals(s.nextLine()), is(true));
    }

    @Test
    public void testPPMEndsWithNewLine() {
        Canvas c = new Canvas(5, 3);
        String PPM = c.toPPM();
        assertThat(PPM.endsWith("\n"), is(true));
    }

    @Test
    public void testPPM70CharLines() {
        Canvas c = new Canvas(10, 2);
        Color c1 = new Color(1, 0.8, 0.6);
        for (int y = 0; y < c.getHeight(); y++) {
            for (int x = 0; x < c.getWidth(); x++) {
                c.writePixel(x, y, c1);
            }
        }
        String PPM = c.toPPM();
        String line4 = "255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204";
        String line5 = "153 255 204 153 255 204 153 255 204 153 255 204 153";
        String line6 = "255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204";
        String line7 = "153 255 204 153 255 204 153 255 204 153 255 204 153";
        Scanner s = new Scanner(new StringReader(PPM));
        String discard = s.nextLine();
        discard = s.nextLine();
        discard = s.nextLine();
        assertThat(line4.equals(s.nextLine()), is(true));
        assertThat(line5.equals(s.nextLine()), is(true));
        assertThat(line6.equals(s.nextLine()), is(true));
        assertThat(line7.equals(s.nextLine()), is(true));
    }

}
