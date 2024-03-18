package mk.ukim.finki.NP.ZadaciZaVezbanje2.TextProcessor;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class TextProcessor {
    private List<Text> lines;
    private Set<String> words;
    private Map<String, Integer> map;

    public TextProcessor() {
        this.lines = new ArrayList<>();
        this.words = new TreeSet<>(String::compareTo);
        this.map = new TreeMap<>();
    }

    public void readText(InputStream is) {
        Scanner sc = new Scanner(is);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            lines.add(new Text(line));

            String lineFinal = line.toLowerCase().replaceAll("[^a-zA-Z\\s+]", "");

            String[] parts = lineFinal.split("\\s+");
            words.addAll(Arrays.asList(parts));
        }
        fillTheMap();
    }

    public void fillTheMap() {
        words.forEach(word -> {
            for (Text text : lines) {
                map.computeIfAbsent(word, k -> 0);
                map.put(word, map.get(word) + text.getOccurrences(word));
            }
        });
    }

    public void printTextsVectors(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);

        lines.forEach(text -> pw.println(text.getFreq(words)));

        pw.flush();
    }

    public void printCorpus(OutputStream os, int n, boolean ascending) {
        Comparator<Integer> comparator = Comparator.comparing(Integer::intValue);
        if (!ascending) {
            comparator = comparator.reversed();
        }

        PrintWriter pw = new PrintWriter(os);

        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(comparator))
                .limit(n)
                .forEach(entry -> pw.println(entry.getKey() + " : " + entry.getValue()));

        pw.flush();
    }

    public void mostSimilarTexts (OutputStream os) {
        PrintWriter pw = new PrintWriter(os);
        double max = 0;
        int maxI = 0, maxJ = 0;

        for (int i = 0;i < lines.size() - 1;i++) {
            for (int j = 0;j < lines.size();j++) {
                if (i != j) {
                    double similarity = CosineSimilarityCalculator.cosineSimilarity(lines.get(i).frequencies(),
                            lines.get(j).frequencies());

                    if (similarity > max) {
                        max = similarity;
                        maxI = i;
                        maxJ = j;
                    }
                }
            }
        }

        pw.println(lines.get(maxI));
        pw.println(lines.get(maxJ));
        pw.println(String.format("%.10f", max));

        pw.flush();
    }
}
