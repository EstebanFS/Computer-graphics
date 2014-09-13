/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathPackage;

/**
 *
 * @author Chucho
 */
public class Translation2D extends Matrix2D{

    public Translation2D(double[][] matrix) {
        super(matrix);
    }
    
    public Translation2D(int x, int y){
        super(x,y);
    }
    
}
