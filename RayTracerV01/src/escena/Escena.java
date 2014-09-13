package escena;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author htrefftz
 */

import java.util.ArrayList;

public class Escena {

    private ArrayList<Entidad> arrayEntidades;
    private ArrayList<Color> arrayColores;
    private ArrayList<Material> arrayMateriales;
    private boolean DEBUG = true;
    private Color colorBackground = new Color(0.1, 0.1, 0.9);
    private Punto coordenaLuzPuntual;
    private Color colorLuzPuntual;
    private Color luzAmbiental;

    public Escena() {
        arrayEntidades = new ArrayList<Entidad>();
        arrayColores = new ArrayList<Color>();
        arrayMateriales = new ArrayList<Material>();
    }

    public void addEntidad(Entidad entidad) {
        arrayEntidades.add(entidad);
        if (DEBUG) System.out.println("Escena: addEntidad " + entidad);
    }

    public void addColor(Color color) {
        arrayColores.add(color);
        if (DEBUG) System.out.println("Escena: addColor " + color);
    }
    
    public void addMaterial(Material material) {
        arrayMateriales.add(material);
        if (DEBUG) System.out.println("Escena: addMaterial " + material);
    }

    public Color getColor(int i) {
        return arrayColores.get(i);
    }

    public Material getMaterial(int i) {
        return arrayMateriales.get(i);
    }

     public Punto getCoordenaLuzPuntual() {
        return coordenaLuzPuntual;
    }

    public void setCoordenaLuzPuntual(Punto coordenaLuzPuntual) {
        this.coordenaLuzPuntual = coordenaLuzPuntual;
    }

    public Color getColorLuzPuntual() {
        return colorLuzPuntual;
    }

    public void setColorLuzPuntual(Color colorLuzPuntual) {
        this.colorLuzPuntual = colorLuzPuntual;
    }

    public Color getLuzAmbiental() {
        return luzAmbiental;
    }

    public void setLuzAmbiental(Color luzAmbiental) {
        this.luzAmbiental = luzAmbiental;
    }
    
    public Color calcularColorIluminacion(Punto pixel, Entidad entidad){
        
        // Hallar normal
        Punto centro = ((Esfera) entidad).centro;
        MiVector normal = new MiVector(centro,pixel);
        MiVector l = new MiVector(pixel, coordenaLuzPuntual);
        
        normal.normalizar();
        l.normalizar();
        
        // Hallar U
        MiVector U = new MiVector(l.getX() * -1, l.getY() * -1, l.getZ() * -1);
        double aux = U.productoPunto(normal);
        aux = 2*aux;
        MiVector aux1 = new MiVector(aux * normal.getX(), aux * normal.getY(), 
                                                          aux * normal.getZ());
        MiVector R = U.resta(aux1);
        R.normalizar();
        
        // Hallar V
        MiVector V = new MiVector(pixel.getX() * -1, pixel.getY() * -1, 
                                                            pixel.getZ() * -1);
        V.normalizar();
        // Hallar R.V
        double RV = R.productoPunto(V);
        if(RV < 0.0){
            RV = 0.0;
        }
        // Hallar N.L
        double NL = normal.productoPunto(l);
        if(NL < 0.0){
            NL = 0.0;
        }
        double Ka = arrayMateriales.get(0).ka;
        double Kd = arrayMateriales.get(0).kd;
        double Ks = arrayMateriales.get(0).ks;
        double n = arrayMateriales.get(0).n;
        
        double rObjeto = arrayEntidades.get(0).color.r;
        double gObjeto = arrayEntidades.get(0).color.g;
        double bObjeto = arrayEntidades.get(0).color.b;
       
        double r = (luzAmbiental.r * Ka * rObjeto) + (colorLuzPuntual.r *((Kd * rObjeto * NL) + (Ks * Math.pow(RV, n))));
        double g = (luzAmbiental.g * Ka * gObjeto) + (colorLuzPuntual.g *((Kd * gObjeto * NL) + (Ks * Math.pow(RV, n))));
        double b = (luzAmbiental.b * Ka * bObjeto) + (colorLuzPuntual.b *((Kd * bObjeto * NL) + (Ks * Math.pow(RV, n))));
        
        r = Math.min(r, 1.0);
        g = Math.min(g, 1.0);
        b = Math.min(b, 1.0); 
        
        return new Color(r, g, b);
    }
    public Color intersectarRayo(Rayo rayo) {
        double t = Double.MAX_VALUE;
        Color color = colorBackground;
        for(Entidad entidad: arrayEntidades) {
            if(entidad instanceof Esfera) {
                Solucion solucion = rayo.interseccionEsfera((Esfera)entidad);
                //if (DEBUG) System.out.println(solucion);
                if(solucion.getNumRespuestas() == Solucion.NO_HAY_SOLUCION) {

                } else if (solucion.getNumRespuestas() == Solucion.UNA_SOLUCION) {
                    if(solucion.getT0() < t) {
                        t = solucion.getT0();
                        Punto interseccion = rayo.evaluar(t);
                        color = calcularColorIluminacion(interseccion, entidad);
                    }
                } else if (solucion.getNumRespuestas() == Solucion.DOS_SOLUCIONES) {
                    if(solucion.getT0() < t) {
                        t = solucion.getT0();
                        Punto interseccion = rayo.evaluar(t);
                        color = calcularColorIluminacion(interseccion, entidad);
                    }
                }
            } else if (entidad instanceof Plano) {
                Solucion solucion = rayo.interseccionPlano((Plano)entidad);
            }
        }
        return color;
    }
    
    
}
