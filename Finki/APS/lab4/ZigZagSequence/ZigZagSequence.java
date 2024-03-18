package mk.ukim.finki.lab4.ZigZagSequence;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZigZagSequence {
    static int najdiNajdolgaCikCak(int a[]) {
        int max = 1, tmp = 1;
        int flag = 0;

        if (a.length == 1 && a[0] != 0) {
            return 1;
        }

        if (a[0] > 0)
            flag = 1;

        for (int i = 1; i < a.length; i++) {
            if (flag == 1 && a[i] < 0 && a[i - 1] != 0) {
                tmp++;
            } else if (flag == 0 && a[i] > 0 && a[i - 1] != 0) {
                tmp++;
            } else {
                tmp = 1;
            }

            if (a[i] > 0) {
                flag = 1;
            } else if (a[i] < 0) {
                flag = 0;
            }

            if (tmp > max)
                max = tmp;
        }

        return max;
    }

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i = 0; i < N; i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);

        br.close();

    }
}
