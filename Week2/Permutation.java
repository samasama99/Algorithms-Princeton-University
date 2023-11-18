/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        while (!StdIn.isEmpty()) {
            if (StdRandom.bernoulli(0.5))
                deque.addFirst(StdIn.readString());
            else
                deque.addLast(StdIn.readString());
        }
        int num = Integer.parseInt(args[0]);
        while (!deque.isEmpty() && (num--) != 0) {
            if (StdRandom.bernoulli(0.5))
                StdOut.println(deque.removeLast());
            else
                StdOut.println(deque.removeFirst());
        }
    }
}
