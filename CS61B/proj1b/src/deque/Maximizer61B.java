package deque;
import java.util.Comparator;
import java.util.Iterator;

public class Maximizer61B {
    /**
     * Returns the maximum element from the given iterable of comparables.
     * You may assume that the iterable contains no nulls.
     *
     * @param iterable  the Iterable of T
     * @return          the maximum element
     */
    public static <T extends Comparable<T>> T max(Iterable<T> iterable) {
        Iterator<T> itty = iterable.iterator();
        if (itty.hasNext()) {
            T max = itty.next();
            while (itty.hasNext()) {
                T temp = itty.next();
                if (max.compareTo(temp) < 0) {
                    max = temp;
                }
            }
            return max;
        }
        return null;
    }

    /**
     * Returns the maximum element from the given iterable according to the specified comparator.
     * You may assume that the iterable contains no nulls.
     *
     * @param iterable  the Iterable of T
     * @param comp      the Comparator to compare elements
     * @return          the maximum element according to the comparator
     */
    public static <T> T max(Iterable<T> iterable, Comparator<T> comp) {
        Iterator<T> itty = iterable.iterator();
        if (itty.hasNext()) {
            T max = itty.next();
            while (itty.hasNext()) {
                T temp = itty.next();
                if (comp.compare(max, temp) < 0) {
                    max = temp;
                }
            }
            return max;
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
