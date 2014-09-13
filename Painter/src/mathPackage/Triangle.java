package mathPackage;

import java.awt.Color;

/**
 *
 * @author Jesus Estiven Lopera Jaramillo.
 * @author Esteban Foronda Sierra.
 * @author Mateo Restrepo Restrepo.
 */
public class Triangle implements Comparable<Triangle> {
   
    private Vector3D[] points = new Vector3D[3];
    private Color color;
    
    public Triangle(Vector3D point1, Vector3D point2, Vector3D point3,Color color){
        points[0] = point1;
        points[1] = point2;
        points[2] = point3;
        this.color=color;
    }
    
    public Vector3D calculeMin(int minBy){
        double min = points[0].getPoint()[minBy];
        int point = 0;
        for(int i = 1; i < 3; i++){
            if(points[i].getPoint()[minBy] < min){
                min = points[i].getPoint()[minBy];
                point = i;
            }
        }
        return points[point];
    }
    
    public Vector3D calculeMax(int maxBy){
        double max = points[0].getPoint()[maxBy];
        int point = 0;
        for(int i = 1; i < 3; i++){
            if(points[i].getPoint()[maxBy] > max){
                max = points[i].getPoint()[maxBy];
                point = i;
            }
        }
        return points[point];
    }
    
    public Vector3D[] getPoints(){
        return this.points;
    }

    public Color getColor() {
        return color;
    }
    
    @Override
    public int compareTo(Triangle o) {
        double compareZ = ((Triangle) o).calculeMin(2).getPoint()[2];
        return (int)(this.calculeMin(2).getPoint()[2] - compareZ);
        
    }
}
