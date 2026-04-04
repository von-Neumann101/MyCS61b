package ngrams;

import java.util.Collection;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    private Map<String, TimeSeries> words;
    private TimeSeries counts;
    public NGramMap(String wordsFilename, String countsFilename) {
        In wordsFile = new In(wordsFilename);
        In countsFile = new In(countsFilename);

        words = new HashMap<>();
        counts = new TimeSeries();

        int i = 0, j = 0;
        // words
        while (!wordsFile.isEmpty()) {
            i += 1;
            String nextLine = wordsFile.readLine();
            String[] splitLine = nextLine.split("\t");
            String word = splitLine[0];
            int year = Integer.parseInt(splitLine[1]);
            double count = Double.parseDouble(splitLine[2]);
            if(!words.containsKey(word)) {
                words.put(word,new TimeSeries());
            }
            words.get(word).put(year, count);
        }

        // counts
        while(!countsFile.isEmpty()) {
            j += 1;
            String nextLine = countsFile.readLine();
            String[] splitLine = nextLine.split(",");
            int year = Integer.parseInt(splitLine[0]);
            double count = Double.parseDouble(splitLine[1]);
            counts.put(year, count);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        TimeSeries TSToReturn;
        if(!words.containsKey(word)) return new TimeSeries();
        TSToReturn = new TimeSeries(this.words.get(word), startYear, endYear);
        return TSToReturn;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        TimeSeries TSToReturn;
        if(!words.containsKey(word)) return new TimeSeries();
        TSToReturn = this.words.get(word);
        return TSToReturn;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return counts;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        if(!words.containsKey(word)) return new TimeSeries();
        TimeSeries total_count = new TimeSeries(counts, startYear, endYear);
        TimeSeries word_times = new TimeSeries(words.get(word), startYear, endYear);
        return word_times.dividedBy(total_count);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        if(!words.containsKey(word)) return new TimeSeries();
        TimeSeries total_count = counts;
        TimeSeries word_times = words.get(word);
        return word_times.dividedBy(total_count);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries word = new TimeSeries();
        for(String i : words) {
            if(this.words.get(i) == null) continue;
            TimeSeries newWord = this.words.get(i);
            word = word.plus(newWord);
        }
        word = new TimeSeries(word, startYear, endYear);

        return word.dividedBy(new TimeSeries(counts, startYear, endYear));
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        return null;
    }


}
