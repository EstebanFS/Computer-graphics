package painter;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import mathPackage.*;

/**
 *
 * @author Jesus Estiven Lopera .
 * @author Esteban Foronda Sierra.
 * @author Mateo Restrepo Restrepo.
 */
public class Main extends JPanel {
    int w,h,xIni,yIni,maxInt,n,m;
    Graphics2D g2d;
    Triangle [] triangles;
    
    public Main(){}
    
  /*
   * En esta funcion se dibuja.
   * La funcion es llamada por Java2D.
   * Se recibe una variable Graphics, que contiene la informacion del contexto
   * grafico.
   * Es necesario hacerle un cast a Graphics2D para trabajar en Java2D.
   */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        // size es el tamanio de la ventana.
        Dimension size = getSize();
        // Insets son los bordes y los tatulos de la ventana.
        Insets insets = getInsets();
        w =  size.width - insets.left - insets.right;
        h =  size.height - insets.top - insets.bottom;
        //las constantes de las coordenadas X y Y
        yIni = h/2;
        xIni = w/2;
        makeCartesianPlane();
          try {
              readInput();
              // funcion painter.
              makeDepthSortingMethod(this.triangles);
          } catch (IOException ex) {
              Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
    public void readInput() throws IOException { 
        BufferedReader br = new BufferedReader(new FileReader("entradaPainter.txt"));
        Integer numberOfTriangles = Integer.parseInt(br.readLine());
        this.triangles = new Triangle[numberOfTriangles];
        for (int i = 0; i < numberOfTriangles; i++) {
            Vector3D[] pointsTriangle = new Vector3D[3];
            Color triangleColor=Color.blue;
            String tColorInput = br.readLine();
            if(tColorInput.equalsIgnoreCase("red")){
                triangleColor= Color.red;
            }else if(tColorInput.equalsIgnoreCase("blue")){
                triangleColor= Color.blue;
            }else{
                triangleColor= Color.black;
            }
            for(int j = 0; j < 3; j++){
		String point = br.readLine();
                String[] auxPoints = point.split(" ");
                double xPoint = Double.parseDouble(auxPoints[0]);
                double yPoint = Double.parseDouble(auxPoints[1]);
                double zPoint = Double.parseDouble(auxPoints[2]);
                Vector3D pointTriangle = new Vector3D(xPoint, yPoint, zPoint);
                pointsTriangle[j] = pointTriangle;
            }
                triangles[i] = new Triangle(pointsTriangle[0],pointsTriangle[1], pointsTriangle[2],triangleColor);    
        }  
    }
    
    public void makeDepthSortingMethod(Triangle[] triangles){
        Arrays.sort(triangles);
        if(findConflicts()){
            for(int i=triangles.length-1; i>=0; i--)draw(i);
        }else{
            for(int i=0; i<triangles.length; i++)draw(i);
        }
    }
    
    public boolean findConflicts(){
        if(Painter.firstConflict(triangles)){
            if(Painter.secondConflict(triangles)){
                if(Painter.thirdConflict(triangles)){
                    if(Painter.FourthConflict(triangles)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public void draw(int numberTriangle){
        int xPoints[] = new int[3];
        int yPoints[] = new int[3];
        for(int i = 0; i < 3; i++){
           double xPoint = triangles[numberTriangle].getPoints()[i].getPoint()[0];
           double yPoint = triangles[numberTriangle].getPoints()[i].getPoint()[1];
           xPoints[i] = mapX(xPoint); 
           yPoints[i] = mapY(yPoint);
        }
        g2d.setColor(triangles[numberTriangle].getColor());
        g2d.fillPolygon(xPoints,yPoints, 3);
       
    }
    public int mapY(double yPoint) {
        return (int) yPoint * (-1) + yIni;
    }

    public int mapX(double xPoint) {
        return (int) xPoint + xIni;
    }

    public void makeCartesianPlane() {
        g2d.setColor(Color.black);
        // dibujando plano carteciano
        g2d.drawLine(-w / 2 + xIni, yIni, w / 2 + xIni, yIni); //horizontal
        g2d.drawLine(xIni, -h / 2 + yIni, xIni, h / 2 + yIni); //vertical
    }

  
    public static void main(String[] args) {
        // Crear un nuevo Frame
        final JFrame frame = new JFrame("Painter");
        // Al cerrar el frame, termina la ejecucion de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar un JPanel que se llama Main (esta clase)
        final Main painter = new Main();
        frame.add(painter);
        // Asignarle tamanio
        frame.setSize(1200, 700);
        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar el frame
        frame.setVisible(true);
    }
}