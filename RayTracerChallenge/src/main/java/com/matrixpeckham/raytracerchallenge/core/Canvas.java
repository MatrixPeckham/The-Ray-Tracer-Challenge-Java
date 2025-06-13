/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrixpeckham.raytracerchallenge.core;

import com.matrixpeckham.raytracerchallenge.utils.Utils;
import java.util.Arrays;

/**
 *
 * @author matri
 */
public class Canvas {

    Color[] data;

    private int height;

    private int width;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new Color[width * height];
        for (int i = 0; i < data.length; i++) {
            data[i] = new Color(0, 0, 0);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Canvas other = (Canvas) obj;
        if (this.height != other.height) {
            return false;
        }
        if (this.width != other.width) {
            return false;
        }
        return Arrays.deepEquals(this.data, other.data);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Arrays.deepHashCode(this.data);
        hash = 31 * hash + this.height;
        hash = 31 * hash + this.width;
        return hash;
    }

    public Color pixelAt(int x, int y) {
        return data[index(x, y)];
    }

    public void writePixel(int x, int y, Color n) {
        if (x < 0) {
            return;
        }
        if (y < 0) {
            return;
        }
        if (x >= width) {
            return;
        }
        if (y >= height) {
            return;
        }
        Color c = data[index(x, y)];
        c.r = n.r;
        c.g = n.g;
        c.b = n.b;
    }

    private int index(int x, int y) {
        return x * height + y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String toPPM() {
        StringBuilder out = new StringBuilder();
        out.append("P3\n");
        out.append(width);
        out.append(" ");
        out.append(height);
        out.append("\n");
        out.append(255);
        out.append("\n");

        for (int y = 0; y < height; y++) {
            StringBuilder line = new StringBuilder();
            boolean first = true;
            for (int x = 0; x < width; x++) {
                Color c = pixelAt(x, y);
                String r = "" + (int) (Utils.clamp(c.r, 0, 1) * 255 + 0.5);
                if ((line.length() + r.length()) >= 70) {
                    line.append("\n");
                    out.append(line);
                    line = new StringBuilder();
                    first = true;
                }
                if (!first) {
                    line.append(" ");
                }
                first = false;
                line.append(r);
                String g = "" + (int) (Utils.clamp(c.g, 0, 1) * 255 + 0.5);
                if ((line.length() + g.length()) >= 70) {
                    line.append("\n");
                    out.append(line);
                    line = new StringBuilder();
                    first = true;
                }
                if (!first) {
                    line.append(" ");
                }
                first = false;
                line.append(g);
                String b = "" + (int) (Utils.clamp(c.b, 0, 1) * 255 + 0.5);
                if ((line.length() + b.length()) >= 70) {
                    line.append("\n");
                    out.append(line);
                    line = new StringBuilder();
                    first = true;
                }
                if (!first) {
                    line.append(" ");
                }
                first = false;
                line.append(b);
            }
            out.append(line).append("\n");
        }
        return out.toString();
    }

}
