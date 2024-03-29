package mk.ukim.finki.NP.ZadaciZaVezbanje2.TextProcessor;

import java.util.*;

public class Text {
    private String line;
    private String originalLine;
    private Map<String, Integer> wordFreq;
    private List<Integer> freq;

    public Text(String line) {
        this.originalLine = line;
        this.line = line.toLowerCase().replaceAll("[^a-zA-Z\\s+]", "");
        this.wordFreq = new HashMap<>();
        this.freq = new ArrayList<>();
        fillTheMap();
    }

    public void fillTheMap() {
        String[] parts = line.split("\\s+");
        for (String part : parts) {
            wordFreq.put(part, wordFreq.getOrDefault(part, 0) + 1);
        }
    }

    public String getFreq(Set<String> words) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        List<String> sortedWords = new ArrayList<>(words);
        sortedWords.sort(String::compareTo);

        sortedWords.forEach(word -> {
            if (wordFreq.containsKey(word)) {
                sb.append(wordFreq.get(word)).append(", ");
                freq.add(wordFreq.get(word));
            } else {
                sb.append(0).append(", ");
                freq.add(0);
            }
        });

        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.deleteCharAt(sb.lastIndexOf(" "));
        sb.append("]");

        return sb.toString();
    }

    public int getOccurrences(String word) {
        return wordFreq.getOrDefault(word, 0);
    }

    public List<Integer> frequencies() {
        return freq;
    }

    @Override
    public String toString() {
        return originalLine;
    }
}
