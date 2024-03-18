package mk.ukim.finki.NP.lab7.TermFrequency;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TermFrequency {
    private Map<String, Integer> mapByWord;
    public TermFrequency(InputStream inputStream, String[] stopWords) {
        mapByWord = new TreeMap<>(Comparator.reverseOrder());
        Scanner sc = new Scanner(inputStream);

        Set<String> wordsToNotInclude = new HashSet<>();
        for (String string : stopWords) {
            wordsToNotInclude.add(string.toLowerCase());
        }

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println("Original Line: " + line);
            String finalLine = line.toLowerCase().replaceAll("\\.", "")
                    .replaceAll(",", "");
            System.out.println("Processed Line: " + finalLine);
            String [] parts = finalLine.toLowerCase().split("\\s+");

            for (String part : parts) {
                if (!wordsToNotInclude.contains(part.strip()) && !part.isEmpty()) {
                    mapByWord.put(part.strip(), mapByWord.getOrDefault(part.strip(), 0) + 1);
                }
            }
        }
    }

    public int countTotal() {
        int count = 0;
        for (Map.Entry<String, Integer> entry : mapByWord.entrySet()) {
            count += entry.getValue();
        }

        return count;
    }

    public int countDistinct() {
        return mapByWord.keySet().size();
    }

    public List<String> mostOften(int k) {
        return mapByWord.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
