/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wireframe;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JFrame;
 
import java.util.logging.Level;
import java.util.logging.Logger;
import mathPackage.*;

/**
 *
 * @author Esteban
 */
public class WireFrame extends JPanel {
    int w,h,xIni,yIni,x1,y1,x2,y2,xMin,xMax,yMin,yMax;
    Graphics2D g2d;
    Vector2D[] points;
    String[] auxLines;
    int[][] lines;
    Matrix2D[] transforms;
    

  /*
   * En esta funciÃ³n se dibuja.
   * La funciÃ³n es llamada por Java2D.
   * Se recibe una variable Graphics, que contiene la informaciÃ³n del contexto
   * grÃ¡fico.
   * Es necesario hacerle un cast a Graphics2D para trabajar en Java2D.
   */
    @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g2d = (Graphics2D) g;
      g2d.setColor(Color.BLUE);
      // size es el tamaÃ±o de la ventana.
      Dimension size = getSize();
      // Insets son los bordes y los tÃ­tulos de la ventana.
      Insets insets = getInsets();
      w =  size.width - insets.left - insets.right;
      h =  size.height - insets.top - insets.bottom;
      //las constantes de las coordenadas X y Y
      yIni = h/2;
      xIni = w/2;      
      
      // base para linea : g2d.drawLine(x+xIni, -y+yIni, x+xIni, -y+yIni);
      // Generador de nÃºmeros Random
      // Se va a utilizar nextInt, que devuelve un entero.
      // Generar 100 lineas azules
      makeCartesianPlane();
        try {
            g2d.setColor(Color.red);
            makeDraw();
           // draw();
            
        } catch (IOException ex) {
            Logger.getLogger(WireFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    public Matrix2D makeTransformation(String transformation, String param1,String param2){
        Matrix2D transform;
        switch (transformation) {
            case "rotate":
                // Leer angulo de rotacion alfa double
                double alfa = Math.toRadians(Double.parseDouble(param1));
                // Instanciar la matriz de rotacion
                transform = new Rotation2D(alfa);
                break;
            case "translate":
                {
                    // Leer delta x int delta y int.
                    int x = Integer.parseInt(param1);
                    int y = Integer.parseInt(param2);
                    // Instanciar la matriz de translation
                    transform = new Translation2D(x, y);
                    break;
                }
            case "scale":
                {
                    // Leer double x, double y
                    double x = Double.parseDouble(param1);
                    double y = Double.parseDouble(param2);
                    // Instanciar la matriz de escalamiento.
                    transform = new Scaling2D(x, y);
                    break;
                }
            default:
                transform = new Matrix2D();
                break;
        }
        transformation(transform);
        draw();
        return transform;
    
    }
    
    
    public void transformation (Matrix2D matrixT){
     // Multiplicacion de matrixT por cada punto. 
        for(int i = 0; i < points.length; i++){
            points[i].multiMatrixVector(matrixT);
        }
    
    }
    public void makeDraw() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("entrada.txt"));
        Integer numberOfPoints = Integer.parseInt(br.readLine());
        points = new Vector2D[numberOfPoints];
        for(int i=0; i<numberOfPoints; i++){
           points[i] = new Vector2D(0,0,1);
           String point = br.readLine();
           String[] auxPoints = point.split(",");
           int x = Integer.parseInt(auxPoints[0]);
           points[i].setX(x);
           int y = Integer.parseInt(auxPoints[1]);
           points[i].setY(y); 
        }
        Integer numberOfLines = Integer.parseInt(br.readLine());
        lines = new int [2][numberOfLines];
        for(int i=0;i<numberOfLines;i++){
           String line = br.readLine();
           auxLines = line.split(",");
           int number1 = Integer.parseInt(auxLines[0]);
           int number2 = Integer.parseInt(auxLines[1]);
           lines[0][i] = number1;
           lines[1][i] = number2;
           //double[] firstPoint = points[number1].getPoint();
           //double[] secondPoint = points[number2].getPoint();
           
            //g2d.drawLine(mapX(firstPoint[0]), mapY(firstPoint[1]), mapX(secondPoint[0]), mapY(secondPoint[1]));
        }
        Integer numberOfTransforms = Integer.parseInt(br.readLine());
        transforms = new Matrix2D[numberOfTransforms];
        for(int i=0; i<numberOfTransforms; i++){
           String param1;
           String param2="";
           String trans = br.readLine();
           String[] auxTrans = trans.split(",");
           String transformName = auxTrans[0];
            param1= auxTrans[1];
           if(auxTrans.length>2){
              param2= auxTrans[2];
           }
           transforms[i]= makeTransformation(transformName, param1, param2);
        }
    }
    
    public void draw(){
        for(int i=0;i<lines[0].length;i++){
           int number1 = lines[0][i];
           int number2 = lines[1][i];
           double[] firstPoint = points[number1].getPoint();
           double[] secondPoint = points[number2].getPoint();
           g2d.setColor(Color.blue);
           g2d.drawLine(mapX(firstPoint[0]), mapY(firstPoint[1]), mapX(secondPoint[0]), mapY(secondPoint[1]));
        }
    }
    
    public int mapY(double yPoint){
        return (int)yPoint*(-1) + yIni;
    }
    
    public int mapX(double xPoint){
        return (int)xPoint + xIni;
    }
    
    public void makeCartesianPlane(){
         g2d.setColor(Color.black);
        // dibujando plano carteciano
        g2d.drawLine(-w / 2 + xIni, yIni, w / 2 + xIni, yIni); //horizontal
        g2d.drawLine(xIni, -h / 2 + yIni, xIni, h / 2 + yIni); //vertical
    }

    public static void main(String[] args) {
        // Crear un nuevo Frame
        final JFrame frame = new JFrame("WireFrame");
        // Al cerrar el frame, termina la ejecuciÃ³n de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar un JPanel que se llama Points (esta clase)
        final WireFrame wareframe = new WireFrame();
        frame.add(wareframe);
        // Asignarle tamaÃ±o
        frame.setSize(800, 600);
        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar el frame
        frame.setVisible(true);

    }
}