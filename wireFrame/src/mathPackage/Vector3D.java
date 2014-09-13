/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathPackage;

/**
 *
 * @author Esteban
 */
public class Vector3D {
    private double point[];//0:x, 1:y, 2:z, 3:w
    
    public Vector3D(int x, int y, int z, int w){
        point = new double[4];
        point[0] = x;
        point[1] = y;
        point[2] = z;
        point[3] = w;
    }   
    
     public void multiMatrixVector(Matrix3D transformation){
        double[] aux = point;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                aux[i] = point[j]*transformation.getMatrix()[i][j];
            }
        }
        point = aux;
    }
    
     public double[] normalize(){
        double w= point[3];
        double result[] = new double[2];
        result[0] = point[0]/w;
        result[1] = point[1]/w;
        result[2] = point[2]/w;
        return result; 
    }
    
    
}
