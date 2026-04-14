package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static main.Main.SMALL_HYPONYM_FILE;
import static main.Main.SMALL_SYNSET_FILE;

public class HyponymsHandler extends NgordnetQueryHandler {

    Synset s;
    Graph g;
    GraphBuilder gb;
    Map<String, Set<Integer>> wordToId;
    Map<Integer, Set<String>> IdToWord;

    public HyponymsHandler() {//在创建对象的时候构造图，防止每次查询都构造
        gb = new GraphBuilder(SMALL_HYPONYM_FILE);
        g = gb.buildGraph();
        s = new Synset(SMALL_SYNSET_FILE);
        wordToId = s.getWordToId();
        IdToWord = s.getIdToWord();
    }

    @Override
    public String handle(NgordnetQuery q) {
        return "Hello";
    }
}

