package edu.learning.codefights.tree;

/**
 * Created by duchuunguyen on 9/27/17.
 */
public class findSubStrings {
    public static String[] findSubstrings(String[] words, String[] parts) {
        Trie root = new Trie();
        int max = 0;
        for (String part : parts) {
            if (part.length() > max) {
                max = part.length();
            }
            toTrie(part, root);
        }
        String[] result = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            result[i] = findSub(words[i], root, max);
        }
        return result;
    }

    public static String findSub(String word, Trie trie, int max) {
        int currentMax = 0;
        int currentStart = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.length() - i < currentMax) {
                break;
            }
            Trie currentTrie = trie;
            int j = i;
            while (j < word.length()) {
                currentTrie = currentTrie.children[charToIndex(word.charAt(j))];
                if (currentTrie == null) {
                    break;
                }
                if (currentTrie.isEndLetter) {
                    if (currentMax < (j - i + 1)) {
                        currentMax = j - i + 1;
                        currentStart = i;
                    }
                    if (currentMax == max) {
                        return new StringBuffer(word.substring(0, currentStart))
                                .append("[")
                                .append(word.substring(currentStart, currentStart + currentMax))
                                .append("]")
                                .append(word.substring(currentStart + currentMax)).toString();
                    }
                }
                j++;
            }
        }
        if (currentMax == 0) {
            return word;
        }
        return new StringBuffer(word.substring(0, currentStart))
                .append("[")
                .append(word.substring(currentStart, currentStart + currentMax))
                .append("]")
                .append(word.substring(currentStart + currentMax)).toString();
    }

    public static void toTrie(String part, Trie trie) {
        Trie currentTrie = trie;
        for (int i = 0; i < part.length(); i++) {
            int index = charToIndex(part.charAt(i));
            if (currentTrie.children[index] == null) {
                currentTrie.children[index] = new Trie();
            }
            currentTrie = currentTrie.children[index];
            if (i == part.length() - 1) {
                currentTrie.isEndLetter = true;
            }
        }
    }

    public static int charToIndex(char c) {
        if (c >= 65 && c <= 90) {
            return c - 65;
        }
        return c - 97 + 26;
    }

    static class Trie {
        Trie[] children = new Trie[52];
        boolean isEndLetter = false;
    }

    public static void main(String[] args) {
        String[] words = {"Apple", "Melon", "Orange", "Watermelon"};
        String[] parts = {"a", "mel", "lon", "el", "An"};
        System.out.println(findSubstrings(words, parts));
    }
}
