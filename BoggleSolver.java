/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class BoggleSolver {
    private TrieCapitalized dict;

    public BoggleSolver(String[] dictionary) {
        dict = new TrieCapitalized();
        for (int i = 0; i < dictionary.length; i++) {
            dict.put(dictionary[i]);
        }
    }

    public Iterable<String> getAllValidWords(BoggleBoard board) {
        ArrayList<String> list = new ArrayList<>();
        TrieCapitalized searcTrie = new TrieCapitalized();
        boolean[][] boolArray = new boolean[board.rows()][board.cols()];
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                findWords(i, j, board, boolArray, list, "" + board.getLetter(i, j), searcTrie);
            }
        }
        return list;
    }

    private void findWords(int i, int j, BoggleBoard board, boolean[][] bool,
                           ArrayList<String> list, String word, TrieCapitalized search) {
        int len = word.length();
        if (word.charAt(len - 1) == 'Q') {
            word += 'U';
        }

        if (dict.hasPrefix(word)) {

            if (word.length() >= 3 && dict.contains(word)) {
                if (!search.contains(word)) {
                    search.put(word);
                    list.add(word);
                }
            }
            bool[i][j] = true;
            if (i - 1 >= 0 && !bool[i - 1][j]) {
                findWords(i - 1, j, board, bool, list, word + board.getLetter(i - 1, j), search);
            }

            if (i + 1 < board.rows() && !bool[i + 1][j]) {
                findWords(i + 1, j, board, bool, list, word + board.getLetter(i + 1, j), search);
            }

            if (j - 1 >= 0 && !bool[i][j - 1]) {
                findWords(i, j - 1, board, bool, list, word + board.getLetter(i, j - 1), search);
            }

            if (j + 1 < board.cols() && !bool[i][j + 1]) {
                findWords(i, j + 1, board, bool, list, word + board.getLetter(i, j + 1), search);
            }

            if (i - 1 >= 0 && j - 1 >= 0 && !bool[i - 1][j - 1]) {
                findWords(i - 1, j - 1, board, bool, list, word + board.getLetter(i - 1, j - 1),
                          search);
            }

            if (i - 1 >= 0 && j + 1 < board.cols() && !bool[i - 1][j + 1]) {
                findWords(i - 1, j + 1, board, bool, list, word + board.getLetter(i - 1, j + 1),
                          search);
            }

            if (i + 1 < board.rows() && j - 1 >= 0 && !bool[i + 1][j - 1]) {
                findWords(i + 1, j - 1, board, bool, list, word + board.getLetter(i + 1, j - 1),
                          search);
            }

            if (i + 1 < board.rows() && j + 1 < board.cols() && !bool[i + 1][j + 1]) {
                findWords(i + 1, j + 1, board, bool, list, word + board.getLetter(i + 1, j + 1),
                          search);
            }
            bool[i][j] = false;
        }
    }

    public int scoreOf(String word) {
        if (word.length() == 3 || word.length() == 4) return 1;
        else if (word.length() == 5) return 2;
        else if (word.length() == 6) return 3;
        else if (word.length() == 7) return 5;
        else if (word.length() >= 8) return 11;
        else return 0;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);


    }
}
