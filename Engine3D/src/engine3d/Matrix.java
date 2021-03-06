package engine3d;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 *
 * @author Francesco Forcellato
 */
public class Matrix {

    /**
     * Function to multiply a n*n matrix to a n*1 matrix
     *
     * @param a = matrix
     * @param b = matrix
     * @return the multiplication of a to the vector
     */
    public static double[][] multiply(double[][] a, double[][] b) throws MatrixException {
        if (a[0].length != b.length) {
            throw new MatrixException("The number of columns of the a matrix has to match the number of rows of the b matrix");
        }
        double[][] result = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int k = 0; k < b[0].length; k++) {
                double local = 0;
                for (int j = 0; j < a[0].length; j++) {
                    local += a[i][j] * b[j][k];
                }
                result[i][k] = local;
            }
        }
        return result;
    }

    public static double[][] getIdentityScale(int n, int m, double scale) {
        if (m < n) {
            throw new RuntimeException("It is impossible to create a matrix like that");
        }
        double[][] res = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j < n) {
                    if (i == 0 && j == 0 || i == n - 1 && j == n - 1 || i == j) {
                        res[i][j] = scale;
                    } else {
                        res[i][j] = 0;
                    }
                } else {
                    res[i][j] = 0;
                }
            }

        }
        return res;
    }
    
    /**
     * This is the matrix to give the perspective to the cube
     */
    public static double[][] projection = new double[][]{
        {1.0, 0.0, 0.0},
        {0.0, 1.0, 0.0}
    };

    /**
     * Function that returns the rotation matrix of the X axe
     *
     * @param angle of the rotation
     * @return the rotation matrix to multiply to your matrix
     */
    public static double[][] rotationX(double angle) {
        return new double[][]{
            {1.0, 0.0, 0.0},
            {0.0, cos(angle), -sin(angle)},
            {0.0, sin(angle), cos(angle)}
        };
    }

    /**
     * Function that returns the rotation matrix of the Y axe
     *
     * @param angle of the rotation
     * @return the rotation matrix to multiply to your matrix
     */
    public static double[][] rotationY(double angle) {
        return new double[][]{
            {cos(angle), 0.0, sin(angle)},
            {0.0, 1.0, 0.0},
            {-sin(angle), 0.0, cos(angle)}
        };
    }

    /**
     * Function that returns the rotation matrix of the Z axe
     *
     * @param angle of the rotation
     * @return the rotation matrix to multiply to your matrix
     */
    public static double[][] rotationZ(double angle) {
        return new double[][]{
            {cos(angle), -sin(angle), 0.0},
            {sin(angle), cos(angle), 0.0},
            {0.0, 0.0, 1.0}
        };
    }

    /**
     * Function to print a matrix
     *
     * @param a matrix to be printed
     */
    public static void printMatrix(double[][] a) {
        for (double[] a1 : a) {
            for (int j = 0; j < a1.length; j++) {
                System.out.print(j == 0 ? "[" + a1[j] + ", " : (j + 1) == a1.length ? a1[j] + "]" : a1[j] + ", ");
            }
            System.out.println("");
        }
    }
}

class MatrixException extends RuntimeException {

    public MatrixException(String errorMessage) {
        super(errorMessage);
    }
}
