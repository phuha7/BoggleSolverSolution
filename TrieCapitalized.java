/* *****************************************************************************
 *  Title: TrieSET.java
 *  Author: Robert Sedgewick and Kevin Wayne
 *  Date: 2000 - 2017
 *  Availability: https://algs4.cs.princeton.edu/52trie/TrieSET.java.html
 **************************************************************************** */

public class TrieCapitalized {
    private static final int R = 26;

    private Node root;
    private int n;

    private static class Node {
        private Node[] next = new Node[R];
        private boolean isString;
        private boolean isPrefix;
    }


    public TrieCapitalized() {
    }

    public boolean contains(String key) {
        if (key == null) throw new IllegalArgumentException();
        Node x = get(root, key, 0);
        if (x == null) return false;
        return x.isString;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        int c = key.charAt(d) - 'A';
        return get(x.next[c], key, d + 1);
    }

    public void put(String key) {
        if (key == null) throw new IllegalArgumentException();
        root = put(root, key, 0);
    }

    private Node put(Node x, String key, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            if (!x.isString) n++;
            x.isString = true;
            x.isPrefix = true;
        }
        else {
            int c = key.charAt(d) - 'A';
            x.isPrefix = true;
            x.next[c] = put(x.next[c], key, d + 1);
        }
        return x;
    }

    public int size() {
        return n;
    }

    public boolean hasPrefix(String prefix) {
        Node x = get(root, prefix, 0);
        if (x == null) return false;
        return x.isPrefix;
    }


    public static void main(String[] args) {

    }
}
