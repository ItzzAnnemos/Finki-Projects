package mk.ukim.finki.lab7.Apteka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Apteka {
    public static void main(String[] args) throws IOException {
        // TODO implement
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        CBHT<LekKluch, Lek> apteka = new CBHT<>(N * 2);

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            String[] parts = line.split(" ");

            LekKluch key = new LekKluch(parts[0]);
            Lek lek = new Lek(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));

            apteka.insert(key, lek);
        }

        while (br.ready()) {
            String name = br.readLine();
            if (name.equals("KRAJ")) {
                return;
            }
            int num = Integer.parseInt(br.readLine());

            if (apteka.search(new LekKluch(name)) != null) {
                Lek tmp = apteka.search(new LekKluch(name)).element.value;
                System.out.println(tmp);
                if (tmp.getKolicina() >= num) {
                    apteka.search(new LekKluch(name)).element.value.setKolicina(tmp.getKolicina()-num);
                    System.out.println("Napravena naracka");
                } else {
                    System.out.println("Nema dovolno lekovi");
                }
            } else {
                System.out.println("Nema takov lek");
            }
        }
    }
}
