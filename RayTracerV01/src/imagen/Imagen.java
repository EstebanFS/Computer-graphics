/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package imagen;

import java.io.*;

/**
 *
 * @author htrefftz
 */
public class Imagen {
    int ancho;
    int alto;
    Pixel [][] imagen;

    public Imagen(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        imagen = new Pixel[alto][ancho];
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                imagen[i][j] = new Pixel();
            }
        }
    }

    public void setPixel(int x, int y, int r, int g, int b) {
        imagen[x][y] = new Pixel(r, g, b);
    }

    public void setPixel(int x, int y, Pixel pixel) {
        imagen[x][y] = pixel;
    }

    public void crearImagenEnsayo() {
        for(int i = 0; i < 90; i++) {
            for (int j = 0; j < 30; j++) {
                imagen[i][j] = new Pixel(255, 0, 0);
            }
            for (int j = 30; j < 60; j++) {
                imagen[i][j] = new Pixel(0, 255, 0);
            }
            for (int j = 60; j < 90; j++) {
                imagen[i][j] = new Pixel(0, 0, 255);
            }
        }
    }

    public void escribirImagenEnsayo(String nombreArchivo) {
        File file = new File(nombreArchivo);
        try {
            PrintStream output = new PrintStream(new FileOutputStream(file));
            output.println("P3");
            output.println("#Examen Eafit");
            output.println("3 2");
            output.println("255");
            output.println("255   0   0     0 255   0     0   0 255");
            output.println("255 255   0   255 255 255     0   0   0");
            output.close();
        } catch(Exception e) {
            System.out.println("Problema al escribir " + nombreArchivo);
        }
    }

    public void escribirImagen(String nombreArchivo) {
        File file = new File(nombreArchivo);
        try {
            PrintStream output = new PrintStream(new FileOutputStream(file));
            output.println("P3");
            output.println("#Examen Eafit");
            output.println(ancho + " " + alto);
            output.println("255");
            for(int i = 0; i < alto; i++) {
                for (int j = 0; j < ancho; j++) {
                    output.print(imagen[i][j].r + " " +
                            imagen[i][j].g + " " +
                            imagen[i][j].b + " ");
                }
                output.println();
            }
            output.close();
        } catch(Exception e) {
            System.out.println("Problema al escribir " + nombreArchivo);
        }
    }

    public static void main(String [] args) {
        Imagen imagen = new Imagen(50, 50);
        imagen.escribirImagen("ensayo.ppm");
        imagen = new Imagen(90,90);
        imagen.crearImagenEnsayo();
        imagen.escribirImagen("ensayo.ppm");
    }
}
