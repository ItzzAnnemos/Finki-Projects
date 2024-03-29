package mk.ukim.finki.NP.ZadaciZaVezbanje.Subtitles;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Subtitles {
    ArrayList<Element> list;

    public Subtitles() {
        list = new ArrayList<>();
    }

    public int loadSubtitles(InputStream inputStream) {
        Scanner sc = new Scanner(inputStream);

        while (sc.hasNextLine()) {
            String num = sc.nextLine();
            String time = sc.nextLine();
            String subs = sc.nextLine();

            while (sc.hasNextLine()) {
                String temp = sc.nextLine();
                if (temp.equals("")) {
                    break;
                }
                subs += "\n" + temp;
            }

            Element tmp = new Element(num, time, subs);
            list.add(tmp);
        }

        return list.size();
    }

    public void print() {
        String ret = "";
        for (int i = 0; i < list.size(); i++) {
            ret += list.get(i).toString();
            ret += "\n";
        }

        System.out.print(ret);
    }

    public void shift(int ms) {
        for (Element element : list) {
            element.shift(ms);
        }
    }
}
