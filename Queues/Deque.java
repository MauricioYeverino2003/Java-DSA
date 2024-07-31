/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node back;
    }

    public Deque() {
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (first == null) {
            first = new Node();
            last = first;
        }
        if (size == 0) {
            first.item = item;
            ++size;
        }
        else if (size == 1) {
            first = new Node();
            first.item = item;
            first.next = last;
            last.back = first;
            ++size;
        }
        else {
            Node oldNode = first;
            Node newNode = new Node();
            newNode.item = item;

            newNode.next = oldNode;
            oldNode.back = newNode;
            first = newNode;
            ++size;
        }
    }

    public void addLast(Item item) {
        if (first == null) {
            first = new Node();
            last = first;
        }
        if (size == 0) {
            first.item = item;
            ++size;
        }
        else if (size == 1) {
            last = new Node();
            last.item = item;
            first.next = last;
            last.back = first;
            ++size;
        }
        else {
            Node oldNode = last;
            Node newNode = new Node();
            newNode.item = item;

            oldNode.next = newNode;
            newNode.back = oldNode;
            last = newNode;
            ++size;
        }
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        if (size == 1) {
            Item item = first.item;
            first = null;
            --size;
            return item;
        }
        Item item = first.item;
        first = first.next;
        first.back = null;
        --size;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        if (size == 1) {
            Item item = first.item;
            first = null;
            --size;
            return item;
        }
        Item item = last.item;
        last = last.back;
        last.next = null;
        --size;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<String> items = new Deque<String>();
        items.addFirst("Hola");
        items.addFirst("Cola");
        items.addLast("Rola");
        items.addLast("Sola");
        // items.printValues();

        for (String s : items) System.out.println(s);

        System.out.println("We removed Last: " + items.removeLast());
        for (String s : items) System.out.println(s);

        System.out.println("We removed Last: " + items.removeLast());
        for (String s : items) System.out.println(s);

        System.out.println("We removed: First: " + items.removeFirst());
        for (String s : items) System.out.println(s);

        System.out.println("We removed: First: " + items.removeFirst());
        for (String s : items) System.out.println(s);

        items.addFirst("Hola");
        for (String s : items) System.out.println(s);
    }
}
