package mk.ukim.finki.NP.ZadaciZaVezbanje.Risk;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Risk {
    public Risk() {
    }

    public int processAttacksData (InputStream is) {
        Scanner sc = new Scanner(is);

        int attacks = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String [] players = line.split(";");
            String [] parts1 = players[0].split("\\s+");
            String [] parts2 = players[1].split("\\s+");

            List<Integer> player1 = new ArrayList<>();
            List<Integer> player2 = new ArrayList<>();

            for (int i = 0; i < 3;i++) {
                player1.add(Integer.parseInt(parts1[i]));
                player2.add(Integer.parseInt(parts2[i]));
            }

            player1.sort(Integer::compare);
            player2.sort(Integer::compare);

            boolean flag = true;
            for (int i = 0;i < 3;i++) {
                if (player1.get(i) <= player2.get(i)) {
                    flag = false;
                }
            }

            if (flag)
                attacks++;
        }

        return attacks;
    }

    public void processAttacksData2 (InputStream is) {
        Scanner sc = new Scanner(is);

        int alive;
        int dead;

        while (sc.hasNextLine()) {
            alive = 0;
            dead = 0;
            String line = sc.nextLine();
            String [] players = line.split(";");
            String [] parts1 = players[0].split("\\s+");
            String [] parts2 = players[1].split("\\s+");

            List<Integer> player1 = new ArrayList<>();
            List<Integer> player2 = new ArrayList<>();

            for (int i = 0; i < 3;i++) {
                player1.add(Integer.parseInt(parts1[i]));
                player2.add(Integer.parseInt(parts2[i]));
            }

            player1.sort(Integer::compare);
            player2.sort(Integer::compare);

            for (int i = 0;i < 3;i++) {
                if (player1.get(i) > player2.get(i)) {
                    alive++;
                } else {
                    dead++;
                }
            }

            System.out.println(alive + " " + dead);
        }
    }
}
