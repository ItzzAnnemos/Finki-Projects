package mk.ukim.finki.NP.ZadaciZaVezbanje.MojDDV;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.Scanner;

public class MojDDV {
    private ArrayList<Fiskalna> fiskalni;

    public MojDDV() {
        this.fiskalni = new ArrayList<>();
    }

    public void readRecords(InputStream inputStream) {
        Scanner sc = new Scanner(inputStream);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");
            String tmpId = parts[0];
            ArrayList<Item> tmpItems = new ArrayList<>();
            for (int i = 1; i < parts.length; i += 2) {
                Item tmp = new Item(Integer.parseInt(parts[i]), parts[i + 1].charAt(0));
                tmpItems.add(tmp);

            }
            try {
                Fiskalna temp = new Fiskalna(tmpId, tmpItems);
                fiskalni.add(temp);
            } catch (AmountNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void printTaxReturns(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);

        /*for (int i = 0; i < fiskalni.size(); i++) {
            pw.println(fiskalni.get(i).toString());
        }*/

        for (int i = 0; i < fiskalni.size(); i++) {
            pw.println(fiskalni.get(i).toStringR());
        }

        pw.flush();
    }

    public void printStatistics (OutputStream outputStream) {
        DoubleSummaryStatistics dss = new DoubleSummaryStatistics();
        for (int i = 0;i < fiskalni.size();i++) {
            dss.accept(fiskalni.get(i).taxReturn());
        }

        double avg = dss.getAverage();
        double min = dss.getMin();
        double max = dss.getMax();
        long count = dss.getCount();
        double sum = dss.getSum();

        PrintWriter pw = new PrintWriter(outputStream);

        pw.println(String.format("min:\t%5.3f", min));
        pw.println(String.format("max:\t%5.3f", max));
        pw.println(String.format("sum:\t%5.3f", sum));
        pw.println(String.format("count:\t%d", count));
        pw.println(String.format("avg:\t%5.3f", avg));

        pw.flush();
    }
}
