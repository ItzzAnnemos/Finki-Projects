package mk.ukim.finki.NP.lab2.DoubleMatrix;

import java.io.InputStream;
import java.util.Scanner;

public class MatrixReader {

    public static DoubleMatrix read(InputStream input) throws InsufficientElementsException {
        Scanner sc = new Scanner(input);
        int n = sc.nextInt();
        int m = sc.nextInt();

        double[] arr = new double[n * m];

        for (int i = 0; i < n * m; i++) {
            arr[i] = sc.nextDouble();
        }

        return new DoubleMatrix(arr, n, m);
    }
}
