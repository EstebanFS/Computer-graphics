package bezierSurface;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import mathPackage.*;

/**
 *
 * @author Jesus
 */
public class BezierSurface extends JPanel {
    int w,h,xIni,yIni,maxInt,n,m;
    float deltaU = (float)0.1;
    float deltaV = (float)0.1;
    Graphics2D g2d;
    Vector3D[][] controlPoints;
    Vector3D[][] drawPoints;
    String[] auxControlPoint;
    int[][] lines;
    Matrix3D[] transforms;
    BigInteger[] factorialVector;
    // Plano de proyección
    double plain = -50;
    

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
      
      // base para linea : g2d.drawLine(x+xIni, -y+yIni, x+xIni, -y+yIni)
      makeCartesianPlane();
        try {
            g2d.setColor(Color.red);
            makeDraw();     
        } catch (IOException ex) {
            Logger.getLogger(BezierSurface.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
    public void transformation (Matrix3D matrixT){
     // Multiplicacion de matrixT por cada punto. 
        for (int u = 0; u < (int) (1 / deltaU); u++) {
            for (int v = 0; v < (int) (1 / deltaV); v++) {
                drawPoints[u][v].multiMatrixVector(matrixT);
            }
        }
    
    }
    
    public void makeDraw() throws IOException {
        fac(); // realizar la inicialización del factorial
        BufferedReader br = new BufferedReader(new FileReader("entradaBezier.txt"));
        Integer numberOfRow = Integer.parseInt(br.readLine());
        m = numberOfRow;
        Integer numberOfColumn = Integer.parseInt(br.readLine());
        n = numberOfColumn;
        controlPoints = new Vector3D[numberOfRow][numberOfColumn];
        for (int i = 0; i < numberOfRow; i++) {
            for (int j = 0; j < numberOfColumn; j++) {
                String controlPoint = br.readLine();
                auxControlPoint = controlPoint.split(",");
                double xControl = Double.parseDouble(auxControlPoint[0]);
                double yControl = Double.parseDouble(auxControlPoint[1]);
                double zControl = Double.parseDouble(auxControlPoint[2]);
                controlPoints[i][j] = new Vector3D(xControl, yControl, zControl);
            }
        }
        beziel();

        // Instanciar la matriz de proyección
        Matrix3D proyection = new Matrix3D(plain);
        transformation(proyection);
        for (int u = 0; u < (int) (1 / deltaU); u++) {
            for (int v = 0; v < (int) (1 / deltaV); v++) {
                drawPoints[u][v].normalize();
            }
        }
        draw();
    }
    
    public void fac() {
        maxInt = 20;
        factorialVector = new BigInteger[maxInt];
        factorialVector[0] = new BigInteger("1");
        BigInteger aux;
        String num;
        for (int i = 1; i < maxInt; i++) {
            num = Integer.toString(i);
            aux = new BigInteger(num);
            factorialVector[i] = factorialVector[i - 1].multiply(aux);
        }
    }
    
    public void beziel() {
        drawPoints = new Vector3D[(int) (1 / deltaU)][(int) (1 / deltaV)];
        for (int u = 0; u < (int) (1 / deltaU); u++) {
            for (int v = 0; v < (int) (1 / deltaV); v++) {
                Vector3D vectorAuxm = new Vector3D(0, 0, 0);
                for (int j = 0; j < m; j++) {
                    Vector3D vectorAuxn = new Vector3D(0, 0, 0);
                    for (int k = 0; k < n; k++) {
                        float varBezie = bez(j, m, (float) (v / (1 / deltaV))) * bez(k, n, (float) (u / (1 / deltaU)));
                        Vector3D control = controlPoints[j][k].MultiplicationScalar(varBezie);
                        vectorAuxn = vectorAuxn.sumVector(control);
                    }
                    vectorAuxm = vectorAuxm.sumVector(vectorAuxn);
                }
                drawPoints[u][v] = vectorAuxm;
            }
        }
    }
    
    public int factorial(int n){
        int result;
        result=factorialVector[n].intValue();
        return result;
    }
    
    public float cons(int n, int k){
        float result;
        result = (factorial(n))/(factorial(k)*factorial(n-k));
        return result;
    }
    
    public float bez(int k, int n, float u){
        float result;
        float aux = (float) (cons(n,k)*Math.pow(u, k));
        result = (float)(aux * Math.pow((1-u),(n-k)));
        return result;
    }

    public void draw() {
        for (int u = 0; u < (int) (1 / deltaU) - 1; u++) {
            for (int v = 0; v < (int) (1 / deltaV) - 1; v++) {
                Vector3D vector1 = drawPoints[u][v];
                Vector3D vector2 = drawPoints[u + 1][v];
                Vector3D vector3 = drawPoints[u][v + 1];
                Vector3D vector4 = drawPoints[u + 1][v + 1];
                g2d.setColor(Color.blue);
                g2d.drawLine(mapX(vector1.getPoint()[0]), mapY(vector1.getPoint()[1]),
                        mapX(vector2.getPoint()[0]), mapY(vector2.getPoint()[1]));
                g2d.drawLine(mapX(vector1.getPoint()[0]), mapY(vector1.getPoint()[1]),
                        mapX(vector3.getPoint()[0]), mapY(vector3.getPoint()[1]));
                g2d.drawLine(mapX(vector4.getPoint()[0]), mapY(vector4.getPoint()[1]),
                        mapX(vector2.getPoint()[0]), mapY(vector2.getPoint()[1]));
                g2d.drawLine(mapX(vector4.getPoint()[0]), mapY(vector4.getPoint()[1]),
                        mapX(vector3.getPoint()[0]), mapY(vector3.getPoint()[1]));
                g2d.setColor(Color.red);
                g2d.drawLine(mapX(vector2.getPoint()[0]), mapY(vector2.getPoint()[1]),
                        mapX(vector3.getPoint()[0]), mapY(vector3.getPoint()[1]));
            }
        }
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
        final JFrame frame = new JFrame("WireFrame");
        // Al cerrar el frame, termina la ejecucion de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar un JPanel que se llama Points (esta clase)
        final BezierSurface wareframe = new BezierSurface();
        frame.add(wareframe);
        // Asignarle tamanio
        frame.setSize(800, 600);
        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar el frame
        frame.setVisible(true);
    }
}