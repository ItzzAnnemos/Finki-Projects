package mk.ukim.finki.NP.lab2.DoubleMatrix;

import org.w3c.dom.ls.LSOutput;

import java.text.DecimalFormat;
import java.util.Arrays;

public class DoubleMatrix {
    private final double[][] matrix;

    public DoubleMatrix(double[] a, int n, int m) throws InsufficientElementsException {
        if (n * m > a.length) {
            throw new InsufficientElementsException();
        }

        matrix = new double[n][m];

        int l = a.length - (m * n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = a[l++];
            }
        }
    }

    public String getDimensions() {
        return "[" + rows() + " x " + columns() + "]";
    }

    public int rows() {
        return matrix.length;
    }

    public int columns() {
        return matrix[0].length;
    }

    public double maxElementAtRow(int row) throws InvalidRowNumberException {
        if (row > rows() || row <= 0) {
            throw new InvalidRowNumberException();
        }

        double max = matrix[row - 1][0];
        for (int i = 1; i < columns() - 1; i++) {
            if (matrix[row - 1][i] > max) {
                max = matrix[row - 1][i];
            }
        }

        return max;
    }

    public double maxElementAtColumn(int column) throws InvalidColumnNumberException {
        if (column > columns() || column <= 0) {
            throw new InvalidColumnNumberException();
        }

        double max = matrix[0][column - 1];
        for (int j = 1; j < rows() - 1; j++) {
            if (matrix[j][column - 1] > max) {
                max = matrix[j][column - 1];
            }
        }

        return max;
    }

    public double sum() {
        double sum = 0.0;
        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < columns(); j++) {
                sum += matrix[i][j];
            }
        }

        return sum;
    }

    public double[] toSortedArray() {
        double[] array = new double[rows() * columns()];
        int index = 0;

        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < columns(); j++) {
                array[index++] = matrix[i][j];
            }
        }

        Arrays.sort(array);

        for (int i = 0; i < array.length / 2; i++) {
            double temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }

        return array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < columns(); j++) {
                sb.append(decimalFormat.format(matrix[i][j]));
                if (j != columns() - 1) {
                    sb.append("\t");
                }
            }
            if (i != rows() - 1) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DoubleMatrix matrix1 = (DoubleMatrix) o;
        return Arrays.deepEquals(matrix, matrix1.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }
}
