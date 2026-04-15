package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import java.util.*;

import static main.Main.SMALL_HYPONYM_FILE;
import static main.Main.SMALL_SYNSET_FILE;

public class HyponymsHandler extends NgordnetQueryHandler {
/*写之前务必仔细阅读，不要读一半就开始写！你会缺失很多提示*/
    Synset s;
    Graph g;
    GraphBuilder gb;
    Map<String, Set<Integer>> wordToId;
    Map<Integer, Set<String>> IdToWord;
    NGramMap gram;

    public HyponymsHandler(String wordFile,
                           String countFile,
                           String synsetFile,
                           String hyponymFile) {//在创建对象的时候构造图，防止每次查询都构造
        gb = new GraphBuilder(hyponymFile);
        s = new Synset(synsetFile);
        s.build();
        g = gb.buildGraph();
        wordToId = s.getWordToId();
        IdToWord = s.getIdToWord();
        gram = new NGramMap(wordFile, countFile);
    }

    private Set<String> hyponymsWord(List<String> words) {
        Set<String> result = null;//总结果
        for (String word : words) {
            // 词->多个数字->dfs->多个数字->多个String
            Set<Integer> starts = wordToId.get(word);
            if (starts == null) {
                return null;
            }
            Set<Integer> path = g.getDfSPathFromStart(starts);

            Set<String> curr = new HashSet<>();//每次的结果

            for (int i : path) {//当前的下位词
                curr.addAll(IdToWord.getOrDefault(i, Collections.emptySet()));
            }

            if (result == null) {
                result = curr;//初始化
            } else {
                result.retainAll(curr);//把每次循环的结果拿一块取交集
            }
        }

        return result;
    }

    @Override
    public String handle(NgordnetQuery q) {
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();
        List<String> words = q.words();
        s.build();
        Set<String> result = null;//总结果

        Set<String> candidates = hyponymsWord(words);
        if (candidates == null) return "[]";
        if(k == 0) {
            return new TreeSet<>(candidates).toString();
        }

        Map<String, Double> countMap = new HashMap<>();
        // 获取 countMap
        for (String word : candidates) {
            TimeSeries ts = gram.countHistory(word, startYear, endYear);
            double welcome = 0.0;
            for (double i : ts.values()) {
                welcome += i;
            }
            if (welcome > 0) {
                countMap.put(word, welcome);
            }
        }

        if (result == null) {
            return "[]";
        }
        return result.toString();
    }

    private record CountComparator(Map<String, Double> countMap) implements Comparator<String> {

        @Override
            public int compare(String o2, String o1) {
                int cmp = Double.compare(countMap.get(o2), countMap.get(o1));
                if (cmp != 0) {
                    return cmp;
                }
                return o1.compareTo(o2);
            }
        }
}

