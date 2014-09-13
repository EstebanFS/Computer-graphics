/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escena;

/**
 *
 * @author ESTUDIANTES
 */
public class Material {
    double ka;
    double kd;
    double ks;
    int n;

    public Material(double ka, double kd, double ks, int n) {
        this.ka = ka;
        this.kd = kd;
        this.ks = ks;
        this.n = n;
    }

    @Override
    public String toString() {
        return "Material{" + "ka=" + ka + "kd=" + kd + "ks=" + ks + "n=" + n + '}';
    }


}
