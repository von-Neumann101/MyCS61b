import ngrams.TimeSeries;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TimeSeriesTest {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>();
        expectedYears.add(1991);
        expectedYears.add(1992);
        expectedYears.add(1994);
        expectedYears.add(1995);

        assertThat(totalPopulation.years()).isEqualTo(expectedYears);

        List<Double> expectedTotal = new ArrayList<>();
        expectedTotal.add(0.0);
        expectedTotal.add(100.0);
        expectedTotal.add(600.0);
        expectedTotal.add(500.0);

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertThat(totalPopulation.data().get(i)).isWithin(1E-10).of(expectedTotal.get(i));
        }
    }

    @Test
    public void testEmptyBasic() {
        TimeSeries catPopulation = new TimeSeries();
        TimeSeries dogPopulation = new TimeSeries();

        assertThat(catPopulation.years()).isEmpty();
        assertThat(catPopulation.data()).isEmpty();

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);

        assertThat(totalPopulation.years()).isEmpty();
        assertThat(totalPopulation.data()).isEmpty();
    }

    @Test
    public void dividedByBasicTest() {
        TimeSeries ts1 = new TimeSeries();
        ts1.put(2000, 10.0);
        ts1.put(2001, 20.0);

        TimeSeries ts2 = new TimeSeries();
        ts2.put(2000, 2.0);
        ts2.put(2001, 4.0);

        TimeSeries result = ts1.dividedBy(ts2);

        assertThat(result.get(2000)).isEqualTo(5.0);
        assertThat(result.get(2001)).isEqualTo(5.0);
    }

    @Test
    public void tsMissing() {
        TimeSeries ts1 = new TimeSeries();
        ts1.put(2000, 10.0);
        ts1.put(2001, 20.0);

        TimeSeries ts2 = new TimeSeries();
        ts2.put(2000, 2.0);
        ts2.put(2002, 4.0);

        try {
            TimeSeries result = ts1.dividedBy(ts2);
            Assertions.fail("Expected an IllegalArgumentException to be thrown");
        }catch (IllegalArgumentException e) {
            assertThat(1).isEqualTo(1);
        }
    }

    @Test
    public void thisMissing() {
        TimeSeries ts1 = new TimeSeries();
        ts1.put(2000, 10.0);
        ts1.put(2001, 20.0);

        TimeSeries ts2 = new TimeSeries();
        ts2.put(2000, 2.0);
        ts2.put(2001, 4.0);
        ts2.put(2002, 4.0);

        TimeSeries result = ts1.dividedBy(ts2);

        assertThat(result.get(2000)).isEqualTo(5.0);
        assertThat(result.get(2001)).isEqualTo(5.0);
        assertThat(result.get(2002)).isNull();
    }
} 