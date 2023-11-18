/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size = 0;
    private Node<Item> first;
    private Node<Item> last;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (first == null) {
            first = new Node<>();
            first.value = item;
            last = first;
        }
        else {
            Node<Item> newNode = new Node<Item>();
            newNode.value = item;
            newNode.next = first;
            newNode.next.previous = newNode;
            first = newNode;
        }
        size += 1;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (last == null) {
            first = new Node<>();
            first.value = item;
            last = first;
        }
        else {
            Node<Item> newNode = new Node<Item>();
            newNode.value = item;
            newNode.previous = last;
            newNode.previous.next = newNode;
            last = newNode;
        }
        size += 1;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item value = first.value;
        first = first.next;
        if (first != null) first.previous = null;
        else last = null;
        size -= 1;
        return value;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item value = last.value;
        last = last.previous;
        if (last != null) last.next = null;
        else first = null;
        size -= 1;
        return value;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator<>(first);
    }


    private static class Node<Item> {
        private Item value;
        private Node<Item> next;
        private Node<Item> previous;

        Node() {
        }
    }

    private static class DequeIterator<Item> implements Iterator<Item> {

        private Node<Item> current;

        DequeIterator(Node<Item> start) {
            this.current = start;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item value = current.value;
            current = current.next;
            return value;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Long> testDeque = new Deque<>();

        if (!testDeque.isEmpty()) throw new AssertionError();
        if (testDeque.size() != 0) throw new AssertionError();

        testDeque.addFirst(10L);


        if (testDeque.size() != 1) throw new AssertionError();
        if (testDeque.isEmpty()) throw new AssertionError();

        testDeque.addLast(20L);

        if (testDeque.size() != 2) throw new AssertionError();

        if (testDeque.removeFirst() != 10L) throw new AssertionError();
        if (testDeque.removeFirst() != 20L) throw new AssertionError();

        if (testDeque.size() != 0) throw new AssertionError();
        if (!testDeque.isEmpty()) throw new AssertionError();
        testDeque.addFirst(10L);
        if (testDeque.isEmpty()) throw new AssertionError();
        testDeque.addLast(20L);
        if (testDeque.size() != 2) throw new AssertionError();
        if (testDeque.removeLast() != 20L) throw new AssertionError();
        if (testDeque.removeLast() != 10L) throw new AssertionError();

        if (testDeque.size() != 0) throw new AssertionError();
        testDeque.addFirst(10L);
        testDeque.addFirst(20L);
        testDeque.addFirst(30L);
        testDeque.addFirst(40L);
        testDeque.addFirst(50L);
        testDeque.addFirst(60L);
        testDeque.addFirst(70L);
        testDeque.addFirst(80L);
        testDeque.addFirst(90L);
        testDeque.addFirst(100L);
        testDeque.addFirst(110L);

        if (testDeque.size() != 11) throw new AssertionError();
        Iterator<Long> iterator = testDeque.iterator();
        for (int i = 0; i < testDeque.size(); i++) {
            System.out.println(iterator.next());
        }

        testDeque.addLast(10L);
        testDeque.addLast(20L);
        testDeque.addLast(30L);
        testDeque.addLast(40L);
        testDeque.addLast(50L);
        testDeque.addLast(60L);
        testDeque.addLast(70L);
        testDeque.addLast(80L);
        testDeque.addLast(90L);
        testDeque.addLast(100L);
        testDeque.addLast(110L);

        if (testDeque.size() != 22) throw new AssertionError();
        Iterator<Long> longIterator = testDeque.iterator();
        for (int i = 0; i < testDeque.size(); i++) {
            System.out.println(longIterator.next());
        }
    }
}
