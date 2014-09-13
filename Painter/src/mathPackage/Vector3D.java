package mathPackage;

/**
 *
 * @author Esteban, Mateo, Jesús. 
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
    
    public Vector3D(double [] array){
        this.point = new double[4];
        this.point[0] = array[0];
        this.point[1] = array[1];
        this.point[2] = array[2];
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
    public Vector3D multiplicationVector(Vector3D vector){      
        double[] aux = new double[4];
        aux [0]= (this.getPoint()[1]*vector.getPoint()[2])-(vector.getPoint()[1]*this.getPoint()[2]);                   //i (el x del result)
        aux [1]= -((this.getPoint()[0]*vector.getPoint()[2])-(vector.getPoint()[0]*this.getPoint()[2]));                    //j (el y del result)
        aux [2]= (this.getPoint()[0]*vector.getPoint()[1])-(vector.getPoint()[0]*this.getPoint()[1]);                 //k (el z del result)
        Vector3D result = new Vector3D(aux);
        return result;
    }
    
    public double scalarProductVector(Vector3D vector){
        double productX = this.getPoint()[0]*vector.getPoint()[0];
        double productY = this.getPoint()[1]*vector.getPoint()[1];
        double productZ = this.getPoint()[2]*vector.getPoint()[2];
        double result = productX+productY+productZ;
        return result;
    }
    
    public Vector3D MultiplicationScalar(float scalar){
        double[] aux = new double[4];
        aux [0] = this.getPoint()[0]*scalar;
        aux [1] = this.getPoint()[1]*scalar;
        aux [2] = this.getPoint()[2]*scalar;
        Vector3D result = new Vector3D(aux);
        return result;
    }
    
    public Vector3D sumVector(Vector3D vector){
        double[] aux = new double[4];
        aux[0] = this.getPoint()[0] + vector.getPoint()[0];
        aux[1] = this.getPoint()[1] + vector.getPoint()[1];
        aux[2] = this.getPoint()[2] + vector.getPoint()[2];
        Vector3D result = new Vector3D(aux);
        return result; 
    }
    
     public void normalize(){
        double w = this.point[3];
        this.point[0] = this.point[0]/w;
        this.point[1] = this.point[1]/w;
        this.point[2] = this.point[2]/w;
        this.point[3] = this.point[3]/w;
      }
     
     public double magnitude(){
         double sum = Math.pow(point[0], 2)+Math.pow(point[1], 2)+Math.pow(point[2], 2);
         double magnitude = Math.sqrt(sum);
         return magnitude;
     }
     
     public Vector3D divideVector(double divisor){      
        double[] aux = new double[4];
        for(int i=0;i<aux.length;i++){
                aux[i] = this.point[i]/divisor;
        }
        Vector3D result = new Vector3D(aux);
        return result;
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
