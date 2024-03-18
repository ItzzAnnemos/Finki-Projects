package mk.ukim.finki.lab6.MVR;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MVR {
    public static void main(String[] args) {

        Scanner br = new Scanner(System.in);

        int N = Integer.parseInt(br.nextLine());

        Queue<Gragjanin> licni = new LinkedList<>();
        Queue<Gragjanin> pasosi = new LinkedList<>();
        Queue<Gragjanin> vozacki = new LinkedList<>();

        for(int i=1;i<=N;i++){
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            Gragjanin covek = new Gragjanin(imePrezime,lKarta,pasos,vozacka);

            if (covek.getLicna() == 1) {
                licni.add(covek);
            } else if (covek.getPasos() == 1) {
                pasosi.add(covek);
            } else if (covek.getVozacka() == 1) {
                vozacki.add(covek);
            }
        }

        int n = licni.size();

        for (int i = 0;i < n;i++) {
            Gragjanin tmp = licni.poll();
            tmp.setLicna(0);
            if (tmp.finished()) {
                System.out.println(tmp.getImePrezime());
            } else {
                if (tmp.getPasos() == 1) {
                    pasosi.add(tmp);
                } else if (tmp.getVozacka() == 1) {
                    vozacki.add(tmp);
                }
            }
        }

        n = pasosi.size();
        for (int i = 0;i < n;i++) {
            Gragjanin tmp = pasosi.poll();
            tmp.setPasos(0);
            if (tmp.finished()) {
                System.out.println(tmp.getImePrezime());
            } else {
                if (tmp.getVozacka() == 1) {
                    vozacki.add(tmp);
                }
            }
        }

        n = vozacki.size();
        for (int i = 0;i < n;i++) {
            Gragjanin tmp = vozacki.poll();
            tmp.setVozacka(0);
            if (tmp.finished()) {
                System.out.println(tmp.getImePrezime());
            }
        }
    }
}
