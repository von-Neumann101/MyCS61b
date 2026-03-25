import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class LinkedListDeque61BTest {
    @Test
    public void iteratorTest(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);

        int index = 0;
        for(int i : lld1){
            assertThat(i).isEqualTo(lld1.get(index));
            index += 1;
        }
    }

    @Test
    public void equals
}
