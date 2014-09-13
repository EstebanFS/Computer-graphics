/*
 * Ejemplo bÃ¡sico en Java2D
 * 
 * Basado en el Tutorial de Java2D de ZetTutorial: http://zetcode.com/tutorials/java2dtutorial/
 * 
 * Java tiene un tutorial oficial para Java2D: http://docs.oracle.com/javase/tutorial/2d/index.html
 */
package clipping;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.util.Random;
import javax.naming.BinaryRefAddr;

public class Clipping extends JPanel {

    int w, h, xIni, yIni, x1, y1, x2, y2, xMin, xMax, yMin, yMax;
    int[] code1, code2;
    Graphics2D g2d;
    float u1, u2, x1Lian, x2Lian, y1Lian, y2Lian;

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

        w = size.width - insets.left - insets.right;
        h = size.height - insets.top - insets.bottom;

        //las constantes de las coordenadas X y Y
        yIni = h / 2;
        xIni = w / 2;
        // base para linea : g2d.drawLine(x+xIni, -y+yIni, x+xIni, -y+yIni);

        // Generador de nÃºmeros Random
        // Se va a utilizar nextInt, que devuelve un entero.
        Random r = new Random();

        // Generar 100 lineas azules
        xMax = w / 4;
        xMin = -w / 4;
        yMax = h / 4;
        yMin = -h / 4;
        makeClippingArea();
        makeCartesianPlane();
        float promedioCohen = 0, promedioLiang = 0;
        float totalTiempoCohen = 0, totalTiempoLiang = 0;
        int victoriaCohen = 0, victoriaLiang = 0;
        for (int j = 0; j < 100; j++) {
            totalTiempoCohen = 0;
            totalTiempoLiang = 0;
            int repeticiones = 0;
            while (repeticiones < 10) {
                long tiempoInicioCohen = 0;
                long tiempoInicioLiang = 0;
                long parcialTiempoCohen = 0;
                long parcialTiempoLiang = 0;
                g2d.setColor(Color.BLUE);
                for (int i = 0; i < 1000; i++) {
                    x1 = r.nextInt() % w / 2;
                    y1 = r.nextInt() % h / 2;
                    x2 = r.nextInt() % w / 2;
                    y2 = r.nextInt() % h / 2;
                    // AsÃ­ se pinta un punto
                    //g2d.drawLine(x1 + xIni, -y1 + yIni, x2 + xIni, -y2 + yIni);
                    x1Lian = x1;
                    x2Lian = x2;
                    y1Lian = y1;
                    y2Lian = y2;
                    //CohenSutherland
                    tiempoInicioCohen = System.currentTimeMillis();
                    clippinCohenSutherland();
                    parcialTiempoCohen += System.currentTimeMillis() - tiempoInicioCohen;
                    //g2d.drawLine(x1, y1, x2, y2);
                }
                g2d.setColor(Color.CYAN);
                for (int i = 0; i < 1000; i++) {
                    x1 = r.nextInt() % w / 2;
                    y1 = r.nextInt() % h / 2;
                    x2 = r.nextInt() % w / 2;
                    y2 = r.nextInt() % h / 2;
                    // AsÃ­ se pinta un punto
                    //g2d.drawLine(x1 + xIni, -y1 + yIni, x2 + xIni, -y2 + yIni);
                    x1Lian = x1;
                    x2Lian = x2;
                    y1Lian = y1;
                    y2Lian = y2;
                    //LianBarky
                    tiempoInicioLiang = System.currentTimeMillis();
                    clippingLiangBarky();
                    parcialTiempoLiang += System.currentTimeMillis() - tiempoInicioLiang;
                    //g2d.drawLine(x1, y1, x2, y2);
                }
                totalTiempoCohen += parcialTiempoCohen;
                totalTiempoLiang += parcialTiempoLiang;
                repeticiones++;
            }
            promedioCohen = totalTiempoCohen / repeticiones;
            promedioLiang = totalTiempoLiang / repeticiones;
            if(totalTiempoCohen < totalTiempoLiang){
                victoriaCohen++;
            }else{
                victoriaLiang++;
            }
        }
        System.out.println("El tiempo total de la CohenSutherland es :" + totalTiempoCohen + " miliseg");
        System.out.println("El tiempo total de la LiangBarky es :" + totalTiempoLiang + " miliseg");
        System.out.println("El tiempo Promedio (cada 1000 lineas) de la CohenSutherland es :" + promedioCohen + " miliseg");
        System.out.println("El tiempo Promedio (cada 1000 lineas) de la LiangBarky es :" + promedioLiang + " miliseg");
        if (totalTiempoCohen < totalTiempoLiang) {
            System.out.println("Por lo que se puede decir que CohenSutherland es más eficiente en este caso.");
        } else {
            System.out.println("Por lo que se puede decir que LiangBarky es más eficiente en este caso.");
        }
        System.out.println();
        if (victoriaCohen > victoriaLiang) {
            System.out.println("Por lo que se puede decir que CohenSutherland es más eficiente en "+ (victoriaCohen-victoriaLiang) + " casos más que LiangBarky.");
        } else {
            System.out.println("Por lo que se puede decir que LiangBarky es más eficiente en "+ (victoriaLiang-victoriaCohen)+ " casos más que CohenSutherland.");
        }
        System.out.println();
    }

    public int clippinCohenSutherland() {
        float m;
        boolean done, plotLine;
        done = plotLine = false;
        while (!done) {
            code1 = encode(x1, y1);
            code2 = encode(x2, y2);
            if ((accept(code1, code2)) == 1) {
                done = true;
                plotLine = true;
            } else {
                if ((reject(code1, code2)) == 1) {
                    done = true;
                } else {
                    if (inside(code1) == 1) {
                        swapPoints();
                        swapCodes();
                    }
                    if (x1 == x2) {
                        return 0;
                    }
                    m = (float) (y2 - y1) / (float) (x2 - x1);
                    int[] left = {0, 0, 0, 1};
                    if (check(left) == 1) {
                        y1 += (xMin - x1) * m;
                        x1 = xMin;
                    } else {
                        int[] right = {0, 0, 1, 0};
                        if (check(right) == 1) {
                            y1 += (xMax - x1) * m;
                            x1 = xMax;
                        } else {
                            int[] bottom = {0, 1, 0, 0};
                            if ((check(bottom)) == 1) {
                                if (x2 != x1) {
                                    x1 += (yMax - y1) / m;
                                }
                                y1 = yMax;
                            } else {
                                int[] top = {1, 0, 0, 0};
                                if ((check(top)) == 1) {
                                    if (x2 != x1) {
                                        x1 += (yMin - y1) / m;
                                    }
                                    y1 = yMin;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (plotLine) {
            g2d.drawLine(x1 + xIni, -y1 + yIni, x2 + xIni, -y2 + yIni);
        }
        return 0;
    }

    public int check(int[] side) {
        for (int i = 0; i < 4; i++) {
            if ((code1[i] & side[i]) == 1) {
                return 1;
            }
        }
        return 0;
    }

    public void clippingLiangBarky() {
        u1 = (float) 0.0;
        u2 = (float) 1.0;
        float dx = x2Lian - x1Lian;
        float q1 = x1Lian - xMin;
        float q2 = xMax - x1Lian;
        float q3 = y1Lian - yMin;
        float q4 = yMax - y1Lian;
        float dy;
        if ((clipTest(-dx, q1)) == 1) {
            if ((clipTest(dx, q2)) == 1) {
                dy = y2 - y1;
                if((clipTest(-dy,q3))==1){
                    if((clipTest(dy,q4))==1){
                        if(u2 < 1.0){
                            x2Lian=(x1Lian+u2*dx);
                            y2Lian=(y1Lian+u2*dy);
                        }
                        if(u1 > 0.0){
                            x1Lian=(x1Lian+u1*dx);
                            y1Lian=(y1Lian+u1*dy);
                        }
                        g2d.drawLine(Math.round(x2Lian)+xIni, -Math.round(y2Lian)+yIni, Math.round(x1Lian)+xIni, -Math.round(y1Lian)+yIni);
                    }
                }
            }
            
        }
    }
    
    public int inside(int code[]){
        for(int i=0;i<4;i++){
            if(code[i]==1)return 0;
        }
        return 1;
    }
    
    public int reject(int code1[],int code2[]){
        for(int i=0;i<4;i++){
            if((code1[i]&code2[i])==1)return 1;
        }
        return 0;
    }
    
    public int accept(int code1[], int code2[]){
        for(int i=0;i<4;i++){
            if((code1[i]|code2[i])==1)return 0;
        }
        return 1;
    }
    
    public int[] encode(int x, int y){
        int code[] = new int[4];
        if(x < xMin){
            code[3]=1;
        }else{
            code[3]=0;
        }
        if(x > xMax){
            code[2]=1;
        }else{
            code[2]=0;
        }
        if(y < yMin){
            code[1]=1;
        }else{
            code[1]=0;
        }
        if(y > yMax){
            code[0]=1;
        }else{
            code[0]=0;
        }
        return code;
    }
    
    public void swapPoints(){
        int temp;
        temp = x1;
        x1 = x2;
        x2 = temp;
        temp = y1;
        y1 = y2;
        y2 = temp;
    }
    
    public void swapCodes(){
        int[] temp;
        temp = code1;
        code1 = code2;
        code2 = temp;
    }
    
    public int clipTest(float p, float q){
        float r;
        int returnValue = 1;
        if (p == 0) {
            if (q < 0.0) {
                returnValue = 0;
            }
        }
        if(p<0.0){
            //El punto esta entrando
            r = q/p;
            if(r > u2){
                returnValue = 0;
            }else{
                if(r > u1)u1=r;
            }
        } else{
            if(p > 0.0){
                //El punto esta saliendo
                r=q/p;
                if(r < u1){
                    returnValue = 0;
                }else{
                    if(r < u2)u2=r;
                }
            }
        }
        return returnValue;
    }
    
    public void makeClippingArea(){
        g2d.setColor(Color.red);
        // area de clipping
        g2d.drawLine(xMin + xIni, yMax + yIni, xMin + xIni, yMin + yIni); //left 1
        g2d.drawLine(xMax + xIni, yMax + yIni, xMax + xIni, yMin + yIni); //rigth 2
        g2d.drawLine(xMin + xIni, yMax + yIni, xMax + xIni, h / 4 + yIni); //bottom 3
        g2d.drawLine(xMin + xIni, yMin + yIni, xMax + xIni, yMin + yIni); //top 4
        g2d.drawLine(xMin + xIni - 1, yMax + yIni + 1, xMin + xIni - 1, yMin + yIni - 1); //left 1+pixel
        g2d.drawLine(xMax + xIni + 1, yMax + yIni + 1, xMax + xIni + 1, yMin + yIni - 1); //rigth 2+pixel
        g2d.drawLine(xMin + xIni, yMax + yIni + 1, xMax + xIni, yMax + yIni + 1); //bottom 3+pixel
        g2d.drawLine(xMin + xIni, yMin + yIni - 1, xMax + xIni, yMin + yIni - 1); //top 4+pixel
    }
    
    public void makeCartesianPlane(){
         g2d.setColor(Color.black);
        // dibujando plano carteciano
        g2d.drawLine(-w / 2 + xIni, yIni, w / 2 + xIni, yIni); //horizontal
        g2d.drawLine(xIni, -h / 2 + yIni, xIni, h / 2 + yIni); //vertical
    }

    public static void main(String[] args) {
        // Crear un nuevo Frame
        JFrame frame = new JFrame("clipping");
        // Al cerrar el frame, termina la ejecuciÃ³n de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar un JPanel que se llama Points (esta clase)
        frame.add(new Clipping());
        // Asignarle tamaÃ±o
        frame.setSize(512, 512);
        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar el frame
        frame.setVisible(true);
    }
}