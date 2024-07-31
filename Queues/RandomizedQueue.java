/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */
/*
 * If we use array, We can insert regularly, and retrieve randomly at 0(1)
 * But the problem resides in popping random elements out of array
 *
 *
 * */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        size = 0;
    }

    private Item[] resizeItems(int newLength) {
        Item[] newArray = (Item[]) new Object[newLength];
        for (int i = 0; i < size; ++i) {
            newArray[i] = items[i];
        }
        return newArray;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size >= items.length) {
            items = resizeItems(size * 2);
        }
        items[size] = item;
        ++size;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int random = StdRandom.uniformInt(size);
        Item randItem = items[random];
        items[random] = items[size - 1];
        items[size - 1] = null;
        --size;
        if (size > 0 && size == items.length / 4) {
            items = resizeItems(items.length / 2);
        }
        return randItem;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return items[StdRandom.uniformInt(size)];
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Item[] iteratorItems;
        private int current;

        public ListIterator() {
            iteratorItems = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                iteratorItems[i] = items[i];
            }
            StdRandom.shuffle(iteratorItems, 0, size);
            current = 0;
        }

        public boolean hasNext() {
            return current < size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return iteratorItems[current++];
        }
    }

    public static void main(String[] args) {

    }
}
