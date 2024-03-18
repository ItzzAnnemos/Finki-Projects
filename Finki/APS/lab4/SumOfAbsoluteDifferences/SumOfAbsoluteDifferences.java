package mk.ukim.finki.lab4.SumOfAbsoluteDifferences;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SumOfAbsoluteDifferences {
    static int solve(int numbers[], int N, int K) {
        int[] differences = new int[N - 1];

        // Calculate differences between adjacent numbers
        for (int i = 1; i < N; i++) {
            differences[i - 1] = numbers[i] - numbers[i - 1];
        }

        // Initialize the result with the sum of the first K - 1 differences
        int result = 0;
        for (int i = 0; i < K - 1; i++) {
            result += differences[i];
        }

        // Initialize the maximum sum with the initial result
        int maxSum = result;

        // Iterate through the array and find the maximum sum
        for (int i = K - 1; i < N - 1; i++) {
            result += differences[i];
            result -= differences[i - K + 1];
            maxSum = Math.max(maxSum, result);
        }

        return maxSum;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int numbers[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for (i=0;i<N;i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = solve(numbers, N, K);
        System.out.println(res);

        br.close();

    }
}
