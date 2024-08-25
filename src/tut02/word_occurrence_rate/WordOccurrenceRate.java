package tut02.word_occurrence_rate;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordOccurrenceRate {
    public static void main(String[] args) {
        Map<String, Integer> wordCountMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/tut02/word_occurrence_rate/data.in"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = word.trim();
                    if (!word.isEmpty()) {
                        wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                    }
                    System.out.println(word);
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordCountMap.entrySet());
        wordList.sort(Map.Entry.comparingByKey());

        try (PrintWriter writer = new PrintWriter(new FileWriter("src/tut02/word_occurrence_rate/Ketqua.out"))) {
            writer.println(wordCountMap.size());
            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                double frequency = (double) entry.getValue() / wordList.size();
                writer.printf("%s %.2f%n", entry.getKey(), frequency);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
