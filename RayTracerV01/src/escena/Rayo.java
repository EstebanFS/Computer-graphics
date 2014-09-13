package escena;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author LABREDES
 */
public class Rayo {
    Punto p0;
    MiVector u;

    public Rayo(Punto p0, MiVector u) {
        this.p0 = p0;
        this.u = u;
    }

    public Punto evaluar(double t){
        double x = p0.getX()+t*u.getX();
        double y = p0.getY()+t*u.getY();
        double z = p0.getZ()+t*u.getZ();
        return new Punto(x, y, z);
    }

    public Solucion interseccionEsfera(Esfera esfera){
        int numSoluciones = 0;
        double t0 = 0d;
        double t1 = 0d;

        double a = u.productoPunto(u);
        MiVector v1 = new MiVector(esfera.centro, p0);
        double b = 2*(v1.productoPunto(u));
        double c = v1.productoPunto(v1) - esfera.radio * esfera.radio;

        double determinante = b*b - 4*a*c;
        if(determinante < 0d) {
            numSoluciones = Solucion.NO_HAY_SOLUCION;
        } else if (determinante == 0d) {
            numSoluciones = Solucion.UNA_SOLUCION;
            t0 = -b/(2*a);
        } else {
            numSoluciones = Solucion.DOS_SOLUCIONES;
            t0 = (-b - Math.sqrt(determinante))/(2*a);
            t1 = (-b + Math.sqrt(determinante))/(2*a);
        }
        return new Solucion(numSoluciones, t0, t1);
    }

    public Solucion interseccionPlano(Plano plano){
        int numSoluciones = 0;
        double t0 = 0d;
        double t1 = 0d;

        double denominador = u.productoPunto(plano.getNormal());

        if (denominador == 0){
            numSoluciones = Solucion.NO_HAY_SOLUCION;
        }else{
            numSoluciones = Solucion.UNA_SOLUCION;
            t0 = -(plano.getD() + plano.getNormal().productoPunto(new MiVector(p0)));
            t0 = t0/denominador;
        }

        return new Solucion(numSoluciones, t0, 0d);
    }

    @Override
    public String toString() {
        return "Rayo{" + "p0=" + p0 + "u=" + u + '}';
    }

    public static void main(String [] args) {
        Rayo rayo1 = new Rayo(new Punto(0, 2, 2), new MiVector(0, 0, -1));
        Solucion s1 = rayo1.interseccionEsfera(new Esfera(new Punto(0,0,0), 1d));
        System.out.println(s1);
        Rayo rayo2 = new Rayo(new Punto(0, 1, 2), new MiVector(0, 0, -1));
        Solucion s2 = rayo2.interseccionEsfera(new Esfera(new Punto(0,0,0), 1d));
        System.out.println(s2);
        Rayo rayo3 = new Rayo(new Punto(0, 0, 2), new MiVector(0, 0, -1));
        Solucion s3 = rayo3.interseccionEsfera(new Esfera(new Punto(0,0,0), 1d));
        System.out.println(s3);
        //Rayo rayo4 = new Rayo(new Punto(0, 0, 0), new MiVector(0, 0, -1));
        //Solucion s4 = rayo4.interseccionEsfera(new Esfera(new Punto(0,0,-4), 1d));
        //System.out.println(s4);

        Rayo rayo4 = new Rayo(new Punto (0,0,2), new MiVector(0,0,-1));
        Solucion s4 = rayo4.interseccionPlano(new Plano(0,1,0,1));
        System.out.println(s4);

        Rayo rayo5 = new Rayo(new Punto (0,0,2), new MiVector(0,0,-1));
        Solucion s5 = rayo5.interseccionPlano(new Plano(0,0,1,0));
        System.out.println(s5);

    }
}
