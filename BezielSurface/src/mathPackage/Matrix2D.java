package mathPackage;

/**
 *
 * @author Esteban, Mateo, Jesús. 
 */
public class Matrix2D {
    private double matrix[][];
    private double identidad[][]={{1,0,0},
                                 {0,1,0},
                                 {0,0,1}}; 
    public Matrix2D(){
        this.matrix = this.identidad;
    }
    public Matrix2D(double[][] matrix){
        this.matrix = this.identidad;
        this.matrix = matrix;      
    }
    
    public Matrix2D(int x, int y){//translation
        this.matrix = this.identidad;
        this.matrix[0][2] = x;
        this.matrix[1][2] = y;
    }
    
    public Matrix2D(double angle){//rotation
        this.matrix = this.identidad;        
        this.matrix[0][0] = Math.cos(angle); 
        this.matrix[0][1] = -Math.sin(angle);
        this.matrix[1][0] = Math.sin(angle); 
        this.matrix[1][1] = Math.cos(angle);
    }
    
    public Matrix2D(double x, double y){//scaling
        this.matrix = this.identidad;       
        this.matrix[0][0] = x;  
        this.matrix[1][1] = y;      
    }
 
    public double[][] getMatrix() {
        return matrix;
    }
 
    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }
    
    
}