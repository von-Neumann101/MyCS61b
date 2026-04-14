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
        g = gb.buildGraph();
        wordToId = s.getWordToId();
        IdToWord = s.getIdToWord();
        gram = new NGramMap(wordFile, countFile);
    }

    public HyponymsHandler() {//仅测试用
        gb = new GraphBuilder(SMALL_HYPONYM_FILE);
        g = gb.buildGraph();
        s = new Synset(SMALL_SYNSET_FILE);
        wordToId = s.getWordToId();
        IdToWord = s.getIdToWord();
    }

    @Override
    public String handle(NgordnetQuery q) {
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();
        List<String> words = q.words();
        s.build();
        Set<String> result = null;//总结果

        for (String word : words) {
            // 词->多个数字->dfs->多个数字->多个String
            Set<Integer> starts = wordToId.get(word);
            if (starts == null) {
                return "[]";
            }
            Set<Integer> path = g.getDfSPathFromStart(starts);

            Set<String> curr = new TreeSet<>();//每次的结果

            for (int i : path) {//当前的下位词
                curr.addAll(IdToWord.getOrDefault(i, Collections.emptySet()));
            }

            if (result == null) {
                result = curr;//初始化
            } else {
                result.retainAll(curr);//把每次循环的结果拿一块取交集
            }
        }

        if (result == null) {
            return "[]";
        }
        return result.toString();
    }
}

