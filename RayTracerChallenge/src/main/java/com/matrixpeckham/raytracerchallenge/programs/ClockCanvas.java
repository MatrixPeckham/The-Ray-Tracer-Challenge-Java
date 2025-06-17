/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrixpeckham.raytracerchallenge.programs;

import com.matrixpeckham.raytracerchallenge.core.*;
import java.io.*;

/**
 *
 * @author matri
 */
public class ClockCanvas {

    public static void main(String[] args) throws FileNotFoundException {
        Canvas c = new Canvas(500, 500);
        Color c1 = new Color(1, 1, 1);
        Matrix scr = Transformation.translate(250, 250, 0);
        Matrix m = Transformation.translate(200, 0, 0);
        double radians = 2 * Math.PI / 12;
        Matrix rot = Transformation.rotateZ(radians);
        Tuple p = Tuple.Point(0, 0, 0);
        p = m.mult(p);
        for (int i = 0; i < 12; i++) {
            Tuple p2 = scr.mult(p);
            c.writePixel((int) p2.x, (int) p2.y, c1);
            p = rot.mult(p);
        }

        String ppm = c.toPPM();
        PrintStream ps = new PrintStream(new FileOutputStream("output-"
                + System.currentTimeMillis() + ".ppm"));
        ps.append(ppm);
        ps.close();
    }

}
