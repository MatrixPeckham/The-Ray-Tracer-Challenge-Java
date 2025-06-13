/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrixpeckham.raytracerchallenge.programs;

import static com.matrixpeckham.raytracerchallenge.core.Tuple.Point;
import static com.matrixpeckham.raytracerchallenge.core.Tuple.Vector;

import com.matrixpeckham.raytracerchallenge.core.*;
import java.io.*;

/**
 *
 * @author matri
 */
public class CannonCanvas {

    static class Projectile {

        Tuple loc = Point(0, 1, 0);

        Tuple vel = Vector(1, 1.8, 0).normalize().mul(11.25);

        Projectile() {
        }

        Projectile(Tuple loc, Tuple vel) {
            this.loc = loc;
            this.vel = vel;
        }

    ;

    }

    static class Environment {

        Tuple grav = Vector(0, -0.1, 0);

        Tuple wind = Vector(-0.01, 0, 0);

    }

    public static void main(String[] args) throws FileNotFoundException {
        Environment env = new Environment();
        Projectile proj = new Projectile();
        Canvas c = new Canvas(900, 550);
        Color c1 = new Color(1, 0, 0);
        while (proj.loc.y >= 0) {
            System.out.println(proj.loc + " " + proj.vel);
            c.writePixel((int) (proj.loc.x), (int) (c.getHeight() - proj.loc.y),
                    c1);
            proj = tick(env, proj);
        }
        String ppm = c.toPPM();
        PrintStream ps = new PrintStream(new FileOutputStream("output-"
                + System.currentTimeMillis() + ".ppm"));
        ps.append(ppm);
        ps.close();
    }

    static Projectile tick(Environment env, Projectile proj) {
        Tuple pos = proj.loc.add(proj.vel);
        Tuple vel = proj.vel.add(env.grav).add(env.wind);
        return new Projectile(pos, vel);
    }

}
