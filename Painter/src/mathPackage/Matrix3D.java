package mathPackage;

/**
 *
 * @author Esteban, Mateo, Jesús. 
 */
public class Matrix3D {
    
    private double matrix[][];
    private double identidad[][]={{1,0,0,0},
                                 {0,1,0,0},
                                 {0,0,1,0},
                                 {0,0,0,1}}; 
        
    public Matrix3D(){
        matrix = new double[4][4];
        matrix = identidad;
    }
    
    public Matrix3D(Vector3D posCamera,Vector3D lookingAt, Vector3D Up){
        matrix = new double[4][4];
        matrix = identidad;
        double posCameraX = posCamera.getPoint()[0];
        double posCameraY = posCamera.getPoint()[1];
        double posCameraZ = posCamera.getPoint()[3];
        double lookingAtX = lookingAt.getPoint()[0];
        double lookingAtY = lookingAt.getPoint()[1];
        double lookingAtZ = lookingAt.getPoint()[3];
        double NX = (posCameraX-lookingAtX);
        double NY = (posCameraY-lookingAtY);
        double NZ = (posCameraZ-lookingAtZ);
        Vector3D V = Up;
        Vector3D N = new Vector3D(NX,NY,NZ);
        Vector3D n = N.divideVector(N.magnitude());
        Vector3D multiplication = V.multiplicationVector(n);
        Vector3D u = (multiplication.divideVector(multiplication.magnitude()));
        Vector3D v = n.multiplicationVector(u);
        this.matrix[0][0]=u.getPoint()[0];
        this.matrix[0][1]=u.getPoint()[1];
        this.matrix[0][2]=u.getPoint()[2];
        this.matrix[0][3]=u.scalarProductVector(posCamera);//-u*p0
        this.matrix[1][0]=v.getPoint()[0];
        this.matrix[1][1]=v.getPoint()[1];
        this.matrix[1][2]=v.getPoint()[2];
        this.matrix[1][3]=-v.scalarProductVector(posCamera);//-v*p0
        this.matrix[2][0]=n.getPoint()[0];
        this.matrix[2][1]=n.getPoint()[1];
        this.matrix[2][2]=n.getPoint()[2];
        this.matrix[2][3]=n.scalarProductVector(posCamera);//-n*p0
    }
    
    public Matrix3D(int x, int y, int z){//translation
        this.matrix = this.identidad;
        this.matrix[0][3] = x;
        this.matrix[1][3] = y;
        this.matrix[2][3] = z;
    }
    
    public Matrix3D(String axis,double angle){//rotation
        this.matrix = this.identidad;
        if (axis.equals("z")) {
            this.matrix[0][0] = Math.cos(angle);
            this.matrix[0][1] = -Math.sin(angle);
            this.matrix[1][0] = Math.sin(angle);
            this.matrix[1][1] = Math.cos(angle);
        }
        if(axis.equals("y")){
            this.matrix[0][0] = Math.cos(angle);
            this.matrix[0][2] = Math.sin(angle);
            this.matrix[2][0] = -Math.sin(angle);
            this.matrix[2][2] = Math.cos(angle);
        }
        if(axis.equals("x")){
            this.matrix[1][1] = Math.cos(angle);
            this.matrix[1][2] = -Math.sin(angle);
            this.matrix[2][1] = Math.sin(angle);
            this.matrix[2][2] = Math.cos(angle);
        }
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
