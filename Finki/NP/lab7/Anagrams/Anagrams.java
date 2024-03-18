package mk.ukim.finki.NP.lab7.Anagrams;

import java.io.InputStream;
import java.util.*;

public class Anagrams {
    public static void main(String[] args) {
        findAll(System.in);
    }

    public static void findAll(InputStream inputStream) {
        Scanner sc = new Scanner(inputStream);

        Map<Collection<Character>, List<String>> mapByChars = new LinkedHashMap<>();

        while (sc.hasNextLine()) {
            String word = sc.nextLine();

            Collection<Character> chars = new ArrayList<>();
            for (int i = 0;i < word.length();i++) {
                chars.add(word.charAt(i));
            }

            if (chars.size() >= 4) {
                mapByChars.putIfAbsent(chars, new ArrayList<>());
            }

            for (Map.Entry<Collection<Character>, List<String>> entry : mapByChars.entrySet()) {
                if (entry.getKey().containsAll(chars) && entry.getKey().size() == chars.size() && chars.containsAll(entry.getKey())) {
                    mapByChars.get(entry.getKey()).add(word);
                    break;
                }
            }
        }

        mapByChars.values().stream()
                .filter(list -> !list.isEmpty())
                .map(list -> String.join(" ", list))
                .forEach(System.out::println);
    }
}
