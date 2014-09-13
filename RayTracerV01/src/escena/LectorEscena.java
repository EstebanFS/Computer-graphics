package escena;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author htrefftz
 */
import java.io.*;
import java.util.Scanner;

public class LectorEscena {
    RayTracer rayTracer;
    boolean DEBUG = true;

    public LectorEscena(RayTracer rayTracer, String nombreArchivo) {
        this.rayTracer = rayTracer;
        leerArchivo(nombreArchivo);
    }

    private void leerArchivo(String nombre) {
        try{
            FileInputStream fstream = new FileInputStream(nombre);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null)   {
                parsearLinea(strLine);
            }
            in.close();
        } catch (Exception e){
            System.err.println("Error al leer la escena: " + e.getMessage());
        }
    }

    private void parsearLinea(String s) {
        Scanner sc = new Scanner(s);
        String comando = sc.next();
        if (comando.equals("#")) {
            // nada, es un comentario
        } else if (comando.equals("esfera")) {
            // esfera xcentro ycentro zcentro radio indiceColor indiceMaterial
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            double z = sc.nextDouble();
            Punto centro = new Punto(x, y, z);
            double radio = sc.nextDouble();
            int indiceColor = sc.nextInt();
            int indiceMaterial = sc.nextInt();
            Esfera esfera = new Esfera(centro, radio);
            esfera.setColor(rayTracer.escena.getColor(indiceColor));
            esfera.setMaterial(rayTracer.escena.getMaterial(indiceMaterial));
            if(DEBUG) System.out.println(esfera);
            rayTracer.escena.addEntidad(esfera);
        } else if (comando.equals("plano")) {
            // plano a b c d
            double a = sc.nextDouble();
            double b = sc.nextDouble();
            double c = sc.nextDouble();
            double d = sc.nextDouble();
            Plano plano = new Plano(a, b, c, d);
            rayTracer.escena.addEntidad(plano);
        } else if (comando.equals("color")) {
            // color r g b
            // valores entre 0 y 1
            double r = sc.nextDouble();
            double g = sc.nextDouble();
            double b = sc.nextDouble();
            Color color = new Color(r, g, b);
            rayTracer.escena.addColor(color);
        } else if (comando.equals("material")) {
            // material ka kd ks
            // ka, kd, ks entre 0 y 1
            // n entero
            double ka = sc.nextDouble();
            double kd = sc.nextDouble();
            double ks = sc.nextDouble();
            int n = sc.nextInt();
            Material material = new Material(ka, kd, ks, n);
            rayTracer.escena.addMaterial(material);
        } else if (comando.equals("luzPuntual")) {
            // x, y, z 
            // indiceColor
            // indiceColor menos a Colores leidos
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            double z = sc.nextDouble();
            int indiceColor = sc.nextInt();
            Punto coordenaLuzPuntual = new Punto(x, y, z);
            rayTracer.escena.setCoordenaLuzPuntual(coordenaLuzPuntual);
            rayTracer.escena.setColorLuzPuntual(rayTracer.escena.
                                                        getColor(indiceColor));
        }else if (comando.equals("luzAmbiental")) {
            // indiceColor
            // indiceColor menos a Colores leidos
            int indiceColor = sc.nextInt();
            rayTracer.escena.setLuzAmbiental(rayTracer.escena.
                                                        getColor(indiceColor));
        }  
        
    }

}
