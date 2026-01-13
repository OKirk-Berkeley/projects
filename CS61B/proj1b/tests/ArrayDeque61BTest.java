import deque.ArrayDeque61B;

import deque.Deque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
     public void getTest() {
         Deque61B<Integer> adeq = new ArrayDeque61B<>();
         adeq.addFirst(2);
         adeq.addFirst(1);
         adeq.addLast(3);
         adeq.addFirst(0);
         adeq.addLast(4);

         for (int i = 0; i < 5; i++){
             assertThat(adeq.get(i)).isEqualTo(i);
         }

         assertThat(adeq.get(100)).isNull();
         assertThat(adeq.get(-1)).isNull();
     }

     @Test
     public void isEmptyTest() {
         Deque61B<Integer> deq = new ArrayDeque61B<>();
         assertThat(deq.isEmpty()).isTrue();
         deq.addFirst(0);
         assertThat(deq.isEmpty()).isFalse();
         deq.removeLast();
         assertThat(deq.isEmpty()).isTrue();

         Deque61B<Integer> deq2 = new ArrayDeque61B<>();
         assertThat(deq2.isEmpty()).isTrue();
         deq.addLast(0);
         assertThat(deq.isEmpty()).isFalse();
         deq.removeFirst();
         assertThat(deq.isEmpty()).isTrue();
     }

     @Test
     public void sizeTest() {
         Deque61B<Integer> deq = new ArrayDeque61B<>();
         assertThat(deq.size()).isEqualTo(0);
         deq.addFirst(0);
         assertThat(deq.size()).isEqualTo(1);
         deq.addLast(1);
         assertThat(deq.size()).isEqualTo(2);
         deq.removeLast();
         assertThat(deq.size()).isEqualTo(1);
         deq.removeFirst();
         assertThat(deq.size()).isEqualTo(0);
         deq.removeFirst();
         assertThat(deq.size()).isEqualTo(0);
         deq.removeLast();
         assertThat(deq.size()).isEqualTo(0);
     }

     @Test
     public void toListTest() {
         Deque61B<Integer> adeq = new ArrayDeque61B<>();
         assertThat(adeq.toList()).isEmpty();
         adeq.addFirst(2);
         adeq.addFirst(1);
         adeq.addLast(3);
         adeq.addFirst(0);
         adeq.addLast(4);
         assertThat(adeq.toList()).containsExactly(0, 1, 2, 3, 4).inOrder();

         adeq.removeLast();
         adeq.removeFirst();
         assertThat(adeq.toList()).containsExactly(1, 2, 3).inOrder();
     }

     @Test
     public void removeFirstTest() {
         Deque61B<Integer> adeq = new ArrayDeque61B<>();
         adeq.addFirst(2);
         adeq.addFirst(1);
         adeq.addLast(3);
         adeq.addFirst(0);
         adeq.addLast(4);

         assertThat(adeq.removeFirst()).isEqualTo(0);
         assertThat(adeq.removeFirst()).isEqualTo(1);
         assertThat(adeq.removeFirst()).isEqualTo(2);
         assertThat(adeq.toList()).containsExactly(3, 4).inOrder();
         assertThat(adeq.removeFirst()).isEqualTo(3);
         assertThat(adeq.removeFirst()).isEqualTo(4);
         assertThat(adeq.isEmpty()).isTrue();
         assertThat(adeq.removeFirst()).isNull();
     }

     @Test
     public void removeLastTest() {
         Deque61B<Integer> adeq = new ArrayDeque61B<>();
         adeq.addFirst(2);
         adeq.addFirst(1);
         adeq.addLast(3);
         adeq.addFirst(0);
         adeq.addLast(4);

         assertThat(adeq.removeLast()).isEqualTo(4);
         assertThat(adeq.removeLast()).isEqualTo(3);
         assertThat(adeq.removeLast()).isEqualTo(2);
         assertThat(adeq.toList()).containsExactly(0, 1).inOrder();
         assertThat(adeq.removeLast()).isEqualTo(1);
         assertThat(adeq.removeLast()).isEqualTo(0);
         assertThat(adeq.isEmpty()).isTrue();
         assertThat(adeq.removeLast()).isNull();
     }

     @Test
     public void resizingTest() {
         Deque61B<Integer> deq = new ArrayDeque61B<>();

         for (int i = 0; i < 25; i++) {
             deq.addLast(i);
         }
         assertThat(deq.size()).isEqualTo(25);
         assertThat(deq.toList()).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24).inOrder();

         for (int i = 0; i < 25; i++) {
             deq.removeLast();
         }
         assertThat(deq.isEmpty()).isTrue();

         for (int i = 24; i >= 0; i--) {
             deq.addFirst(i);
         }
         assertThat(deq.size()).isEqualTo(25);
         assertThat(deq.toList()).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24).inOrder();

         for (int i = 0; i < 25; i++) {
             deq.removeFirst();
         }
         assertThat(deq.isEmpty()).isTrue();
     }

     @Test
     public void variableTypeTest() {
         Deque61B<Integer> adeq = new ArrayDeque61B<>();
         adeq.addFirst(2);
         adeq.addFirst(1);
         adeq.addLast(3);
         adeq.addFirst(0);
         adeq.addLast(4);

         Integer fir = adeq.removeFirst();
         Integer las = adeq.removeLast();
         assertThat(fir).isEqualTo(0);
         assertThat(fir).isInstanceOf(Integer.class);
         assertThat(las).isEqualTo(4);
         assertThat(las).isInstanceOf(Integer.class);

         Deque61B<String> strdeq = new ArrayDeque61B<>();
         strdeq.addFirst("a");
         strdeq.addLast("z");
         String x = strdeq.removeFirst();
         assertThat(x).isEqualTo("a");
         assertThat(strdeq.removeLast()).isInstanceOf(String.class);
     }

     @Test
     public void miscTest1() {
         Deque61B<Integer> deq = new ArrayDeque61B<>();
         deq.addFirst(0);
         deq.addFirst(1);
         assertThat(deq.removeLast()).isEqualTo(0);
         assertThat(deq.removeLast()).isEqualTo(1);
         deq.addLast(4);
         deq.addFirst(5);
         assertThat(deq.removeLast()).isEqualTo(4);
         assertThat(deq.toList()).containsExactly(5).inOrder();
         deq.addLast(7);
         deq.addFirst(8);
         deq.addLast(9);
         assertThat(deq.get(3)).isEqualTo(9);
         deq.addLast(11);
     }

     @Test
     public void miscTest2() {
         Deque61B<Integer> testArrayDeque = new ArrayDeque61B<>();
         testArrayDeque.addFirst(0);
         testArrayDeque.addFirst(1);
         assertThat(testArrayDeque.removeFirst()).isEqualTo(1);
         testArrayDeque.addFirst(3);
         assertThat(testArrayDeque.removeLast()).isEqualTo(0);
         testArrayDeque.addFirst(5);
         assertThat(testArrayDeque.removeLast()).isEqualTo(3);
         assertThat(testArrayDeque.removeLast()).isEqualTo(5);
         testArrayDeque.addFirst(8);
         testArrayDeque.addFirst(9);
         assertThat(testArrayDeque.removeFirst()).isEqualTo(9);
         testArrayDeque.addFirst(11);
         testArrayDeque.addFirst(12);
         assertThat(testArrayDeque.removeLast()).isEqualTo(8);
         assertThat(testArrayDeque.removeFirst()).isEqualTo(12);
         testArrayDeque.addFirst(15);
         assertThat(testArrayDeque.get(1)).isEqualTo(11);
         testArrayDeque.addLast(17);
         testArrayDeque.addLast(18);
     }
}
