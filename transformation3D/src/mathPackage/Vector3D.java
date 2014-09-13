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
    
    public Vector3D(double x, double y, double z){
        this.point = new double[4];
        this.point[0] = x;
        this.point[1] = y;
        this.point[2] = z;
        this.point[3] = 1;
    }   
    
     public void multiMatrixVector(Matrix3D transformation){      
        double[] aux = new double[4];
        for(int i=0;i<transformation.getMatrix().length;i++){
            for(int j=0;j<transformation.getMatrix()[0].length;j++){
                aux[i] += transformation.getMatrix()[i][j]*this.point[j];
            }
        }
        this.point = aux;
    }
    
     public void normalize(){
        double w = this.point[3];
        this.point[0] = this.point[0]/w;
        this.point[1] = this.point[1]/w;
        this.point[2] = this.point[2]/w;
        this.point[3] = this.point[3]/w;
      }
     
    public double[] getPoint() {
        return point;
    }
    
    public void setX(double x) {
        point[0] = x;
    }
    
    public void setY(double y) {
       point[1] = y;
    }
    
    public void setZ(double z) {
       point[2] = z;
    }
    
    
}
