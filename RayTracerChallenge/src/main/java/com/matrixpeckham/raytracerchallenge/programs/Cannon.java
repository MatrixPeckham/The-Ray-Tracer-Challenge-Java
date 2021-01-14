/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrixpeckham.raytracerchallenge.programs;

import static com.matrixpeckham.raytracerchallenge.core.Tuple.Point;
import static com.matrixpeckham.raytracerchallenge.core.Tuple.Vector;

import com.matrixpeckham.raytracerchallenge.core.Tuple;

/**
 *
 * @author matri
 */
public class Cannon {

    static class Projectile {

        Tuple loc = Point(0, 1, 0);

        Tuple vel = Vector(1, 0, 0).normalize();

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

        Tuple wind = Vector(0.1, 0, 0);

    }

    public static void main(String[] args) {
        Environment env = new Environment();
        Projectile proj = new Projectile();
        while (proj.loc.y >= 0) {
            System.out.println(proj.loc + " " + proj.vel);
            proj = tick(env, proj);
        }
    }

    static Projectile tick(Environment env, Projectile proj) {
        Tuple pos = proj.loc.add(proj.vel);
        Tuple vel = proj.vel.add(env.grav).add(env.wind);
        return new Projectile(pos, vel);
    }

}
