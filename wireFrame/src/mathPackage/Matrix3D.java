/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathPackage;

/**
 *
 * @author Esteban
 */
public class Matrix3D {
    
    private double matrix[][];
    private double identidad[][]={{1,0,0,0},
                                 {0,1,0,0},
                                 {0,0,1,0},
                                 {0,0,1,0}}; 
        
    public Matrix3D(){
        matrix = new double[4][4];
        matrix = identidad;
    }
    
    public Matrix3D(int x, int y, int z){//translation
        this.matrix = this.identidad;
        this.matrix[0][3] = x;
        this.matrix[1][3] = y;
        this.matrix[2][3] = z;
    }
    
    public Matrix3D(String axis,double angle){//rotation
        this.matrix = this.identidad;        
        this.matrix[0][0] = Math.cos(angle); 
        this.matrix[0][1] = -Math.sin(angle);
        this.matrix[1][0] = Math.sin(angle); 
        this.matrix[1][1] = Math.cos(angle);
    }
    
    public Matrix3D(double x, double y, double z){//scaling
        this.matrix = this.identidad;       
        this.matrix[0][0] = x;  
        this.matrix[1][1] = y;
        this.matrix[2][2] = z; 
    }
    
    public Matrix3D(double d){//proyection
        this.matrix = this.identidad;       
        this.matrix[3][2] = 1/d;
        this.matrix[3][3] = 0;
    }

    public double[][] getMatrix() {
        return matrix;
    }
    
    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }
}
