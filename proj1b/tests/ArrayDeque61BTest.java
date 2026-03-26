import deque.ArrayDeque61B;

import deque.Deque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

    @Test
    @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                .toList();
        assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    }

    @Test
    public void addFirstTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);

        assertThat(lld1.toList()).containsExactly(3, 2, 1).inOrder();
    }

    @Test
    public void addLastTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);

        assertThat(lld1.toList()).containsExactly(1, 2, 3).inOrder();
    }

    @Test
    public void addLastAndFirstTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addLast(1);
        lld1.addFirst(2);
        lld1.addLast(3);

        assertThat(lld1.toList()).containsExactly(2, 1, 3).inOrder();
    }

    @Test
    public void getTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);

        assertThat(lld1.get(0)).isEqualTo(1);
        assertThat(lld1.get(1)).isEqualTo(2);
        assertThat(lld1.get(2)).isEqualTo(3);

        assertThat(lld1.get(3)).isEqualTo(null);
        assertThat(lld1.get(-1)).isEqualTo(null);
    }

    @Test
    public void toListTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

        assertThat(lld1.toList()).isEmpty();

        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);

        List<Integer> expected = lld1.toList();
        for (int i = 0; i < expected.size(); i++) {
            assertThat(lld1.get(i)).isEqualTo(expected.get(i));
        }
    }

    @Test
    public void sizeTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

        assertThat(lld1.size()).isEqualTo(0);

        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);

        assertThat(lld1.size()).isEqualTo(3);
    }

    @Test
    public void isEmptyTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

        assertThat(lld1.isEmpty()).isTrue();

        lld1.addLast(1);

        assertThat(lld1.isEmpty()).isFalse();
    }

    @Test
    public void removeFirstTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

        assertThat(lld1.removeFirst()).isNull();

        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);

        assertThat(lld1.removeFirst()).isEqualTo(1);
        assertThat(lld1.toList()).containsExactly(2, 3).inOrder();
    }

    @Test
    public void removeLastTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

        assertThat(lld1.removeLast()).isNull();

        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);

        assertThat(lld1.removeLast()).isEqualTo(3);
        assertThat(lld1.toList()).containsExactly(1, 2).inOrder();
    }

    @Test
    public void iteratorTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);

        int index = 0;
        for(int i : lld1){
            assertThat(i).isEqualTo(lld1.get(index));
            index += 1;
        }
    }

    @Test
    public void toStringTest(){
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("关注");
        lld1.addLast("B站");
        lld1.addLast("冯-诺依曼101");
        lld1.addLast("谢谢喵");

        assertThat(lld1.toString()).isEqualTo("[关注, B站, 冯-诺依曼101, 谢谢喵]");
    }

    @Test
    public void equalsTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        Deque61B<Integer> lld2 = new ArrayDeque61B<>();
        lld1.addLast(4);
        lld2.addLast(4);
        assertThat(lld1.equals(lld2)).isTrue();
        
        lld2.addLast(2);
        assertThat(lld1.equals(lld2)).isFalse();

    }
}
