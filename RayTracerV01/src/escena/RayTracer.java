package escena;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author htrefftz
 */
import imagen.Pixel;
import imagen.Imagen;

public class RayTracer {

    Escena escena;
    LectorEscena lectorEscena;
    int ancho, alto;
    boolean DEBUG = true;

    public RayTracer(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        escena = new Escena();
        lectorEscena = new LectorEscena(this, "escena.txt");

    }

    public void GenerarImagen(){
        Rayo rayo;
        double deltax = 2d / ancho;
        double deltay = 2d / alto;
        Imagen imagen = new Imagen(ancho, alto);
        for (int i = 0; i<alto; i++){
            for (int j=0; j<ancho; j++){
                Punto punto2 = new Punto(-1 + i * deltax, 1 - j * deltay, -2d);
                Punto punto1 = new Punto(0d, 0d, 0d);
                MiVector vector = new MiVector(punto1, punto2);
                rayo = new Rayo(punto1, vector);
                //if(DEBUG) System.out.println(rayo);
                Color color = escena.intersectarRayo(rayo);
                Pixel pixel = new Pixel(color);
                imagen.setPixel(i, j, pixel);
            }
        }
        imagen.escribirImagen("salida.ppm");
    }

    public static void main(String [] args) {
        RayTracer rayTracer = new RayTracer(400, 400);
        rayTracer.GenerarImagen();
    }

}
