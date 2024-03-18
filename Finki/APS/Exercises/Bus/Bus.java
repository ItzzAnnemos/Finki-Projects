package mk.ukim.finki.ZadaciZaVezbanje.Bus;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();

        if (N >= M) {
            System.out.println(N * 100);
            if (M != 0) {
                System.out.println((N + M - 1) * 100);
            } else {
                System.out.println(N * 100);
            }
        } else {
            int counter = 0;
            int m = M;
            while ((m -= N) > 0) {
                counter++;
            }
            System.out.println((N * 100) + ((N * counter + m) * 100));
            System.out.println((N + M - 1)*100);
        }
    }
}
