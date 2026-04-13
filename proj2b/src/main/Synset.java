package main;

import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Synset {
    Map<Integer, Set<String>> idToWord;
    Map<String, Set<Integer>> wordToId;
    String file;
    Synset (String file) {
        idToWord = new HashMap<>();
        wordToId = new HashMap<>();
        this.file = file;
    }

    void build() {
        In in = new In(file);

        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] p = line.split(",");

            int id = Integer.parseInt(p[0].trim());
            String[] words = p[1].trim().split(" ");

            Set<String> wordSet = new HashSet<>();
            for (String w: words) {
                if(w.isEmpty()) continue;
                wordSet.add(w);
            }
            idToWord.put(id, wordSet);

            for (String w: wordSet) {
                wordToId.put(w, new HashSet<>());
                wordToId.get(w).add(id);
            }
        }
    }

    Map<Integer, Set<String>> getIdToWord() {
        return idToWord;
    }

    Map<String, Set<Integer>> getWordToId() {
        return wordToId;
    }
}
