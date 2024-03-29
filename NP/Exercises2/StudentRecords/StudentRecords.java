package mk.ukim.finki.NP.ZadaciZaVezbanje2.StudentRecords;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class StudentRecords {
    private List<File> files;
    private Map<String, List<File>> mapByDirection;

    public StudentRecords() {
        this.files = new ArrayList<>();
        this.mapByDirection = new TreeMap<>(Comparator.naturalOrder());
    }

    int readRecords(InputStream inputStream) {
        Scanner sc = new Scanner(inputStream);

        int count = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");

            List<Integer> list = new ArrayList<>();
            for (int i = 2; i < parts.length; i++) {
                list.add(Integer.parseInt(parts[i]));
            }
            files.add(new File(parts[0], parts[1], list));
            mapByDirection.putIfAbsent(parts[1], new ArrayList<>());
            mapByDirection.get(parts[1]).add(files.get(count));
            count++;
        }

        return count;
    }

    public void writeTable(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);

        Comparator<File> comparator = Comparator.comparing(File::getAverage).reversed()
                .thenComparing(File::getCode);

        mapByDirection.forEach((key, tmp) -> {
            tmp.sort(comparator);
            pw.println(key);
            tmp.forEach(pw::println);
        });

        pw.flush();
    }

    public void writeDistribution(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);

        List<Map.Entry<String, List<File>>> entryList = new ArrayList<>(mapByDirection.entrySet());

        entryList.sort((entry1, entry2) -> {
            int sumTens1 = entry1.getValue().stream().mapToInt(File::getNumOfTens).sum();
            int sumTens2 = entry2.getValue().stream().mapToInt(File::getNumOfTens).sum();
            return Integer.compare(sumTens2, sumTens1);
        });

        for (Map.Entry<String, List<File>> entry : entryList) {
            String key = entry.getKey();
            List<File> value = entry.getValue();
            pw.println(key);
            int six = value.stream().mapToInt(File::getNumOfSixes).sum();
            int seven = value.stream().mapToInt(File::getNumOfSevens).sum();
            int eight = value.stream().mapToInt(File::getNumOfEights).sum();
            int nine = value.stream().mapToInt(File::getNumOfNines).sum();
            int ten = value.stream().mapToInt(File::getNumOfTens).sum();
            pw.println(String.format("%2d | %s(%d)", 6, getStars(six), six));
            pw.println(String.format("%2d | %s(%d)", 7, getStars(seven), seven));
            pw.println(String.format("%2d | %s(%d)", 8, getStars(eight), eight));
            pw.println(String.format("%2d | %s(%d)", 9, getStars(nine), nine));
            pw.println(String.format("%2d | %s(%d)", 10, getStars(ten), ten));
        }

        pw.flush();
    }

    private String getStars(int x) {
        double tmp = x / 10.0;

        return "*".repeat((int) Math.ceil(Math.max(0, tmp)));
    }
}
