package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static main.Main.SMALL_HYPONYM_FILE;
import static main.Main.SMALL_SYNSET_FILE;

public class HyponymsHandler extends NgordnetQueryHandler {
    @Override
    public String handle(NgordnetQuery q) {
        return "Hello";
    }
}

