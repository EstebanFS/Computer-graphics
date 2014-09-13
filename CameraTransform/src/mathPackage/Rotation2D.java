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
public class Rotation2D extends Matrix2D{

    public Rotation2D(double[][] matrix) {
        super(matrix);
    }
    
    public Rotation2D(double angle){
        super(angle);
    }
    
}
