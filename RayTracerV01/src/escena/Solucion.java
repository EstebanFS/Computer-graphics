package escena;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author LABREDES
 */
public class Solucion {
    int numRespuestas;
    double t0, t1;
    public static final int NO_HAY_SOLUCION = 0;
    public static final int UNA_SOLUCION = 1;
    public static final int DOS_SOLUCIONES = 2;

    public Solucion(int numRespuestas, double t0, double t1) {
        this.t0 = t0;
        this.t1 = t1;
        this.numRespuestas = numRespuestas;
    }

    public int getNumRespuestas() {
        return numRespuestas;
    }

    public void setNumRespuestas(int numRespuestas) {
        this.numRespuestas = numRespuestas;
    }

    public double getT0() {
        return t0;
    }

    public void setT0(double t0) {
        this.t0 = t0;
    }

    public double getT1() {
        return t1;
    }

    public void setT1(double t1) {
        this.t1 = t1;
    }

    @Override
    public String toString() {
        String s = "Num. Respuestas: " + numRespuestas + " t0 " + t0 + " t1 " + t1;
        return s;
    }
    

}
