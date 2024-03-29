package mk.ukim.finki.lab7.Lozinki;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Lozinki {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        HashMap<String, String> passwords = new HashMap<>();

        for(int i=1;i<=N;i++){
            String imelozinka = br.readLine();
            String[] pom = imelozinka.split(" ");

            passwords.put(pom[0], pom[1]);
        }

        while (br.ready()) {
            String line = br.readLine();
            if (line.equals("KRAJ")) {
                return;
            }

            String [] parts = line.split(" ");

            if (passwords.containsKey(parts[0])) {
                String tmp = passwords.get(parts[0]);
                if (tmp.equals(parts[1])) {
                    System.out.println("Najaven");
                    return;
                } else {
                    System.out.println("Nenajaven");
                }
            } else {
                System.out.println("Nenajaven");
            }
        }

    }
}
