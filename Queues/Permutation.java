/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> items = new RandomizedQueue<String>();
        int size = Integer.parseInt(args[0]);

        for (int i = 0; i < size; ++i) {
            items.enqueue(StdIn.readString());
        }

        for (String item : items) {
            System.out.println(item);
        }
    }
}
