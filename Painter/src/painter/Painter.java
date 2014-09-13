package painter;

import mathPackage.Triangle;
import mathPackage.Vector3D;

/**
 *
 * @author Jesus Estiven Lopera. 
 * @autor Esteban Foronda.
 * @autor Mateo Restrepo.
 */
public class Painter {
    
    public static boolean firstConflict(Triangle[] triangles){
        double zMinFirstTriangle = triangles[0].calculeMin(2).getPoint()[2];
        double zMaxFirstTriangle = triangles[0].calculeMax(2).getPoint()[2];
        double zMinSecondTriangle = triangles[1].calculeMin(2).getPoint()[2];
        double zMaxSecondTriangle = triangles[1].calculeMax(2).getPoint()[2];
        if(zMaxFirstTriangle == zMaxSecondTriangle)return true;
        if(zMaxFirstTriangle > zMaxSecondTriangle){
            if(zMinFirstTriangle < zMaxSecondTriangle)return true;
        }else{
            if(zMinSecondTriangle < zMaxFirstTriangle)return true;
        }
        return false;
    }
    
     public static boolean secondConflict(Triangle[] triangles){
        double xMinFirstTriangle = triangles[0].calculeMin(0).getPoint()[0];
        double xMaxFirstTriangle = triangles[0].calculeMax(0).getPoint()[0];
        double xMinSecondTriangle = triangles[1].calculeMin(0).getPoint()[0];
        double xMaxSecondTriangle = triangles[1].calculeMax(0).getPoint()[0];
        if(xMaxFirstTriangle == xMaxSecondTriangle)return true;
        if(xMaxFirstTriangle > xMaxSecondTriangle){
            if(xMinFirstTriangle < xMaxSecondTriangle)return true;
        }else{
            if(xMinSecondTriangle < xMaxFirstTriangle)return true;
        }
        return false;
    }
     
     public static boolean thirdConflict(Triangle[] triangles){
         Vector3D preMultiplicationFirstVector = triangles[0].getPoints()[1].MultiplicationScalar(-1);
         Vector3D firstVector = triangles[0].getPoints()[1].sumVector(preMultiplicationFirstVector);
         Vector3D preMultiplicationSecondVector = triangles[0].getPoints()[1].MultiplicationScalar(-1);
         Vector3D secondVector = triangles[0].getPoints()[2].sumVector(preMultiplicationSecondVector);
         Vector3D plane = firstVector.multiplicationVector(secondVector);
         //se hace p cruz para hallar a b c
         double a = plane.getPoint()[0];
         double b = plane.getPoint()[1];
         double c = plane.getPoint()[2];
         //encontrar d
         double x1 = triangles[0].getPoints()[0].getPoint()[0];
         double y1 = triangles[0].getPoints()[0].getPoint()[1];
         double z1 = triangles[0].getPoints()[0].getPoint()[2];
         double d = (a*x1) + (b*y1) + (c*z1);
         //reemplazar cada vertice 2 triangulo plano por la ecuacion del plano
         double x2 = triangles[1].getPoints()[0].getPoint()[0];
         double y2 = triangles[1].getPoints()[0].getPoint()[1];
         double z2 = triangles[1].getPoints()[0].getPoint()[2];
         double result1 = ((a*x2) + (b*y2) + (c*z2)) - d;
         double x3 = triangles[1].getPoints()[0].getPoint()[0];
         double y3 = triangles[1].getPoints()[0].getPoint()[1];
         double z3 = triangles[1].getPoints()[0].getPoint()[2];
         double result2 = ((a*x3) + (b*y3) + (c*z3)) - d;
         double x4 = triangles[1].getPoints()[0].getPoint()[0];
         double y4 = triangles[1].getPoints()[0].getPoint()[1];
         double z4 = triangles[1].getPoints()[0].getPoint()[2];
         double result3 = ((a*x4) + (b*y4) + (c*z4)) - d;
         if(result1 > 0)return true;
         if(result2 > 0)return true;
         if(result3 > 0)return true;
         return false;
     }
     
     public static boolean FourthConflict(Triangle[] triangles){
         Vector3D preMultiplicationFirstVector = triangles[1].getPoints()[1].MultiplicationScalar(-1);
         Vector3D firstVector = triangles[1].getPoints()[1].sumVector(preMultiplicationFirstVector);
         Vector3D preMultiplicationSecondVector = triangles[1].getPoints()[1].MultiplicationScalar(-1);
         Vector3D secondVector = triangles[1].getPoints()[2].sumVector(preMultiplicationSecondVector);
         Vector3D plane = firstVector.multiplicationVector(secondVector);
         //se hace p cruz para hallar a b c
         double a = plane.getPoint()[0];
         double b = plane.getPoint()[1];
         double c = plane.getPoint()[2];
         //encontrar d
         double x1 = triangles[1].getPoints()[0].getPoint()[0];
         double y1 = triangles[1].getPoints()[0].getPoint()[1];
         double z1 = triangles[1].getPoints()[0].getPoint()[2];
         double d = (a*x1) + (b*y1) + (c*z1);
         //reemplazar cada vertice 2 triangulo plano por la ecuacion del plano
         double x2 = triangles[0].getPoints()[0].getPoint()[0];
         double y2 = triangles[0].getPoints()[0].getPoint()[1];
         double z2 = triangles[0].getPoints()[0].getPoint()[2];
         double result1 = ((a*x2) + (b*y2) + (c*z2)) - d;
         double x3 = triangles[0].getPoints()[0].getPoint()[0];
         double y3 = triangles[0].getPoints()[0].getPoint()[1];
         double z3 = triangles[0].getPoints()[0].getPoint()[2];
         double result2 = ((a*x3) + (b*y3) + (c*z3)) - d;
         double x4 = triangles[0].getPoints()[0].getPoint()[0];
         double y4 = triangles[0].getPoints()[0].getPoint()[1];
         double z4 = triangles[0].getPoints()[0].getPoint()[2];
         double result3 = ((a*x4) + (b*y4) + (c*z4)) - d;
         if(result1 > 0)return true;
         if(result2 > 0)return true;
         if(result3 > 0)return true;
         return false;
     }
    
}
