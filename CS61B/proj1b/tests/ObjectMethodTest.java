import deque.ArrayDeque61B;

import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ObjectMethodTest {

    @Test
    public void iteratorTest() {
        Deque61B<Integer> deq = new ArrayDeque61B<>();
        deq.addFirst(3);
        deq.addLast(4);
        deq.addFirst(2);
        deq.addLast(5);
        deq.addFirst(1);
        assertThat(deq).containsExactly(1, 2, 3, 4, 5).inOrder();

        int var = 1;
        for (int i : deq) {
            assertThat(i).isEqualTo(var);
            var++;
        }

        Deque61B<Integer> deq2 = new LinkedListDeque61B<>();
        deq2.addFirst(3);
        deq2.addLast(4);
        deq2.addFirst(2);
        deq2.addLast(5);
        deq2.addFirst(1);
        assertThat(deq2).containsExactly(1, 2, 3, 4, 5).inOrder();

        var = 1;
        for (int i : deq2) {
            assertThat(i).isEqualTo(var);
            var++;
        }
    }

    @Test
    public void equalsTest() {
        Deque61B<Integer> deq = new LinkedListDeque61B<>();
        deq.addFirst(3);
        deq.addLast(4);
        deq.addFirst(2);
        deq.addLast(5);
        deq.addFirst(1);

        Deque61B<Integer> deq2 = new LinkedListDeque61B<>();
        deq2.addFirst(3);
        deq2.addLast(4);
        deq2.addFirst(2);
        deq2.addLast(5);

        assertThat(deq.equals(deq2)).isFalse();
        deq2.addFirst(1);
        assertThat(deq).isEqualTo(deq2);
        deq.removeLast();
        assertThat(deq2.equals(deq)).isFalse();

        Deque61B<Integer> adeq = new ArrayDeque61B<>();
        adeq.addFirst(3);
        adeq.addLast(4);
        adeq.addFirst(2);
        adeq.addLast(5);
        adeq.addFirst(1);

        Deque61B<Integer> adeq2 = new ArrayDeque61B<>();
        adeq2.addFirst(3);
        adeq2.addLast(4);
        adeq2.addFirst(2);
        adeq2.addLast(5);

        assertThat(adeq.equals(adeq2)).isFalse();
        adeq2.addFirst(1);
        assertThat(adeq).isEqualTo(adeq2);
        adeq.removeLast();
        assertThat(adeq2.equals(adeq)).isFalse();

        assertThat(deq.equals(adeq)).isTrue();
    }

    @Test
    public void toStringTest() {
        Deque61B<Integer> deq = new LinkedListDeque61B<>();
        deq.addFirst(3);
        deq.addLast(4);
        deq.addFirst(2);
        deq.addLast(5);
        deq.addFirst(1);

        assertThat(deq.toString()).isEqualTo("[1, 2, 3, 4, 5]");

        Deque61B<Integer> deq2 = new LinkedListDeque61B<>();
        deq2.addFirst(3);
        deq2.addLast(4);
        deq2.addFirst(2);
        deq2.addLast(5);
        deq2.addFirst(1);

        assertThat(deq2.toString()).isEqualTo("[1, 2, 3, 4, 5]");
    }
}
