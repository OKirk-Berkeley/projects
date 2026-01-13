import deque.ArrayDeque61B;
import deque.Maximizer61B;

import org.junit.jupiter.api.*;

import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class git Maximizer61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    private static class IntegerMod10Comparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return (o1 % 10) - (o2 % 10);
        }
    }

    @Test
    public void basicTest() {
//        ArrayDeque61B<String> ad = new ArrayDeque61B<>(new StringLengthComparator());
        ArrayDeque61B<String> ad = new ArrayDeque61B<>();
        ad.addFirst("");
        ad.addFirst("2");
        ad.addFirst("fury road");
        String test = Maximizer61B.max(ad, new StringLengthComparator());
        assertThat(test).isEqualTo("fury road");
    }

    @Test
    public void miscTest() {
        ArrayDeque61B<String> deq = new ArrayDeque61B<>();
        Comparable test1 = Maximizer61B.max(deq);
        assertThat(test1).isNull();
        deq.addFirst("a");
        deq.addFirst("b");
        deq.addFirst("c");
        Comparable test2 = Maximizer61B.max(deq);
        assertThat(test2).isEqualTo("c");

        ArrayDeque61B<Integer> intDeq = new ArrayDeque61B<>();
        intDeq.addFirst(14);
        intDeq.addFirst(29);
        intDeq.addFirst(37);
        int test3 = Maximizer61B.max(intDeq, new IntegerMod10Comparator());
        assertThat(test3).isEqualTo(29);
    }
}
