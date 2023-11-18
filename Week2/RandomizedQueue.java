import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // construct an empty randomized queue
    private Deque<Item> core;

    public RandomizedQueue() {
        core = new Deque<>();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return core.isEmpty();
    }

    // return the number of items on the randomized queue
    public int size() {
        return core.size();
    }

    // add the item
    public void enqueue(Item item) {
        core.addFirst(item);
    }

    // remove and return a random item
    public Item dequeue() {
        return core.removeLast();
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int randomNumber = StdRandom.uniformInt(core.size());
        Iterator<Item> iterator = core.iterator();
        Item value = iterator.next();
        for (int i = 0; i < randomNumber; i++) {
            value = iterator.next();
        }
        return value;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<Item>(core);
    }

    // unit testing (required)

    private static class RandomizedQueueIterator<Item> implements Iterator<Item> {

        private Deque<Item> core;

        RandomizedQueueIterator(Deque<Item> source) {
            core = new Deque<>();
            Iterator<Item> current = source.iterator();
            for (int i = 0; i < source.size(); i++) {
                boolean isLast = StdRandom.bernoulli(0.5);
                if (isLast) core.addLast(current.next());
                else core.addFirst(current.next());
            }
        }

        public boolean hasNext() {
            return !core.isEmpty();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            boolean isLast = StdRandom.bernoulli(0.5);
            if (isLast) return core.removeLast();
            return core.removeFirst();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<Long> testDeque = new RandomizedQueue<>();
        if (!testDeque.isEmpty()) throw new AssertionError();
        testDeque.enqueue(10L);
        testDeque.enqueue(20L);
        if (testDeque.isEmpty()) throw new AssertionError();
        if (testDeque.dequeue() != 10L) throw new AssertionError();
        if (testDeque.dequeue() != 20L) throw new AssertionError();
    }
}