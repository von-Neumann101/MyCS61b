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
    public void toListTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

        assertThat(lld1.toList()).isEmpty();

        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);

        List<Integer> expected = lld1.toList();
        for (int i = 0; i < expected.size(); i++) {
            assertThat(lld1.getRecursive(i)).isEqualTo(expected.get(i));
        }
    }

}
