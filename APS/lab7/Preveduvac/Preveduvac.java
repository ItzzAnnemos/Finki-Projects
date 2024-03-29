package mk.ukim.finki.lab7.Preveduvac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Preveduvac {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        OBHT<String, String> translate = new OBHT<>(N*2);

        for(int i=1;i<=N;i++){
            String imelozinka = br.readLine();
            String[] pom = imelozinka.split(" ");

            translate.insert(pom[1], pom[0]);
        }

        while (br.ready()) {
            String line = br.readLine();
            if (line.equals("KRAJ")) {
                return;
            }

            if (translate.search(line) != OBHT.NONE) {
                System.out.println(translate.getBucket(translate.search(line)).value);
            } else {
                System.out.println("/");
            }
        }
    }
}
