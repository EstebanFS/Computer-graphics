package mathPackage;


/**
 *
 * @author Esteban
 */
public class Vector2D {
    private double point[];//0:x, 1:y, 2:w
    
    public Vector2D(double x, double y, double w){
        point = new double[3];
        point[0] = x;
        point[1] = y;
        point[2] = w;
    }
   
    public void multiMatrixVector(Matrix2D transformation){
        double[] aux = new double[3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                aux[i] += transformation.getMatrix()[i][j]*point[j];
            }
        }
        point = aux;
    }
    
    public double[] normalize(){
        double magnitude =point[3];
        double result[] = new double[2];
        result[0] = point[0]/magnitude;
        result[1] = point[1]/magnitude;
        return result; 
    }
    
    public double magnitude(){
        double result = Math.sqrt((Math.pow(point[0],2) + Math.pow(point[1],2)));
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
    
}
