/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathPackage;

/**
 *
 * @author Esteban
 */
public class SegmentLine {
    private int[] initialPoint;
    private int[] finalPoint;

public SegmentLine(int xInitial, int yInitial, int zInitial, 
        int xFinal, int yFinal, int zFinal) {
    initialPoint = new int[3];
    finalPoint = new int[3];
    initialPoint[0] = xInitial;
    initialPoint[1] = yInitial;
    initialPoint[2] = zInitial;
    finalPoint[0] = xFinal;
    finalPoint[1] = yFinal;
    finalPoint[2] = zFinal;
    }

}
