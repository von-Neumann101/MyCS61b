package ngrams;

import java.util.List;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        for(int i : ts.keySet()) {
            if(i >= startYear && i <= endYear) {
                this.put(i, ts.get(i));
            }
        }
    }

    /**
     *  Returns all years for this time series in ascending order.
     */
    public List<Integer> years() {
        return new ArrayList<>(this.keySet());
    }

    /**
     *  Returns all data for this time series. Must correspond to the
     *  order of years().
     */
    public List<Double> data() {
        return new ArrayList<>(this.values());
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries sToReturn = new TimeSeries();
        if(ts.isEmpty() && this.isEmpty()) {
            return sToReturn;
        }
        
        int i = 0, j = 0;

        List<Integer> l1 = ts.years();
        List<Integer> l2 = this.years();

        while(true) {
            if(i == ts.size() && j < this.size()) {
                for(int k = j; k < this.size(); k++) {
                    sToReturn.put(l2.get(k), this.get(l2.get(k)));
                }
                break;
            }
            if(i < ts.size() && j == this.size()) {
                for(int k = i; k < ts.size(); k++) {
                    sToReturn.put(l1.get(k), this.get(l1.get(k)));
                }
                break;
            }
            if(i == ts.size() && j == this.size()) {
                break;
            }

            int year1 = l1.get(i);
            int year2 = l2.get(j);

            if(year1 > year2) {
                sToReturn.put(year2, this.get(year2));
                j += 1;
            } else if (year1 < year2) {
                sToReturn.put(year1, ts.get(year1));
                i += 1;
            }else {
                sToReturn.put(year1, ts.get(year1) + this.get(year2));
                i += 1;
                j += 1;
            }
        }
        return sToReturn;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        // TODO: Fill in this method.
        return null;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
