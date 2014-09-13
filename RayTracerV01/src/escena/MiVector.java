package escena;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author LABREDES
 */
public class MiVector {
    double x;
    double y;
    double z;

    public MiVector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public MiVector(Punto p1, Punto p2) {
        this.x = p2.getX() - p1.getX();
        this.y = p2.getY() - p1.getY();
        this.z = p2.getZ() - p1.getZ();
    }

    public MiVector(Punto p){
        this.x = p.getX();
        this.y = p.getY();
        this.z = p.getZ();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    double productoPunto (MiVector v){
        double r;
        r = x*v.getX()+y*v.getY()+z*v.getZ();
        return r;
    }

    public MiVector productoCruz (MiVector v){
        double x1 = y*v.getZ()-z*v.getY();
        double y1 = -x*v.getZ()+z*v.getX();
        double z1 =  x*v.getY()-y*v.getX();
        return new MiVector(x1, y1, z1);
    }

    public void normalizar(){
        double magn = magnitud();
        x = x/magn;
        y = y/magn;
        z = z/magn;
    }

    public double magnitud(){
        double r;
        r = Math.sqrt(x*x + y*y + z*z);
        return r;
    }

    public MiVector suma(MiVector v) {
        double x1 = x + v.getX();
        double y1 = y + v.getY();
        double z1 = z + v.getZ();
        return new MiVector(x1, y1, z1);
    }

    public MiVector resta(MiVector v) {
        double x1 = x - v.getX();
        double y1 = y - v.getY();
        double z1 = z - v.getZ();
        return new MiVector(x1, y1, z1);
    }

    @Override
    public String toString() {
        String s = "x: " + x + " y: " + y + " z: " + z;
        return s;
    }

}
