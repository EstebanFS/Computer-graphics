package escena;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author htrefftz
 */
public class Color {
    // Estos valores están entre 0 y 1
    double r;
    double g;
    double b;

    public Color(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color() {
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }

    public double getB() {
        return b;
    }

    public double getG() {
        return g;
    }

    public double getR() {
        return r;
    }



    @Override
    public String toString() {
        return "Color{" + "r=" + r + "g=" + g + "b=" + b + '}';
    }

}
