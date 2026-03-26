import deque.Deque61B;
import deque.LinkedListDeque61B;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

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
    public void equalsTest(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        Deque61B<Integer> lld2 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);

        lld2.addLast(1);
        lld2.addLast(2);
        lld2.addLast(3);
        lld2.addLast(4);

        assertThat(lld1.equals(lld2)).isTrue();

        lld1.removeFirst();

        assertThat(lld2.equals(lld1)).isFalse();
    }

    @Test
    public void equalsTest2(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        Deque61B<Integer> lld2 = new ArrayDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);

        lld2.addLast(1);
        lld2.addLast(2);
        lld2.addLast(3);
        lld2.addLast(4);

        assertThat(lld1.equals(lld2)).isTrue();

        lld1.removeFirst();

        assertThat(lld2.equals(lld1)).isFalse();
    }

    @Test
    public void toStringTest(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);

        assertThat(lld1.toString()).isEqualTo("[1, 2, 3, 4]");
    }
}
