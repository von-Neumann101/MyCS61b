package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.TimeSeries;
import ngrams.NGramMap;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap map;
    public HistoryTextHandler(NGramMap map) {
        this.map = map;
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        StringBuilder response = new StringBuilder();
        for(String s : words) {
            response.append(s).append(": ");
            response.append(map.weightHistory(s, startYear, endYear).toString());
            response.append("\n");
        }
        return response.toString();
    }
}
