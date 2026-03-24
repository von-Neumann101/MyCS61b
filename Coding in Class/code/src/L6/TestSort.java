package L6;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;//用于检测的库

public class TestSort {
    @Test //类似于一段文档，加入以后不需要main方法了
    public void testSort(){
        String[] input = {"awa", "QwQ", "ToT"};
        String[] expected = {"QwQ", "ToT", "awa"};
        Sort.sort(input);

        assertThat(input).isEqualTo(expected);
    }

    @Test
    public void testFindSmallest(){
        String[] input = {"ToT", "QwQ", "awa"};
        int expected = 1;

        int actual = Sort.findSmallest(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testSwap(){
        String[] input = {"ToT", "QwQ", "awa"};
        String[] expected = {"QwQ", "ToT", "awa"};

        Sort.swap(input, 0, 1);
        assertThat(input).isEqualTo(expected);
    }

}
