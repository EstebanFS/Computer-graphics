/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package imagen;
import escena.Color;

/**
 *
 * @author htrefftz
 */
public class Pixel {
    // Valores entre 0 y 255
    int r;
    int g;
    int b;

    public Pixel(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Pixel(Color color) {
        this.r = (int)(color.getR() * 255d);
        this.g = (int)(color.getG() * 255d);
        this.b = (int)(color.getB() * 255d);
    }

    public Pixel() {
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }
}
